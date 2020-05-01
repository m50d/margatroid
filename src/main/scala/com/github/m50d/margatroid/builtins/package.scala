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
    def apply(arguments: IndexedSeq[Value], scope: Scope): Value = arguments.foldLeft[Value](Void()){
      case (_, Grouped(IndexedSeq(Literal(functionName), args @ _*))) =>
        scope.get(functionName).asInstanceOf[FunctionModel](args.toIndexedSeq, scope)
      case (_, Reference(key)) => scope.get(key)  
      case (_, x) => x
    }
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
    
  def defaultScope() = Scope(None, Map("+" -> +, "eval" -> eval, "proc" -> proc))
//		List<String> argumentNames = ((Quoted) arguments.get(1)).contents.map(v -> ((Literal) v).value).collect(Collectors.toList());
//		List<AstNode> body = ((Quoted) arguments.get(2)).contents.collect(Collectors.toList());
//		Function function = new Function() {
//			@Override
//			public Valueu apply(List<? extends Valueu> arguments, Scope scope) {
//				
//			}};
}