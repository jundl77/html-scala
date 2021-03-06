package com.julianbrendl.htmlscala.handlers

import com.julianbrendl.htmlscala.model.ReactComponent

import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox
import io.circe._

/** Transpiles a Scala [[ReactComponent]] into plain HTML.*/
object Transpiler {

  private val toolbox = currentMirror.mkToolBox()
  private val IMPORTS =
    """import com.julianbrendl.htmlscala.model.ReactComponent;
       import scalatags.Text.all._;
       import scalatags.Text;"""

  /** Transpiles a Scala [[ReactComponent]] into plain HTML.
    *
    * @param json [[Json]] object containing the code of a [[ReactComponent]]
    * @return The plain HTML representation of the [[ReactComponent]]
    */
  def transpile(json: Json): String = {

    // Define variables
    val c: HCursor = json.hcursor
    var code = ""

    // Extract all invoice components from the json
    try {
      code = decodeString(c, "code")
    } catch {
      // TODO: Handle error properly
      case e: IllegalArgumentException => println("Exception caught: " + e);
    }

    val ast = this.toolbox.parse(IMPORTS + code)
    val compiledCode = this.toolbox.compile(ast)
    val reactComponent = compiledCode().asInstanceOf[ReactComponent]

    reactComponent.renderAsString()
  }

  /** Decodes the value of a key as long as it's a string
    *
    * @param c [[HCursor]] for a [[Json]] object
    * @param key The key corresponding to the value to decode
    * @throws java.lang.IllegalArgumentException Thrown if json is not correct
    * @return The string corresponding to the key
    */
  @throws(classOf[IllegalArgumentException])
  private def decodeString(c: HCursor, key: String): String = c.get[String](key) match {
    case Left(error) => throw new IllegalArgumentException("Error parsing " + key + " to String.")
    case Right(json) => json
  }
}
