class WinScore(scorePlayer1: Int, scorePlayer2: Int): Score(scorePlayer1, scorePlayer2) {

    override fun getScore(): String =
        when {
            scorePlayer1 > scorePlayer2 -> "Win for player1"
            else -> "Win for player2"
        }
}