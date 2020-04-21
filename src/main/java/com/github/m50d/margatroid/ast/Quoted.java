package com.github.m50d.margatroid.ast;

import java.util.stream.Stream;

public class Quoted implements Node {
	public final Stream<Node> contents;

	public Quoted(Stream<Node> contents) {
		this.contents = contents;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.quoted(contents);
	}
}
