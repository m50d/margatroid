package com.github.m50d.margatroid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.m50d.margatroid.builtins.DefaultScope;
import com.github.m50d.margatroid.builtins.Plus;
import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Scope;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.Literal;
import com.github.m50d.margatroid.model.ast.Node;
import com.github.m50d.margatroid.model.ast.Quoted;
import com.github.m50d.margatroid.model.ast.Node.Catamorphism;
import com.github.m50d.margatroid.parser.Parser;

public class Margatroid {
	Value stage(Node ast, Scope scope) {
		return ast.catamorphism(new Catamorphism<Value>() {
			@Override
			public Value grouped(Stream<Value> contents) {
				List<Value> invocation = contents.collect(Collectors.toList());
				String functionName = ((Literal) invocation.remove(0)).value;
				return ((Function) scope.get(functionName)).apply(invocation, scope);
			}

			@Override
			public Value quoted(Stream<Node> contents) {
				return new Quoted(contents);
			}

			@Override
			public Value literal(String value) {
				return new Literal(value);
			}

			@Override
			public Value reference(String value) {
				return scope.get(value);
			}
		});
	}
	
	public Value run(String input) {
		Node ast = new Parser().parse(input).collect(Collectors.toList()).get(0);
		return stage(ast, new DefaultScope());
	}
}
