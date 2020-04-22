package com.github.m50d.margatroid;

import static org.junit.Assert.*;
import org.junit.Test;

import com.github.m50d.margatroid.model.Value;
import com.github.m50d.margatroid.model.ast.LiteralNode;

public class MargatroidTest {
	Margatroid interpreter = new Margatroid();

	@Test
	public void interpreter() {
		Value result = interpreter.run("[ + 1 2 3 ]");
		assertEquals(new LiteralNode("6"), result);
	}

}
