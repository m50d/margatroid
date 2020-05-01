package com.github.m50d.margatroid.parser

import org.junit.Assert._
import org.junit.Test
import com.github.m50d.margatroid.model.Literal
import com.github.m50d.margatroid.model.Grouped

class ParserTest {
  val parser = new Parser()

  private[this] def roundtripTest(input: String) = {
    val parseResult = parser.parse(input)
    assertEquals(input, parseResult.map(_.prettyPrint).mkString(" "))
    parseResult
  }

  @Test
  def literals(): Unit = assertEquals(IndexedSeq(new Literal("1"), new Literal("2"), new Literal("3")), roundtripTest("1 2 3"))
  
  @Test def group(): Unit = roundtripTest("[ 1 2 3 ]") match {
    case IndexedSeq(_: Grouped) => //expected
  }

  @Test
  def nested(): Unit = roundtripTest("[ [ 1 ] ]")
}