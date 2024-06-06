package crossword;
import crossword.CrosswordGenerator;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private static int h = 2;
	private static int w = 2;
	public static void main(String[] args) {
		// Creating instance of JFrame
        JFrame frame = new JFrame("Word Serch");
        
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
	        	JButton tutorial = new JButton("Numbers only click when done");
	        	tutorial.setBounds((int) screenSize.getWidth()/2-75, (int) screenSize.getHeight()/2 -200, 250 ,100 );
	    		frame.add(tutorial);
	    		tutorial.doClick();
	    	
	        	sizeMenu(frame);
	            // makes the grid
	        	tutorial.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){
	    	        	tutorial.setVisible(false);
	            girdMaker(frame);
	     

	        }  
	    });  


        
	        }  
	    }); 
        
	}
	public static void sizeMenu(JFrame frame) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
		
		JTextField selctorw = new JTextField("0");
		JTextField selctorh = new JTextField("0");
		JButton confirm = new JButton("Confirm size");
		
		selctorw.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
		selctorh.setBounds((int) screenSize.getWidth()/2 +50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
		
		
        // adding button in JFrame
		confirm.setBounds((int) screenSize.getWidth()/2-50, (int) screenSize.getHeight()/2 +50, 200 ,100 );
		confirm.doClick();
		
		frame.add(confirm);
        frame.add(selctorw);
        frame.add(selctorh);
        confirm.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){  	
	        	w = Integer.parseInt(selctorw.getText());
	        	h = Integer.parseInt(selctorh.getText());
	        	confirm.setVisible(false);
	        	selctorw.setVisible(false);
	        	selctorh.setVisible(false);
	        }  
	    });
        
       	


	}
	public static void girdMaker(JFrame frame) {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		for(int i =0; i<h; i++) {
			for(int k =0; k<w; k++) {
				JButton p = new JButton("a");
				p.setBounds((int) screenSize.getWidth()/w *k, (int) screenSize.getHeight()/h *i, (int)screenSize.getWidth()/w ,(int) screenSize.getHeight()/h );
				frame.add(p);
				p.doClick();
				p.setEnabled(false);
				buttonList.add(p);
				
			}
		}

	}
}
