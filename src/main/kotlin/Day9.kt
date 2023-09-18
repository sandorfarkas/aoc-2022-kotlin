import kotlin.math.abs

class Day9(private val input: List<String> = readInput("day9")) {
    var hX = 0
    var hY = 0
    var tX = 0
    var tY = 0

    private fun getDeltaHead(command: String): Coordinate {
        return when (command) {
            "U" -> Coordinate(0, 1)
            "D" -> Coordinate(0, -1)
            "L" -> Coordinate(-1, 0)
            "R" -> Coordinate(1, 0)
            else -> Coordinate(0, 0)
        }
    }

    private fun getDeltaFollowing(hX: Int, hY: Int, tX: Int, tY: Int): Coordinate {
        if (abs(hX - tX) <= 1 && abs(hY - tY) <= 1) {
            return Coordinate(0, 0)
        }

        if (hX == tX) {
            return if (hY < tY) {
                Coordinate(0, -1)
            } else {
                Coordinate(0, 1)
            }
        }

        if (hY == tY) {
            return if (hX < tX) {
                Coordinate(-1, -0)
            } else {
                Coordinate(1, 0)
            }
        }

        var dX = 0
        var dY = 0
        if (hY < tY) {
            dY--
        } else {
            dY++
        }

        if (hX < tX) {
            dX--
        } else {
            dX++
        }
        return Coordinate(dX, dY)
    }

    fun first() {
        println("~ Part I ~")
        val beenThere = mutableSetOf<Coordinate>()
        input.forEach {
            val parts = it.split(" ")
            val command = parts[0]
            val amount = parts[1].toInt()

            for (i in 0 until amount) {
                val deltaHead = getDeltaHead(command)
                hX += deltaHead.x
                hY += deltaHead.y
                val delta = getDeltaFollowing(hX, hY, tX, tY)
                tX += delta.x
                tY += delta.y
                beenThere.add(Coordinate(tX, tY))
//                println("$command H: $hX $hY T: $tX $tY")
//                print("(${tX}, ${tY}),")
            }
        }
        println(beenThere.size)
    }

    private val knots = Array<Coordinate>(10) { _ -> Coordinate(0, 0) }

    fun second() {
        println("~ Part II ~")
        val beenThere = mutableSetOf<Coordinate>()
        input.forEach {
            val parts = it.split(" ")
            val command = parts[0]
            val amount = parts[1].toInt()

            for (amountIndex in 0 until amount) {
                for (knotIndex in knots.indices) {
                    if (knotIndex == 0) {
                        val deltaHead = getDeltaHead(command)
                        knots[knotIndex] = Coordinate(knots[knotIndex].x + deltaHead.x, knots[knotIndex].y + deltaHead.y)
                    } else {
                        val delta = getDeltaFollowing(knots[knotIndex - 1].x, knots[knotIndex - 1].y, knots[knotIndex].x, knots[knotIndex].y)
                        knots[knotIndex] = Coordinate(knots[knotIndex].x + delta.x, knots[knotIndex].y + delta.y)
                        if (knotIndex == 9) {
                            beenThere.add(Coordinate(knots[knotIndex].x, knots[knotIndex].y))
                        }
                    }
                }
//                println("$command H: $hX $hY T: $tX $tY")
//                print("(${tX}, ${tY}),")
            }
        }
        knots.forEach { println(it) }

        println(beenThere.size)
    }

    class Coordinate(val x: Int, val y: Int) {
        override fun toString(): String {
            return "$x $y"
        }

        override fun equals(other: Any?): Boolean {
            if (other !is Coordinate) return false
            return this.x == other.x && this.y == other.y
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }
}