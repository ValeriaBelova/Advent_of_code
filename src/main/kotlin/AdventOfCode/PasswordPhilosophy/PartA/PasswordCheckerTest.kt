package AdventOfCode

import org.junit.Assert.assertEquals
import org.junit.Test
//import java.io.File
//import kotlin.test.assertEquals

class PasswordPhilosophyTest {

    @Test
    fun testCountValidPasswords() {
        val inputFile = "src/main/kotlin/AdventOfCode/PasswordPhilosophy/PartA/TestInput.txt"
        val expectedValidCount = 4
        val actualValidCount = countValidPasswords(inputFile)
        assertEquals(expectedValidCount, actualValidCount)
    }

}