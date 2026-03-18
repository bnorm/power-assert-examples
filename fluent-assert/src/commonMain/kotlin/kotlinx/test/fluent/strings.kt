package kotlinx.test.fluent

import kotlinx.powerassert.PowerAssert

@PowerAssert
fun AssertScope<String>.hasLength(length: Int) {
    if (subject.length != length) {
        collect("String \"$subject\" does not have length '$length'.", PowerAssert.explanation)
    }
}

@PowerAssert
fun AssertScope<String>.startsWith(prefix: String, ignoreCase: Boolean = false) {
    if (!subject.startsWith(prefix, ignoreCase)) {
        collect(
            "String \"$subject\" does not start with \"$prefix\"${if (ignoreCase) " (ignoring case)" else ""}.",
            PowerAssert.explanation,
        )
    }
}
