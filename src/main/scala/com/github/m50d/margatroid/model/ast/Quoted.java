package com.github.m50d.margatroid.model.ast;

import java.util.stream.Stream;

public class Quoted implements AstNode {
	public final Stream<AstNode> contents;

	public Quoted(Stream<AstNode> contents) {
		this.contents = contents;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.quoted(contents);
	}

	@Override
	public String prettyPrint() {
		return "{ " + contents.map(n -> n.prettyPrint()) + " }";
	}
}
