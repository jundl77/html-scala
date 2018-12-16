package com.julianbrendl.htmlscala

import com.julianbrendl.htmlscala.server.Router
import fs2.Task
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp
import org.http4s._
import scala.concurrent.duration._

import scala.util.Properties.envOrNone
import org.http4s.server.middleware.{CORS, CORSConfig}

/** Starting point of server */
object Main extends StreamApp {

  val port: Int = envOrNone("HTTP_PORT").fold(3001)(_.toInt)

  val cors: HttpService => HttpService = (s: HttpService) =>
    CORS(
      s,
      CORSConfig(
        anyOrigin = true,
        allowedMethods = Some(Set("GET", "POST")),
        maxAge = 1.day.toSeconds,
        allowCredentials = true
      )
    )

  /** Start the server */
  def stream(args: List[String]): fs2.Stream[Task, Nothing] = BlazeBuilder.bindHttp(port, host="0.0.0.0")
    .mountService(cors(Router.httpService), "/")
    .serve
}
