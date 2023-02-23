package AdventOfCode

import java.io.File

//Uppgift: 2020/day2 part 1 - Password philosophy

//Det blev någonting fel med dator i the North Pole Toboggan Rental Shop och det går inte längre att logga in.
// Korrumperad databas innehåller listan av lösenord och regler när lösenordet ska användas.

//Exempel av följande lista:
//1-3 a: abcde
//1-3 b: cdefg
//2-9 c: ccccccccc

//Varje rad beskriver regler och lösenord. Regler visar det lägsta och det högsta antal gånger som den skrivna bokstaven
//måste visa upp sig för att lösenordet blir giltigt.
// Exempel: 1-3 a betyder att lösenordet måste innehålla a minst 1 gång och max 3 gånger.
//Två lösenord är giltiga i exempel ovanför. Lösenordet som är i mitten är inte godkänt: det innehåller ingen instans b,
// men måste innehålla åtminstone 1 b.
// Lösenord 1 och 3 är korrekta: första innehåller 1 a och den tredje 9 c, både inom respektive regler.

//Uppgift: Hur många lösenord är giltiga enligt företags policy?

fun countValidPasswords(inputFile: String): Int {
    val input = File(inputFile).readLines()
    var validCount = 0
    for (line in input) {
        val (range, charWithColon, password) = line.split(" ")
        val char = charWithColon[0]
        val (min, max) = range.split("-").map { it.toInt() }
        val count = password.count { it == char }
        if (count in min..max) {
            validCount++
        }
    }
    return validCount
}

fun main() {
    val inputFile = "src/main/kotlin/AdventOfCode/PasswordPhilosophy/InputPassword.txt"
    val validCount = countValidPasswords(inputFile)
    println("Number of valid passwords: $validCount")
}

