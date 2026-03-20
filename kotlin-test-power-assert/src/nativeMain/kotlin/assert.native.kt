package kotlinx.test.powerassert

import kotlinx.powerassert.EqualityExpression

internal actual fun fail(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    failDefault(message, equalityErrors)
}
