package kotlinx.test.powerassert

import kotlin.powerassert.EqualityExpression
import org.opentest4j.AssertionFailedError
import org.opentest4j.MultipleFailuresError

internal actual fun fail(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    throw when (equalityErrors.size) {
        0 -> AssertionFailedError(message)

        1 -> {
            val error = equalityErrors[0]
            AssertionFailedError(message, error.rhs, error.lhs)
        }

        else -> {
            MultipleFailuresError(
                message,
                equalityErrors.map { EqualityError(it) },
            )
        }
    }
}

private class EqualityError(
    expression: EqualityExpression
) : AssertionFailedError(
    "Expected <${expression.rhs}>, actual <${expression.lhs}>",
    expression.rhs, expression.lhs,
) {
    override fun fillInStackTrace(): Throwable = this // Stack trace is unnecessary.
}
