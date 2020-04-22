package com.github.m50d.margatroid.model;

import java.util.List;

public interface Function extends Value {
	Value apply(List<Value> arguments, Scope scope);
}
