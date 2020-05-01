package com.github.m50d.margatroid

import com.github.m50d.margatroid.model.FunctionModel
import com.github.m50d.margatroid.model.Value
import com.github.m50d.margatroid.model.Scope
import com.github.m50d.margatroid.model.Literal
import com.github.m50d.margatroid.model.Quoted
import com.github.m50d.margatroid.model.Void
import scala.collection.mutable.Map
import com.github.m50d.margatroid.model.Grouped
import com.github.m50d.margatroid.model.Reference

package object builtins {
  object eval extends FunctionModel {
    def apply(argument: Value, scope: Scope): Value = argument match {
      case Grouped(IndexedSeq(Literal(functionName), args @ _*)) =>
        scope.get(functionName).asInstanceOf[FunctionModel](args map (apply(_, scope)) toIndexedSeq, scope)
      case Reference(key) => scope.get(key)  
      case x => x
    } 
    override def apply(arguments: IndexedSeq[Value], scope: Scope): Value =
      arguments.foldLeft[Value](Void())((_, x) => apply(x, scope))
  }
  object proc extends FunctionModel {
    def apply(arguments: IndexedSeq[Value], scope: Scope): Value = arguments match {
      case IndexedSeq(Literal(functionName), Quoted(arguments), Quoted(body)) =>
        val argumentNames = arguments collect {case Literal(n) => n}
        val function = new FunctionModel {
          override def apply(arguments: IndexedSeq[Value], scope: Scope) = {
            val bodyScope = Scope(Some(scope))
            argumentNames zip arguments foreach (bodyScope.put _).tupled
            eval(body, bodyScope)
          }
        }
        scope.put(functionName, function)
        Void()
    }
  }
  object + extends FunctionModel {
    def apply(arguments: IndexedSeq[Value], scope: Scope): Value =
      Literal(arguments.collect {
        case Literal(i) => i.toInt
      }.sum.toString)
  }
    
  def prelude() = Scope(None, Map("+" -> +, "eval" -> eval, "proc" -> proc))
}