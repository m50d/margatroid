package com.github.m50d.margatroid.ast;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grouped implements Node {
	public final Stream<Node> contents;
	
	public Grouped(Stream<Node> contents) {
		this.contents = contents;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.grouped(contents.map(n -> n.catamorphism(catamorphism)));
	}

	@Override
	public String prettyPrint() {
		return "[" + contents.map(n -> n.prettyPrint()).collect(Collectors.joining(" ")) + "]"; 
	}
}
