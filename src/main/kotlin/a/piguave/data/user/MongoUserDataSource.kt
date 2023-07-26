package a.piguave.data.user

import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.bson.BsonDateTime

class MongoUserDataSource(db: MongoDatabase): UserDataSource {
    private val users = db.getCollection<User>("users")
    override suspend fun createUser(id: String, createUserRequest: CreateUserRequest) {
        users.insertOne(
            with(createUserRequest){
                User(name, bio, BsonDateTime(birthdate.toEpochDay()), gender, interestedIn, emptyList(), emptyList(), pictures)
            }
        )
    }

    override suspend fun likeUser(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun passUser(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editUser(id: String, editUserRequest: EditUserRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun getMatches(id: String) {
        TODO("Not yet implemented")
    }
}