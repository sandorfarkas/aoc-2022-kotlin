class Day10(private val input: List<String> = readInput("day10")) {
    private var cycle = 1
    private var registerX = 1
    private var sum = 0

    private fun printSignalStrength() {
        if ((cycle - 20) % 40 == 0) {
            //println("C: $cycle $registerX - ${cycle * registerX}")
            sum += cycle * registerX
        }
    }

    private fun isSpritePixel(x: Int): Boolean {
        val posX = x % 40
        return -1 <= registerX - posX && registerX -posX <=1
    }

    private fun drawScreen() {
        val pixel = if (isSpritePixel(cycle - 1)) { "#" } else { " " }
        if ((cycle-1) % 40 == 0) {
            println(pixel)
        } else {
            print(pixel)
        }
    }

    private fun processCommand(command: String, value: Int) {
        when (command) {
            "noop" -> {
                cycle++
                printSignalStrength()
            }
            "addx" -> {
                cycle++
                printSignalStrength()
                cycle++
                registerX += value
                printSignalStrength()
            }
        }
    }

    private fun processCommandSecond(command: String, value: Int) {
        when (command) {
            "noop" -> {
                cycle++
                drawScreen()
            }
            "addx" -> {
                cycle++
                drawScreen()
                cycle++
                registerX += value
                drawScreen()
            }
        }
    }

    fun first() {
        println("~ Part I ~")
        drawScreen()
        input.forEach {
            run {
                val parts = it.split(" ")
                val command = parts[0]
                var value = 0
                if (parts.size == 2) { value = parts[1].toInt() }
                processCommand(command, value)
            }
        }
        println(sum)
    }

    fun second() {
        println("~ Part II ~")
        input.forEach {
            run {
                val parts = it.split(" ")
                val command = parts[0]
                var value = 0
                if (parts.size == 2) { value = parts[1].toInt() }
                processCommandSecond(command, value)
            }
        }

    }
}