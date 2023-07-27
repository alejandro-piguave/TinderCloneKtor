package a.piguave.data.repository

import a.piguave.data.match.MatchDataSource
import a.piguave.data.message.MessageDataSource
import a.piguave.data.user.UserDataSource
import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import a.piguave.rest.response.ProfileResponse

class TinderRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val matchDataSource: MatchDataSource,
    private val messageDataSource: MessageDataSource
): TinderRepository {
    override suspend fun createUser(id: String, request: CreateUserRequest): Boolean {
        return  with(request) { userDataSource.createUser(id, name, birthdate, bio, gender, interestedIn, pictures) }
    }

    override suspend fun editUser(id: String, request: EditUserRequest): Boolean {
        return with(request){ userDataSource.editUser(id, bio, gender, interestedIn, pictures) }
    }

    override suspend fun getProfiles(id: String): List<ProfileResponse> {
        return listOf()
    }

    override suspend fun likeProfile(id: String, likedId: String): LikeResult {
        val result = userDataSource.likeUser(id, likedId)
        if(!result) return LikeResult.NotAcknowledged
        return if(userDataSource.hasUserLikedBack(id, likedId)) {
            val matchId = matchDataSource.createMatch(id, likedId)
            if(matchId == null) LikeResult.NotAcknowledged
            else LikeResult.Acknowledged(matchId)
        } else LikeResult.Acknowledged()
    }

    override suspend fun passProfile(id: String, passedId: String): Boolean {
        return userDataSource.passUser(id, passedId)
    }
}