package a.piguave.rest.response

import a.piguave.data.user.User
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Serializable
data class ProfileResponse(
    val userId: String,
    val name: String,
    val age: Int,
    val bio: String,
    val pictures: List<String>
){
    constructor(user: User):
            this(
                user.id,
                user.name,
                ChronoUnit.YEARS.between(user.birthdate, LocalDate.now()).toInt(),
                user.bio,
                user.pictures
            )

}