package com.github.m50d.margatroid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;
import com.github.m50d.margatroid.ast.Node.Catamorphism;
import com.github.m50d.margatroid.ast.Quoted;
import com.github.m50d.margatroid.builtins.Plus;
import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.parser.Parser;

public class Margatroid {
	private static final Map<String, Function> DEFAULT_SCOPE = new HashMap<>();
	static {
		DEFAULT_SCOPE.put("+", new Plus());
	}
	
	Node stage(Node ast, Map<String, Function> scope) {
		return ast.catamorphism(new Catamorphism<Node>() {
			@Override
			public Node grouped(Stream<Node> contents) {
				List<Node> invocation = contents.collect(Collectors.toList());
				String functionName = ((Literal) invocation.remove(0)).value;
				return scope.get(functionName).apply(invocation);
			}

			@Override
			public Node quoted(Stream<Node> contents) {
				return new Quoted(contents);
			}

			@Override
			public Node literal(String value) {
				return new Literal(value);
			}
		});
	}
	
	public Node run(String input) {
		Node ast = new Parser().parse(input).collect(Collectors.toList()).get(0);
		return stage(ast, DEFAULT_SCOPE);
	}
}
