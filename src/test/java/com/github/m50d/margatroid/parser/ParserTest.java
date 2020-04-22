package com.github.m50d.margatroid.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.m50d.margatroid.ast.Grouped;
import com.github.m50d.margatroid.ast.Literal;
import com.github.m50d.margatroid.ast.Node;

public class ParserTest {
	Parser parser = new Parser();
	
	private List<Node> roundtripTest(String input) {
		List<Node> parseResult = parser.parse(input).collect(Collectors.toList());
		assertEquals(input, parseResult.stream().map(pr -> pr.prettyPrint()).collect(Collectors.joining(" ")));
		return parseResult;
	}

	@Test
	public void basicFunctionality() {
		List<Node> parseResult = roundtripTest("1 2 3");
		assertEquals(Arrays.asList(new Literal("1"), new Literal("2"), new Literal("3")), parseResult);
		parseResult = roundtripTest("[ 1 2 3 ]");
		assertEquals(1, parseResult.size());
		assert(parseResult.get(0) instanceof Grouped);
	}

	@Test
	public void nested() {
//		roundtripTest("[ [ ] ]");
	}

}
