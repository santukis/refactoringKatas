import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private const val PLAYER_1 = "player1"
private const val PLAYER_2 = "player2"

internal class TennisGameTest {

    companion object {
        @JvmStatic
        fun getTennisGameScore(): Stream<Arguments> = Stream.of(
            Arguments.of(0, 0, "Love-All"),
            Arguments.of(1, 1, "Fifteen-All"),
            Arguments.of(2, 2, "Thirty-All"),
            Arguments.of(3, 3, "Deuce"),
            Arguments.of(4, 4, "Deuce"),
            Arguments.of(4, 0, "Win for player1"),
            Arguments.of(4, 1, "Win for player1"),
            Arguments.of(4, 2, "Win for player1"),
            Arguments.of(4, 3, "Advantage player1"),
            Arguments.of(0, 4, "Win for player2"),
            Arguments.of(1, 4, "Win for player2"),
            Arguments.of(2, 4, "Win for player2"),
            Arguments.of(3, 4, "Advantage player2"),
            Arguments.of(1, 0, "Fifteen-Love"),
            Arguments.of(2, 0, "Thirty-Love"),
            Arguments.of(2, 1, "Thirty-Fifteen"),
            Arguments.of(3, 0, "Forty-Love"),
            Arguments.of(3, 1, "Forty-Fifteen"),
            Arguments.of(3, 2, "Forty-Thirty"),
            Arguments.of(0, 1, "Love-Fifteen"),
            Arguments.of(0, 2, "Love-Thirty"),
            Arguments.of(1, 2, "Fifteen-Thirty"),
            Arguments.of(0, 3, "Love-Forty"),
            Arguments.of(1, 3, "Fifteen-Forty"),
            Arguments.of(2, 3, "Thirty-Forty"),
        )
    }

    private val game = TennisGame(PLAYER_1, PLAYER_2)

    @ParameterizedTest
    @MethodSource("getTennisGameScore")
    fun score(
        firstPlayerPoints: Int,
        secondPlayerPoints: Int,
        expectedScore: String
    ) {
        setPlayerScore(PLAYER_1, firstPlayerPoints)
        setPlayerScore(PLAYER_2, secondPlayerPoints)

        val score = game.getScore()
        assertEquals(expectedScore, score)
    }

    private fun setPlayerScore(player: String, pointsWon: Int) {
        for (point in 1..pointsWon) {
            game.wonPoint(player)
        }
    }
}