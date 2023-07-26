package a.piguave.data.match

interface MatchDataSource {
    fun getMatches(id: String): List<Match>
}