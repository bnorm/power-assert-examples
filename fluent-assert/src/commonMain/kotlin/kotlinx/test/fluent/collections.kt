package kotlinx.test.fluent

import kotlinx.powerassert.PowerAssert

@PowerAssert
fun AssertScope<Collection<*>>.hasSize(size: Int) {
    // Adding custom assertions is as easy as writing the condition and failure message!
    if (subject.size != size) {
        collect("Collection \"${subject}\" does not have size '$size'.", PowerAssert.explanation)
    }
}
