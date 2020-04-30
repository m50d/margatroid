package com.github.m50d.margatroid.model;

import java.util.List;

public interface Function extends Value {
	Value apply(List<? extends Value> arguments, Scope scope);
	@Override
	default String prettyPrint() {
		return "(function)";
	}
}
