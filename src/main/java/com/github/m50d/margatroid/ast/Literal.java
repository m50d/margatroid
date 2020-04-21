package com.github.m50d.margatroid.ast;

public class Literal implements Node {
	public final String value;

	public Literal(String value) {
		this.value = value;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.literal(value);
	}
}
