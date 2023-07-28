package a.piguave.data.user

import com.mongodb.client.model.*
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import java.time.LocalDate

class MongoUserDataSource(db: MongoDatabase): UserDataSource {
    private val users = db.getCollection<User>("users")
    override suspend fun createUser(id: String, name: String, birthdate: LocalDate, bio: String, gender: Gender, interestedIn: Interest, pictures: List<String>): Boolean {
        return users.insertOne(User(id, name, bio, birthdate, gender, interestedIn, emptyList(), emptyList(), pictures)).wasAcknowledged()
    }

    override suspend fun editUser(id: String, bio: String?, gender: Gender?, interestedIn: Interest?, pictures: List<String>?): Boolean {
        if(bio == null && gender == null && interestedIn == null) return false

        val filter = Filters.eq("_id", id)

        val bioUpdate = bio?.let { Updates.set(User::bio.name, it) }
        val genderUpdate = gender?.let { Updates.set(User::gender.name, it) }
        val interestedInUpdate = interestedIn?.let { Updates.set(User::interestedIn.name, it) }
        val picturesUpdate = pictures?.let { Updates.set(User::pictures.name, it) }
        val update = Updates.combine(bioUpdate, genderUpdate, interestedInUpdate, picturesUpdate)

        val options = UpdateOptions().upsert(false)

        return users.updateOne(filter, update, options).wasAcknowledged()
    }

    override suspend fun getUser(id: String): User? {
        val filter = Filters.eq("_id", id)
        return users.find(filter).firstOrNull()
    }

    override suspend fun getUsersFor(user: User): List<User> {
        val interestFilter = Filters.`in`(User::interestedIn.name, Interest.BOTH, user.gender.toInterest())
        val genderFilter = user.interestedIn.toGender()?.let { Filters.eq(User::gender.name, it) }
        val idFilter = Filters.nin("_id", user.liked + user.passed + user.id)

        val filter = Filters.and(interestFilter, genderFilter, idFilter)

        val userList = mutableListOf<User>()
        return users.find(filter).limit(20).toList(userList)
    }


    override suspend fun likeUser(id: String, likedUserId: String): Boolean {
        val filter = Filters.eq("_id", id)
        val update = Updates.push(User::liked.name, likedUserId)
        val options = UpdateOptions().upsert(true)
        return users.updateOne(filter, update, options).wasAcknowledged()
    }

    override suspend fun hasUserLikedBack(id: String, likedUserId: String): Boolean {
        val filter = Filters.and(Filters.eq("_id", likedUserId), Filters.all(User::liked.name, id))
        return users.find(filter).firstOrNull() != null
    }

    override suspend fun passUser(id: String, passedUser: String): Boolean {
        val filter = Filters.eq("_id", id)
        val update = Updates.push(User::passed.name, passedUser)
        val options = UpdateOptions().upsert(true)
        return users.updateOne(filter, update, options).wasAcknowledged()
    }
}