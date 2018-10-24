package com.julianbrendl.htmlscala.handlers

import java.util.Locale

import io.circe._

/**
  * Creates an Invoice from a [[Json]] object
  */
object InvoiceParser {

  /** Creates an Invoice from a [[Json]] object
    *
    * @param json [[Json]] object to parse into an Invoice
    * @return A new Invoice object
    */
  def parseInvoice(json: Json): Int = {

    // Define variables
    val c: HCursor = json.hcursor
    var locale: Locale = Locale.GERMAN
    var company = ""

    // Extract all invoice components from the json
    try {
      company = decodeString(c, "company")
    } catch {
      // TODO: Handle error properly
      case e: IllegalArgumentException => println("exception caught: " + e);
    }
    
    1
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

  /** Decodes the value of a key as long as it's an integer
    *
    * @param c [[HCursor]] for a [[Json]] object
    * @param key The key corresponding to the value to decode
    * @throws java.lang.IllegalArgumentException Thrown if json is not correct
    * @return The integer corresponding to the key
    */
  @throws(classOf[IllegalArgumentException])
  private def decodeInt(c: HCursor, key: String): Int = c.get[Int](key) match {
    case Left(error) => throw new IllegalArgumentException("Error parsing " + key + " to Int.")
    case Right(json) => json
  }
}
