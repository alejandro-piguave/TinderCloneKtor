package a.piguave.rest.routes

import a.piguave.data.user.UserDataSource
import a.piguave.rest.request.CreateUserRequest
import a.piguave.rest.request.EditUserRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.users(userDataSource: UserDataSource){
    post("users") {
        val request = call.receive<CreateUserRequest>()
        with(request) { userDataSource.createUser("alexpi", name, birthdate, bio, gender, interestedIn, pictures) }
        call.respond(HttpStatusCode.OK)
    }

    put("users") {
        val request = call.receive<EditUserRequest>()
        with(request){ userDataSource.editUser("alexpi", bio, gender, interestedIn, pictures) }
        call.respond(HttpStatusCode.OK)
    }

    delete("users") {
        call.respond(HttpStatusCode.OK)
    }
}