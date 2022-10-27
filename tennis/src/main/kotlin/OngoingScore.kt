class OngoingScore(scorePlayer1: Int, scorePlayer2: Int): Score(scorePlayer1, scorePlayer2) {

    override fun getScore(): String = getOngoingScore(scorePlayer1) + "-" + getOngoingScore(scorePlayer2)

    private fun getOngoingScore(playerScore: Int): String =
        when (playerScore) {
            1 -> "Fifteen"
            2 -> "Thirty"
            3 -> "Forty"
            else -> "Love"
        }
}