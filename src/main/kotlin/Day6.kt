class Day6(private val input: List<String> = readInput("day6")) {

    private fun printFirstFourPosition(line: String) {
        for (i in 0..line.length - 4) {
            if (line[i] != line[i+1] && line[i] != line[i+2] && line[i] != line[i+3]
                && line[i+1] != line[i+2] && line[i+1] != line[i+3]
                && line[i+2] != line[i+3]) {
                println( i+4 );
                return;
            }
        }
    }

    private fun printFirstFourteenPosition(line: String) {

        loop@for (i in 0..line.length - 14) {
            var processed = line[i].toString();
            for (j in i+1 until i+14) {
                if (processed.count{ it == line[j]} >= 1) {
                    continue@loop
                } else {
                    processed += line[j];
                }
            }
            println(i+14)
            break
        }
    }


    fun first() {
        println("~ Part I ~")
        input.forEach { printFirstFourPosition(it) }
    }

    fun second() {
        println("~ Part II ~")
        input.forEach { printFirstFourteenPosition(it) }
    }
}