package AdventOfCode.After.Registers

import java.io.File

//Uppgift 2017/day8 part A: I heard you like registers

//källan för inspiration: https://www.reddit.com/r/adventofcode/comments/7icnff/2017_day_8_solutions/

// register har namn och värde
class Register(val name: String, var value: Int = 0)

// instruktion
class Instruction(
    val registerName: String,
    val operation: String, // ("inc" eller "dec")
    val amount: Int, // belopp som ska användas att beräkna registers värde
    val conditionRegisterName: String, // namn av register som används i condition
    val comparisonOperator: String, // operator som används i jämförelser (exempel, ">", "<=", "==")
    val comparisonValue: Int // värde använt i condition
) {
    // returnerar värde true, om condition true för input värde av register
    fun evaluateCondition(registers: Map<String, Int>): Boolean {
        val conditionValue = registers.getOrDefault(conditionRegisterName, 0)
        return compare(conditionValue, comparisonOperator, comparisonValue)
    }

    // implementerar instruktion via ändringar av input värde av register
    fun execute(registers: MutableMap<String, Int>) {
        val currentValue = registers.getOrDefault(registerName, 0)
        val newValue = if (operation == "inc") currentValue + amount else currentValue - amount
        registers[registerName] = newValue
    }
}

fun main() {
    //
    val instructions = File("src/main/kotlin/AdventOfCode/After/Registers/InputRegisters.txt").readLines().map { parseInstruction(it) }

    // inicialiserar alla registrar med 0 värde
    val registers = mutableMapOf<String, Int>().withDefault { 0 }

    // spårar max värde i any register
    var largestValue = Int.MIN_VALUE

    // implementera varje instruktion som följer sin condition
    for (instruction in instructions) {
        if (instruction.evaluateCondition(registers)) {
            instruction.execute(registers)
            largestValue = maxOf(largestValue, registers[instruction.registerName]!!)
        }
    }

    // skriver ut max värde i any register
    val finalValue = registers.values.max()
    println("Largest value in any register: $finalValue")
}

// analyserar raden med instruktion och returnerar objekt av instruktionen
fun parseInstruction(instruction: String): Instruction {
    val regex = Regex("""(\w+) (inc|dec) (-?\d+) if (\w+) ([><=!]+) (-?\d+)""")
    val matchResult = regex.find(instruction) ?: throw IllegalArgumentException("Invalid instruction: $instruction")
    val (registerName, operation, amountStr, conditionRegisterName, comparisonOperator, comparisonValueStr) = matchResult.destructured
    val amount = amountStr.toInt()
    val comparisonValue = comparisonValueStr.toInt()
    return Instruction(registerName, operation, amount, conditionRegisterName, comparisonOperator, comparisonValue)
}

// jämför värde med värde av comparison med användning av vald operator
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