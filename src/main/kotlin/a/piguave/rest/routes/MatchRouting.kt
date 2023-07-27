package a.piguave.rest.routes

import a.piguave.data.repository.TinderRepository
import a.piguave.rest.request.SendMessageRequest
import a.piguave.rest.response.MessageResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.matches(repository: TinderRepository){
    get("matches"){
        val matches = repository.getMatches("alexpi")
        call.respond(HttpStatusCode.OK, matches)
    }

    delete("matches/{matchId}"){
        call.respond(HttpStatusCode.OK)
    }

    get("matches/{matchId}/messages") {
        call.respond(HttpStatusCode.OK, listOf<MessageResponse>())

    }
    post("matches/{matchId}/messages") {
        val matchId = call.parameters["matchId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        val request = call.receive<SendMessageRequest>()
        repository.sendMessage("alexpi", matchId, request.message)
        call.respond(HttpStatusCode.OK)
    }
}