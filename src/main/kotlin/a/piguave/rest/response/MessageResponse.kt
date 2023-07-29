package a.piguave.rest.response

import a.piguave.data.message.Message
import a.piguave.rest.serializer.OffsetDateTimeAsString
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Serializable
data class MessageResponse(
    val id: String,
    @Serializable(OffsetDateTimeAsString::class)
    val timestamp: OffsetDateTime,
    val text: String,
    val isOwnMessage: Boolean
){
    constructor(userId: String, message: Message): this(
        message.id.toString(),
        message.id.date.toInstant().atOffset(ZoneOffset.UTC),
        message.message,
        userId == message.senderId
    )
}
