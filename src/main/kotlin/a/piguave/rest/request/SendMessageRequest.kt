package a.piguave.rest.request

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(val message: String)
