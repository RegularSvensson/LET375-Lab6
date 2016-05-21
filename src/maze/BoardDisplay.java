package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.*;
import java.util.Observable;
import java.util.Observer;

public class BoardDisplay extends Board implements Observer {
	
	private int gridSize, rowOffset, colOffset;
	private Color bkColor, lineColor, pathColor;
	private Canvas canvas;
	
	public BoardDisplay(Canvas canvas, int maxRow, int maxCol) {
		super(maxRow, maxCol);
		this.canvas = canvas;
		bkColor = Color.lightGray; 
		lineColor = Color.blue; 
		pathColor = Color.red;
		Dimension d = canvas.getSize();
		int width = d.width - 4;
		int height = d.height - 5;
		int gdRow = height/maxRow, gdCol = width/maxCol;
		gridSize = ( gdRow < gdCol ? gdRow : gdCol );
		// compute offset to center it
		rowOffset = (height-maxRow*gridSize)/2 + 2;
		colOffset = (width-maxCol*gridSize)/2 + 2;
		// Initialize display
		drawGrid();
		knockDownWall(0, Point.Direction.LEFT);
		knockDownWall(maxCell - 1, Point.Direction.RIGHT);
	}
	
	private void drawGrid() {
		for ( int row = 0; row <= maxRow; row++ )
			myLine( 0, row*gridSize, maxCol*gridSize, row*gridSize, lineColor );
		for ( int col = 0; col <= maxCol; col++ )
			myLine( col*gridSize, 0, col*gridSize, maxRow*gridSize, lineColor );
		
	}
	
	private void fillCell( int cellId ) {
		int row = getRow(cellId), col = getCol(cellId);
		canvas.setForegroundColour(pathColor);
		if ( gridSize > 4 )
		    canvas.fill( new Ellipse2D.Double( colOffset + col*gridSize + 1, rowOffset + row*gridSize + 1, 
				                               gridSize-1, gridSize-1 ) );
		else
		    canvas.fill( new Rectangle2D.Double( colOffset + col*gridSize + 1, rowOffset + row*gridSize + 1, 
                                                 gridSize-1, gridSize-1 ) );
	}
	
	// Knock down the wall of cell cellId in direction dir
	private void knockDownWall( int cellId, Point.Direction dir ) {
		knockDownWall( getRow( cellId ), getCol( cellId ), dir );
	}
	
	private void knockDownWall( int row, int col, Point.Direction dir ) {
		// Compute coordinates of line segment to remove
		int c0 = 0, r0 = 0, c1 = 0, r1 = 0;
		if ( dir == Point.Direction.UP ) {
			c0 = col*gridSize + 1;
			c1 = (col + 1)*gridSize - 1;
			r0 = r1 = row*gridSize;
		} else if ( dir == Point.Direction.RIGHT ) {
			r0 = row*gridSize + 1;
			r1 = (row + 1)*gridSize - 1;
			c0 = c1 = (col + 1)*gridSize;
		} else if ( dir == Point.Direction.DOWN ) {	
			c0 = col*gridSize + 1;
			c1 = (col + 1)*gridSize - 1;
			r0 = r1 = (row + 1)*gridSize;
		} else if ( dir == Point.Direction.LEFT ) {
			r0 = row*gridSize + 1;
			r1 = (row + 1)*gridSize - 1;
			c0 = c1 = col*gridSize;
		}
		// Erase the line
		myLine( c0, r0, c1, r1, bkColor );
	}
	
	private void myLine( int c1, int r1, int c2, int r2, Color c )  {
		canvas.setForegroundColour(c);
		canvas.drawLine( colOffset + c1, rowOffset + r1, colOffset + c2, rowOffset + r2 );
	}
	    
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		// Check if arg is instance of Pair
		if(arg instanceof Pair) {
			// Knock down wall
			Pair<Integer, Point.Direction> pair = (Pair<Integer, Point.Direction>) arg;
			knockDownWall(pair.first, pair.second);
		}
		// Else, check if arg is instance of Integer
		else if (arg instanceof Integer)
			// Fill cell
			fillCell((Integer) arg);
	}
}
