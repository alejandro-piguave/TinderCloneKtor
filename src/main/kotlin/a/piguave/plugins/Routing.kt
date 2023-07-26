package a.piguave.plugins

import a.piguave.data.user.UserDataSource
import a.piguave.rest.routes.matches
import a.piguave.rest.routes.profiles
import a.piguave.rest.routes.users
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(userDataSource: UserDataSource) {
    routing {
        users(userDataSource)
        profiles()
        matches()
    }
}
