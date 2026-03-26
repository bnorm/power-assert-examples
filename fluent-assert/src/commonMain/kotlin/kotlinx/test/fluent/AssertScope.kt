package kotlinx.test.fluent

import kotlin.powerassert.Explanation
import kotlin.powerassert.PowerAssert

@PowerAssert.Ignore // Always exclude the assertion scope from Power-Assert explanations.
interface AssertScope<out T> {
    val subject: T
    fun collect(message: String?, explanation: Explanation?)
    fun fail(message: String?, explanation: Explanation?)

    // TODO support sub-scopes
}
