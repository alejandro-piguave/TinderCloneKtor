package a.piguave.data.message

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Message(
    val matchId: String,
    val senderId: String,
    val message: String,
    @BsonId val id: ObjectId = ObjectId()
)
