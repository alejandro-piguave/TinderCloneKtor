package a.piguave

import a.piguave.data.TinderRepository
import a.piguave.data.TinderRepositoryImpl
import a.piguave.data.match.MatchDataSource
import a.piguave.data.match.MongoMatchDataSource
import a.piguave.data.message.MessageDataSource
import a.piguave.data.message.MongoMessageDataSource
import a.piguave.data.user.MongoUserDataSource
import a.piguave.data.user.UserDataSource
import a.piguave.plugins.configureMonitoring
import a.piguave.plugins.configureRouting
import a.piguave.plugins.configureSecurity
import a.piguave.plugins.configureSerialization
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    val mongoPassword = System.getenv("MONGO_PASSWORD")
    val connectionString = "mongodb+srv://alejandro-piguave:$mongoPassword@cluster0.skb9zqj.mongodb.net/?retryWrites=true&w=majority"
    val client = MongoClient.create(connectionString)
    val db = client.getDatabase("tinder-clone")

    val userDataSource: UserDataSource = MongoUserDataSource(db)
    val messageDataSource: MessageDataSource = MongoMessageDataSource(db)
    val matchDataSource: MatchDataSource = MongoMatchDataSource(db)

    val repository: TinderRepository = TinderRepositoryImpl(userDataSource, matchDataSource, messageDataSource)

    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting(repository)
}
