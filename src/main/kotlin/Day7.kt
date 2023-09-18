class Day7(private val input: List<String> = readInput("day7")) {
    private val files = mutableListOf<FileItem>()

    private fun processInput() {
        var i = 0
        var currentDir = "";
        while (i < input.size) {
            val line = input[i]
            if (line.startsWith("$")) {
                val parts = line.split(' ')
                val command = parts[1]

                if (command == "cd") {
                    when (val dir = parts[2]) {
                        "/" -> {
                            currentDir = ""
                            files.add(FileItem("/", 0));
                        }
                        ".." -> {
                            currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"))
                        }
                        else -> {
                            currentDir = "$currentDir/$dir"
                            files.add(FileItem(currentDir, 0));
                        }
                    }
                }
                if (command == "ls") {
                    var nextLine = input[i + 1]
                    var count = 0
                    while (!nextLine.startsWith("$")) {
                        if (!nextLine.startsWith("dir")) {
                            val fileData = nextLine.split(" ")
                            files.add(FileItem("$currentDir/${fileData[1]}", fileData[0].toInt()))
                        }
                        count++
                        nextLine = if (i + count + 1 == input.size) {
                            "$" // end of file
                        } else {
                            input[i + count + 1]
                        }
                    }
                    i += count
                }
                i++
            }
        }
    }

    fun first() {
        println("~ Part I ~")
        processInput()
        println(files.filter { file -> file.size == 0 }
            .map { dir ->
                files.filter { fileItem -> fileItem.path.startsWith("${dir.path}") }.sumOf { fileItem -> fileItem.size }
            }
            .filter { sum -> sum <= 100_000 }
            .sum())
    }

    fun second() {
        println("~ Part II ~")
        val rootSize = files.filter { file -> file.path == "/" }.sumOf { dir ->
            files.filter { fileItem -> fileItem.path.startsWith("${dir.path}") }.sumOf { fileItem -> fileItem.size }
        }
        val diskSize = 70_000_000
        val requiredSize = 30_000_000
        val availableSize = diskSize - rootSize

        println(rootSize)
        println(availableSize)
        println()

        println(files.filter { file -> file.size == 0 && file.path != "/" }
            .map { dir ->
                files.filter { fileItem -> fileItem.path.startsWith("${dir.path}/") }.sumOf { fileItem -> fileItem.size }
            }
            .filter { size -> availableSize + size >= requiredSize }
            .min())

    }

    class FileItem(val path: String, val size: Int) {
        override fun toString(): String {
            return "$path $size"
        }
    }
}