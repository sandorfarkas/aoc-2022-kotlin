class Day5(private val input: List<String> = readInput("day5")) {
    private val columns = Array(9){""}
    private var numberOfColumns = 0;
    private var bottomLineIndex = 0;

    private fun processInput() {
        input.forEachIndexed{ index, line ->
            if (line.startsWith(" 1   2   3")) {
                bottomLineIndex = index
                numberOfColumns = line.trim().substring(line.length - 2).toInt()
            }
        }

        for (i in bottomLineIndex - 1 downTo  0) {
            input[i].forEachIndexed{ chrIndex, chr -> run {
                if (chr.code in 65..90) {
                    val columnIndex = (chrIndex-1)/4
                    columns[columnIndex] = columns[columnIndex] + chr
                }
            }}
        }
    }

    private fun processMoves() {
        for (i in bottomLineIndex+2 until input.size) {
            val move = input[i].split(" ")
            val amount = move[1].toInt()
            val from = move[3].toInt() - 1
            val to = move[5].toInt() - 1

            for (j in 1 .. amount) {
                val item = columns[from].substring(columns[from].length - 1)
                columns[from] = columns[from].substring(0, columns[from].length - 1)
                columns[to] = columns[to] + item
            }
        }
    }

    private fun processMoves9001() {
        for (i in bottomLineIndex+2 until input.size) {
            val move = input[i].split(" ")
            val amount = move[1].toInt()
            val from = move[3].toInt() - 1
            val to = move[5].toInt() - 1

            val item = columns[from].substring(columns[from].length - amount)
            columns[from] = columns[from].substring(0, columns[from].length - amount)
            columns[to] = columns[to] + item

        }
    }

    fun first() {
        println("~ Part I ~")
        processInput()
        processMoves()
        columns.forEach { if (it.trim() !== "" ) print(it.substring(it.length - 1)) }
        println()
    }

    fun second() {
        println("~ Part II ~")
        processInput()
        processMoves9001()
        columns.forEach { if (it.trim() !== "" ) print(it.substring(it.length - 1)) }
        println()
    }
}