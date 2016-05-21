package observerExample.model;

/**
 * Genereates the next Fibonacci number: 1, 1, 2, 3, 5, 8, 13,... 
 * 
 * @author Uno Holmer
 * @version 2009-05-06
 */
public class FibonacciGenerator extends NumberGenerator 
{
    private long currentFib, nextFib;

    /**
     * Constructor for objects of class PrimeGenerator
     */
    public FibonacciGenerator()
    {
        reset();
    }
    
   /**
    * Compute the next Fibonacci number
    */
    public void computeNext()
    {
        nextFib += currentFib;
        currentFib = nextFib - currentFib;
        
        // These two methods are inherited from class Observable
        setChanged();                  // The code above definitely changed my state
        notifyObservers(currentFib);   // so tell every interested observing object about it.
    }
    
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        currentFib = 1;
        nextFib = 1; 
        // These two methods are inherited from class Observable
        setChanged();                // The loop above definitely changed my state,
        notifyObservers(currentFib); // so tell every interested observing object about it.
    }
    
    //Return the current Fibonacci number   
    public long getValue()
    {
        return currentFib;
    }
}
