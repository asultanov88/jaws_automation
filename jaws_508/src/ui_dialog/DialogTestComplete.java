package ui_dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class DialogTestComplete {

	   public static void testComplete(String message, boolean error) {
		   

		      JFrame frame = new JFrame("Jaws Test");
		      
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		      JTextPane f = new JTextPane();
		      	      
		      if(error == false) {
		    	  
			      f.setContentType("text/html"); // let the text pane know this is what you want
			      f.setText("<html><br><h3 color=\"blue\">Jaws test is complete.</h3>"
				      		+ "The report can be viewed at:"
				      		+ "<br><br>"+message+"</html>"); // showing off
			      f.setEditable(false); // as before
			      f.setBackground(null); // this is the same as a JLabel
			      f.setBorder(new EmptyBorder(0,10,0,0)); // remove the border	  

		    	  
		      }else {
		    	  
			      f.setContentType("text/html"); // let the text pane know this is what you want
			      f.setText("<html><br><h3 color=\"red\">Encountered error.</h3>"
				      		+ "Error details:"
				      		+ "<br><br>"+message+"</html>");
			      f.setEditable(false); // as before
			      f.setBackground(null); // this is the same as a JLabel
			      f.setBorder(new EmptyBorder(0,10,0,0)); // remove the border	
			      
		      }
		     

		      
		      
		      //Adding buttons.
			  JPanel panel = new JPanel();
		      LayoutManager layout = new FlowLayout();  
		      panel.setLayout(layout);     

		      JButton button_exit = new JButton("Exit");
		 
		      button_exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					  frame.setVisible(false);
					  frame.dispose();
					
				} 	           
		     
		      });
		      
		      JButton button_copy = new JButton("Copy to Clipboard");
		 	 
		      button_copy.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					StringSelection selection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(selection, selection);
					
				} 	           
		     
		      }); 
		     
		      panel.add(button_exit);
		      panel.add(button_copy);
		      frame.getContentPane().add(f, BorderLayout.NORTH);
		      frame.getContentPane().add(panel, BorderLayout.SOUTH);
		      
		      if(error == true) {
		    	  
			      frame.setSize(700, 500); 
			      frame.setLocationRelativeTo(null);  
			      frame.setVisible(true);
			      frame.toFront();
			      frame.setAlwaysOnTop(true);
		    	  
		      }else {
		    	  
			      frame.setSize(560, 250); 
			      frame.setLocationRelativeTo(null);  
			      frame.setVisible(true);
			      frame.toFront();
			      frame.setAlwaysOnTop(true);
		    	  
		      }

		      
	   }
	
}
