package a.piguave.data.match

import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoMatchDataSource(db: MongoDatabase): MatchDataSource {
    private val matches = db.getCollection<Match>("matches")
    override suspend fun createMatch(id: String, matchedUserId: String): String? {
        val match = Match(listOf(id, matchedUserId))
        val acknowledged = matches.insertOne(match).wasAcknowledged()
        return if(acknowledged) match.id.toString()
        else null
    }

    override suspend fun getMatches(id: String): List<Match> {
        TODO("Not yet implemented")
    }
}