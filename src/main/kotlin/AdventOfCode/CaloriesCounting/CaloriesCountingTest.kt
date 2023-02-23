package AdventOfCode.CaloriesCounting


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File


fun findMaxCalories(input: List<String>): Int {
    var maxCalories = 0
    var currentCalories = 0

    for (line in input) {
        if (line.isBlank()) {
            // reached end of an elf's inventory, update maxCalories if necessary
            if (currentCalories > maxCalories) {
                maxCalories = currentCalories
            }
            currentCalories = 0
        } else {
            // add calories from current line to current elf's total
            currentCalories += line.toInt()
        }
    }

    // check if last elf has the highest calorie count
    if (currentCalories > maxCalories) {
        maxCalories = currentCalories
    }

    return maxCalories
}

class CaloriesCountingTest {
    @Test
    fun testFindMaxCalories() {
        // create temporary input file
        val inputFile = File.createTempFile("input", ".txt")
        inputFile.writeText("1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000")

        // read input and call function to find max calories
        val input = inputFile.readLines()
        val maxCalories = findMaxCalories(input)

        // verify max calories
        assertEquals(24000, maxCalories)

        // clean up temporary file
        inputFile.delete()
    }
}
