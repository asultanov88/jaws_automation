package ui_dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.openqa.selenium.WebDriver;

import main.HelperFunctions;

public class ButtonJawsExit {
	
	public static JButton generateButton(WebDriver driver, JFrame frame) {
				
		 JButton button_exit = new JButton("Exit");
		 javax.swing.ToolTipManager.sharedInstance().setDismissDelay(20000);
		 button_exit.setToolTipText("<html>Click this button to exit the tool.<br>It will close the browser too.</html>");
	      
	      button_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				  frame.setVisible(false);
				  frame.dispose();
				  
				  if(driver != null) {
					  
					  driver.close();  
					  
				  }
				  
				
			} 	           
	     
	      });
		return button_exit;
	}

}
