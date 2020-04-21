package com.github.m50d.margatroid.model;

import java.util.List;

import com.github.m50d.margatroid.ast.Node;

public interface Function {
	Node apply(List<Node> arguments);
}
