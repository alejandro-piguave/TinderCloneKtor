package a.piguave.rest.routes

import a.piguave.data.repository.TinderRepository
import a.piguave.rest.request.SendMessageRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

fun Route.matches(repository: TinderRepository){
    get("matches"){
        val matches = repository.getMatches("alexpi")
        call.respond(HttpStatusCode.OK, matches)
    }

    delete("matches/{matchId}"){
        call.respond(HttpStatusCode.OK)
    }

    get("matches/{matchId}/messages") {
        val matchId = call.parameters["matchId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val messages = repository.getMessages("alexpi", matchId)
        call.respond(HttpStatusCode.OK, messages)
    }

    webSocket("matches/{matchId}/messages") {
        val matchId = call.parameters["matchId"] ?: kotlin.run {
            close(CloseReason(CloseReason.Codes.NOT_CONSISTENT, "Couldn't find a match id"))
            return@webSocket
        }

        repository.getMessagesFlow("alexpi", matchId).collect {
            sendSerialized(it)
        }
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