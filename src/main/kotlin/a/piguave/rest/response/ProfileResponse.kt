package a.piguave.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val userId: String,
    val name: String,
    val age: String,
    val bio: String,
    val pictures: List<String>
)