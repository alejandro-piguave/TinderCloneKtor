package a.piguave.rest.response

import a.piguave.rest.serializer.OffsetDateTimeAsString
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class MatchResponse(
    val id: String,
    @Serializable(OffsetDateTimeAsString::class)
    val timestamp: OffsetDateTime,
    val lastMessage: String,
    val matchedUserId: String
)