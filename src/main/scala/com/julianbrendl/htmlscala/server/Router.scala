package com.julianbrendl.htmlscala.server

import com.julianbrendl.htmlscala.handlers.Transpiler
import com.julianbrendl.htmlscala.handlers.StatusCheck
import io.circe._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

/** Specifies all routes of the server */
object Router {

  /** Route used to handle invoices */
  val httpService = HttpService {

    // Route to status check
    case req @ GET -> Root / "status" =>
      for {
        // Decode string if there is any
        str <- req.as[String]

        // Transpile to HTML
        status = StatusCheck.check()

        // Send response
        resp <- Ok(status.asJson).putHeaders(Header("Access-Control-Allow-Origin", "*"))
      } yield resp

    // Route to transpile
    case req @ POST -> Root / "transpile" =>
      for {
        // Decode json
        json <- req.as[Json]

        // Transpile to HTML
        htmlCode = Transpiler.transpile(json)

        // Send response
        resp <- Ok(htmlCode.asJson).putHeaders(Header("Access-Control-Allow-Origin", "*"))
      } yield resp
  }
}
