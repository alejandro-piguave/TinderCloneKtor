package a.piguave.data

import a.piguave.data.match.MatchDataSource
import a.piguave.data.message.MessageDataSource
import a.piguave.data.user.UserDataSource
import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest

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
}