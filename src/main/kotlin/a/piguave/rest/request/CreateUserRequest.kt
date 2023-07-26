package a.piguave.rest.request

import a.piguave.data.user.Gender
import a.piguave.data.user.Interest
import a.piguave.rest.serializer.LocalDateAsString
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class CreateUserRequest(
    val name: String,
    val bio: String,
    @Serializable(LocalDateAsString::class)
    val birthdate: LocalDate,
    val gender: Gender,
    val interestedIn: Interest,
    val pictures: List<String>
)