package kotlinx.test.fluent

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StringTests {
    @Test
    fun `basic test`() {
        val subject = "Kotlin"
        val expectedLength = 4
        val expectedPrefix = "kot"
        val error = assertFailsWith<AssertionError> {
            assertThat(subject) {
                hasLength(expectedLength)
                startsWith(expectedPrefix)
            }
        }
        assertEquals(
            actual = error.message?.trimIndent(),
            expected = """
                Assertion failed:
                 * String "Kotlin" does not have length '4'.
                 * String "Kotlin" does not start with "kot".

                assertThat(subject) {
                           |
                           Kotlin

                    hasLength(expectedLength)
                              |
                              4

                    startsWith(expectedPrefix)
                               |
                               kot

                }
            """.trimIndent()
        )
    }
}
