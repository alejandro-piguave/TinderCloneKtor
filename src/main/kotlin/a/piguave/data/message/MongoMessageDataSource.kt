package a.piguave.data.message

import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoMessageDataSource(db: MongoDatabase): MessageDataSource {
    private val messages = db.getCollection<Message>("messages")

}