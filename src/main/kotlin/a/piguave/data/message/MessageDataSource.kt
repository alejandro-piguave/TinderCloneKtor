package a.piguave.data.message

interface MessageDataSource {
    suspend fun createMessage(matchId: String, senderId: String, text: String): Boolean
}