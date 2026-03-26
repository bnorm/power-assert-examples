package kotlinx.test.fluent

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GeneralTests {
    @Test
    fun `basic test`() {
        val subject: Any? = Mascot(name = "Unknown")
        val error = assertFailsWith<AssertionError> {
            assertThat(subject) {
                require(subject is Mascot)
                check(subject.name == "Kodee")
            }
        }
        assertEquals(
            actual = error.message?.trimIndent(),
            expected = """
                Assertion failed:
                 * Condition failed: subject.name == "Kodee"

                assertThat(subject) {
                           |
                           Mascot(name=Unknown)

                    require(subject is Mascot)
                    check(subject.name == "Kodee")
                                  |    |
                                  |    false
                                  "Unknown"

                }
            """.trimIndent()
        )
    }

    @Test
    fun `require failure test`() {
        val subject: Any? = "Unknown"
        val error = assertFailsWith<AssertionError> {
            assertThat(subject) {
                require(subject is Mascot)
                check(subject.name == "Kodee")
            }
        }
        assertEquals(
            actual = error.message?.trimIndent(),
            expected = """
                Assertion failed:
                 * Condition failed: subject is Mascot

                assertThat(subject) {
                           |
                           "Unknown"

                    require(subject is Mascot)
                                    |
                                    false

                    check(subject.name == "Kodee")
                }
            """.trimIndent()
        )
    }
}
