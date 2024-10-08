package core.extensions

fun String.isValidNumber(): Boolean {
    val validPrefixes = listOf("010", "011", "012", "015")
    return this.length == 11 && validPrefixes.any { this.startsWith(it) }
}

fun String.isValidUsername(): Boolean {
    val nameParts = this.trim().split("\\s+".toRegex())
    return nameParts.size >= 2 && nameParts.all { it.all { char -> char.isLetter() } }
}

fun String.isValidKycUsername(minLength: Int = 3, maxLength: Int = 15): Boolean {
    val trimmedUsername = this.trim()
    return trimmedUsername.length in minLength..maxLength && trimmedUsername.all { it.isLetterOrDigit() }
}

fun String.isCreditCardFormatted(): Boolean {
    val expectedLength = 16
    val expectedPattern = Regex("""\d{4}-\d{4}-\d{4}-\d{4}""")
    return this.length == expectedLength
}

fun String.isCreditCardPassword(): Boolean {
    return this.length == 4
}

fun String.formatCardNumber(): String {
    return "**** **** **** ${this.takeLast(4)}"
}