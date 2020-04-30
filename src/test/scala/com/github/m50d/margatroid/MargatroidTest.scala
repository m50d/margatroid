package com.github.m50d.margatroid

import org.junit.Test
import org.junit.Assert._
import com.github.m50d.margatroid.model.ast.Literal

class MargatroidTest {
	val interpreter = new Margatroid()

	@Test
	def basicFunctionality() = {
		val result = interpreter.run("[ + 1 2 3 ]")
		assertEquals(new Literal("6"), result)
	}
	
	@Test
	def function() = {
		val result = interpreter.run("[ proc plus2 { x } { [ + 2 $x ] } ] [ plus2 3 ]")
		assertEquals(new Literal("5"), result)
	}
}