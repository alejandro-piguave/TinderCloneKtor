package a.piguave.rest.request

import a.piguave.data.user.Gender
import a.piguave.data.user.Interest
import kotlinx.serialization.Serializable

@Serializable
class EditUserRequest(
    val bio: String?,
    val gender: Gender?,
    val interestedIn: Interest?
)