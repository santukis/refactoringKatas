abstract class Score(
    protected val scorePlayer1: Int,
    protected val scorePlayer2: Int
) {
    abstract fun getScore(): String
}