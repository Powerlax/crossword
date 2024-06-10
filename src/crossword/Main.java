package crossword;
import crossword.CrosswordGenerator;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private static int h = 3;
	private static int w = 3;
	private static int i = 0;
	private static int k = 0;
	private static int temp = 0;
	private static boolean b = false;
	private static String attempt ="";
	private static String secretWord ="aaa";
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
	    		tutorial.setEnabled(false);
	    	
	        	
	    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
	    		
	    		JTextField selctorw = new JTextField("0");
	    		JTextField selctorh = new JTextField("0");
	    		JButton confirm = new JButton("Confirm size");
	    		
	    		selctorw.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
	    		selctorh.setBounds((int) screenSize.getWidth()/2 +50, (int) screenSize.getHeight()/2 -50, 100 ,100 );
	    		
	    		
	            // adding button in JFrame
	    		confirm.setBounds((int) screenSize.getWidth()/2-50, (int) screenSize.getHeight()/2 +50, 200 ,100 );
	    		frame.add(confirm);
	    		confirm.doClick();
	    		
	    		
	            frame.add(selctorw);
	            frame.add(selctorh);
	            confirm.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){ 
	    	        	if(Integer.parseInt(selctorw.getText())>w) {
	    	        		w = Integer.parseInt(selctorw.getText());
	    	        	}
	    	        	if(Integer.parseInt(selctorh.getText())>h) {
	    	        		h = Integer.parseInt(selctorh.getText());
	    	        	}
	    	        	confirm.setVisible(false);
	    	        	selctorw.setVisible(false);
	    	        	selctorh.setVisible(false);
	    	        	tutorial.setEnabled(true);
	    		    	
	    	        }  
	    	    });
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

	public static void girdMaker(JFrame frame) {
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
	
		ArrayList<Integer> cords = new ArrayList<Integer>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		
		JButton clear = new JButton("clear");

		
		int x = 0;
	    boolean dontask = true;
		for(i =0; i<h; i++) {
			for( k =0; k<w; k++) {
				JButton p = new JButton("a");
				if (dontask) {x += (int)screenSize.getWidth()/(w+1);}
				p.setBounds((int) screenSize.getWidth()/(w+1) *k, (int) screenSize.getHeight()/(h+1) *i, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
				frame.add(p);
				p.doClick();
				
				buttonList.add(p);
	            p.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){ 
	    	        
	    	        	cords.add(i*w+k);
	    	        	
	    	        	p.setEnabled(false);
	    	        }  
	    	    });
	            clear.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){ 
	    	        	p.setEnabled(true);
	    	        }  
	    	    });
	            
	    	}
			dontask = false;
		}
		
		clear.setBounds(x,(int) screenSize.getHeight()/(h+1), (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
		frame.add(clear);
		clear.doClick();


		
		
		
		JButton submit = new JButton("check word");
		submit.setBounds(x,0, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
		frame.add(submit);
		submit.doClick();
		
		submit.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){ 
	        	for(int a =1; a<cords.size(); a++) {
	        		if(cords.get(a) > cords.get(a+1)) {
	        			 temp = cords.get(a);
	        			 cords.set(a, a+1);
	        			 cords.set(a+1, temp);
	        			 
	        		}
	        	}
	        	for(int a =0; a<cords.size(); a++) {
	        		attempt+= buttonList.get(cords.get(a));

	        	}
	        	
        		if(secretWord ==attempt) {
        			for(int c = 0; c<cords.size(); c++) {
        				
        			}
        		}
	        	
	        }  
	    }); 
		}
	

	}

