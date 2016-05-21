package maze;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ExtendedGraph extends Graph {
	
	/**
	 * Returns a list of integers that represents the
	 * shortest path to a destination.
	 * @param destinationName
	 * @return
	 */
	public List<Integer> getPath(int destinationName) {
		// Call unweighted function
		unweighted(0);
		
		// Create vertex for destinationName
		Vertex vertex = vertexMap.get(destinationName);
		
		// Check if vertex does not exists
		if (vertex == null)
			// Throw exception
			throw new NoSuchElementException("No destination vertex found");
		
		// Create new lists
		List<Integer> list = new ArrayList<Integer>();
		
		// Return call to private getPath() to get shortest path as a list
		return getPath(vertex, list);
	}
	
	/**
	 * Returns a list of integers that represents the
	 * shortest path to a destination.
	 * @param destination 
	 * @param list
	 * @return
	 */
	private List<Integer> getPath(Vertex destination, List<Integer> list) {
		// Check if destination does not exists, base case
		if (destination == null)
			// Return the list
			return list;
		
		// Add destination name to list
		list.add(destination.name);
		
		// Return recursive call to getPath() towards base case
		return getPath(destination.prev, list);	
	}
	
	/**
	 * Adds edges to maze 
	 * @param sourceName
	 * @param destinationName
	 */
	public void addEdge(int sourceName, int destinationName) {
		addEdge(sourceName, destinationName, 0);
		addEdge(destinationName, sourceName, 0);
	}
}
