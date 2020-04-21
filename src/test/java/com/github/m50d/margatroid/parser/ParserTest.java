package com.github.m50d.margatroid.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;

public class ParserTest {
	Parser parser = new Parser();

	@Test
	public void basicFunctionality() {
		List<Node> parseResult = parser.parse("1 2 3").collect(Collectors.toList());
		assertEquals(Arrays.asList(new Literal("1"), new Literal("2"), new Literal("3")), parseResult);
	}

}
