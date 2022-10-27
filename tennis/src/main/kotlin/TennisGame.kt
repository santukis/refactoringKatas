

class TennisGame(player1Name: String, player2Name: String) {

    private val player1: Player = Player(player1Name)
    private val player2: Player = Player(player2Name)

    fun wonPoint(playerName: String) {
        if (playerName === player1.name) {
            player1.addPoint()

        } else {
            player2.addPoint()
        }
    }

    fun getScore(): String = Referee.updateScore(player1, player2).getScore()

}