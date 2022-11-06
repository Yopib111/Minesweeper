import kotlin.random.Random

fun generatePredictablePassword(seed: Int): String {
    var randomPassword = ""
    val gener = Random(seed)
    for (i in 1..10) {
        randomPassword += gener.nextInt(33, 127).toChar()
    }
    return randomPassword
}
