package com.github.m50d.margatroid.ast;

import java.util.Objects;

import com.github.m50d.margatroid.model.Value;

public final class Literal implements Node, Value {
	public final String value;

	public Literal(String value) {
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
		Literal other = (Literal) obj;
		return Objects.equals(value, other.value);
	}
}
