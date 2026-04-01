# power-assert-examples

These examples are provided as a companion to the
[Kotlin Power-Assert Explanation KEEP](https://github.com/Kotlin/KEEP/discussions/478).
They can also be used as a playground for experimenting with the new Power-Assert APIs which will be available in 2.4.0.

## kotlin-test-power-assert

Tired of not getting great IntelliJ integration with Power-Assert? In this example, we can use the presence of failed
`EqualityExpression`s to construct the necessary assertion errors so "click to see difference" is shown in IntelliJ!

## fluent-assert

Prefer a fluent or soft-assert style of writing assertions? Power-Assert can help you achieve this as well! By combining
multiple `CallExplanation`s into a single explanation, we can write a DSL which provides information on both the subject
and the asserted conditions.

## pretty-print

Why stop at assertions? Power-Assert can be used to provide expression information for all sorts of use cases. With a
little bit of creativity, we can create a custom Power-Assert based pretty-print!
