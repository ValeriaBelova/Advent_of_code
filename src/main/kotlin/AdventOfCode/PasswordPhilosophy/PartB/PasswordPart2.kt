package AdventOfCode.PasswordPhilosophy

import java.io.File

//Uppgift: 2020/day2 part 2 - Password philosophy

// Företags manager förstår att han blandade ihop policy från sitt gammalt jobb med policyn på nuvarande arbetsplats.
//Den korrekta policy beskriver två positioner i lösenordet, där 1 betyder det första bokstaven, 2 - den andra bokstaven,
//osv. Viktigt! Det finns ingen "index zero"!
// Bara en av positionerna måste innehålla den markerade bokstaven. Om det finns flera samma bokstav på andra ställe i lösenordet
// då bryr systemet (policyn) inte om detta.
//
//Exempel likadan som i del 1:
//
//1-3 a: abcde is valid: position 1 contains a and position 3 does not.
//1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
//2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.

//Hur många lösenord är giltiga enligt nya företags regler?



fun countValidPasswords(inputFile: String): Int {
    val input = File(inputFile).readLines()
    var validCount = 0
    for (line in input) {
        val (positions, charWithColon, password) = line.split(" ")
        val char = charWithColon[0]
        val (pos1, pos2) = positions.split("-").map { it.toInt() }
        val match1 = password[pos1 - 1] == char
        val match2 = password[pos2 - 1] == char
        if (match1 xor match2) {
            validCount++
        }
    }
    return validCount
}

fun main() {
    val inputFile = "src/main/kotlin/AdventOfCode/PasswordPhilosophy/Part2/InputPassword.txt"
    val validCount = countValidPasswords(inputFile)
    println("Number of valid passwords: $validCount")
}
