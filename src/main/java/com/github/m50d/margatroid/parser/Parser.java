package com.github.m50d.margatroid.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.github.m50d.margatroid.ast.Grouped;
import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;
import com.github.m50d.margatroid.ast.Quoted;

public class Parser {
	Stream<Node> parse(List<String> input) {
		if(input.size() == 0) return Stream.empty();
		int closing;
		String next = input.get(0).intern();
		switch(next) {
			case "[":
				//FIXME not necessarily the first one!
				closing = input.indexOf("]");
				return Stream.concat(Stream.of(new Grouped(parse(input.subList(1, closing)))), 
						parse(input.subList(closing + 1, input.size())));
			case "{":
				closing = input.lastIndexOf("}");
				return Stream.concat(Stream.of(new Quoted(parse(input.subList(1, closing)))), 
						parse(input.subList(closing + 1, input.size())));
			default:
				return Stream.concat(Stream.of(new Literal(next)), parse(input.subList(1, input.size())));
		}
	}
	public Stream<Node> parse(String input) {
		return parse(Arrays.asList(input.split(" ")));
	}
}
