import kotlin.random.Random

fun sumInts(): Int {
    var sum = 0
    for (i in 1..101) {
        sum += sum + i
    }
    return sum
}