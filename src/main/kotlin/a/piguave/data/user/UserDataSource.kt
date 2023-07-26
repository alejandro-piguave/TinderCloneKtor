package a.piguave.data.user

import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest

interface UserDataSource {
    suspend fun createUser(id: String, createUserRequest: CreateUserRequest)

    suspend fun likeUser(id: String)

    suspend fun passUser(id: String)

    suspend fun editUser(id: String, editUserRequest: EditUserRequest)

    suspend fun getMatches(id: String)

}