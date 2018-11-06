# html-scala

A simple and light-weight server that transpiles Scala code to HTML.

This project arose from the need to build React components using Scala.

### Example:

Scala code:

```scala
new ReactComponent {
    def messageOfTheDay() = "Hello World!"

    override def render(): Text.TypedTag[String] = {
        div(
            h1("Amazing Title"),
            div(
                p("Message of the day: " + messageOfTheDay()),
                p("This is the first paragraph."),
                p("This is the second paragraph.")
            )
        )
    }
}
```

Resulting HTML:

```html
<div>
    <h1>Amazing Title</h1>
    <div>
        <p>Message of the day: Hello World!</p>
        <p>This is the first paragraph.</p>
        <p>This is the second paragraph.</p>
    </div>
</div>
```