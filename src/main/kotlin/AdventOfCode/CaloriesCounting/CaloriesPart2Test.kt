package AdventOfCode.CaloriesCounting

import org.junit.Assert.assertEquals
import org.junit.Test
//import java.io.File
//import kotlin.test.Test
//import kotlin.test.assertEquals

class TopThreeElvesTest {
    @Test
    fun testCalculateTopThreeElvesCalories() {
        val input = listOf(
            "10062", "15651", "1271", "14355", "7220",
            "",
            "4878", "1754", "8466", "4741", "2582", "3003", "5327", "3172", "1327", "6310",
            "",
            "22413", "4145", "22294",
            "",
            "23108", "30453",
            "",
            "2084"
        )

        val calories = IntArray(input.size)
        var i = 0
        for (line in input) {
            if (line.isNotBlank()) {
                calories[i] += line.toInt()
            } else {
                i++
            }
        }

        calories.sortDescending()
        val totalCalories = calories[0] + calories[1] + calories[2]

        assertEquals(45000, totalCalories)
    }
}
