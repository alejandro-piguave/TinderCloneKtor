package a.piguave.rest.routes

import a.piguave.data.TinderRepository
import a.piguave.rest.response.MatchResponse
import a.piguave.rest.response.MessageResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.matches(repository: TinderRepository){
    get("matches"){
        call.respond(HttpStatusCode.OK, listOf<MatchResponse>())
    }

    delete("matches/{matchId}"){
        call.respond(HttpStatusCode.OK)
    }

    get("matches/{matchId}/messages") {
        call.respond(HttpStatusCode.OK, listOf<MessageResponse>())

    }
    post("matches/{matchId}/messages") {
        call.respond(HttpStatusCode.OK)

    }
}