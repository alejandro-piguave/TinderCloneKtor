package a.piguave.data.user

import java.time.LocalDate

interface UserDataSource {
    suspend fun createUser(id: String, name: String, birthdate: LocalDate, bio: String,  gender: Gender, interestedIn: Interest, pictures: List<String>)

    suspend fun editUser(id: String, bio: String?, gender: Gender?, interestedIn: Interest?, pictures: List<String>?)

    suspend fun getUsers(): List<User>

    suspend fun likeUser(id: String): Boolean

    suspend fun passUser(id: String): Boolean
}