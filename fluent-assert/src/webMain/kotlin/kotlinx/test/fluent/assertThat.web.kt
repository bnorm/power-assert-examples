package kotlinx.test.fluent

import kotlinx.powerassert.EqualityExpression

internal actual fun fail(message: String, equalityErrors: List<EqualityExpression>): Nothing {
    throw AssertionError(message)
}
