class DrawScore(scorePlayer1: Int, scorePlayer2: Int): Score(scorePlayer1, scorePlayer2) {
    override fun getScore(): String =
        when (scorePlayer1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
}