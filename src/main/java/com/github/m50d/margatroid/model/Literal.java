package com.github.m50d.margatroid.model;

import java.util.Objects;

import com.github.m50d.margatroid.model.ast.LiteralNode;

public class Literal implements Value {
	public final String value;

	public Literal(String value) {
		this.value = value;
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