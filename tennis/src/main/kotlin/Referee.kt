object Referee {

    fun updateScore(player1: Player, player2: Player): Score =
        when {
            player1.isTiedTo(player2) -> DrawScore(player1.score, player2.score)
            eitherPlayerHasAdvantage(player1, player2) -> AdvantageScore(player1.score, player2.score)
            eitherPlayerHasWon(player1, player2) -> WinScore(player1.score, player2.score)
            else -> OngoingScore(player1.score, player2.score)
        }

    private fun eitherPlayerHasAdvantage(player1: Player, player2: Player) =
        player1.hasAdvantage(player2) || player2.hasAdvantage(player1)

    private fun eitherPlayerHasWon(player1: Player, player2: Player) =
        player1.hasWon(player2) || player2.hasWon(player1)
}