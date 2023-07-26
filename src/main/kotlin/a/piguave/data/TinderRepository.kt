package a.piguave.data

import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest

interface TinderRepository {
    suspend fun createUser(id: String, request: CreateUserRequest): Boolean

    suspend fun editUser(id: String, request: EditUserRequest): Boolean

}