package a.piguave.rest.response

import a.piguave.data.match.Match
import a.piguave.rest.serializer.OffsetDateTimeAsString
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Serializable
data class MatchResponse(
    val id: String,
    @Serializable(OffsetDateTimeAsString::class)
    val timestamp: OffsetDateTime,
    val lastMessage: String,
    val matchedUserId: String
) {
    constructor(id: String, match: Match) : this(
        match.id.toString(),
        match.id.date.toInstant().atOffset(ZoneOffset.UTC),
        match.lastMessage,
        match.matchedUsers.first { it != id }
    )
}