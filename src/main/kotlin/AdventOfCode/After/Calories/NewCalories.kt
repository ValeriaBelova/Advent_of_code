package AdventOfCode.After.Calories

import java.io.File

// Uppgift 2022/day 1: Calorie counting

// inspiration plockat från: https://www.reddit.com/r/adventofcode/comments/z9ezjb/2022_day_1_solutions/


// klass med data av grupp av tomtar och deras kalorier
data class Group(val calories: List<Int>) {
    // metod som beräknar antal kalorier för gruppen
    fun totalCalories(): Int {
        return calories.sum()
    }
}

// klass att göra beräkningar enligt listan av gruppen av varor
class CalorieCounter(val groups: List<Group>) {
    // metod för beräkning av max antal kalorier för alla grupper i listan
    fun maxCalories(): Int {
        // kolla genom (jämföra) varje objekt i gruppen med antal kalorier och hitta max value
        return groups.map { it.totalCalories() }.maxOrNull() ?: 0
    }

    // metod som hittar antal kalorier i tre första grupper i listan
    fun topThreeTotalCalories(): Int {
        // sorterar grupper enligt antal kalorier från högst till lägst
        val sortedGroups = groups.sortedByDescending { it.totalCalories() }
        // väljer första tre grupper från sorterad listan
        val topThree = sortedGroups.take(3)
        // returnerar summan av kalorier i tre bästa grupper
        return topThree.sumOf { it.totalCalories() }
    }
}

fun main() {
    val input = File("src/main/kotlin/AdventOfCode/After/Calories/InputCalories.txt").readLines()

    // skapar en tom lista av objekter i gruppen
    val groups = mutableListOf<Group>()
    // skapar tum lista för att förvara antal kalorier för current grupp av varor
    var currentGroup = mutableListOf<Int>()

    // loopar varje rad i input
    for (line in input) {
        // om raden är tum, då avslutas arbetet med current grupp
        if (line.isBlank()) {
            // skapar en ny objekt av grupp med beräkning av kalorier för current grupp och lägga den till listan av grupper
            groups.add(Group(currentGroup.toList()))
            // clear listan av kalori beräkningar för nästa grupp av tomtar
            currentGroup.clear()
        } else {
            // annars lägg en heltal i denna rad till listan av kalorier för varje current grupp
            currentGroup.add(line.toInt())
        }
    }
    // Om raden är tom i input och current grupp av tomtar finns fortfarande, då lägg till den till listan av grupper
    if (currentGroup.isNotEmpty()) {
        groups.add(Group(currentGroup.toList()))
    }

    // skapar objekt av counter av kalorier med listan av gruppobjekt
    val calorieCounter = CalorieCounter(groups)

    // skriva ut max antal kalorier och tre bästa kalorisamlingar.
    println("Part A: The maximum number of calories consumed by any one group of elves is ${calorieCounter.maxCalories()}.")
    println("Part B: The top three elves carry a total of ${calorieCounter.topThreeTotalCalories()} calories.")
}