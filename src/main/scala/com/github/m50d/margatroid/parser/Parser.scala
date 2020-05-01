package com.github.m50d.margatroid.parser

import com.github.m50d.margatroid.model._
import scala.annotation.tailrec

class Parser {
  @tailrec
  final def parse(input: List[String], stack: List[IndexedSeq[AstNode] => IndexedSeq[AstNode]] = List.empty, accumulator: IndexedSeq[AstNode] = IndexedSeq.empty): IndexedSeq[AstNode] =
    input match {
      case Nil =>
        assert(stack isEmpty)
        accumulator
      case ("]" | "}") :: tail =>
        parse(tail, stack.tail, stack.head(accumulator))
      case "[" :: tail =>
        parse(tail, { ns: IndexedSeq[AstNode] => accumulator :+ Grouped(ns) } :: stack)
      case "{" :: tail =>
        parse(tail, { ns: IndexedSeq[AstNode] => accumulator :+ Quoted(ns) } :: stack)
      case sref :: tail if sref.startsWith("$") =>
        parse(tail, stack, accumulator :+ Reference(sref stripPrefix "$"))
      case literal :: tail =>
        parse(tail, stack, accumulator :+ Literal(literal))
    }

  def parse(input: String): IndexedSeq[AstNode] = parse(input.split(" ").toList)
}