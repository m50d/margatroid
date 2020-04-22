package com.github.m50d.margatroid.builtins;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Literal;
import com.github.m50d.margatroid.model.Scope;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.AstNode;
import com.github.m50d.margatroid.model.ast.LiteralNode;
import com.github.m50d.margatroid.model.ast.Quoted;
import com.github.m50d.margatroid.model.Void;

public class Proc implements Function {

	@Override
	public Value apply(List<? extends Value> arguments, Scope scope) {
		String functionName = ((Literal) arguments.get(0)).value;
		List<String> argumentNames = ((Quoted) arguments.get(1)).contents.map(v -> ((LiteralNode) v).value).collect(Collectors.toList());
		List<AstNode> body = ((Quoted) arguments.get(2)).contents.collect(Collectors.toList());
		Function function = new Function() {
			@Override
			public Value apply(List<? extends Value> arguments, Scope scope) {
				Scope bodyScope = new Scope(Optional.of(scope));
				Iterator<String> argumentNamesIterator = argumentNames.iterator();
				Iterator<? extends Value> argumentIterator = arguments.iterator();
				while(argumentNamesIterator.hasNext())
					bodyScope.put(argumentNamesIterator.next(), argumentIterator.next());
				return new Eval().apply(body, bodyScope);
			}};
		scope.put(functionName, function);
		return new Void();
	}

}
