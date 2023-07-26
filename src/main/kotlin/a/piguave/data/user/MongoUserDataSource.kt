package a.piguave.data.user

import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import java.time.LocalDate

class MongoUserDataSource(db: MongoDatabase): UserDataSource {
    private val users = db.getCollection<User>("users")
    override suspend fun createUser(id: String, name: String, birthdate: LocalDate, bio: String, gender: Gender, interestedIn: Interest, pictures: List<String>) {
        users.insertOne(
            User(id, name, bio, birthdate, gender, interestedIn, emptyList(), emptyList(), pictures)
        )
    }

    override suspend fun editUser(id: String, bio: String?, gender: Gender?, interestedIn: Interest?, pictures: List<String>?) {
        if(bio == null && gender == null && interestedIn == null) return

        val filter = Filters.eq("_id", id)

        val bioUpdate = bio?.let { Updates.set(User::bio.name, it) }
        val genderUpdate = gender?.let { Updates.set(User::gender.name, it) }
        val interestedInUpdate = interestedIn?.let { Updates.set(User::interestedIn.name, it) }
        val picturesUpdate = pictures?.let { Updates.set(User::pictures.name, it) }
        val update = Updates.combine(bioUpdate, genderUpdate, interestedInUpdate, picturesUpdate)

        val options = UpdateOptions().upsert(true)

        users.updateOne(filter, update, options)
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun likeUser(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun passUser(id: String): Boolean {
        TODO("Not yet implemented")
    }
}