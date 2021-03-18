package com.urbanise.interview.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
	private final String id;
	private final List<String> dependendServices = new ArrayList<String>();
	private final List<Node> dependendNodes = new ArrayList<Node>();
	private final Set<String> parents = new HashSet<String>();
	
	public List<Node> getDependendNodes() {
		return dependendNodes;
	}

	public Node(String id) {
		this.id = id;
	}

	public List<String> getDependendServices() {
		return dependendServices;
	}

	public Set<String> getParents() {
		return parents;
	}

	public void setParents(String parent) {
		this.parents.add(parent);
	}

	public String getId() {
		return id;
	}
	
}
