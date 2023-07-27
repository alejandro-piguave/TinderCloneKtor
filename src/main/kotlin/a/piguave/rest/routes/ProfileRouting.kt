package a.piguave.rest.routes

import a.piguave.data.repository.LikeResult
import a.piguave.data.repository.TinderRepository
import a.piguave.rest.response.LikeResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.profiles(repository: TinderRepository){
    get("profiles"){
        val profiles = repository.getProfiles("alexpi")
        call.respond(HttpStatusCode.OK, profiles)
    }

    post("profiles/{userId}/like") {
        val userId = call.parameters["userId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        when(val result = repository.likeProfile("alexpi", userId)){
            is LikeResult.Acknowledged -> {
                call.respond(HttpStatusCode.OK, LikeResponse(result.matchId))

            }
            LikeResult.NotAcknowledged -> call.respond(HttpStatusCode.Conflict)
        }
    }

    post("profiles/{userId}/pass") {
        val userId = call.parameters["userId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val result = repository.passProfile("alexpi", userId)
        call.respond(if(result) HttpStatusCode.OK else HttpStatusCode.Conflict)
    }
}