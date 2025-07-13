package ui_dialog;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.HelperFunctions;
import main.JawsFunctions;
import main.SelectiveTest;
import main.StartTest;
import objects.FocusableElements;
import objects.TabIndex;

public class ButtonSelectiveTest {
	
	public static String originaly_style = "";
	
public static JButton generateButton(WebDriver driver, JFrame frame) {
	
    JButton button_select_start = new JButton("Selective Test"); 
    javax.swing.ToolTipManager.sharedInstance().setDismissDelay(20000);
    button_select_start.setToolTipText("<html>Use this function to test selective area on the page.<br>This will ignore the elements outside your selection area<br>and focus on the elements inside your selection area only.</html>");
    
    
    button_select_start.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
    	    String message = "Enter section name";
    	    
    	    String module_name = JOptionPane.showInputDialog(frame, message, "Module Name", JOptionPane.WARNING_MESSAGE);
    	    
    	    if (module_name == null) {
    	      // User clicked cancel
    	    }else if(module_name.trim().length() <1){
    	    	
    	    	JOptionPane.showMessageDialog(frame, "Module name is required to start.");
    	    	
    	    }
    	    else{
    	    	
    			Map<String, String> cords = new HashMap<String, String>();
    			
    			frame.setVisible(false);
    			
    			String jaws_inject = "";
    			
    			try {
    				
    				jaws_inject = ((JavascriptExecutor) driver).executeScript("return window.jaws_inject;").toString().trim();
    				
				} catch (Exception e) {}
    			
    			if(jaws_inject != "true") {
    				
    					String script_down = "window._jaws_mousedown =  function (event) {"+
        					"if(document.getElementById(\"jaws_highlighter\")){document.getElementById(\"jaws_highlighter\").remove();}"+
        					"window.jaws_inject = true;"+	
        					"window.cord_x_down = event.pageX;"+
        					"window.cord_y_down = event.pageY;"+
        					"console.log(event);"+
        					"console.log(\"value_X:\"+window.cord_x_down);"+
        					"console.log(\"value_Y:\"+window.cord_y_down);}";   
        				
    					String script_up = "window._jaws_mouseup = function (event) {"+					
        					"window.cord_x_up = event.pageX;"+
        					"window.cord_y_up = event.pageY;"+					
        					"console.log(\"value_X:\"+window.cord_x_up);"+
        					"console.log(\"value_Y:\"+window.cord_y_up);"+
        					
        					"var width = 0;"+
        					"var height = 0;"+
        					"var top = 0;"+
        					"var left = 0;"+
        					
        					"if(cord_x_down != cord_x_up && cord_y_down != cord_y_up){"+
        									
        					"if(cord_x_down < cord_x_up && cord_y_down < cord_y_up){"+
        						//start from top left to bottom right.
        						
        						"width = cord_x_up - cord_x_down;"+
        						"height = cord_y_up - cord_y_down;"+
        						
        						"top = cord_y_down;"+
        						"left = cord_x_down;"+
        						
        					"}else if(cord_x_down > cord_x_up && cord_y_down < cord_y_up){"+
        						//start from top right to bottom left.
        						
        						"width = cord_x_down - cord_x_up;"+
        						"height = cord_y_up - cord_y_down;"+
        						
        						"top = cord_y_down;"+
        						"left = cord_x_up;"+
        						
        					"}else if(cord_x_down < cord_x_up && cord_y_down > cord_y_up){"+
        						//start from bottom left to top right.
        						
        						"width = cord_x_up - cord_x_down;"+
        						"height = cord_y_down - cord_y_up;"+
        						
        						"top = cord_y_up;"+
        						"left = cord_x_down;"+
        						
        					"}else if(cord_x_down > cord_x_up && cord_y_down > cord_y_up){"+
        						//start from bottom right to top left.	
        						
        						"width = cord_x_down - cord_x_up;"+
        						"height = cord_y_down - cord_y_up;"+
        						
        						"top = cord_y_up;"+
        						"left = cord_x_up;"+
        					"}"+
        					
        					
        					"console.log(\"width:\"+width);"+
        					
        					"console.log(\"height:\"+height);"+
        					
        					"var highlighter = document.createElement(\"div\");"+
        					
        					"highlighter.setAttribute(\"id\", \"jaws_highlighter\");"+
        					
        					"highlighter.setAttribute(\"style\", \"width:\"+width+\"px; height:\"+height+\"px; border: 3px dotted red!important; position: absolute; top:\"+top+\"px; left:\"+left+\"px; z-index:999999999999999999; background-color: rgba(44, 130, 201, 0.2);\");"+
        					
        					"document.body.appendChild(highlighter);"+ 
        												
        					"setTimeout(function(){ if (confirm('Should I use selected area for Jaws Testing?')) {"+
        					
        					"if(document.getElementById(\"jaws_highlighter\")){document.getElementById(\"jaws_highlighter\").remove();}"+
        					
        					"window.start_jaws_test = true;"+
        					
        					//Remove Listener HERE
        					 
        					"} else {"+
        					
        					"if(document.getElementById(\"jaws_highlighter\")){document.getElementById(\"jaws_highlighter\").remove();}"+
        					
        					"window.start_jaws_test = false;"+
        					
    						//Remove Listener HERE
        					
        					"}"+
        					
        					"}, 2000);"+
        					
        					"}"+
        					
        					"}";
    					
    					//JS inject _jaws_mousedown function.
    					((JavascriptExecutor) driver).executeScript(script_down);
        				
        				//JS inject _jaws_mouseup function.
        				((JavascriptExecutor) driver).executeScript(script_up);
        				
        				//JS add mousedown listener to body.
        				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].addEventListener(\"mousedown\", window._jaws_mousedown);");
        				
        				 //JS add mouseup listener to body.        				
        				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].addEventListener(\"mouseup\", window._jaws_mouseup);");
    			
    			}else if(jaws_inject == "true"){
    				
	    				//JS add mousedown listener to body.
	    				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].addEventListener(\"mousedown\", window._jaws_mousedown);");
    				
