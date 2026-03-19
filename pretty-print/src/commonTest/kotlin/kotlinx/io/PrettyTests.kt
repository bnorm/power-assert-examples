package kotlinx.io

import kotlin.test.Test
import kotlin.test.assertEquals

class PrettyTests {
    @Test
    fun `basic test`() {
        val other = "World"
        val self = "kodee"
        val actual =
            pformat(
                """
                    Hello, ${other.replaceFirstChar { it.titlecase() }}!
                    My name is $self.
                """.trimIndent()
            )
        assertEquals(
            actual = actual,
            expected = $$"""
                ""$${'"'}
                    Hello, ${other.replaceFirstChar { it.titlecase() } = World}!
                    My name is ${self = kodee}.
                ""$${'"'}.trimIndent()
            """.trimIndent(),
        )
    }
}
