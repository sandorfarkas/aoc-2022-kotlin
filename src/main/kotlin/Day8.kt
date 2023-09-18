class Day8(private val input: List<String> = readInput("day8")) {
    private val rowNum = input.size
    private val colNum = input[0].length
    private val trees = Array(rowNum) { IntArray(colNum) }

    private fun processInput() {
        input.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, column ->
                trees[rowIndex][columnIndex] = column.toString().toInt()
            }
        }
    }

    private fun isVisible(x: Int, y: Int): Boolean {
        var leftVisible = true
        for (i in x - 1 downTo 0) {
            if (trees[y][i] >= trees[y][x]) {
                leftVisible = false
            }
        }
        var rightVisible = true
        for (i in x + 1 until colNum) {
            if (trees[y][i] >= trees[y][x]) {
                rightVisible = false
            }
        }
        var upVisible = true
        for (i in y - 1 downTo 0) {
            if (trees[i][x] >= trees[y][x]) {
                upVisible = false
            }
        }
        var downVisible = true
        for (i in y + 1 until rowNum) {
            if (trees[i][x] >= trees[y][x]) {
                downVisible = false
            }
        }
        return leftVisible || rightVisible || upVisible || downVisible
    }

    private fun getScenicScore(x: Int, y: Int): Int {
        var leftVisible = 0
        for (i in x - 1 downTo 0) {
            if (trees[y][i] >= trees[y][x]) {
                leftVisible++
                break
            } else {
                leftVisible++
            }
        }
        var rightVisible = 0
        for (i in x + 1 until colNum) {
            if (trees[y][i] >= trees[y][x]) {
                rightVisible++
                break
            } else {
                rightVisible++
            }
        }
        var upVisible = 0
        for (i in y - 1 downTo 0) {
            if (trees[i][x] >= trees[y][x]) {
                upVisible++
                break
            } else {
                upVisible++
            }
        }
        var downVisible = 0
        for (i in y + 1 until rowNum) {
            if (trees[i][x] >= trees[y][x]) {
                downVisible++
                break
            }
            else {
                downVisible++
            }
        }
        return leftVisible * rightVisible * upVisible * downVisible
    }

    fun first() {
        println("~ Part I ~")
        processInput()
        var countVisible = 0
        for (y in 1 until colNum - 1) {
            for (x in 1 until rowNum - 1) {
                if (isVisible(x, y)) {
                    countVisible++
                }
            }
        }

        println(countVisible + ((rowNum * 2) + (colNum * 2) - 4))
    }

    fun second() {
        println("~ Part II ~")

        var max = 0
        for (y in 1 until colNum - 1) {
            for (x in 1 until rowNum - 1) {
                val ss = getScenicScore(x, y)
                if (ss > max) {
                    max = ss
                }
            }
        }
        println(max)
    }
}