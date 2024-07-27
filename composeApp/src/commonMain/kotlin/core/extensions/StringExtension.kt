package core.extensions

fun String.isValidNumber(): Boolean {
    val validPrefixes = listOf("010", "011", "012", "015")
    return this.length == 10 && validPrefixes.any { this.startsWith(it) }
}

fun String.isValidUsername(): Boolean {
    val nameParts = this.trim().split("\\s+".toRegex())
    return nameParts.size >= 2 && nameParts.all { it.all { char -> char.isLetter() } }
}