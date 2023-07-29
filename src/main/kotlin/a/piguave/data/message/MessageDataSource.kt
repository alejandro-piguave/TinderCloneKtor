package a.piguave.data.message

import kotlinx.coroutines.flow.Flow

interface MessageDataSource {
    suspend fun createMessage(matchId: String, senderId: String, text: String): Boolean

    fun getMessagesFlow(matchId: String): Flow<Message>

    suspend fun getMessages(matchId: String): List<Message>
}