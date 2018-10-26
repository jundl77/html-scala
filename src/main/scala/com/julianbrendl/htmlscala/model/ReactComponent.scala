package com.julianbrendl.htmlscala.model

import scalatags.Text

abstract class ReactComponent {
  def render(): Text.TypedTag[String]
  def renderAsString(): String = render().toString()
}