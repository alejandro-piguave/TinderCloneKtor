package a.piguave.data.message

import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoMessageDataSource(db: MongoDatabase): MessageDataSource {
    private val messages = db.getCollection<Message>("messages")
    override suspend fun createMessage(matchId: String, senderId: String, text: String): Boolean {
        return messages.insertOne(Message(matchId, senderId, text)).wasAcknowledged()
    }

}