class Day1(private val input: List<String> = readInput("day1")) {
    private val sums = mutableListOf<Int>()

    fun first() {
        println("~ Part I ~")
        var buffer = 0;
        input.forEach{
            if (it != "") {
                buffer += it.toInt();
            } else {
                sums += buffer
                buffer = 0
            }
        }
        sums += buffer
        println(sums.max())
    }

    fun second() {
        println("~ Part II ~")
        val reverseSums = sums.sortedDescending()
        println((0 until 3).sumOf { it -> reverseSums[it] })
    }
}

