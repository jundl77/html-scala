package com.julianbrendl.htmlscala

import com.julianbrendl.htmlscala.server.Router
import fs2.Task
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

import scala.util.Properties.envOrNone

/** Starting point of server */
object Main extends StreamApp {

  val port: Int = envOrNone("HTTP_PORT").fold(3001)(_.toInt)

  /** Start the server */
  def stream(args: List[String]): fs2.Stream[Task, Nothing] = BlazeBuilder.bindHttp(port, host="0.0.0.0")
    .mountService(Router.httpService, "/")
    .serve
}
