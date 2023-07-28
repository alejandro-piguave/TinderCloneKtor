package a.piguave.data.user

import java.time.LocalDate

interface UserDataSource {
    suspend fun createUser(id: String, name: String, birthdate: LocalDate, bio: String,  gender: Gender, interestedIn: Interest, pictures: List<String>): Boolean

    suspend fun editUser(id: String, bio: String?, gender: Gender?, interestedIn: Interest?, pictures: List<String>?): Boolean

    suspend fun getUser(id: String): User?

    suspend fun getUsersFor(user: User): List<User>

    suspend fun likeUser(id: String, likedUserId: String): Boolean

    suspend fun hasUserLikedBack(id: String, likedUserId: String): Boolean

    suspend fun passUser(id: String, passedUser: String): Boolean
}