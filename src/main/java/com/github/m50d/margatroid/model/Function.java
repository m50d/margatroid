package com.github.m50d.margatroid.model;

import java.util.List;
import java.util.Map;

public interface Function extends Value {
	Value apply(List<Value> arguments, Map<String, Value> scope);
}
