package a.piguave.data.repository

sealed class LikeResult{
    data object NotAcknowledged: LikeResult()
    data class Acknowledged(val matchId: String? = null): LikeResult()
}
