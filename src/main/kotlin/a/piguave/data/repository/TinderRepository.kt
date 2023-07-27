package a.piguave.data.repository

import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import a.piguave.rest.response.MatchResponse
import a.piguave.rest.response.ProfileResponse

interface TinderRepository {
    suspend fun createUser(id: String, request: CreateUserRequest): Boolean

    suspend fun editUser(id: String, request: EditUserRequest): Boolean

    suspend fun getProfiles(id: String): List<ProfileResponse>

    suspend fun likeProfile(id: String, likedId: String): LikeResult

    suspend fun passProfile(id: String, passedId: String): Boolean

    suspend fun getMatches(id: String): List<MatchResponse>

    suspend fun sendMessage(id: String, matchId: String, message: String): Boolean

}