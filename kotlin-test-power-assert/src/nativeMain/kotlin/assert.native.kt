package kotlinx.test.powerassert

import kotlinx.powerassert.EqualityExpression

internal actual fun failMultiple(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    failMultipleDefault(message, equalityErrors)
}
