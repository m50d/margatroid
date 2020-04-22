package com.github.m50d.margatroid.builtins;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.model.Function;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.Literal;

public class Plus implements Function {

	@Override
	public Value apply(List<Value> arguments, Map<String, Value> scope) {
		return new Literal(arguments.stream().collect(Collectors.summingInt(n -> Integer.valueOf(((Literal)n).value))).toString());
	}
	
}
