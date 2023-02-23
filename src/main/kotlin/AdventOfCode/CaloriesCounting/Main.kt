package AdventOfCode.CaloriesCounting

import java.io.File

// Uppgift 2022/day 1 part A: Calorie counting

//Tomtar har mat med sig på en resa. I vår input har vi kalorier som varje tomte har med sig.
//Varje tomte skriver ner antal kalorier som varje mat de har köpt innehåller, till exempel meals, snacks, rations, etc.
// En element per rad. Varje tomte separerar sina kalorier från andras med en blank rad.
// Om nån tomte har slut på kalorier och behöver extra snacks, den måste veta vilken tomte att fråga.
// Därför behöver man veta hur många kalorier bär den tomte som har mest kalorier.
// Exempel: 24000 (den fjärde tomte har med sig).

//Uppgift: att hitta vilken tomte har mest kalorier med sig. Hur många kalorier har han med sig på resan?

fun main() {
    val input = File("src/main/kotlin/AdventOfCode/CaloriesCounting/InputCalories.txt").readLines() //läser filen

    var maxCalories = 0
    var currentCalories = 0

    for (line in input) { //loopar input file
        if (line.isBlank()) { // på blanka rader 0 kalorier
            if (currentCalories > maxCalories) {
                maxCalories = currentCalories
            }
            currentCalories = 0
        } else {
            currentCalories += line.toInt() // räknar kalorier av varje tomte separat och returnerar resultat i Integer
        }
    }

    if (currentCalories > maxCalories) {
        maxCalories = currentCalories
    }

    println(maxCalories)
}


