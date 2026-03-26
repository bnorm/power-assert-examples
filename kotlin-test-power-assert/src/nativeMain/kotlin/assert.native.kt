package kotlinx.test.powerassert

import kotlin.powerassert.EqualityExpression

internal actual fun fail(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    failDefault(message, equalityErrors)
}
