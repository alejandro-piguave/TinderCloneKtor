package a.piguave.data.message

import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.changestream.FullDocument
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class MongoMessageDataSource(db: MongoDatabase): MessageDataSource {
    private val messages = db.getCollection<Message>("messages")
    override suspend fun createMessage(matchId: String, senderId: String, text: String): Boolean {
        return messages.insertOne(Message(matchId, senderId, text)).wasAcknowledged()
    }

    override fun getMessagesFlow(matchId: String): Flow<Message> {
        val filter = Filters.eq("fullDocument.${Message::matchId.name}", matchId)
        val changeStream = messages.watch(
            listOf(
                Aggregates.match(Filters.eq("operationType", "insert")),
                Aggregates.match(filter)
            )
        ).fullDocument(FullDocument.DEFAULT)
        return changeStream.map { it.fullDocument!! }
    }

    override suspend fun getMessages(matchId: String): List<Message> {
        val filter = Filters.eq(Message::matchId.name, matchId)
        val messageList = mutableListOf<Message>()
        messages.find(filter).toList(messageList)
        return messageList
    }

}