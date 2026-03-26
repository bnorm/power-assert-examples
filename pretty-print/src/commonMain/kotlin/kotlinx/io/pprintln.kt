package kotlinx.io

import kotlin.powerassert.CallExplanation
import kotlin.powerassert.PowerAssert

@PowerAssert
fun pprintln(message: String) {
    val explanation = PowerAssert.explanation
    println(explanation?.toPretty() ?: message)
}

@PowerAssert
fun pformat(message: String): String {
    val explanation = PowerAssert.explanation
    return explanation?.toPretty() ?: message
}

internal fun CallExplanation.toPretty(): String = buildString {
    val messageArg = arguments.last()!!
    val source = source.redact(beforeIndex = messageArg.startOffset, afterIndex = messageArg.endOffset)
    var start = 0
    for (expression in expressions) {
        val prefix = source.getOrNull(expression.startOffset - 1)
        val suffix = source.getOrNull(expression.endOffset)
        if (prefix == '$') {
            append(source.substring(start, expression.startOffset))
            append("{") // Add surrounding braces.
            append(source.substring(expression.startOffset, expression.endOffset))
            append(" = ")
            append(expression.value)
            append("}")
            start = expression.endOffset
        } else if (prefix == '{' && suffix == '}') {
            append(source.substring(start, expression.startOffset))
            append(source.substring(expression.startOffset, expression.endOffset))
            append(" = ")
            append(expression.value)
            start = expression.endOffset
        }
    }
    append(source.substring(start))
}.trimIndent()

private fun String.redact(beforeIndex: Int, afterIndex: Int): String {
    val upstream = this
    return buildString {
        require(beforeIndex in upstream.indices && afterIndex in 0..upstream.length && beforeIndex <= afterIndex)
        var index = 0
        while (index < beforeIndex) {
            val c = upstream[index++]
            if (c.isWhitespace()) {
                append(c)
            } else {
                append(' ')
            }
        }
        append(upstream.substring(beforeIndex, afterIndex))
        index = afterIndex
        while (index < length) {
            val c = upstream[index++]
            if (c.isWhitespace()) {
                append(c)
            } else {
                append(' ')
            }
        }
    }
}
