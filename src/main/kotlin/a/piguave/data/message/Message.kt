package a.piguave.data.message

import org.bson.BsonDateTime
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Message(
    val senderId: String,
    val text: String,
    val timestamp: BsonDateTime,
    @BsonId val id: ObjectId = ObjectId()
)
