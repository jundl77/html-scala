package de.hackundsoehne.invoicegen

import com.julianbrendl.htmlscala.model.ReactComponent
import com.julianbrendl.htmlscala.server.Router
import org.http4s.dsl._
import org.http4s.{Method, Request, Response, Status}
import org.specs2.matcher.MatchResult
import io.circe.literal._
import org.http4s.circe._

class RouterSpec extends org.specs2.mutable.Specification {

  // Define test cases
  "Transpilation Test" >> {
    "return 200" >> {
      testServerStatus()
    }
    "transpile successfully" >> {
      testTranspilation()
    }
  }

  /** Constructs a test request with a piece of test code in Scala to transpile to HTML.*/
  private[this] val routerEval: Response = {

    // Define Scala code to test
    val code =
      """
      new ReactComponent {
        def myName() = "Julian Brendl"

        override def render(): Text.TypedTag[String] = {
          div(
            h1("Test"),
            div(
             p("My name is: " + myName()),
             p("This is my second paragraph"),
             p("This is my third paragraph")
            )
          )
        }
      }
      """.stripMargin
    val body = json"""{"code": $code}"""

    // Build test request to send to server
    val req = Request(Method.POST, uri("/transpile"))
      .withBody(body)
      .unsafeRun()

    // Execute test request
    val task = Router.transpile.orNotFound(req)
    task.unsafeRun()
  }

  /** Test if server returns 200. */
  private[this] def testServerStatus(): MatchResult[Status] =
    routerEval.status must beEqualTo(Status.Ok)

  /** Test if transpilation is performed successfully. */
  private[this] def testTranspilation(): MatchResult[String] = {
    val plainHTML = "\"<div><h1>Test</h1><div><p>My name is: Julian Brendl</p><p>This is my second paragraph</p>" +
      "<p>This is my third paragraph</p></div></div>\""
    routerEval.as[String].unsafeRun() must beEqualTo(plainHTML)
  }
}
