package a.piguave.data.match

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Match(
    val matchedUsers: List<String>,
    val lastMessage: String,
    @BsonId val id: ObjectId = ObjectId(),
)