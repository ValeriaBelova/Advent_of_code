package AdventOfCode.PasswordPhilosophy

import junit.framework.TestCase.assertEquals
import org.junit.Test
//import java.io.File
//import kotlin.test.assertEquals

class PasswordPhilosophyTest {

    @Test
    fun testCountValidPasswords() {
        val inputFile = "src/main/kotlin/AdventOfCode/PasswordPhilosophy/Part2/TestInput.txt"
        val expectedValidCount = 9
        val actualValidCount = countValidPasswords(inputFile)
        assertEquals(expectedValidCount, actualValidCount)
    }

}


