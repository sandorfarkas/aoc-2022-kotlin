class Day3(private val input: List<String> = readInput("day3")) {
    private fun getCharValue(chr: Char): Int {
        val charValue = chr.code
        if (charValue in 65..90) {
            return charValue - 38
        }
        return charValue - 96
    }

    private fun getDiffFromRow(row: String): Char {
        val part1 = row.substring(0, row.length/2)
        val part2 = row.substring(row.length/2)

        var letter: Char = 'e';
        part1.forEach{chr ->
            if (part2.contains(chr)) {
                letter = chr;
            }
        }

        return letter
    }

    private fun getCommonFromRows(): List<Char> {
        val chars = mutableListOf<Char>()
        for (i in 0..input.size-1 step 3) {

            var letter: Char = 'e';
            input[i].forEach{chr ->
                if (input[i+1].contains(chr) && input[i+2].contains(chr)) {
                    letter = chr;
                }
            }

            chars.add(letter)
        }
        return chars
    }

    fun first() {
        println("~ Part I ~")

        println(input.sumOf { row -> getCharValue(getDiffFromRow(row)) })
    }

    fun second() {
        println("~ Part II ~")

        println(getCommonFromRows().map{getCharValue(it)}.sum())
    }
}