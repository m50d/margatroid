package com.github.m50d.margatroid.model.ast;

import java.util.Objects;

public final class LiteralNode implements AstNode {
	public final String value;

	public LiteralNode(String value) {
		this.value = value;
	}

	@Override
	public <T> T catamorphism(Catamorphism<T> catamorphism) {
		return catamorphism.literal(value);
	}

	@Override
	public String prettyPrint() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LiteralNode other = (LiteralNode) obj;
		return Objects.equals(value, other.value);
	}
}
