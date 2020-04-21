package com.github.m50d.margatroid;

import static org.junit.Assert.*;
import org.junit.Test;

import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;

public class MargatroidTest {
	Margatroid interpreter = new Margatroid();

	@Test
	public void interpreter() {
		Node result = interpreter.run("[ + 1 2 3 ]");
		assertEquals(new Literal("6"), result);
	}

}
