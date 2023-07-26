package a.piguave.data.match

import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoMatchDataSource(db: MongoDatabase): MatchDataSource {
    private val matches = db.getCollection<Match>("matches")

    override fun getMatches(id: String): List<Match> {
        TODO("Not yet implemented")
    }
}