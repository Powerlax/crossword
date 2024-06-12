package crossword;
import crossword.CrosswordGenerator;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private static int h = 4;
	private static int w = 4;
	private static int i = 0;
	private static int k = 0;
	private static int temp = 0;
	private static boolean b = false;
	private static boolean solved = false;
	private static int Score = 0;
	private static String won ="";
	
	private static String attempt ="";
	private static String secretWord ="notEmpty";
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
	    		JButton confirm = new JButton("Confirm size Min: (4,4)");
	    		
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
		Crossword c= CrosswordGenerator.generate(h, w);
	
		
		
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
	
		ArrayList<Integer> cords = new ArrayList<Integer>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		
		
		
		
		int x = 0;
	    boolean dontask = true;
		for(i =0; i<h; i++) {
			for( k =0; k<w; k++) {
				JButton p = new JButton(Character.toString(c.getCrossword()[i][k]));
				
				if (dontask) {x += (int)screenSize.getWidth()/(w+1);}
				p.setBounds((int) screenSize.getWidth()/(w+1) *k, (int) screenSize.getHeight()/(h+1) *i, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
				frame.add(p);
				p.doClick();
				
				buttonList.add(p);
	            p.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){   	        
	    	        	cords.add(buttonList.indexOf(e.getSource()));	    	        	
	    	        	p.setEnabled(false);
	    	        }  
	    	    });

	            
	    	}
			dontask = false;
		}
		


		
		
		
		JButton submit = new JButton("check word");
		submit.setBounds(x,0, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
		frame.add(submit);
		submit.doClick();
		JButton score = new JButton("Score :" + Score);
		score.setBounds(x,(int) screenSize.getHeight()/(h+1)*1, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
		frame.add(score);
		score.doClick();
		String str = "";
		int split = 40;
		for(int word =0; word<c.getWordList().length; word++) {
			str+=c.getWordList()[word] ;
			if(str.length() >split) {
			
				str = str.substring(0, split)+ "\n" +str.substring(split,str.length());
				split+=40;
			}
			str+= " ";
		}

		JButton words = new JButton("<html>" + str.replaceAll("\\n", "<br>") + "</html>");
		words.setBounds(x,(int) screenSize.getHeight()/(h+1)*2, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1)*(h-2) );
		frame.add(words);
		words.doClick();
		words.setEnabled(false);
		
		
		submit.addActionListener(new ActionListener(){ 
	         public  void actionPerformed(ActionEvent e){
	        	boolean pass = true;
	        	solved = false;
	        	attempt ="";
	        	cords.sort(null);
	        	if(cords.size() < 2) {
	        		return;
	        	}
	        	int row = cords.get(0)/w;
	        	int col = cords.get(0)%w;
	        	int row1 = cords.get(1)/w;
	        	int col1 = cords.get(1)%w;
	        	if(row == row1 && col1 == col+1) {
		        	for(int temp =2; temp<cords.size(); temp++) {
		        		 row1 = cords.get(temp)/w;
			        	 col1 = cords.get(temp)%w;
			        	 if(row != row1 || col1 != col+temp) {
			        		 pass = false;
			        		 break;
			        	 }
		        	}
	        	}
	        	else if(row1 == row+1 && col1 == col){
		        	for(int temp =2; temp<cords.size(); temp++) {
		        		 row1 = cords.get(temp)/w;
			        	 col1 = cords.get(temp)%w;
			        	 if(row1 != row+temp || col1 != col) {
			        		 pass = false;
			        		 break;
			        	 }
		        	}
	        		
	        	}
	        	else {
	        		 pass = false;
	        		
	        	}
	        	

	        	
	        	if(pass ==true) {
	        		
		        	for(int a =0; a<cords.size(); a++) {
		        		attempt+= buttonList.get(cords.get(a)).getText();
	
		        	}
		        	
		        	for(int len =0; len<c.getWordList().length; len++) {
		        		secretWord=c.getWordList()[len];
		        		
		        		if(secretWord.equals(attempt)) {
		        			won +=secretWord;
		        			cords.clear();
		        			solved = true;
		        			Score += 50*attempt.length();
		        			score.setText("Score :" + Score);
		        			if(Score == won.length()*50) {
		        				
		        			}
		        			break;
		        		}
		        	}
	        	}
	        	if(solved == false) {
	        		for(int len =0; len<cords.size();len++) {
	        			buttonList.get(cords.get(len)).setEnabled(true);
	        		}
	        		cords.clear();
	        	}       	
	        }  
	    }); 
		}
	

	}

