package maze;

import java.util.ArrayList;
import java.util.List;

public class Maze extends Board {
  	
	// declare an ExtendedGraph
	private ExtendedGraph extendedGraph;
	
    public Maze( int rows, int cols ) {
    	super(rows,cols);
//    	 Implement this!
    }
    
    public void create() {
    	// Reset maze
    	extendedGraph = new ExtendedGraph();
    	
    	// Use DisjointSets to create maze
    	DisjointSets disjointSet = new DisjointSets(maxCell);
    	int nrSets = maxCell;
    	
    	// Repeat for all sets
    	while (nrSets > 1) {
    		// Create a random cell
    		int cellId = (int) (Math.random() * maxCell);
    		
    		// Initialize points on the maze from the random cell
    		Point pointOne = new Point(getRow(cellId), getCol(cellId));
    		Point pointTwo = new Point(getRow(cellId), getCol(cellId));
    		
    		// Declare a direction of point
    		Point.Direction direction;
    		
    		// Decide a random direction to go
    		switch ((int) (Math.random() * 4)) {
	    		case 0: direction = Point.Direction.UP;
	    				break;
	    		case 1: direction = Point.Direction.DOWN;
	    				break;
	    		case 2: direction = Point.Direction.LEFT;
	    				break;
	    		case 3: direction = Point.Direction.RIGHT;
	    				break;
	    		default: direction = Point.Direction.ERROR;
    		}
    		
    		// Move pointTwo in direction
    		pointTwo.move(direction);
    		
    		// Check if pointTwo is valid
    		if(isValid(pointTwo)) {
    			// Get data from pointOne and pointTwo
    			int idOne = getCellId(pointOne);
    			int idTwo = getCellId(pointTwo);
    			int setOne = disjointSet.find(idOne);
    			int setTwo = disjointSet.find(idTwo);
    			
    			// Check if setOne does not equal setTwo
    			if (setOne != setTwo) {
    				// Connect points
    				disjointSet.union(setOne, setTwo);
    				
    				// Add edges
    				extendedGraph.addEdge(idOne, idTwo, 0);
    				extendedGraph.addEdge(idTwo, idOne, 0);
    				
    				// Notify observers of point and direction
    				Pair<Integer, Point.Direction> pair = new Pair<Integer, Point.Direction> (idOne, direction);
    				setChanged();
    				notifyObservers(pair);
    				
    				// Decrement number of sets to investigate
    				nrSets--;
    			}
    		}
    	}
    	
    }
    
    public void search() {
    	// Fetch shortest path through maze
    	List<Integer> shortestPath = extendedGraph.getPath(maxCell - 1);
    	
    	// Notify observers
    	for (Integer cellId : shortestPath) {
    		setChanged();
    		notifyObservers(cellId);
    	}
    }
    
//    ...
}
