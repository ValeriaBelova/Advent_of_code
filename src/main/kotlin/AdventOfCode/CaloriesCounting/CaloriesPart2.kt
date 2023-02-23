package AdventOfCode.CaloriesCounting

import java.io.File

//Uppgift 2022/day 1 part B: Calorie counting

//Det kan vara så att en tomte som hade mest kalorier fick slut på allting. Så att det inte händer tomtar vill gärna veta
// antal kalorier som top tre tomtar har med sig. I sånt fall om en tomte har inga flera, då finns chans att ta från andra två.
// Exempel: top tre tomtar: the fourth Elf (with 24000 Calories), then the third Elf (with 11000 Calories),
//// then the fifth Elf (with 10000 Calories). Summan av alla kalorier av alla denna tre tomtar är 45000.

//Uppgift: att hitta de top tre tomtar som har mest kalorier på resan. Hur många kalorier totalt har dem?


fun main() {
    val input = File("src/main/kotlin/AdventOfCode/CaloriesCounting/InputCalories.txt").readLines()

    val calories = IntArray(input.size)
    var i = 0
    for (line in input) {  //loopar rader med kalorier, räknar och sparar
        if (line.isNotBlank()) {
            calories[i] += line.toInt()
        } else {
            i++
        }
    }

    calories.sortDescending() // returnerar kalorier i naturlig sorterings ordning
    val totalCalories = calories[0] + calories[1] + calories[2] // top tre tomtar

    println("The top three elves carry a total of $totalCalories calories.")
}