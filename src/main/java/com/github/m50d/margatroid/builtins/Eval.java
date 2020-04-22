package com.github.m50d.margatroid.builtins;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Literal;
import com.github.m50d.margatroid.model.Scope;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.Void;
import com.github.m50d.margatroid.model.ast.LiteralNode;
import com.github.m50d.margatroid.model.ast.AstNode;
import com.github.m50d.margatroid.model.ast.Quoted;
import com.github.m50d.margatroid.model.ast.AstNode.Catamorphism;

public class Eval implements Function {

	@Override
	public Value apply(List<? extends Value> arguments, Scope scope) {
		Catamorphism<Value> catamorphism = new Catamorphism<Value>() {
			@Override
			public Value grouped(Stream<Value> contents) {
				List<Value> invocation = contents.collect(Collectors.toList());
				String functionName = ((LiteralNode) invocation.remove(0)).value;
				return ((Function) scope.get(functionName)).apply(invocation, scope);
			}

			@Override
			public Value quoted(Stream<AstNode> contents) {
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
		};
		Value current = new Void();
		for(Value argument: arguments)
			current = ((AstNode) argument).catamorphism(catamorphism);
		return current;
	}

}
