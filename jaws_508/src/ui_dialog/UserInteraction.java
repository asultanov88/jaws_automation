package ui_dialog;

import java.awt.AWTException;
import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.HelperFunctions;
import main.JawsFunctions;
import main.StartTest;
import objects.ConfigFile;
import objects.VersionNumber;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class UserInteraction {
	
   public static WebDriver driver = null;

public static void main(String[] args) throws InterruptedException, AWTException, IOException, org.json.simple.parser.ParseException {
	   
	   ConfigFile.checkFile("config"); 

	   ConfigFile.checkFile("readme");
	   
	   createWindow();
	    
   }

   public static void createWindow() throws AWTException, IOException, InterruptedException, org.json.simple.parser.ParseException   { 
	   
	  JawsFunctions.minizeAll();
		  
	  try {
		
		  driver = HelperFunctions.startChrome();
		  
	} catch (Exception e) {
		// TODO: handle exception
	}
	
      JFrame frame = new JFrame("Jaws Test "+VersionNumber.version_number);
      
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent event) {
				  frame.setVisible(false);
				  frame.dispose();
				  if(driver != null) {
					  
					  driver.close();
					  
				  }
    	    }
    	});
      
      
      //Adding label.
      JLabel label_ins = new JLabel();
      if(driver != null) {
    	  
          label_ins.setText("<html><br><h3 color=\"blue\">How it works</h3>"
            		+ "1. Disable all notifications in Control Panel."
            		+ "<br><br>2. Use the Chrome browser that was opened in the background."
            		+ "<br><br>3. Navigate to the desired web page manually."
            		+ "<br><br>4. Make sure the browser in use remains in focus."
            		+ "<br><br>5. Start your desired test type."
            		+ "<br><br>6. Do not use your computer while test is in progress."
            		+ "</html>");
          
          frame.setSize(600, 360); 
    	  
      }else {
    	  
    	  label_ins.setText("<html><br><h3 color=\"red\">You must update Chrome Driver first.</h3>"
    	  		+ "How to update Chrome Driver:"
    	  		+ "<br><br>1. Find out what version of Google Chrome you are using."
    	  		+ "<br><br>2. Navigate to 'https://chromedriver.chromium.org/downloads' page"
    	  		+ "<br><br>3. Download the corresponding chrome driver that matches your Google Chrome version."
    	  		+ "<br><br>4. Restart your PC."
    	  		+ "<br><br>5. Unzip the downloaded file and place it to: 'C:\\\\chromedriver' (replace the existing file)"
    	  		+ "<br><br>6. Start the Jaws Test Tool."
    	  		+ "<br><br>...or just contact asultanov@dssinc.com to help you."
    	  		+ "</hrml>");
    	  
    	  frame.setSize(600, 400); 
    	  
      }
      
      label_ins.setBorder(new EmptyBorder(0,10,0,0));
      
      //Adding buttons.
	  JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);     

    JButton button_flow_start = ui_dialog.ButtonStartFlowTest.generateButton(driver, frame);
      
    JButton button_explore_start = ui_dialog.ButtonStartModuleTest.generateButton(driver, frame);
      
    JButton button_select_start = ui_dialog.ButtonSelectiveTest.generateButton(driver, frame);
    
    JButton button_modal_start = ui_dialog.ButtonModalTest.generateButton(driver, frame);
      
    JButton button_exit = ui_dialog.ButtonJawsExit.generateButton(driver, frame);
      
	  if(driver != null) {
		  
		  panel.add(button_flow_start); 
		  panel.add(button_explore_start);
		  panel.add(button_select_start);
		  panel.add(button_modal_start);		  
		  panel.add(button_exit);
		  
	  }else {		  
		
		  panel.add(button_exit);
		  
	  }
	  frame.getContentPane().add(label_ins, BorderLayout.NORTH);
	  frame.getContentPane().add(panel, BorderLayout.SOUTH);

	  frame.setLocationRelativeTo(null);  
	  frame.setVisible(true);
	  frame.toFront();
	  frame.setAlwaysOnTop(true);
   }
   
} 