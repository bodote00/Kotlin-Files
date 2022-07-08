package com.example.tereziabodova_zapocet

class Manager {

    private var currentPlayer = 1
    var player1Points = 0
    var player2Points = 0

    //changes of players
    val currentPlayerMark: String
        get()
        {
            return if (currentPlayer == 1) "X" else "O"
        }

    //virtual representation of boxes
    private var state = arrayOf(                                                  // create 2D array
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )


    //whenever I make a move it pull out row and column position (row and col changes to 1)......
    fun makeMove(position: Position): WinningLine? {
        state[position.row][position.column] = currentPlayer
        val winningLine = hasGameEnded()

        if (winningLine == null) {                      //if someone is not yet winner
            currentPlayer = 3 - currentPlayer
        } else {
            when (currentPlayer) {
                1 -> player1Points++                //if it is player1, we increase his points
                2 -> player2Points++                //the same we do here with player2
            }
        }
        return winningLine
    }

    //we reset boxes
    fun reset()                                                             //reset 2D array
    {
        state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        currentPlayer = 1
    }


    //every possibility for ending the game (for winning)
    private fun hasGameEnded(): WinningLine?                                     ///every possible versions of WinningLines
    {
        if (state[0][0] == currentPlayer && state[0][1] == currentPlayer && state[0][2] == currentPlayer) {
            return WinningLine.ROW_0
        } else if (state[1][0] == currentPlayer && state[1][1] == currentPlayer && state[1][2] == currentPlayer) {
            return WinningLine.ROW_1
        } else if (state[2][0] == currentPlayer && state[2][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.ROW_2
        } else if (state[0][0] == currentPlayer && state[1][0] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.COLUMN_0
        } else if (state[0][1] == currentPlayer && state[1][1] == currentPlayer && state[2][1] == currentPlayer) {
            return WinningLine.COLUMN_1
        } else if (state[0][2] == currentPlayer && state[1][2] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.COLUMN_2
        } else if (state[0][0] == currentPlayer && state[1][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.DIAGONAL_LEFT
        } else if (state[0][2] == currentPlayer && state[1][1] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.DIAGONAL_RIGHT
        }
        return null
    }
}
