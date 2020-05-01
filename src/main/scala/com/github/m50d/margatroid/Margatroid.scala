package com.github.m50d.margatroid

import com.github.m50d.margatroid.parser.Parser
import com.github.m50d.margatroid.builtins._

class Margatroid {
  def run(input: String) = {
		val ast = new Parser().parse(input)
		eval(ast, prelude)
	}
}