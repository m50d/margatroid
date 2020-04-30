package com.github.m50d.margatroid;

import java.util.List;
import java.util.stream.Collectors;

import com.github.m50d.margatroid.builtins.DefaultScope;
import com.github.m50d.margatroid.builtins.Eval;
import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.AstNode;
import com.github.m50d.margatroid.parser.Parser;

public class Margatroid {
	public Value run(String input) {
		List<AstNode> ast = new Parser().parse(input).collect(Collectors.toList());
		return new Eval().apply(ast, new DefaultScope());
	}
}
