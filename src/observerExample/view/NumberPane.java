package observerExample.view;

import observerExample.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
/**
 * A GUI to the number generator classes
 * @author Uno Holmer 
 * @version 2009-05-06
 */
public class NumberPane extends JPanel implements Observer
{ 
    private TextField outputField;
    
    public NumberPane(String buttonText,final NumberGenerator generator)
    {
        generator.addObserver(this);  // I want to be notified when the generator object changes it's state
                                      // addObserver is inherited from class Observable    
        setLayout(new GridLayout(3,1,10,10));
        
        JButton nextButton = new JButton(buttonText);
        nextButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) { generator.computeNext(); }
        });
        add(nextButton);
        
        outputField = new TextField();
        outputField.setEditable(false);
        outputField.setBackground(Color.WHITE);
        add(outputField);
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) { generator.reset(); }
        });
        add(resetButton);
    }  
    
    // Observer's update method - called when the observed object calls notifyObservers()
    public void update(Observable obj,Object o)
    {   
        if ( obj instanceof NumberGenerator && o instanceof Long ) {
            Long value = (Long)o;
            outputField.setText(value.toString());   
        }
    }
}