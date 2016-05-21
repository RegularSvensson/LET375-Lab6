package observerExample;

 

import observerExample.view.*;
import observerExample.model.*;

/**
 * This class contains the data handling part of the program
 * 
 * @author Uno Holmer 
 * @version 2007-05-08
 */
public class Main {
	
	public static void main(String[] args) {
        new UserInterface( new PrimeGenerator(), new FibonacciGenerator() );
	}
	
}
