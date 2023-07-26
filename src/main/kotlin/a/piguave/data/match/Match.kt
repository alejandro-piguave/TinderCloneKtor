package a.piguave.data.match

import org.bson.BsonDateTime
import org.bson.codecs.pojo.annotations.BsonId

data class Match(
    @BsonId val id: String,
    val matchedUsers: List<String>,
    val lastMessage: String,
    val timestamp: BsonDateTime,
)