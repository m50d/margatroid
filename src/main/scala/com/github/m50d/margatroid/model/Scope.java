package com.github.m50d.margatroid.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scope implements Value {
	public final Optional<Scope> parent;
	public final Map<String, Value> contents = new HashMap<>();

	public Scope(Optional<Scope> parent) {
		this.parent = parent;
	}

	public Value get(String key) {
		return contents.containsKey(key) ? contents.get(key) : parent.isPresent() ? parent.get().get(key) : new Void();
	}
	
	public void put(String key, Value value) {
		contents.put(key, value);
	}

	@Override
	public String prettyPrint() {
		return "(scope)";
	}
}
