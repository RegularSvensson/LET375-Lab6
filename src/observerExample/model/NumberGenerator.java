package observerExample.model;

import java.util.Observable;

/**
 * Abstract interface of number generators
 * 
 * @author Uno Holmer
 * @version 2006-01-25
 */

public abstract class NumberGenerator extends Observable
{
	public abstract void computeNext();
	public abstract long getValue();
	public abstract void reset();
}
