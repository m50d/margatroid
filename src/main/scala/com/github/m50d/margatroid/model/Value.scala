package com.github.m50d.margatroid.model

import scala.collection.mutable.Map

sealed trait Value {
  val prettyPrint: String
}
final case class Void() extends Value {
  override val prettyPrint = "(void)"  
}
final case class Scope(parent: Option[Scope], contents: Map[String, Value] = Map.empty) extends Value {
  override val prettyPrint = "(scope)"
  def get(key: String): Value = contents.get(key).getOrElse(parent.fold[Value](Void())(_ get key))
  def put(key: String, value: Value) = copy(contents = contents + (key -> value))
}
trait FunctionModel extends Value {
  override val prettyPrint = "(function)"
  def apply(arguments: IndexedSeq[Value], scope: Scope): Value
}
sealed trait AstNode extends Value
final case class Grouped(contents: IndexedSeq[AstNode]) extends AstNode {
  override val prettyPrint = contents.map(_.prettyPrint).mkString("[ ", " ", " ]")
}
final case class Quoted(contents: IndexedSeq[AstNode]) extends AstNode {
  override val prettyPrint = contents.map(_.prettyPrint).mkString("{ ", " ", " }")
}
final case class Literal(value: String) extends AstNode {
  override val prettyPrint = value
}
final case class Reference(key: String) extends AstNode {
  override val prettyPrint = s"$$$key"
}