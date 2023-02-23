package AdventOfCode.Registers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class MainTest {

    @Test
    fun `test largest value in register after running instructions`() {
        val instructions = listOf(
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10"
        )

        val file = File.createTempFile("test", "txt")
        file.writeText(instructions.joinToString(separator = "\n"))

        val registers = mutableMapOf<String, Int>()

        var largestValue = Int.MIN_VALUE

        for (instruction in instructions) {
            val regex = Regex("""(\w+) (inc|dec) (-?\d+) if (\w+) ([><=!]+) (-?\d+)""")
            val matchResult = regex.find(instruction)

            if (matchResult != null) {
                val (register, operation, amount, conditionRegister, comparison, comparisonValue) = matchResult.destructured

                val currentValue = registers.getOrDefault(register, 0)
                val conditionValue = registers.getOrDefault(conditionRegister, 0)

                if (compare(conditionValue, comparison, comparisonValue.toInt())) {
                    val newValue = if (operation == "inc") currentValue + amount.toInt() else currentValue - amount.toInt()
                    registers[register] = newValue
                    largestValue = maxOf(largestValue, newValue)
                }
            }
        }

        val expectedLargestValue = 10
        val actualLargestValue = largestValue
        assertEquals(expectedLargestValue, actualLargestValue)
    }

    private fun compare(value: Int, operator: String, comparisonValue: Int): Boolean {
        return when (operator) {
            ">" -> value > comparisonValue
            ">=" -> value >= comparisonValue
            "<" -> value < comparisonValue
            "<=" -> value <= comparisonValue
            "==" -> value == comparisonValue
            "!=" -> value != comparisonValue
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }
    }
}
