package com.julianbrendl.htmlscala

import scala.reflect.runtime.universe._
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox

object Debug {
  def main(args: Array[String]): Unit = {
    val toolbox = currentMirror.mkToolBox()
    val code1 = q"""
      import scalatags.Text.all._

      html(
        head(
          script("some script")
        ),
        body(
          h1("Test"),
          div(
            p("This is my first paragraph"),
            p("This is my second paragraph")
          )
        )
      )"""

    val result1 = toolbox.compile(code1)()

    print(result1.toString())
  }
}
