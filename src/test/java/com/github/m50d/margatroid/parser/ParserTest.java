package com.github.m50d.margatroid.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.m50d.margatroid.model.ast.Grouped;
import com.github.m50d.margatroid.model.ast.LiteralNode;
import com.github.m50d.margatroid.model.ast.AstNode;

public class ParserTest {
	Parser parser = new Parser();
	
	private List<AstNode> roundtripTest(String input) {
		List<AstNode> parseResult = parser.parse(input).collect(Collectors.toList());
		assertEquals(input, parseResult.stream().map(pr -> pr.prettyPrint()).collect(Collectors.joining(" ")));
		return parseResult;
	}

	@Test
	public void basicFunctionality() {
		List<AstNode> parseResult = roundtripTest("1 2 3");
		assertEquals(Arrays.asList(new LiteralNode("1"), new LiteralNode("2"), new LiteralNode("3")), parseResult);
		parseResult = roundtripTest("[ 1 2 3 ]");
		assertEquals(1, parseResult.size());
		assert(parseResult.get(0) instanceof Grouped);
	}

	@Test
	public void nested() {
		roundtripTest("[ [ 1 ] ]");
	}

}
