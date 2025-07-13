package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import objects.FocusableElements;
import objects.TabIndex;

public class SelectiveTest {
	
	public static boolean selective_test = false;
	
	public static Map<String, String> cords = new HashMap<String, String>();
	
	public static boolean error = false;
	
	public static String error_text = "";
	
	public static List<TabIndex> retain_tab_index = new ArrayList();
	
	public static List<FocusableElements> unselected_area_elements = new ArrayList();
	
	
	public static boolean confirmElements(WebDriver driver) throws InterruptedException, FileNotFoundException, IOException, ParseException {
		
		List<FocusableElements> focusable_elements = HelperFunctions.getFocusableElements(driver);
		
		Map<String, Double> area_parameters = HelperFunctions.calculateArea(cords);
		
		List<List<FocusableElements>> all_elements = HelperFunctions.selectElementsInArea(driver, focusable_elements, area_parameters);
		
		List<FocusableElements> selected_area_elements = all_elements.get(0);
		
		for(FocusableElements element: selected_area_elements) {
			
			WebElement area_element = HelperFunctions.checkLabel(driver, element.xpath);			
			
			HelperFunctions.jsHighlightElement(driver, area_element, "2", false);
			
		}
		
		Thread.sleep(2000);
		
		if(selected_area_elements.size() < 1) {
			
			HelperFunctions.triggerAlert(driver, "At least 1 element must be outlined. Please reselect elements.");
			
			return false;
			
		}
		
		Thread.sleep(3000);
		
		String js_element_confirm = "window.jaws_confirm = null; if (confirm('Are you happy to test the outlined elements?')) {" + 
				"window.jaws_confirm = true;" + 
				"}else{" + 
				"window.jaws_confirm = false;" + 
				"}";
		
		
		((JavascriptExecutor) driver).executeScript(js_element_confirm);
		
		Thread.sleep(2000);
		
		
		String test_trigger = "";
		
		for(int i=0; i < 60; i++) {
			
			boolean alert_check = false;  
			
			try {
				
				driver.switchTo().alert();
				
				alert_check = true;
				
			} catch (Exception e_1) {}
			
			if(alert_check == false) {
				
				try {
					
					test_trigger = ((JavascriptExecutor) driver).executeScript("return window.jaws_confirm;").toString().trim().toLowerCase();
					
					break;
					
				} catch (Exception e) {}
		
		
			}
			
			Thread.sleep(1000);
		
		}
		
		if(test_trigger == "true") {
			
			Thread.sleep(2000);
			
            HelperFunctions.resetAllElementStyle(driver);
            
            try {
				
            	WebElement first_element = driver.findElement(By.xpath(selected_area_elements.get(0).xpath));
            	
            	((JavascriptExecutor) driver).executeScript("arguments[0].focus();", first_element);
            	
			} catch (Exception e) {
				// TODO: handle exception
			}
            
    		unselected_area_elements = all_elements.get(1);
    		
    		for(FocusableElements element: unselected_area_elements) {
    			
    			try {
    				
    				WebElement unselected_element = driver.findElement(By.xpath(element.xpath));
    				
    				try {
    					
    					String orig_tab_index = unselected_element.getAttribute("tabindex").trim();
    					
    					if(orig_tab_index.length() > 0) {
    						
    						TabIndex tab_index = new TabIndex();
	    					
            				tab_index.xpath = element.xpath;
            				
            				tab_index.tab_index = orig_tab_index;
            				
            				retain_tab_index.add(tab_index);
    						
    					}
						
					} catch (Exception e_1) {}
    				
    				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('tabindex', '-1');", unselected_element);
    				
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    		}
    		
			String dummy_element_id = HelperFunctions.generateDummyElements(driver);
			
			main.StartTest.selective_xpath = "//*[@id='"+dummy_element_id+"']";    		
    		
			return true;
			
		}else if(test_trigger == "false") {
			
			Thread.sleep(2000);
			
            HelperFunctions.resetAllElementStyle(driver);
            
            return false;
			
		}else {
			
			Alert alert = driver.switchTo().alert();
            
			alert.dismiss();
            
			Thread.sleep(2000);
			
            HelperFunctions.resetAllElementStyle(driver);
            
            return false;
			
		}

	}

}
