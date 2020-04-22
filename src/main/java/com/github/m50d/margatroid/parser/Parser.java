package com.github.m50d.margatroid.parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Stream;

import com.github.m50d.margatroid.model.ast.Grouped;
import com.github.m50d.margatroid.model.ast.LiteralNode;
import com.github.m50d.margatroid.model.ast.AstNode;
import com.github.m50d.margatroid.model.ast.Quoted;

public class Parser {
	Stream<AstNode> parse(Iterator<String> input) {
		Stream.Builder<AstNode> accumulator = Stream.builder();
		final Stack<Function<Stream<AstNode>, Stream.Builder<AstNode>>> stack = new Stack<>();
		while (input.hasNext()) {
			String next = input.next().intern();
			switch (next) {
			case "]":
			case "}":
				accumulator = stack.pop().apply(accumulator.build());
				break;
			case "[":
				Stream.Builder<AstNode> accumulator2 = accumulator;
				stack.push(sn -> accumulator2.add(new Grouped(sn)));
				accumulator = Stream.builder();
				break;
			case "{":
				Stream.Builder<AstNode> accumulator3 = accumulator;
				stack.push(sn -> accumulator3.add(new Quoted(sn)));
				accumulator = Stream.builder();
				break;
			default:
				accumulator.add(new LiteralNode(next));
			}
		}
		assert stack.isEmpty();
		return accumulator.build();
	}

	public Stream<AstNode> parse(String input) {
		return parse(Arrays.asList(input.split(" ")).iterator());
	}
}
