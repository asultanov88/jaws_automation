package ui_dialog;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.openqa.selenium.WebDriver;

import main.JawsFunctions;
import main.StartTest;

public class ButtonStartFlowTest {
	
	public static JButton generateButton(WebDriver driver, JFrame frame) {
		
	      JButton button_flow_start = new JButton("Flow Test");
	      javax.swing.ToolTipManager.sharedInstance().setDismissDelay(20000);
	      button_flow_start.setToolTipText("<html>Use this function to test the entire page.<br>This will test all elements on your current page<br>then close the tool and browser upon finish.</html>");
	      
	      
	      button_flow_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				  frame.setVisible(false);
				  frame.dispose();
				  try {
					
					try {
						
						main.StartTest.startTest(driver, "Jaws_Test", false);
						
					} catch (org.json.simple.parser.ParseException e1) {}
					
					if(StartTest.error == true) {
						
						ui_dialog.DialogTestComplete.testComplete(StartTest.error_text, true);
						
					}else {
						
						ui_dialog.DialogTestComplete.testComplete(JawsFunctions.reportDirectoryPath, false);
						
					}
					
					JawsFunctions.resetVariables();
				  } catch (IOException | InterruptedException | AWTException | ParseException e1) {}
				
			} 	           
	     
	      });
	      
	      return button_flow_start;
		
	}

}
