package minesweeper

import java.awt.List
import kotlin.random.Random

var mines = 0
var correckMines = 0
val board = Array(9) { charArrayOf('.' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.') }
var hiddenBoard = Array(9) { charArrayOf('.' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.') }
//    board.clone()
//    Array(9) { charArrayOf('.' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.') }
var closedMinesLeft = 0

fun main() {
    println("How many mines do you want on the field? ")
    mines = readln().toInt()
    loadMines()
    digitCount()
    printHiddenBoard()

// копируею данные из основного массива в скрытый кроме Х
    for (i in 0..8) {
        for (j in 0..8) {
            if (board[i][j] == 'X') hiddenBoard[i][j] = '.'
            if (board[i][j].isDigit()) hiddenBoard[i][j] = board[i][j]

        }
    }

    do {
        println("Set/unset mine marks or claim a cell as free: ")
        val (readCoordinateX,readCoordinateY, style) = readln().split(' ')
        when (style) {
            "mine" -> {
                setDelMinesMarks(readCoordinateY.toInt()-1, readCoordinateX.toInt()-1)
                printHiddenBoard()
            }
            "free" -> {
                if (board[readCoordinateY.toInt()-1][readCoordinateX.toInt()-1] == 'X') {
                    println("You stepped on a mine and failed! ")
                } else {
                    checkEmptySlots(readCoordinateY.toInt()-1, readCoordinateX.toInt()-1)
                    printHiddenBoard()
                }
            }
        }

        checkEmptySlots(readCoordinateY.toInt()-1, readCoordinateX.toInt()-1)

    } while ((correckMines != mines) || (closedMinesLeft != correckMines))
    println("Congratulations! You found all the mines!")
}
fun printBoard() {
    println("""
         │123456789│
        —│—————————│
    """.trimIndent())
    for (i in 0..8) {
        print(i+1)
        println(board[i].joinToString(prefix = "│", separator = "", postfix = "│"))
    }
    println("—│—————————│")
}

fun printHiddenBoard() {
    println("""
         │123456789│
        —│—————————│
    """.trimIndent())
    for (i in 0..8) {
        print(i+1)
        println(hiddenBoard[i].joinToString(prefix = "│", separator = "", postfix = "│"))
    }
    println("—│—————————│")
}

fun loadMines() {
    var minesTotal = mines
    do{
        val x = Random.nextInt(0,9)
        val y = Random.nextInt(0,9)
        if (board[x][y] == '.' && x != 0 && y != 0) {
            board[x][y] = 'X'
            minesTotal--
        }
    } while (minesTotal != 0)

}

fun digitCount() {
    for (y in 0..8) {
        for (x in 0..8) {
            if (board[y][x] == 'X') {
//              сначала проверяю все углы
                if (y == 0 && x == 0) {
                    if (board[y + 1][x] == '.') {
                        board[y + 1][x] = '1'
                    } else if (board[y + 1][x].isDigit()) {
                        board[y + 1][x] = (board[y + 1][x].code + 1).toChar()
                    }
                    if (board[y][x + 1] == '.') {
                        board[y][x + 1] = '1'
                    } else if (board[y][x + 1].isDigit()) {
                        board[y][x + 1] = (board[y][x + 1].code + 1).toChar()
                    }
                    if (board[y + 1][x + 1] == '.') {
                        board[y + 1][x + 1] = '1'
                    } else if (board[y + 1][x + 1].isDigit()) {
                        board[y + 1][x + 1] = (board[y + 1][x + 1].code + 1).toChar()
                    }
                }

                if (y == 0 && x == 8) {
                    if (board[y + 1][x] == '.') {
                        board[y + 1][x] = '1'
                    } else if (board[y + 1][x].isDigit()) {
                        board[y + 1][x] = (board[y + 1][x].code + 1).toChar()
                    }
                    if (board[y][x - 1] == '.') {
                        board[y][x - 1] = '1'
                    } else if (board[y][x - 1].isDigit()) {
                        board[y][x - 1] = (board[y][x - 1].code + 1).toChar()
                    }
                    if (board[y + 1][x - 1] == '.') {
                        board[y + 1][x - 1] = '1'
                    } else if (board[y + 1][x - 1].isDigit()) {
                        board[y + 1][x - 1] = (board[y + 1][x - 1].code + 1).toChar()
                    }
                }

                if (y == 8 && x == 0) {
                    if (board[y - 1][x] == '.') {
                        board[y - 1][x] = '1'
                    } else if (board[y - 1][x].isDigit()) {
                        board[y - 1][x] = (board[y - 1][x].code + 1).toChar()
                    }
                    if (board[y][x + 1] == '.') {
                        board[y][x + 1] = '1'
                    } else if (board[y][x + 1].isDigit()) {
                        board[y][x + 1] = (board[y][x + 1].code + 1).toChar()
                    }
                    if (board[y - 1][x + 1] == '.') {
                        board[y - 1][x + 1] = '1'
                    } else if (board[y - 1][x + 1].isDigit()) {
                        board[y - 1][x + 1] = (board[y - 1][x + 1].code + 1).toChar()
                    }
                }

                if (y == 8 && x == 8) {
                    if (board[y - 1][x] == '.') {
                        board[y - 1][x] = '1'
                    } else if (board[y - 1][x].isDigit()) {
                        board[y - 1][x] = (board[y - 1][x].code + 1).toChar()
                    }
                    if (board[y][x - 1] == '.') {
                        board[y][x - 1] = '1'
                    } else if (board[y][x - 1].isDigit()) {
                        board[y][x - 1] = (board[y][x - 1].code + 1).toChar()
                    }
                    if (board[y - 1][x - 1] == '.') {
                        board[y - 1][x - 1] = '1'
                    } else if (board[y - 1][x - 1].isDigit()) {
                        board[y - 1][x - 1] = (board[y - 1][x - 1].code + 1).toChar()
                    }
                }

//              теперь проверяю верхнюю сторону
                if (y == 0 && x in 1..7) {
                    if (board[y][x + 1] == '.') {
                        board[y][x + 1] = '1'
                    } else if (board[y][x + 1].isDigit()) {
                        board[y][x + 1] = (board[y][x + 1].code + 1).toChar()
                    }
                    if (board[y + 1][x + 1] == '.') {
                        board[y + 1][x + 1] = '1'
                    } else if (board[y + 1][x + 1].isDigit()) {
                        board[y + 1][x + 1] = (board[y + 1][x + 1].code + 1).toChar()
                    }
                    if (board[y + 1][x] == '.') {
                        board[y + 1][x] = '1'
                    } else if (board[y + 1][x].isDigit()) {
                        board[y + 1][x] = (board[y + 1][x].code + 1).toChar()
                    }
                    if (board[y + 1][x - 1] == '.') {
                        board[y + 1][x - 1] = '1'
                    } else if (board[y + 1][x - 1].isDigit()) {
                        board[y + 1][x - 1] = (board[y + 1][x - 1].code + 1).toChar()
                    }
                    if (board[y][x - 1] == '.') {
                        board[y][x - 1] = '1'
                    } else if (board[y][x - 1].isDigit()) {
                        board[y][x - 1] = (board[y][x - 1].code + 1).toChar()
                    }


                }

//              теперь проверяю правую сторону
                if (y in 1..7 && x == 8) {
                    if (board[y + 1][x] == '.') {
                        board[y + 1][x] = '1'
                    } else if (board[y + 1][x].isDigit()) {
                        board[y + 1][x] = (board[y + 1][x].code + 1).toChar()
                    }
                    if (board[y + 1][x - 1] == '.') {
                        board[y + 1][x - 1] = '1'
                    } else if (board[y + 1][x - 1].isDigit()) {
                        board[y + 1][x - 1] = (board[y + 1][x - 1].code + 1).toChar()
                    }
                    if (board[y][x - 1] == '.') {
                        board[y][x - 1] = '1'
                    } else if (board[y][x - 1].isDigit()) {
                        board[y][x - 1] = (board[y][x - 1].code + 1).toChar()
                    }
                    if (board[y - 1][x - 1] == '.') {
                        board[y - 1][x - 1] = '1'
                    } else if (board[y - 1][x - 1].isDigit()) {
                        board[y - 1][x - 1] = (board[y - 1][x - 1].code + 1).toChar()
                    }
                    if (board[y - 1][x] == '.') {
                        board[y - 1][x] = '1'
                    } else if (board[y - 1][x].isDigit()) {
                        board[y - 1][x] = (board[y - 1][x].code + 1).toChar()
                    }


                }

//              теперь проверяю нижнюю сторону
                if (y == 8 && x in 1..7) {
                    if (board[y][x - 1] == '.') {
                        board[y][x - 1] = '1'
                    } else if (board[y][x - 1].isDigit()) {
                        board[y][x - 1] = (board[y][x - 1].code + 1).toChar()
                    }
                    if (board[y - 1][x - 1] == '.') {
                        board[y - 1][x - 1] = '1'
                    } else if (board[y - 1][x - 1].isDigit()) {
                        board[y - 1][x - 1] = (board[y - 1][x - 1].code + 1).toChar()
                    }
                    if (board[y - 1][x] == '.') {
                        board[y - 1][x] = '1'
                    } else if (board[y - 1][x].isDigit()) {
                        board[y - 1][x] = (board[y - 1][x].code + 1).toChar()
                    }
                    if (board[y - 1][x + 1] == '.') {
                        board[y - 1][x + 1] = '1'
                    } else if (board[y - 1][x + 1].isDigit()) {
                        board[y - 1][x + 1] = (board[y - 1][x + 1].code + 1).toChar()
                    }
                    if (board[y][x + 1] == '.') {
                        board[y][x + 1] = '1'
                    } else if (board[y][x + 1].isDigit()) {
                        board[y][x + 1] = (board[y][x + 1].code + 1).toChar()
                    }


                }

//              теперь проверяю левую сторону
                if (y in 1..7 && x == 0) {
                    if (board[y - 1][x] == '.') {
                        board[y - 1][x] = '1'
                    } else if (board[y - 1][x].isDigit()) {
                        board[y - 1][x] = (board[y - 1][x].code + 1).toChar()
                    }
                    if (board[y - 1][x + 1] == '.') {
                        board[y - 1][x + 1] = '1'
                    } else if (board[y - 1][x + 1].isDigit()) {
                        board[y - 1][x + 1] = (board[y - 1][x + 1].code + 1).toChar()
                    }
                    if (board[y][x + 1] == '.') {
                        board[y][x + 1] = '1'
                    } else if (board[y][x + 1].isDigit()) {
                        board[y][x + 1] = (board[y][x+1].code + 1).toChar()
                    }
                    if (board[y + 1][x + 1] == '.') {
                        board[y + 1][x + 1] = '1'
                    } else if (board[y + 1][x + 1].isDigit()) {
                        board[y + 1][x + 1] = (board[y + 1][x + 1].code + 1).toChar()
                    }
                    if (board[y + 1][x] == '.') {
                        board[y + 1][x] = '1'
                    } else if (board[y + 1][x].isDigit()) {
                        board[y + 1][x] = (board[y + 1][x].code + 1).toChar()
                    }


                }
//              теперь проверяем в центре с восьми сторон
                if (y in 1..7 && x in 1..7) {
                    if (board[y-1][x] == '.') {
                        board[y-1][x] = '1'
                    } else if (board[y-1][x].isDigit()) {
                        board[y-1][x] = (board[y-1][x].code + 1).toChar()
                    }
                    if (board[y-1][x+1] == '.') {
                        board[y-1][x+1] = '1'
                    } else if (board[y-1][x+1].isDigit()) {
                        board[y-1][x+1] = (board[y-1][x+1].code + 1).toChar()
                    }
                    if (board[y][x+1] == '.') {
                        board[y][x+1] = '1'
                    } else if (board[y][x+1].isDigit()) {
                        board[y][x+1] = (board[y][x+1].code + 1).toChar()
                    }
                    if (board[y+1][x+1] == '.') {
                        board[y+1][x+1] = '1'
                    } else if (board[y+1][x+1].isDigit()) {
                        board[y+1][x+1] = (board[y+1][x+1].code + 1).toChar()
                    }
                    if (board[y+1][x] == '.') {
                        board[y+1][x] = '1'
                    } else if (board[y+1][x].isDigit()) {
                        board[y+1][x] = (board[y+1][x].code + 1).toChar()
                    }
                    if (board[y+1][x-1] == '.') {
                        board[y+1][x-1] = '1'
                    } else if (board[y+1][x-1].isDigit()) {
                        board[y+1][x-1] = (board[y+1][x-1].code + 1).toChar()
                    }
                    if (board[y][x-1] == '.') {
                        board[y][x-1] = '1'
                    } else if (board[y][x-1].isDigit()) {
                        board[y][x-1] = (board[y][x-1].code + 1).toChar()
                    }
                    if (board[y-1][x-1] == '.') {
                        board[y-1][x-1] = '1'
                    } else if (board[y-1][x-1].isDigit()) {
                        board[y-1][x-1] = (board[y-1][x-1].code + 1).toChar()
                    }









                }


            }



        }
    }

}

fun setDelMinesMarks(coordinateY: Int, coordinateX: Int) {
    if (board[coordinateY][coordinateX] == 'X') {
        hiddenBoard[coordinateY][coordinateX] = '*'
        closedMinesLeft++
        correckMines++
    } else if (board[coordinateY][coordinateX].isDigit()) {
        println("There is a number here!")
    } else if (hiddenBoard[coordinateY][coordinateX] == '*') {
        hiddenBoard[coordinateY][coordinateX] = '.'
        closedMinesLeft--
    }
    else {
        hiddenBoard[coordinateY][coordinateX] = '*'
        closedMinesLeft++
    }

}

fun checkEmptySlots(coordinateY: Int, coordinateX: Int) {
    if (board[coordinateY][coordinateX] == '.') {
        hiddenBoard[coordinateY][coordinateX] = '/'

        for (i in coordinateX downTo 1) {
            if (hiddenBoard[coordinateY][i-1] == '.' && hiddenBoard[coordinateY][i] == '/') {
                hiddenBoard[coordinateY][i-1] = '/'
            }
            if (coordinateY > 0 && hiddenBoard[coordinateY-1][coordinateX] == '.') hiddenBoard[coordinateY-1][coordinateX] = '/'
            if (coordinateY < 8 && hiddenBoard[coordinateY+1][coordinateX] == '.') hiddenBoard[coordinateY+1][coordinateX] = '/'
        }
        for (i in coordinateX..7) {
            if (hiddenBoard[coordinateY][i+1] == '.' && hiddenBoard[coordinateY][i] == '/') {
                hiddenBoard[coordinateY][i+1] = '/'
            }
            if (coordinateY > 0 && hiddenBoard[coordinateY-1][coordinateX] == '.') hiddenBoard[coordinateY-1][coordinateX] = '/'
            if (coordinateY < 8 && hiddenBoard[coordinateY+1][coordinateX] == '.') hiddenBoard[coordinateY+1][coordinateX] = '/'
        }

        for (y in coordinateY downTo 0) {
            repeat(9) {
                for (x in 0.. 8) {
                    if (x < 8 && hiddenBoard[y][x] == '.' && hiddenBoard[y][x+1] == '/' ||
                        x > 0 && hiddenBoard[y][x] == '.' && hiddenBoard[y][x-1] == '/' ||
                        y > 0 && hiddenBoard[y][x] == '.' && hiddenBoard[y-1][x] == '/' ||
                        y < 8 && hiddenBoard[y][x] == '.' && hiddenBoard[y+1][x] == '/') {
                        hiddenBoard[y][x] = '/'
                    }
                }
            }
        }

        for (y in coordinateY .. 8) {
            repeat(9) {
                for (x in 0.. 8) {
                    if (x < 8 && hiddenBoard[y][x] == '.' && hiddenBoard[y][x+1] == '/' ||
                        x > 0 && hiddenBoard[y][x] == '.' && hiddenBoard[y][x-1] == '/' ||
//                        x == 8 && hiddenBoard[y][x] == '.' && hiddenBoard[y][x-1] == '/' ||
                        y > 0 && hiddenBoard[y][x] == '.' && hiddenBoard[y-1][x] == '/' ||
                        y < 8 && hiddenBoard[y][x] == '.' && hiddenBoard[y+1][x] == '/'
//                        y == 0 && hiddenBoard[y][x] == '.' && hiddenBoard[y+1][x] == '/'
                    ) {
                        hiddenBoard[y][x] = '/'
                    }
                }
            }
        }

    }
}