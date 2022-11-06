import kotlin.random.Random

fun makeDecision(): String {
    return when (Random.nextInt(1, 4)) {
        1 -> "Rock"
        2 -> "Paper"
        else -> "Scissors"
    }
}
