package com.github.m50d.margatroid.builtins;
import java.util.List;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;
import com.github.m50d.margatroid.model.Function;

public class Plus implements Function {

	@Override
	public Node apply(List<Node> arguments) {
		return new Literal(arguments.stream().collect(Collectors.summingInt(n -> Integer.valueOf(((Literal)n).value))).toString());
	}
	
}
