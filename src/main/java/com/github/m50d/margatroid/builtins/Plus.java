package com.github.m50d.margatroid.builtins;
import java.util.List;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Scope;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.LiteralNode;

public class Plus implements Function {

	@Override
	public Value apply(List<? extends Value> arguments, Scope scope) {
		return new LiteralNode(arguments.stream().collect(Collectors.summingInt(n -> Integer.valueOf(((LiteralNode)n).value))).toString());
	}
	
}
