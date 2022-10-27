data class Player(val name: String) {
    var score: Int = 0
        private set

    fun addPoint() {
        score++
    }

    fun isTiedTo(player: Player) = score == player.score

    fun hasAdvantage(player: Player) = canWin() && score - player.score == 1

    fun hasWon(player: Player) = canWin() && score - player.score >= 2

    private fun canWin() = score >= 4
}