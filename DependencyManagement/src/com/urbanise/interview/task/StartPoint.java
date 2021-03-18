package com.urbanise.interview.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartPoint {
	public static void main(String[] args) {
		try {
			List<String> start = readDependencyInformation(args[0]);
			DependencyTree tree = new DependencyTree();
			tree.transformToDependencies(start);

			for (Entry<String, Node> entry : tree.getDependencyTree().entrySet()) {
				Set<String> set = new HashSet<>();
				tree.buildDependencyTree(entry.getKey(), set);
				Logger.getLogger(StartPoint.class.getSimpleName()).log(Level.INFO,
						"Root with ID: " + "[" + entry.getKey().toUpperCase() + "]" + " has following dependencies : " + set.toString());
			}
		}catch (IOException e) {
			Logger.getLogger(StartPoint.class.getSimpleName()).log(Level.SEVERE, "Such file probbably do not exist, please re-check file location. ");
			e.printStackTrace();
		}
		
	}
	
	private static List<String> readDependencyInformation(String filePath) throws IOException{
		Path path = Paths.get(filePath);
		if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			List<String> result = Files.readAllLines(path);
			return result;
		}
		return new ArrayList<>();
	}

}
