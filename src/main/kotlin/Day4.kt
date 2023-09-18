class Day4(private val input: List<String> = readInput("day4")) {
    private fun isContain(row: String): Boolean {
        val parts = row.split(",")
        val l1 = parts[0].split("-")
        val l2 = parts[1].split("-")

        val r1s = l1[0].toInt()
        val r1e = l1[1].toInt()
        val r2s = l2[0].toInt()
        val r2e = l2[1].toInt()

        return (r1s <=r2s && r1e >= r2e) ||
                (r2s <= r1s && r2e >= r1e)
    }

    private fun isOverlap(row: String): Boolean {
        val parts = row.split(",")
        val l1 = parts[0].split("-")
        val l2 = parts[1].split("-")

        val r1 = l1[0].toInt()..l1[1].toInt()
        val r2 = l2[0].toInt()..l2[1].toInt()

        var found = false
        r1.forEach{
            if (r2.contains(it)) {
                found = true
            }
        }
        r2.forEach{
            if (r1.contains(it)) {
                found = true
            }
        }

        return found
    }

    fun first() {
        println("~ Part I ~")
        println(input.count { isContain(it) })
    }

    fun second() {
        println("~ Part II ~")
        println(input.count { isOverlap(it) })
    }


}