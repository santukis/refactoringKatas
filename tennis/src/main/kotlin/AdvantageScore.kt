class AdvantageScore(scorePlayer1: Int, scorePlayer2: Int): Score(scorePlayer1, scorePlayer2) {

    override fun getScore(): String =
        when {
            scorePlayer1 > scorePlayer2 -> "Advantage player1"
            else -> "Advantage player2"
        }
}