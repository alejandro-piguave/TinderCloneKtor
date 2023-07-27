package a.piguave.data.match

import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class MongoMatchDataSource(db: MongoDatabase): MatchDataSource {
    private val matches = db.getCollection<Match>("matches")
    override suspend fun createMatch(id: String, matchedUserId: String): String? {
        val match = Match(listOf(id, matchedUserId))
        val acknowledged = matches.insertOne(match).wasAcknowledged()
        return if(acknowledged) match.id.toHexString()
        else null
    }

    override suspend fun updateLastMessage(id: String, message: String): Boolean {
        val filter = Filters.eq("_id", ObjectId(id))
        val update = Updates.set(Match::lastMessage.name, message)
        val options = UpdateOptions().upsert(false)

        return matches.updateOne(filter, update, options).wasAcknowledged()
    }

    override suspend fun getMatches(id: String): List<Match> {
        val filter = Filters.all(Match::matchedUsers.name, id)
        val matchList = mutableListOf<Match>()
        matches.find(filter).toList(matchList)
        return matchList
    }
}