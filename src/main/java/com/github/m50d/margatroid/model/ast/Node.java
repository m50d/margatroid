package com.github.m50d.margatroid.model.ast;

import java.util.stream.Stream;

import com.github.m50d.margatroid.model.Value;

public interface Node extends Value {
	interface Catamorphism<T> {
		T grouped(Stream<T> contents);
		T quoted(Stream<Node> contents);
		T literal(String value);
		T reference(String value);
	}
	<T> T catamorphism(Catamorphism<T> catamorphism);
	String prettyPrint();
}
