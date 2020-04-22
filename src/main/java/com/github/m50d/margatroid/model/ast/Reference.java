package com.github.m50d.margatroid.ast;

public class Reference implements Node {
	public final String value;

	public Reference(String value) {
		this.value = value;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.reference(value);
	}

	@Override
	public String prettyPrint() {
		return "$" + value;
	}
}
