# html-scala

[![Build Status](https://travis-ci.org/jundl77/html-scala.svg?branch=master)](https://travis-ci.org/jundl77/html-scala)

A simple, light-weight server that transpiles Scala code to HTML. 

This project arose from the need to build React components using Scala. Under the hood, html-scala uses [scalatags](https://github.com/lihaoyi/scalatags). Look at scalatags to see what Scala code you can write and how it is transformed to HTML.

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

## API

### /transpile
Make a POST request to ```/transpile``` with the following payload as JSON:

```json
{
  code: "code to transpile"
}
```

**Important:** The code has to be formatted as shown in the example above, using ```ReactComponent```.

### /status
You can check the status of the service with a simple GET request to ```/status```. If everything is okay, 200 should be returned.

## Docker 

You can find this server as a docker image under [julianbrendl/html-scala](https://hub.docker.com/r/julianbrendl/html-scala/).
