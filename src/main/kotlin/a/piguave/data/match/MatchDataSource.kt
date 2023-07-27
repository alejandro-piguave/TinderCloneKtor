package a.piguave.data.match

interface MatchDataSource {
    suspend fun getMatches(id: String): List<Match>

    suspend fun createMatch(id: String, matchedUserId: String): String?

    suspend fun updateLastMessage(id: String, message: String): Boolean
}