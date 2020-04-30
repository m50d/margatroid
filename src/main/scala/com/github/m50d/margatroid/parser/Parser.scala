package com.github.m50d.margatroid.parser

import java.util.stream.Stream
import com.github.m50d.margatroid.model.ast.AstNode
import java.util.Stack
import com.github.m50d.margatroid.model.ast.Grouped
import com.github.m50d.margatroid.model.ast.Quoted
import com.github.m50d.margatroid.model.ast.Reference
import com.github.m50d.margatroid.model.ast.Literal
import java.util.Arrays
import java.util.Iterator

class Parser {
 def parse(input: Iterator[String]): Stream[AstNode] = {
		var accumulator = Stream.builder[AstNode]()
		val stack = new Stack[Function[Stream[AstNode], Stream.Builder[AstNode]]]
		while (input.hasNext) {
			val next = input.next().intern();
			next match {
			case "]" |"}" =>
				accumulator = stack.pop().apply(accumulator.build())
			case "[" =>
				val accumulator2 = accumulator
				stack.push(sn => accumulator2.add(new Grouped(sn)))
				accumulator = Stream.builder()
			case "{" =>
				val accumulator3 = accumulator
				stack.push(sn => accumulator3.add(new Quoted(sn)))
				accumulator = Stream.builder()
			case _ =>
				accumulator.add(if(next.startsWith("$")) new Reference(next.substring(1)) else new Literal(next))
			}
		}
		assert(stack.isEmpty())
		accumulator.build()
	}

	def parse(input: String): Stream[AstNode] = parse(Arrays.asList(input.split(" "): _*).iterator())
}