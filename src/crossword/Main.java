package crossword;
import crossword.CrosswordGenerator;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private static int h;
	private static int w;
	public static void main(String[] args) {
		// Creating instance of JFrame
        JFrame frame = new JFrame();
        
        // Gets screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        
 
        // Creating instance of JButton
        JButton button = new JButton("Start");
        
        // x axis, y axis, width, height, centering
        button.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
        
        // adding button in JFrame
        frame.add(button);
 
        // 400 width and 500 height
        frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
 
        // using no layout managers
        frame.setLayout(null);
 
        // making the frame visible
        frame.setVisible(true);
        
        //Make Start button start the game
        button.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){  
	        	button.setVisible(false);
	        	// selection menu
	        	JButton tutorial = new JButton("Numbers only");
	        	tutorial.setBounds((int) screenSize.getWidth()/2-50, (int) screenSize.getHeight()/2 -200, 200 ,100 );
	    		frame.add(tutorial);
	    		tutorial.doClick();
	    		tutorial.setEnabled(false);;
	        	sizeMenu(frame);
	     

	        }  
	    });  
        
        
	}
	public static void sizeMenu(JFrame frame) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
		
		JTextField selctorw = new JTextField("0");
		JTextField selctorh = new JTextField("0");
		
		selctorw.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
		selctorh.setBounds((int) screenSize.getWidth()/2 +50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
		
        // adding button in JFrame

        frame.add(selctorw);
        selctorw.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){  	
	        	w = Integer.parseInt(selctorw.getText());
	        }  
	    });
        frame.add(selctorh);
        selctorh.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){  	
	        	h = Integer.parseInt(selctorh.getText());
	        }  
	    });
	}
	public static void girdMaker() {}
}
