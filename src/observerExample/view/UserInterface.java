package observerExample.view;

import observerExample.model.*;
import observerExample.model.PrimeGenerator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A GUI to the number generator classes
 * 
 * @author Uno Holmer 
 * @version 2006-01-25
 */

public class UserInterface 
{
    private JFrame frame;

    /**
     * Constructor for objects of class UserInterface
     */
    public UserInterface(PrimeGenerator primeGen,FibonacciGenerator fibGen)
    {
        frame = new JFrame("Number generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,2,10,10));
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        // A number pane is a push button and a associated output label
        contentPane.add(new NumberPane("Next prime number",primeGen));
        contentPane.add(new NumberPane("Next Fibonacci number",fibGen));
        primeGen.reset();
        fibGen.reset();
        
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}
