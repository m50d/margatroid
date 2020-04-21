package com.github.m50d.margatroid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;
import com.github.m50d.margatroid.ast.Node.Catamorphism;
import com.github.m50d.margatroid.ast.Quoted;
import com.github.m50d.margatroid.model.Function;

public class Margatroid {
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
}
