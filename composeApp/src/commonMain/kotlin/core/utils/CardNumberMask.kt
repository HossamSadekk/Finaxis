package core.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberMask(
    private val separator: String = " ",
    private val displayOnlyLastFourDigits: Boolean = false,
    private val digitMask: String = "X",
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return makeCardNumberFilter(text, separator, displayOnlyLastFourDigits)
    }

    private fun makeCardNumberFilter(
        text: AnnotatedString,
        separator: String,
        displayOnlyLastFourDigits: Boolean,
    ): TransformedText {
        // format: XXXX XXXX XXXX XXXX by default
        val trimmed =
            if (text.text.length >= 16) text.text.substring(0..15) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += if (displayOnlyLastFourDigits && i !in 12..16) digitMask else trimmed[i]
            if (i == 3 || i == 7 || i == 11) out += separator
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 7 -> offset + 1
                    offset <= 11 -> offset + 2
                    offset <= 16 -> offset + 3
                    else -> 19
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 9 -> offset - 1
                    offset <= 14 -> offset - 2
                    offset <= 19 -> offset - 3
                    else -> 16
                }
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}