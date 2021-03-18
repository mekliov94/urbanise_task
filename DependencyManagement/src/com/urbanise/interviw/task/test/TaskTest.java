package com.urbanise.interviw.task.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.urbanise.interview.task.CircularTreeException;
import com.urbanise.interview.task.DependencyTree;

class TaskTest {

	private static List<String> mock = new ArrayList<String>();
	private static Set<String> mockResult = new HashSet<String>();
	private static List<String> mockCircularity = new ArrayList<String>();

	@BeforeAll
	static void initializeMock() {
		mock.add("a,b,c");
		mock.add("b,c,e");
		mock.add("c,g");
		mock.add("d,a,f");
		mock.add("e,f");
		mock.add("f,h");
		
		mockResult.add("b");
		mockResult.add("c");
		mockResult.add("g");
		mockResult.add("e");
		mockResult.add("f");
		mockResult.add("h");
		
		mockCircularity.add("a,b,c");
		mockCircularity.add("b,c,e");
		mockCircularity.add("c,g,a");
		mockCircularity.add("d,a,f");
		mockCircularity.add("e,f");
		mockCircularity.add("f,h");
	}

	@Test
	void test() {
		DependencyTree tree = new DependencyTree();
		tree.transformToDependencies(mock);
		Set<String> resultSet = tree.buildDependencyTree("a", new LinkedHashSet<>());
		
		Assertions.assertTrue(resultSet.size() == 6);
		Assertions.assertTrue(mockResult.containsAll(resultSet));
	}
	
	@Test
	void testCircularity() {
		DependencyTree tree = new DependencyTree();
		tree.transformToDependencies(mockCircularity);
		
		Assertions.assertThrows(CircularTreeException.class, () -> {
			tree.buildDependencyTree("a", new LinkedHashSet<>());
		});
	}

}
