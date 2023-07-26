package a.piguave.rest.routes

import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.users(){
    post("users") {
        val request = call.receive<CreateUserRequest>()
        call.respond(HttpStatusCode.OK)
    }

    put("users") {
        val request = call.receive<EditUserRequest>()
        call.respond(HttpStatusCode.OK)
    }

    delete("users") {
        call.respond(HttpStatusCode.OK)
    }
}