package com.github.m50d.margatroid.model.ast;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grouped implements AstNode {
	public final Stream<AstNode> contents;
	
	public Grouped(Stream<AstNode> contents) {
		this.contents = contents;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.grouped(contents.map(n -> n.catamorphism(catamorphism)));
	}

	@Override
	public String prettyPrint() {
		return "[ " + contents.map(n -> n.prettyPrint()).collect(Collectors.joining(" ")) + " ]"; 
	}
}
