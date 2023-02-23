package AdventOfCode.After.Password

import java.io.File

//Uppgift: 2020/day2 both parts - Password philosophy

//källan för inspiration: https://www.reddit.com/r/adventofcode/comments/k52psu/2020_day_02_solutions/

// class av data för att beskriva regler av lösenord hur de ser ut på raden
data class PasswordRule(val min: Int, val max: Int, val letter: Char)

// klass av data för att beskriva lösenord och regler kopplade till detta lösenord
data class Password(val rule: PasswordRule, val password: String)

// funktion att kontrollera om lösenord är giltig enligt första delen av uppgiften
fun Password.isValidPart1(): Boolean {
    // beräkna antal input av förväntat bokstav i lösenordet
    val count = password.count { it == rule.letter }
    // kontrollera om antalet är within limits
    return count in rule.min..rule.max
}

// funktion att kontrollera om lösenord är giltigt enligt del 2 av uppgiften
fun Password.isValidPart2(): Boolean {
    // kontrollera om bokstaven i första position markerat i reglerna stämmer
    val first = password.getOrNull(rule.min - 1) == rule.letter
    // kontrollera om bokstaven finns i andra position markerad i regler
    val second = password.getOrNull(rule.max - 1) == rule.letter
    // returnerar true om bokstaven syns exakt i en av två positioner
    return first.xor(second)
}

// funktion att omvandla raden till exemplar av lösenord
fun parsePassword(input: String): Password {
    // fördela input rad i komponenter
    val (range, letter, password) = input.split(" ")
    val (min, max) = range.split("-").map(String::toInt)
    // skapa en exemplar av regel för lösenord från de analyserade komponenter
    val rule = PasswordRule(min, max, letter[0])
    // skapa och returnera exemplar av lösenord baserad på regler och lösenord
    return Password(rule, password)
}

fun main() {
    // läser input och omvandlal varje rad till exemplar av lösenord
    val input = File("src/main/kotlin/AdventOfCode/After/Password/Input.txt").readLines().map(::parsePassword)
    // beräkna antal giltiga lösenord enligt del 1 uppgift
    val validCountPart1 = input.count(Password::isValidPart1)
    // enligt deluppgift 2
    val validCountPart2 = input.count(Password::isValidPart2)
    // skriva ut
    println(validCountPart1)
    println(validCountPart2)
}