	    				 //JS add mouseup listener to body.        				
	    				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].addEventListener(\"mouseup\", window._jaws_mouseup);");
				
    			}
    			
    			
    			try {
    				
    				WebElement body = driver.findElement(By.tagName("body"));
    				
    				originaly_style = body.getAttribute("style").trim();
    				
    				String unselectable_style = originaly_style + "user-select: none;";
    				
    				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+unselectable_style+"');", body);
    				
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    			HelperFunctions.triggerAlert(driver, "Hightlight the area to select elements.");
    			
    			String test_trigger = "";
    			
    			for(int i=0; i < 60; i++) {
    				
    				try {
    					
    					boolean alert_check = false;    					
    					
    					try {
    						
    						driver.switchTo().alert();
    						
    						alert_check = true;
    						
    					} catch (Exception e_1) {}
    					
    					if(alert_check == false) {
    						
    						try {
    							
    							test_trigger = ((JavascriptExecutor) driver).executeScript("return window.start_jaws_test;").toString().trim();
								
							} catch (Exception e) {}
    						
    						
    						if(test_trigger == "true") {
    							//Scenario 1. Start the test.
    							//The mouseup / mousedown listener is removed.
    							
    							((JavascriptExecutor) driver).executeScript("window.start_jaws_test = null;");
    							
    							WebElement body = driver.findElement(By.tagName("body"));
    							
    							((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+originaly_style+"');", body);
    							
    							System.out.println("Starting Selective test.");
    														
    							cords.put("key_down_x", ((JavascriptExecutor) driver).executeScript("return window.cord_x_down;").toString().trim());
    							
    							cords.put("key_down_y", ((JavascriptExecutor) driver).executeScript("return window.cord_y_down;").toString().trim());
    							
    							cords.put("key_up_x", ((JavascriptExecutor) driver).executeScript("return window.cord_x_up;").toString().trim());
    							
    							cords.put("key_up_y", ((JavascriptExecutor) driver).executeScript("return window.cord_y_up;").toString().trim());
    							
    							((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mouseup\", _jaws_mouseup);");
    							
    							((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mousedown\", _jaws_mousedown);");
    							
    							break;
    							
    						}else if(test_trigger == "false") {
    							//Scenario 2. Cancel the test.
    							//The mouseup / mousedown listener is removed.
    							
    							((JavascriptExecutor) driver).executeScript("window.start_jaws_test = null;");
    							
    							WebElement body = driver.findElement(By.tagName("body"));
    							
    							((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+originaly_style+"');", body);
    								
    							frame.setVisible(true);
    							
    							System.out.println("Canceling Selective test.");
    							
    							((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mouseup\", _jaws_mouseup);");
    							
    							((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mousedown\", _jaws_mousedown);");
    							
    							break;
    						
    						}
    						
    					}			
    					
    					try {
    						Thread.sleep(1000);
    					} catch (InterruptedException e) {}
    					
    				} catch (Exception e) {
    					
    					try {
    						Thread.sleep(1000);
    					} catch (InterruptedException e_1) {}
    					
    				}
    				
    			}
    			
    			if(test_trigger == "") {
    				//Scenario 3. Idle timeout. Bring back the UI, reset variables.
    				//The mouseup / mousedown listener is removed.
    				
					try {
						
						Alert alert = driver.switchTo().alert();	
						
						alert.dismiss();
						
						Thread.sleep(1000);
						
						WebElement body = driver.findElement(By.tagName("body"));
	    				
	    				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+originaly_style+"');", body);
	    				
	    				JawsFunctions.resetVariables();  	
	    				
	    				frame.setVisible(true);
	    				
	    				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mouseup\", _jaws_mouseup);");
						
						((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mousedown\", _jaws_mousedown);");
						
					} catch (Exception e_1) {
						
						WebElement body = driver.findElement(By.tagName("body"));
	    				
	    				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+originaly_style+"');", body);
	    				
	    				JawsFunctions.resetVariables();  	
	    				
	    				frame.setVisible(true);
	    				
	    				((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mouseup\", _jaws_mouseup);");
						
						((JavascriptExecutor) driver).executeScript("document.getElementsByTagName(\"body\")[0].removeEventListener(\"mousedown\", _jaws_mousedown);");
	    										
					}    				
    			
    			}else if(test_trigger == "true") {
    				
    				main.SelectiveTest.selective_test = true;
    				
    				main.SelectiveTest.cords = cords;
    				
    				try {
    					
    					boolean confirm_test = main.SelectiveTest.confirmElements(driver);
    					
    					if(confirm_test == true) {
    						
    						System.out.println("TEST CONFIRMED");
    						
    						main.StartTest.selective_test = true;
    						
    						main.StartTest.startTest(driver, module_name, true);
    						
    						if(StartTest.error == true) {
    							
    							ui_dialog.DialogTestComplete.testComplete(StartTest.error_text, true);
    							driver.close();
    							frame.dispose();
    							
    						}else {
    							
    							//Enable code if JS variable reset does not work.
    							/*String url = driver.getCurrentUrl();
    							driver.get(url);
    							driver.navigate().refresh();*/
    							frame.setVisible(true); 
    							JOptionPane.showMessageDialog(frame, "Selective test report is complete.");	    							  
    							JawsFunctions.resetJs(driver);
    							HelperFunctions.removeDummyElements(driver);
    							HelperFunctions.resetTabIndex(driver);    							
    							
    							
    							JawsFunctions.resetVariables();
    						}
    						
    						
    					}else {
    						
    						boolean alert_check = false;
    						
    						for(int i=0; i < 60; i++) {  
    							
    							try {
    								
    								driver.switchTo().alert();
    								
    								alert_check = true;
    								
    							} catch (Exception e_1) {
    								
    								alert_check = false;
    								
    								break;
    								
    							}
    							
    							Thread.sleep(1000);
    							
    						}
    						
    						if(alert_check == false) {
    							
    							HelperFunctions.resetAllElementStyle(driver);
    							
    							frame.setVisible(true);
    							
    						}else {
    							
    							Alert alert = driver.switchTo().alert();
    							
    							alert.accept();
    							
    							Thread.sleep(1000);
    							
    							frame.setVisible(true);
    							
    						}
    						
    						System.out.println("TEST NOT CONFIRMED");
    						
    					}
						

						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Unable to start selective test.");
						frame.dispose();
						driver.close();
					}
    				
    			}
    			
    		  }    	    	
    	    	
    	    }
		
    });
    
    return button_select_start;
	
}

}
