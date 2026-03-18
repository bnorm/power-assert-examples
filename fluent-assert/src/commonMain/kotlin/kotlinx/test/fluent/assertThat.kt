package kotlinx.test.fluent

import kotlinx.powerassert.*

@PowerAssert
fun <T> assertThat(subject: T, block: AssertScope<T>.() -> Unit) {
    val primary = PowerAssert.explanation ?: error("power-assert compiler-plugin is required")

    val failures = mutableListOf<Pair<String?, List<Expression>>>()
    val scope = object : AssertScope<T> {
        override val subject: T get() = subject

        override fun collect(message: String?, explanation: Explanation?) {
            // Adjust the offset of the expressions to be relative to the primary explanation.
            val adjusted = explanation?.expressions?.map { it.copy(explanation.offset - primary.offset) }.orEmpty()
            failures.add(message to adjusted)
        }

        override fun fail(message: String?, explanation: Explanation?) {
            collect(message, explanation)
            fail(subject, primary, failures)
        }
    }

    scope.block()
    fail(subject, primary, failures)
}

private fun fail(
    subject: Any?,
    primary: CallExplanation,
    failures: MutableList<Pair<String?, List<Expression>>>,
) {
    if (failures.isNotEmpty()) {
        val expressions = failures.flatMap { it.second }
            // Do not repeat the subject in the failure message.
            .filter { it.value !== subject }
            // Attempt to reduce duplication of similar expressions by comparing source code and value.
            .distinctBy { Pair(primary.source.substring(it.startOffset, it.endOffset), it.value) }

        // Create a fake argument to combine failures with primary explanation.
        val synthetic = CallExplanation.Argument(-1, -1, CallExplanation.Argument.Kind.VALUE, expressions)
        val combined = CallExplanation(primary.offset, primary.source, primary.arguments + synthetic)

        // Craft a message that includes all failure messages and diagram of entire assertion scope.
        throw AssertionError(buildString {
            appendLine("Assertion failed:")
            for ((msg, _) in failures) {
                if (msg != null) appendLine(" * $msg")
            }
            if (failures.isNotEmpty()) appendLine()
            append(combined.toDefaultMessage())
        })
    }
}

