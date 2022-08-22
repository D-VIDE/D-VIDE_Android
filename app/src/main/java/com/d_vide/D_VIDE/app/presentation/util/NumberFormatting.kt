package com.d_vide.D_VIDE.app.presentation.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat

class NumberFormatting : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val originalText = text.text
        val formattedText = formatAmountOrMessage(text.text)

        val offsetMapping = object : OffsetMapping  {

            override fun originalToTransformed(offset: Int): Int {
                if (originalText.isValidFormattableAmount) {
                    return when {
                        offset <= 3 -> offset
                        offset <= 6 -> offset + 1
                        offset <= 9 -> offset + 2
                        else -> 11
                    }
                }
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (originalText.isValidFormattableAmount) {
                    return when {
                        offset <= 3 -> offset
                        offset <= 7 -> offset - 1
                        offset <= 10 -> offset - 2
                        offset <= 13 -> offset - 3
                        else -> 10
                    }
                }
                return offset
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping
        )
    }
}

val String.isValidFormattableAmount get(): Boolean = isNotBlank() && isDigitsOnly() && length <= 10

fun formatAmountOrMessage(input: String)
: String = if (input.isValidFormattableAmount) { DecimalFormat("#,###").format(input.toDouble()) }
           else { input }