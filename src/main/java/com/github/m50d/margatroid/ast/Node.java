package com.github.m50d.margatroid.ast;

import java.util.stream.Stream;

public interface Node {
	interface Catamorphism<T> {
		T grouped(Stream<T> contents);
		T quoted(Stream<Node> contents);
		T literal(String value);
	}
	<T> T catamorphism(Catamorphism<T> catamorphism);
}
