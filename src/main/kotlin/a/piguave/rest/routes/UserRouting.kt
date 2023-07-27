package a.piguave.rest.routes

import a.piguave.data.repository.TinderRepository
import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.users(repository: TinderRepository){
    post("users") {
        val request = call.receive<CreateUserRequest>()
        val result = repository.createUser("lauri", request)
        call.respond(if(result) HttpStatusCode.OK else HttpStatusCode.Conflict)
    }

    put("users") {
        val request = call.receive<EditUserRequest>()
        val result = repository.editUser("alexpi", request)
        call.respond(if(result) HttpStatusCode.OK else HttpStatusCode.Conflict)
    }

    delete("users") {
        call.respond(HttpStatusCode.OK)
    }
}