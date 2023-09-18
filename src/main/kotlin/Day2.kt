class Day2(private val input: List<String> = readInput("day2")) {
    private fun getScorePart1(game: String): Int {
        return when(game) {
            "A X" -> (1 + 3)
            "A Y" -> (2 + 6)
            "A Z" -> (3 + 0)

            "B X" -> (1 + 0)
            "B Y" -> (2 + 3)
            "B Z" -> (3 + 6)

            "C X" -> (1 + 6)
            "C Y" -> (2 + 0)
            "C Z" -> (3 + 3)
            else -> {
                0
            }
        }
    }

    private fun getScorePart2(game: String): Int {
        return when(game) {
            "A X" -> (3 + 0)
            "A Y" -> (1 + 3)
            "A Z" -> (2 + 6)

            "B X" -> (1 + 0)
            "B Y" -> (2 + 3)
            "B Z" -> (3 + 6)

            "C X" -> (2 + 0)
            "C Y" -> (3 + 3)
            "C Z" -> (1 + 6)
            else -> {
                0
            }
        }
    }

    fun first() {
        println("~ Part I ~")
        println(input.sumOf { getScorePart1(it) })
    }

    fun second() {
        println("~ Part II ~")
        println(input.sumOf { getScorePart2(it) })
    }
}