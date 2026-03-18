package kotlinx.test.powerassert

import kotlinx.powerassert.EqualityExpression

internal actual fun failMultiple(
    message: String,
    equalityErrors: List<EqualityExpression>
) {
    // TODO service loader for JUnit version specific implementations?
    failMultipleDefault(message, equalityErrors)
}
