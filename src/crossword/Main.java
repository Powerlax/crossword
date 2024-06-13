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
	private static String str = "";
	private static ArrayList<Color> og = new ArrayList<Color>();
	private static ArrayList<String> wordList  = new ArrayList<String>() ;
	private int score_multiplier = 1;
	public static String playerName;
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
	    // Creating instance of JButton
	    
	    JTextField name = new JTextField("Name");
	    
	    // x axis, y axis, width, height, centering
	    name.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 +50, 100 ,100 );
	    
	    // adding button in JFrame
	    frame.add(name);
	    
	    
	    
	    button.addActionListener(new ActionListener(){ 
	        public void actionPerformed(ActionEvent e){ 
	        	playerName = name.getText();
	        	name.setVisible(false);
	        	button.setVisible(false);
	        	// selection menu
	        	JLabel tutorial = new JLabel("numbers only");
	        	tutorial.setBounds((int) screenSize.getWidth()/2, (int) screenSize.getHeight()/2-120 , 250 ,100 );
	    		frame.add(tutorial);
	    		
	    		tutorial.setEnabled(false);
	    	
	        	
	    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
	    		
	    		JTextField selctorw = new JTextField("0");
	    		JTextField selctorh = new JTextField("0");
	    		JButton confirm = new JButton("Confirm size Min: 4");
	    		
	    		selctorw.setBounds((int) screenSize.getWidth()/2 -50, (int) screenSize.getHeight()/2 -50, 200 ,100 );
	    		
	    		
	    		
	            // adding button in JFrame
	    		confirm.setBounds((int) screenSize.getWidth()/2-50, (int) screenSize.getHeight()/2 +50, 200 ,100 );
	    		frame.add(confirm);
	    		confirm.doClick();
	    		
	    		
	            frame.add(selctorw);
	            frame.add(selctorh);

	            // makes the grid
	            confirm.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){
	    	        	confirm.setVisible(false);
	    	        	if(Integer.parseInt(selctorw.getText())>w) {
	    	        		w = Integer.parseInt(selctorw.getText());
	    	        	}
	    	        	h=w;
	    	        	
	    	        	selctorw.setVisible(false);
	    	        	selctorh.setVisible(false);
	    	        	
	    	        	confirm.setVisible(false);
	    	        	tutorial.setVisible(false);
	    	        	girdMaker(frame);
	     

	        }  
	    });  


	    
	        }  
	    }); 
	    
	}

	private static void girdMaker(JFrame frame) {
		Crossword c= CrosswordGenerator.generate(w, h);

		
		
		
		ArrayList<JButton> buttonList = new ArrayList<JButton>();

		ArrayList<Integer> cords = new ArrayList<Integer>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		
		
		
		
		int x = 0;
	    boolean dontask = true;
		for(i =0; i<h; i++) {
			for( k =0; k<w; k++) {
				JButton p = new JButton(Character.toString(c.getCrossword()[i][k]));
				p.setBackground(new Color(255,255,255));
				
				if (dontask) {x += (int)screenSize.getWidth()/(w+1);}
				p.setBounds((int) screenSize.getWidth()/(w+1) *k, (int) screenSize.getHeight()/(h+1) *i, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
				frame.add(p);
				p.doClick();
				
				buttonList.add(p);
				og.add(new Color(255,255,255));
	            p.addActionListener(new ActionListener(){ 
	    	        public void actionPerformed(ActionEvent e){
	    	        	p.setBackground(new Color(220,220,220));
	    	        	
	    	        	
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
		submit.setBackground(new Color(255,255,255));
		JButton score = new JButton("Score :" + Score);
		score.setBounds(x,(int) screenSize.getHeight()/(h+1)*1, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1) );
		frame.add(score);
		score.doClick();
		score.setBackground(new Color(255,255,255));
		
		
		
		for(int word =0; word<c.getWordList().length; word++) {
			str+=c.getWordList()[word] ;

			str+= "\n ";
		}

		JButton words = new JButton("<html>" + str.replaceAll("\\n", "<br>") + "</html>");
		words.setBounds(x,(int) screenSize.getHeight()/(h+1)*2, (int)screenSize.getWidth()/(w+1) ,(int) screenSize.getHeight()/(h+1)*(h-2) );
		frame.add(words);
		words.doClick();
		words.setBackground(new Color(255,255,255));
		
		for(int l =0; l<c.getWordList().length; l++) {
			wordList.add(c.getWordList()[l]);
		}
	    ActionListener taskPerformer = e -> {
	        // Your task code here (e.g., updating a component)
	    	Score -= 1;
	    	score.setText("Score :" + Score);
	    };
		Timer timer = new Timer(1000, taskPerformer);
	    timer.setRepeats(true); // Set to false if you want it to fire only once
	    timer.start();
		submit.addActionListener(new ActionListener(){ 
	         public  void actionPerformed(ActionEvent e){
	        
	        	boolean pass = true;
	        	solved = false;
	        	attempt ="";
	        	cords.sort(null);
	        	if(cords.size() == 0) {
	        		return;
	        	}
	        	if(cords.size() > 1) {
	        		
	        	
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
			        	
			        	for(int len =0; len<wordList.size(); len++) {
			        		secretWord=wordList.get(len);
			        		
			        		if(secretWord.equals(attempt)) {
			        			wordList.remove(len);
			        			
			        			solved = true;
			        			Score += 50*attempt.length();
			        			score.setText("Score :" + Score);
			        			str =str.substring(0, str.indexOf(secretWord)) + str.substring(str.indexOf(secretWord)+secretWord.length());
			        			words.setText("<html>" + str.replaceAll("\\n", "<br>") + "</html>");
			        			if(wordList.size() == 0) {
			        				timer.stop();
			        				HighScoreManager h = new HighScoreManager();
			        				h.saveHighScore(playerName, Score);
			        				h.loadHighScores();
			        				
			        				words.setText("<html>" + ("You win! High score:" + h.getHighestScore()+"\n"+"Your score: "+Score).replaceAll("\\n", "<br>") + "</html>");
			        			}
				        	 	for(int l =0; l<cords.size();l++) {
				        			buttonList.get(cords.get(l)).setEnabled(true);
				        			buttonList.get(cords.get(l)).setBackground(new Color(0, 255, 0));
				        			og.set(cords.get(l),new Color(0,255,0));
				        		}
				        		cords.clear();
			        			break;
			        		}
			        	
			        	}
		        	}
	        	}
	            for(int l =0; l<cords.size();l++) {
	        		 buttonList.get(cords.get(l)).setBackground(og.get(cords.get(l)));
	        		 
	        	}
	        	 	for(int len =0; len<cords.size();len++) {
	        			buttonList.get(cords.get(len)).setEnabled(true);
	        		}
	        		cords.clear();
	        	       	
	        }  
	    }); 
		}
	}












