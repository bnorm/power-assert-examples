package kotlinx.test.powerassert

import kotlinx.powerassert.EqualityExpression
import kotlinx.powerassert.PowerAssert
import kotlinx.powerassert.toDefaultMessage
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.test.assertEquals
import kotlin.test.fail

@OptIn(ExperimentalContracts::class)
@PowerAssert
fun powerAssert(condition: Boolean, @PowerAssert.Ignore message: String? = null) {
    contract { returns() implies condition }
    if (!condition) {
        val explanation = PowerAssert.explanation
            ?: fail(message)

        val equalityErrors = buildList {
            for (expression in explanation.expressions) {
                if (expression is EqualityExpression && expression.value == false) {
                    add(expression)
                }
            }
        }

        val failureMessage = buildString {
            // OpenTest4J likes to trim messages. Use ZWSP (U+200B) characters to preserve newlines.
            appendLine(message?.takeIf { it.isNotBlank() } ?: "\u200B")
            append(explanation.toDefaultMessage())
            append("\u200B")
        }

        when (equalityErrors.size) {
            0 -> fail(message)
            1 -> equalityErrors[0].let { error -> assertEquals(error.rhs, error.lhs, message) }
            else -> failMultiple(failureMessage, equalityErrors)
        }
    }
}

internal expect fun failMultiple(
    message: String,
    equalityErrors: List<EqualityExpression>
)

internal fun failMultipleDefault(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    throw AssertionError(message).apply {
        for (expression in equalityErrors) {
            addSuppressed(expression.toAssertion())
        }
    }
}

internal fun EqualityExpression.toAssertion(): Throwable {
    // TODO is there a way to clear the stacktrace?
    return runCatching { assertEquals(rhs, lhs) }
        .exceptionOrNull() ?: AssertionError("Expected: <${rhs}>, actual: <${lhs}>")
}
