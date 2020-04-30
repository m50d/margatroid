package com.github.m50d.margatroid.builtins;

import java.util.Optional;

import com.github.m50d.margatroid.model.Scope;

public class DefaultScope extends Scope {
	public DefaultScope() {
		super(Optional.empty());
		contents.put("+", new Plus());
		contents.put("eval", new Eval());
		contents.put("proc", new Proc());
	}

}
