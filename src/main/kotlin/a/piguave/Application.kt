package a.piguave

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

    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting(userDataSource)
}
