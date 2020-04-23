package com.github.m50d.margatroid.builtins;
import java.util.List;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Scope;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.Literal;

public class Plus implements Function {

	@Override
	public Value apply(List<? extends Value> arguments, Scope scope) {
		return new Literal(arguments.stream().collect(Collectors.summingInt(n -> Integer.valueOf(((Literal)n).value))).toString());
	}
	
}
