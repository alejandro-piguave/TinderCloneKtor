package a.piguave.rest.routes

import a.piguave.data.TinderRepository
import a.piguave.rest.response.ProfileResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.profiles(repository: TinderRepository){
    get("profiles"){
        call.respond(HttpStatusCode.OK, listOf<ProfileResponse>())
    }

    post("profiles/{userId}/like") {

    }

    post("profiles/{userId}/pass") {

    }
}