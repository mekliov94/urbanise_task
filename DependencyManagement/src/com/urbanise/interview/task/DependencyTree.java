package com.urbanise.interview.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DependencyTree {
	private final Map<String, Node> dependencies = new HashMap<>();
	private final Set<String> traversedNodes = new HashSet<>();

	public void transformToDependencies(List<String> listOfDependencies) {
		dependencies.clear();
		for (int j = 0; j < listOfDependencies.size(); j++) {
			String[] dependencyArr = listOfDependencies.get(j).split(",");
			String itemId = dependencyArr[0];
			Node node = new Node(itemId);
			dependencies.put(node.getId(), node);
			if (dependencyArr.length > 1) {
				for (int i = 1; i < dependencyArr.length; i++) {
					String dependencyNodeId = dependencyArr[i];
					Node dependencyNode = new Node(dependencyNodeId);
					node.getDependendNodes().add(dependencyNode);
				}
			}
		}
	}

	public Set<String> buildDependencyTree(String currentNode, Set<String> resultSet) throws CircularTreeException {
		this.setTraversedNode(currentNode);
		if (this.dependencies.get(currentNode) != null) {
			if(willCauseCircularity(dependencies.get(currentNode).getDependendNodes())) {
				throw new CircularTreeException("Circularity Fond in Three for Node with ID: " + currentNode);
			}
			this.dependencies.get(currentNode).getDependendNodes().forEach(item -> {
				resultSet.add(item.getId());
				buildDependencyTree(item.getId(), resultSet);
				traversedNodes.remove(currentNode);

			});
		}
		traversedNodes.remove(currentNode);
		return resultSet;
	}

	public Map<String, Node> getDependencyTree() {
		return dependencies;
	}

	public void setTraversedNode(String nodeId) {
		this.traversedNodes.add(nodeId);
	}

	public void clearTraversedNodes() {
		this.traversedNodes.clear();
	}

	private boolean willCauseCircularity(List<Node> dependencyNodes) {
		return dependencyNodes.stream().anyMatch(item -> this.traversedNodes.contains(item.getId()));
	}

}
