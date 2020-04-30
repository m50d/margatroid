package com.github.m50d.margatroid

import com.github.m50d.margatroid.parser.Parser
import java.util.stream.Collectors
import com.github.m50d.margatroid.builtins.DefaultScope
import com.github.m50d.margatroid.builtins.Eval

class Margatroid {
  def run(input: String) = {
		val ast = new Parser().parse(input).collect(Collectors.toList())
		new Eval().apply(ast, new DefaultScope())
	}
}