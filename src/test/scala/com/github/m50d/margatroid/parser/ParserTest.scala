package com.github.m50d.margatroid.parser

import org.junit.Assert._
import java.util.stream.Collectors
import org.junit.Test
import com.github.m50d.margatroid.model.ast.Literal
import com.github.m50d.margatroid.model.ast.Grouped
import java.util.Arrays

class ParserTest {
  val parser = new Parser()

  private[this] def roundtripTest(input: String) = {
    val parseResult = parser.parse(input).collect(Collectors.toList())
    assertEquals(input, parseResult.stream().map[String](_.prettyPrint()).collect(Collectors.joining(" ")))
    parseResult
  }

  @Test
  def basicFunctionality() = {
    var parseResult = roundtripTest("1 2 3")
    assertEquals(Arrays.asList(new Literal("1"), new Literal("2"), new Literal("3")), parseResult)
    parseResult = roundtripTest("[ 1 2 3 ]")
    assertEquals(1, parseResult.size)
    assert(parseResult.get(0).isInstanceOf[Grouped])
  }

  @Test
  def nested(): Unit = roundtripTest("[ [ 1 ] ]")
}