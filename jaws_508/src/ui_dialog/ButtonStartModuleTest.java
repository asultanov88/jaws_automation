package ui_dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;

import main.JawsFunctions;
import main.StartTest;

public class ButtonStartModuleTest {
	
	public static JButton generateButton(WebDriver driver, JFrame frame) {
		
	    JButton button_explore_start = new JButton("Module Test");
	    javax.swing.ToolTipManager.sharedInstance().setDismissDelay(20000);
	    button_explore_start.setToolTipText("<html>Use this function to test the entire page.<br>This will test all elements on your current page<br>then leave the tool and browser open upon finish.<br>You may continue testing other pages/modules.</html>");
	      
	      
	      button_explore_start.addActionListener(new ActionListener() {    	  
	    	
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		
	    	    String message = "Enter Module Name";
	    	    
	    	    String module_name = JOptionPane.showInputDialog(frame, message, "Module Name", JOptionPane.WARNING_MESSAGE);
	    	    
	    	    if (module_name == null) {
	    	      // User clicked cancel
	    	    }else if(module_name.trim().length() <1){
	    	    	
	    	    	JOptionPane.showMessageDialog(frame, "Module name is required to start.");
	    	    	
	    	    }
	    	    else{
	    	    	
	  			  frame.setVisible(false); 
	  			  
	  			  try {
	  				  
	  				main.StartTest.startTest(driver, module_name, true);
	  				
					if(StartTest.error == true) {
						
						ui_dialog.DialogTestComplete.testComplete(StartTest.error_text, true);
						frame.dispose();
						
					}else {
						
		  				frame.setVisible(true); 
		  				JOptionPane.showMessageDialog(frame, "Module report is complete.");  				
		  				JawsFunctions.resetVariables();  	
						
					}
	  				
	  			  } catch (Exception e1) {}
	    			
	    		}

				
			} 	           
	     
	      });
	      
	      return button_explore_start;
		
	}

}
