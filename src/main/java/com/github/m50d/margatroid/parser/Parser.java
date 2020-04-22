package com.github.m50d.margatroid.parser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Stream;

import com.github.m50d.margatroid.ast.Grouped;
import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;
import com.github.m50d.margatroid.ast.Quoted;

public class Parser {
	Stream<Node> parse(Iterator<String> input, Stream.Builder<Node> accumulator, Stack<Function<Stream<Node>, Stream.Builder<Node>>> stack) {
		if (!input.hasNext()) {
			assert stack.isEmpty();
			return accumulator.build();
		}
		String next = input.next().intern();
		switch (next) {
		case "]":
		case "}":
			return parse(input, stack.pop().apply(accumulator.build()), stack);
		case "[":
			stack.push(sn -> accumulator.add(new Grouped(sn)));
			return parse(input, Stream.builder(), stack);
		case "{":
			stack.push(sn -> accumulator.add(new Quoted(sn)));
			return parse(input, Stream.builder(), stack);
		default:
			accumulator.add(new Literal(next));
			return parse(input, accumulator, stack);
		}
	}

	public Stream<Node> parse(String input) {
		return parse(Arrays.asList(input.split(" ")).it);
	}
}
