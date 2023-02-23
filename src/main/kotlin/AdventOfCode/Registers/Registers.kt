package AdventOfCode.Registers

import java.io.File

//Uppgift 2017/day8 part A: I heard you like registers

// Varje instruktion innehåller flera delar: registret att ändra (öka eller minska registrets värde),
// ökar med eller minskar med, and condition (förutsättningar att ändra).
// Om förutsättningar fyller inte, då skippa instruktionen utan ändringar i registret. Alla register börjar med 0.
// Instruktionerna ser på följande sätt:
//b inc 5 if a > 1
//a inc 1 if b < 5
//c dec -10 if a >= 1
//c inc -20 if c == 10
//Because a starts at 0, it is not greater than 1, and so b is not modified.
//a is increased by 1 (to 1) because b is less than 5 (it is 0).
//c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
//c is increased by -20 (to -10) because c is equal to 10.
//After this process, the largest value in any register is 1.

//Vad är det högsta värde i alla (any) register efter att alla instruktionerna har implementerats?

fun main() {
    val instructions = File("src/main/kotlin/AdventOfCode/Registers/Inputt.txt").readLines() //reads file content as a list of lines
    val registers = mutableMapOf<String, Int>() //create changeable collection med String och Int

    var largestValue = Int.MIN_VALUE // konstanta med minimalt värde från början

    for (instruction in instructions) { //in a list of strings
        val regex = Regex("""(\w+) (inc|dec) (-?\d+) if (\w+) ([><=!]+) (-?\d+)""") //creates a regular expression from the specified pattern string
        val matchResult = regex.find(instruction)//returns the first match of a regular expression

        if (matchResult != null) {
            val (register, operation, amount, conditionRegister, comparison, comparisonValue) = matchResult.destructured //wrapper providing components


            //returns the value corresponding to the given key or default value if the key is not present in the map
            val currentValue = registers.getOrDefault(register, 0)
            val conditionValue = registers.getOrDefault(conditionRegister, 0)

            if (compare(conditionValue, comparison, comparisonValue.toInt())) {
                val newValue =
                    if (operation == "inc")
                        currentValue + amount.toInt()
                    else currentValue - amount.toInt()
                registers[register] = newValue //läggs i mappen
                largestValue = maxOf(largestValue, newValue) // returnerar större av de två värdena
            }
        }
    }

    val finalValue = registers.values.max()
    println("Largest value in any register: $finalValue")
}

fun compare(value: Int, operator: String, comparisonValue: Int): Boolean {
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