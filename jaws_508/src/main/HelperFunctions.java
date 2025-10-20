package main;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import objects.ConfigFile;
import objects.FocusableElements;
import objects.ReformattedLog;
import objects.TabElement;
import objects.TabIndex;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class HelperFunctions {
	static String process_id = "";

	static List<String> highlight_xpath = new ArrayList();

	static List<String> highlight_style = new ArrayList();
	
	
public void playSound(String path) {
	
	URL url = getClass().getResource(path);
    AudioClip clip = Applet.newAudioClip(url);
    clip.play();	
    	
}

	public static void removeOrderNumber(WebDriver driver) throws InterruptedException {

		for (int i = 0; i < 60; i++) {

			List<WebElement> elements_before = driver.findElements(By.name("jaws_order_box"));

			Thread.sleep(2000);

			List<WebElement> elements_after = driver.findElements(By.name("jaws_order_box"));

			if (elements_before.size() == elements_after.size()) {

				break;

			}

			Thread.sleep(1000);
		}

		String remove_order = "var order_boxes = document.getElementsByName(\"jaws_order_box\");"
				+ "var parent_node = order_boxes[0].parentNode;" + "for(var i=0; i<order_boxes.length; i++){"
				+ "parent_node.removeChild(order_boxes[i]);" + "}";

		while (driver.findElements(By.name("jaws_order_box")).size() > 0) {

			try {
				((JavascriptExecutor) driver).executeScript(remove_order);
			} catch (Exception e) {
			}

		}

	}
	
	public static String getComputedStyle(WebDriver driver, WebElement element) {
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		String script = "var s = '';" +
		                "var o = getComputedStyle(arguments[0]);" +
		                "for(var i = 0; i < o.length; i++){" +
		                "s+=o[i] + ':' + o.getPropertyValue(o[i])+';';}" + 
		                "return s;";
		
		String style = executor.executeScript(script, element).toString().replaceAll(" ", "");
		
		return style;
		
	}

	public static void addOrderNumber(TabElement element, WebDriver driver, String order_number) {
		
		try {
			
			String[] arr_void_tags = {"textarea", "select", "area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param", "source", "track", "wbr"};
			
			List<String>list_void_elements = Arrays.asList(arr_void_tags);
			
			WebElement el = driver.findElement(By.xpath(element.xpath));			
			
			String el_tag_name = el.getTagName().toLowerCase();
			
			String el_style = el.getAttribute("style");
			
			String el_computed_style = getComputedStyle(driver, el);
			
			String upd_style = "";
			
			if(!list_void_elements.contains(el_tag_name)) {
				
				if(!el_computed_style.contains(";position:relative;") && !el_computed_style.contains(";position:fixed;") && 
					!el_computed_style.contains(";position:absolute;") && !el_computed_style.contains(";position:sticky;")) {
					
					upd_style = el_style + "position:relative!important;";
				
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+upd_style+"')", el);
				
				}	
				
				
				
				String order_box = "var order_box = document.createElement(\"div\");" +

									"order_box.setAttribute(\"name\", \"jaws_order_box\");" +

									"order_box.innerHTML = \"<span style='color: #FF0000; font-weight: bold; font-size:14px!important;'>" + order_number
									
									+ "</span>\";" +
										
									"order_box.setAttribute(\"style\", \"top: 0px; left: 0px; "
									
									+ "background-color: #39ff14!important;"
									+ "padding: 2px; "
									+ "position: absolute!important;"
									+ "z-index:999999999999999999;"
									+ "max-width:25px!important;"
									+ "height:20px!important; "
									+ "display: flex;"
									+ "justify-content: center;"
									+ "align-items: center;" 
									+ "box-sizing: border-box;\");"									
									+ "arguments[0].appendChild(order_box);";

				((JavascriptExecutor) driver).executeScript(order_box, el);	
				
			}else {
				
				WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                        "return arguments[0].parentNode;", el);
				
				String parent_style = parent.getAttribute("style");
				
				String pr_computed_style = getComputedStyle(driver, parent);
				
				if(!pr_computed_style.contains(";position:relative;") && !pr_computed_style.contains(";position:fixed;") && 
						!pr_computed_style.contains(";position:absolute;") && !pr_computed_style.contains(";position:sticky;")) {
						
						upd_style = parent_style + "position:relative!important;";
						
						((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '"+upd_style+"')", parent);
						
					}				
								
				String offset_left = "return arguments[0].offsetLeft;";
				
				String offset_top = "return arguments[0].offsetTop;";
				
				String top = ((JavascriptExecutor) driver).executeScript(offset_top, el).toString();

				String left = ((JavascriptExecutor) driver).executeScript(offset_left, el).toString();
				
				String order_box = "var order_box = document.createElement(\"div\");" +

									"order_box.setAttribute(\"name\", \"jaws_order_box\");" +

									"order_box.innerHTML = \"<span style='color: #FF0000; font-weight: bold; font-size:14px!important;'>" + order_number
									
									+ "</span>\";" +
										
									"order_box.setAttribute(\"style\", \"top: "+top+"px; left: "+left+"px; "
									
									+ "background-color: #39ff14!important;"
									+ "padding: 2px; "
									+ "position: absolute!important;"
									+ "z-index:999999999999999999;"
									+ "max-width:25px!important;"
									+ "height:20px!important; "
									+ "display: flex;"
									+ "justify-content: center;"
									+ "align-items: center;" 
									+ "box-sizing: border-box;\");"									
									+ "arguments[0].appendChild(order_box);";

				((JavascriptExecutor) driver).executeScript(order_box, parent);					
				
			}
			
	
			
		} catch (Exception e) {System.out.println(e);}
		
	}

	public static String generateDummyElements(WebDriver driver) {

		String js_dummy_elements_before = "var jaws_dummy_elements = [];" + "for (var i = 0; i < 3; i++) {"
				+ "  var el_id = \"jaws_placeholder_\"+i.toString();"
				+ "  var element = document.createElement(\"jaws_placeholder\");"
				+ "  element.setAttribute(\"title\", \"jaws placeholder element\");"
				+ "  element.setAttribute(\"id\", el_id);" + "  element.setAttribute(\"tabindex\", \"0\");"
				+ "  element.setAttribute(\"z-index\", \"99998\");"
				+ "  element.setAttribute(\"style\", \"position: absolute; top: 1px; left: 1px;\");"
				+ "  document.body.prepend(element);" + "  jaws_dummy_elements.push(el_id);" + "}"
				+ "var new_placeholder_el = jaws_dummy_elements.join(\";\");" + "return new_placeholder_el;";

		String new_elements_id = ((JavascriptExecutor) driver).executeScript(js_dummy_elements_before).toString()
				.trim();

		List<String> element_ids_before = Arrays.asList(new_elements_id.split(";"));

		Collections.reverse(element_ids_before);

		return element_ids_before.get(0);

	}

	public static void removeDummyElements(WebDriver driver) {

		try {

			String js_dummy_elements = "for (var i = 0; i < 10; i++) {"
					+ "var el_id = \"jaws_placeholder_\"+i.toString();"
					+ "if(document.getElementById(el_id)){document.getElementById(el_id).remove();}" + "}";

			((JavascriptExecutor) driver).executeScript(js_dummy_elements);

		} catch (Exception e) {
		}

	}

	// This functions sorts out (selects) elements that are in user highlighted
	// area.
	public static List<List<FocusableElements>> selectElementsInArea(WebDriver driver, List<FocusableElements> focusable_elements,
			Map<String, Double> area_parameters)
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		List<FocusableElements> selected_area_elements = new ArrayList();

		List<FocusableElements> unselected_area_elements = new ArrayList();

		for (FocusableElements element : focusable_elements) {

			boolean check = false;

			// Top left corner.
			double element_x_top_left = element.left;

			double element_y_top_left = element.top;

			// Top right corner.
			double element_x_top_right = element.left + element.width;

			double element_y_top_right = element.top;

			// Bottom left corner.
			double element_x_bottom_left = element.left;

			double element_y_bottom_left = element.top + element.height;

			// Bottom right corner.
			double element_x_bottom_right = element.left + element.width;

			double element_y_bottom_right = element.top + element.height;

			double element_width = element.width;

			double element_height = element.height;

			if (element_x_top_left > area_parameters.get("top_x")
					&& element_x_top_left < area_parameters.get("top_x") + area_parameters.get("width")) {

				if (element_y_top_left > area_parameters.get("top_y")
						&& element_y_top_left < area_parameters.get("top_y") + area_parameters.get("height")) {

					check = true;

				}

			} else if (element_x_top_right > area_parameters.get("top_x")
					&& element_x_top_right < area_parameters.get("top_x") + area_parameters.get("width")) {

				if (element_y_top_right > area_parameters.get("top_y")
						&& element_y_top_right < area_parameters.get("top_y") + area_parameters.get("height")) {

					check = true;

				}

			} else if (element_x_bottom_left > area_parameters.get("top_x")
					&& element_x_bottom_left < area_parameters.get("top_x") + area_parameters.get("width")) {

				if (element_y_bottom_left > area_parameters.get("top_y")
						&& element_y_bottom_left < area_parameters.get("top_y") + area_parameters.get("height")) {

					check = true;

				}

			} else if (element_x_bottom_right > area_parameters.get("top_x")
					&& element_x_bottom_right < area_parameters.get("top_x") + area_parameters.get("width")) {

				if (element_y_bottom_right > area_parameters.get("top_y")
						&& element_y_bottom_right < area_parameters.get("top_y") + area_parameters.get("height")) {

					check = true;

				}

			}

			if (check == true) {

				selected_area_elements.add(element);

			} else {

				unselected_area_elements.add(element);

			}

		}

		// This will check if modal exists in selected area. If modal exists, it will
		// move all elements that are not in modal to unselected_area_elements list.
		//I must add a better logic to check for modal. Need to find element with top-left=0 and top=0 with 100% width and height (could be in pixels).
		if (ConfigFile.getModalFocus() == true) {
			
			//Add logic if modal is displayed here.
			boolean modal_exists = false;
			
			String modal_overlay = "";
			
			String inner_height = ((JavascriptExecutor) driver).executeScript("return window.innerHeight;").toString();
			
			String inner_width = ((JavascriptExecutor) driver).executeScript("return window.innerWidth;").toString();
			
			int view_height = (int) Double.parseDouble(removeAlphaChars(inner_height));
		    
		    int view_width = (int) Double.parseDouble(removeAlphaChars(inner_width));
			
		    selected_element:
			for (FocusableElements element : selected_area_elements) {
				
				List<String> xpathList = HelperFunctions.splitXpath(element.xpath);
				
				String last_el_xpath = "";
				
				for(int i=0; i < xpathList.size(); i++) {
					
					try {
						
						WebElement target_el = null; 
						
						if(last_el_xpath == "") {
							
							target_el = driver.findElement(By.xpath(element.xpath));
							
						}else {
							
							target_el = driver.findElement(By.xpath(last_el_xpath));
							
						}
						
						WebElement parent_el = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", target_el);
						
						last_el_xpath = getXpath(parent_el, driver);
						
						String parent_tag = parent_el.getTagName();
								
						if(!parent_tag.contains("body") && !parent_tag.contains("html")) {			
							
							int parent_left = (int) Double.parseDouble(removeAlphaChars(parent_el.getCssValue("left"))); 

							int parent_top = (int) Double.parseDouble(removeAlphaChars(parent_el.getCssValue("top"))); 
							
							int parent_width = (int) Double.parseDouble(removeAlphaChars(parent_el.getCssValue("width"))); 
							
							int parent_height = (int) Double.parseDouble(removeAlphaChars(parent_el.getCssValue("height")));
							
							String parent_position = parent_el.getCssValue("position").toLowerCase().trim();
							
							int parent_z_index =  (int) Double.parseDouble(removeAlphaChars(parent_el.getCssValue("z-index")));
							
							if(parent_left <= 0 && parent_top <= 0) {
								
								if(parent_position.equals("fixed") || parent_position.equals("absolute")) {
									
									if(parent_z_index > 0) {
										
										int height_ratio = (int)(parent_height*100)/view_height;
										
										int width_ratio = (int)(parent_width*100)/view_width;
										
										if(height_ratio > 98 && width_ratio > 98) {
											
											modal_exists = true;
											
											modal_overlay = last_el_xpath;
											
											System.out.println("Modal found.");
											
											System.out.println("Modal parent tag: "+last_el_xpath);
											
											break selected_element;
											
										}
										
									}
									
								}
								
							}
							
						}						
					
					
					} catch (Exception e) {}
					
				}

			}

			if (modal_exists == true) {

				List<FocusableElements> update_selected_elements = new ArrayList();

				List<FocusableElements> update_unselected_elements = new ArrayList();

				for (FocusableElements element : selected_area_elements) {
					
					//Re-sort modal elements here. Only those elements that are in the modal are kept in focus.
					if (element.xpath.startsWith(modal_overlay)) {

						update_selected_elements.add(element);

					} else {

						update_unselected_elements.add(element);

					}

				}

				selected_area_elements.clear();

				selected_area_elements = update_selected_elements;

				// Joining two lists of unselected_area_elements.
				for (FocusableElements element : update_unselected_elements) {

					unselected_area_elements.add(element);

				}

			}

		}

		List<List<FocusableElements>> all_elements = new ArrayList<List<FocusableElements>>();

		all_elements.add(selected_area_elements);

		all_elements.add(unselected_area_elements);

		return all_elements;

	}

	// Calculates the area of selection.
	public static Map<String, Double> calculateArea(Map<String, String> cords) {

		double cord_x_down = Double.valueOf(cords.get("key_down_x"));

		double cord_y_down = Double.valueOf(cords.get("key_down_y"));

		double cord_x_up = Double.valueOf(cords.get("key_up_x"));

		double cord_y_up = Double.valueOf(cords.get("key_up_y"));

		double area_top_x = 0.00;

		double area_top_y = 0.00;

		double area_bottom_x = 0.00;

		double area_bottom_y = 0.00;

		double width = 0.00;

		double height = 0.00;

		if (cord_x_down < cord_x_up && cord_y_down < cord_y_up) {

			// start from top left to bottom right.
			width = cord_x_up - cord_x_down;
			height = cord_y_up - cord_y_down;

			area_top_y = cord_y_down;
			area_top_x = cord_x_down;

			area_bottom_x = area_top_x + width;
			area_bottom_y = area_top_y + height;

		} else if (cord_x_down > cord_x_up && cord_y_down < cord_y_up) {
			// start from top right to bottom left.

			width = cord_x_down - cord_x_up;
			height = cord_y_up - cord_y_down;

			area_top_y = cord_y_down;
			area_top_x = cord_x_up;

			area_bottom_x = area_top_x + width;
			area_bottom_y = area_top_y + height;

		} else if (cord_x_down < cord_x_up && cord_y_down > cord_y_up) {
			// start from bottom left to top right.

			width = cord_x_up - cord_x_down;
			height = cord_y_down - cord_y_up;

			area_top_y = cord_y_up;
			area_top_x = cord_x_down;

			area_bottom_x = area_top_x + width;
			area_bottom_y = area_top_y + height;

		} else if (cord_x_down > cord_x_up && cord_y_down > cord_y_up) {
			// start from bottom right to top left.

			width = cord_x_down - cord_x_up;
			height = cord_y_down - cord_y_up;

			area_top_y = cord_y_up;
			area_top_x = cord_x_up;

			area_bottom_x = area_top_x + width;
			area_bottom_y = area_top_y + height;
		}

		Map<String, Double> area_parameters = new HashMap<String, Double>();

		area_parameters.put("top_x", area_top_x);

		area_parameters.put("top_y", area_top_y);

		area_parameters.put("bottom_x", area_bottom_x);

		area_parameters.put("bottom_y", area_bottom_y);

		area_parameters.put("height", height);

		area_parameters.put("width", width);

		return area_parameters;

	}

	// Gets focusable element list for Selective Test. Object contains xpath, top
	// cord and left cord.
	public static List<FocusableElements> getFocusableElements(WebDriver driver)
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		String js_element_cords = "window._jaws_get_cords = function (el) {"
				+ "    var rect = el.getBoundingClientRect(),"
				+ "    scrollLeft = window.pageXOffset || document.documentElement.scrollLeft,"
				+ "    scrollTop = window.pageYOffset || document.documentElement.scrollTop;"
				+ "    return { top: rect.top + scrollTop, left: rect.left + scrollLeft }}";

		String include_table = ConfigFile.getIncludeTable();

		String js_focusable_elements = "";
		
		if (include_table.contains("true")) {

			js_focusable_elements = "window._jaws_getElements = function(element = document) {"
					+ "  var elements = [...element.querySelectorAll("
					+ "    'tr, td, iframe, a, button, input, textarea, select, details,[tabindex]:not([tabindex=\"-1\"])'"
					+ "  )];" + "  elements = elements.filter(el => !el.hasAttribute('disabled'));"
					+ "  elements = elements.filter(el => !el.hasAttribute('hidden'));"
					+ "  elements = elements.filter(el => function (el){"
					+ " var el_cords = window._jaws_get_cords(el);" + " if(el_cords.top ==0 && el_cords.left == 00){"
					+ " return false;}else{return true;}" + " });" +

					"  return elements;" + "}";

		} else {

			js_focusable_elements = "window._jaws_getElements = function(element = document) {"
					+ "  var elements = [...element.querySelectorAll("
					+ "    'iframe, a, button, input, textarea, select, details,[tabindex]:not([tabindex=\"-1\"])'"
					+ "  )];" + "  elements = elements.filter(el => !el.hasAttribute('disabled'));"
					+ "  elements = elements.filter(el => !el.hasAttribute('hidden'));"
					+ "  elements = elements.filter(el => function (el){"
					+ " var el_cords = window._jaws_get_cords(el);" + " if(el_cords.top ==0 && el_cords.left == 00){"
					+ " return false;}else{return true;}" + " });" +

					"  return elements;" + "}";

		}

		String js_var_elements = "window.jaws_focusable_elements = window._jaws_getElements();";

		String js_var_xpath_list = "window.jaws_xpath_list = [];";

		String js_get_absolute_xpath = "window._jaws_get_xpath = function(item, index) {" + "var element = item;"
				+ "var comp, comps = [];" + "var parent = null;" + "var xpath = '';"
				+ "var getPos = function(element) {" + "var position = 1, curNode;"
				+ "if (element.nodeType == Node.ATTRIBUTE_NODE) {" + "return null;" + "}"
				+ "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"
				+ "if (curNode.nodeName == element.nodeName) {" + "++position;" + "}" + "}" + "return position;" + "};"
				+

				"if (element instanceof Document) {" + "return '/';" + "}" +

				"for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"
				+ "comp = comps[comps.length] = {};" + "switch (element.nodeType) {" + "case Node.TEXT_NODE:"
				+ "comp.name = 'text()';" + "break;" + "case Node.ATTRIBUTE_NODE:"
				+ "comp.name = '@' + element.nodeName;" + "break;" + "case Node.PROCESSING_INSTRUCTION_NODE:"
				+ "comp.name = 'processing-instruction()';" + "break;" + "case Node.COMMENT_NODE:"
				+ "comp.name = 'comment()';" + "break;" + "case Node.ELEMENT_NODE:" + "comp.name = element.nodeName;"
				+ "break;" + "}" + "comp.position = getPos(element);" + "}" +

				"for (var i = comps.length - 1; i >= 0; i--) {" + "comp = comps[i];"
				+ "xpath += '/' + comp.name.toLowerCase();" + "if (comp.position !== null) {"
				+ "xpath += '[' + comp.position + ']';" + "}" + "}" +

				"window.jaws_xpath_list.push(xpath);" +

				"}";

		String js_foreach_element = "window.jaws_focusable_elements.forEach(window._jaws_get_xpath);";

		String js_join_xpath_list = "window.jaws_joined_list = window.jaws_xpath_list.join();";

		String js_return_joined_list = "return window.jaws_joined_list;";

		((JavascriptExecutor) driver).executeScript(js_element_cords);

		((JavascriptExecutor) driver).executeScript(js_focusable_elements);

		((JavascriptExecutor) driver).executeScript(js_var_elements);

		((JavascriptExecutor) driver).executeScript(js_var_xpath_list);

		((JavascriptExecutor) driver).executeScript(js_get_absolute_xpath);

		((JavascriptExecutor) driver).executeScript(js_foreach_element);

		((JavascriptExecutor) driver).executeScript(js_join_xpath_list);

		String js_xpath_as_string = ((JavascriptExecutor) driver).executeScript(js_return_joined_list).toString();

		List<String> xpath_list = Arrays.asList(js_xpath_as_string.split(","));

		// This list contains all focusable elements.
		List<FocusableElements> obj_focusable_el = new ArrayList();

		for (String xpath : xpath_list) {

			try {

				WebElement element = driver.findElement(By.xpath(xpath));

				double element_width = element.getSize().getWidth();

				double element_height = element.getSize().getHeight();

				String el_cords = ((JavascriptExecutor) driver)
						.executeScript("return window._jaws_get_cords(arguments[0]);", element).toString();

				List<String> temp_split = Arrays.asList(el_cords.split(","));

				String left = temp_split.get(0).replaceAll("[^\\d.]", "").trim();

				String top = temp_split.get(1).replaceAll("[^\\d.]", "").trim();

				if (Double.parseDouble(left) != 0.00 || Double.parseDouble(top) != 0.00) {

					// Activate command line if causes issues.
					// if (element_width != 0.00 && element_height != 0.00) {

					FocusableElements f_element = new FocusableElements();

					f_element.xpath = xpath;

					f_element.top = Double.parseDouble(top);

					f_element.left = Double.parseDouble(left);

					f_element.width = element_width;

					f_element.height = element_height;

					obj_focusable_el.add(f_element);
					// }

				}

			} catch (Exception e) {
				System.out.print("Error in HelperFunctions.getFocusableElements() function.");
			}

		}

		return obj_focusable_el;

	}

	// Generates pop up alert on web browser.
	public static void triggerAlert(WebDriver driver, String alertMessage) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("alert('" + alertMessage + "');");

	}

	// Resets all elements to their original style after taking tab tracker
	// sreenshot.
	public static void resetAllElementStyle(WebDriver driver) {

		for (int i = 0; i < highlight_xpath.size(); i++) {

			try {

				WebElement element = driver.findElement(By.xpath(highlight_xpath.get(i)));

				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);",
						element, highlight_style.get(i));

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		highlight_xpath.clear();

		highlight_style.clear();

	}

	// This function ensures that element in focus is within the browser, not the
	// address bar or any other Chrome buttons.
	// If element in focus is within the port view, the xpath will be more than 2
	// when split into list.
	// Else it will keep pressing tab (up to 10 times) until the focus is within the
	// port view.
	public static void checkElementFocus(WebDriver driver) throws AWTException, InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		List<FocusableElements> obj_focusable_el = HelperFunctions.getFocusableElements(driver);

		String first_el_xpath = obj_focusable_el.get(0).xpath;

		WebElement first_element = driver.findElement(By.xpath(first_el_xpath));

		((JavascriptExecutor) driver).executeScript("arguments[0].focus();", first_element);

	}

	// Highlights the element with red border. Captures and returns element's
	// original style.
	public static void jsHighlightElement(WebDriver driver, WebElement element, String pixels, boolean reset) {

		String element_xpath = HelperFunctions.getXpath(element, driver);

		String parent_xpath = element_xpath + "/parent::node()";

		try {

			WebElement parent_element = driver.findElement(By.xpath(parent_xpath));

			if (parent_element.getTagName().contains("label")) {

				String parent_style = parent_element.getAttribute("style").trim();

				jsInjectStyle(driver, parent_element, pixels, parent_style);

				if (reset == true) {

					Thread.sleep(2000);

					jsRemoveElementHighligh(driver, parent_element, parent_style);

				} else {

					List<String> check_xpathList = HelperFunctions.splitXpath(parent_xpath);

					if (check_xpathList.size() > 2) {

						highlight_xpath.add(parent_xpath);

						highlight_style.add(parent_style);

					}

				}

			} else {

				String element_style = element.getAttribute("style").trim();

				jsInjectStyle(driver, element, pixels, element_style);

				if (reset == true) {

					Thread.sleep(2000);

					jsRemoveElementHighligh(driver, element, element_style);

				} else {

					List<String> check_xpathList = HelperFunctions.splitXpath(element_xpath);

					if (check_xpathList.size() > 2) {

						highlight_xpath.add(element_xpath);

						highlight_style.add(element_style);

					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Injects the style for highlighting the element in focus.
	public static void jsInjectStyle(WebDriver driver, WebElement element, String pixels, String orig_style) {

		if (orig_style.length() > 0) {

			List<String> style_list = Arrays.asList(orig_style.split(";"));

			List<String> sorted_style = new ArrayList();

			for (String style : style_list) {

				String single_style = style.trim();

				if (single_style.length() > 0 && !single_style.contains("outline")) {

					sorted_style.add(single_style);

				}

			}

			String final_style = "";

			for (String style : sorted_style) {

				final_style = final_style + style + ";";

			}

			final_style = final_style + "outline: " + pixels
					+ "px solid #39ff14!important; outline-offset: 0 !important;";

			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '" + final_style + "');",
					element);

		} else {

			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'outline: " + pixels
					+ "px solid #39ff14!important; outline-offset: 0 !important;');", element);

		}

	}

	// Resets element's original style. Style must be captured in
	// jsHighlightElement() function.
	public static void jsRemoveElementHighligh(WebDriver driver, WebElement element, String style) {

		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				style);

	}

	// Gets and returns element's absolute xpath.
	public static String getXpath(WebElement element, WebDriver driver) {

		try {

			return (String) ((JavascriptExecutor) driver)
					.executeScript("function absoluteXPath(element) {" + "var comp, comps = [];" + "var parent = null;"
							+ "var xpath = '';" + "var getPos = function(element) {" + "var position = 1, curNode;"
							+ "if (element.nodeType == Node.ATTRIBUTE_NODE) {" + "return null;" + "}"
							+ "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"
							+ "if (curNode.nodeName == element.nodeName) {" + "++position;" + "}" + "}"
							+ "return position;" + "};" +

							"if (element instanceof Document) {" + "return '/';" + "}" +

							"for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"
							+ "comp = comps[comps.length] = {};" + "switch (element.nodeType) {"
							+ "case Node.TEXT_NODE:" + "comp.name = 'text()';" + "break;" + "case Node.ATTRIBUTE_NODE:"
							+ "comp.name = '@' + element.nodeName;" + "break;"
							+ "case Node.PROCESSING_INSTRUCTION_NODE:" + "comp.name = 'processing-instruction()';"
							+ "break;" + "case Node.COMMENT_NODE:" + "comp.name = 'comment()';" + "break;"
							+ "case Node.ELEMENT_NODE:" + "comp.name = element.nodeName;" + "break;" + "}"
							+ "comp.position = getPos(element);" + "}" +

							"for (var i = comps.length - 1; i >= 0; i--) {" + "comp = comps[i];"
							+ "xpath += '/' + comp.name.toLowerCase();" + "if (comp.position !== null) {"
							+ "xpath += '[' + comp.position + ']';" + "}" + "}" +

							"return xpath;" +

							"} return absoluteXPath(arguments[0]);", element);

		} catch (Exception e) {

			return "Unable to extraxt element xpath.";

		}

	}

	// Analyzes JAWS' log and add corresponding entries to the tabbed element
	// object.
	public static void mapJawsText(List<ReformattedLog> jawsReformmatedLog, List<TabElement> tabbedElements,
			int loop_start, int loop_end) {

		int last_index = 0;

		int log_size = jawsReformmatedLog.size();

		for (int a = loop_start; a < loop_end; a++) {

			TabElement tabbedElement = tabbedElements.get(a);

			List<String> jawsSpeech = new ArrayList<>();

			boolean match_record = false;
			
			//I insert &nbsp to separate lines from each other. This will show up in HTML report as blank space..
			for (int i = last_index; i < log_size; i++) {

				if (jawsReformmatedLog.get(i).time > tabbedElement.unix_timestamp
						&& jawsReformmatedLog.get(i).time < tabbedElement.unix_timestamp + 6000) {

					jawsSpeech.add(jawsReformmatedLog.get(i).text + "&nbsp");

					match_record = true;

					if (match_record == true) {

						last_index = i + 1;

						break;

					}

				}

			}

			if (match_record == true) {

				for (int i = last_index; i < log_size; i++) {

					if (jawsReformmatedLog.get(i).time > tabbedElement.unix_timestamp
							&& jawsReformmatedLog.get(i).time < tabbedElement.unix_timestamp + 7000) {

						jawsSpeech.add(jawsReformmatedLog.get(i).text + "&nbsp");

						last_index = i;

					} else {

						last_index = i;

						break;

					}

				}

			}
			
			//I must remove the modal content speech from the first elements. The modal content is returned because,
			//this is the first active element on the page at this point.
			String joined_speech = "";
			
/*	Uncomment this once you figure out how to deal with modal speech.		
 * if(jawsSpeech.size() > 0 && main.StartTest.selective_test == true && a == 0) {
				
				boolean modal = false;
				
				for(String speech: jawsSpeech) {
					
					if(speech.toLowerCase().contains("modal")) {
						
						modal = true;
						
					}
					
				}
				
				if(modal == true) {
					
					try {
						
						//If the word "modal" found in the speech, the first two entries are skipped as they have modal info.
						for(int i = 2; i < jawsSpeech.size(); i++) {
							
							joined_speech = joined_speech+jawsSpeech.get(i);
							
						}
						
					} catch (Exception e) {
						for(int i = 0; i < jawsSpeech.size(); i++) {
							
							joined_speech = joined_speech+jawsSpeech.get(i);
							
						}
					}
					
				}else {
					
					//All relevant text gets assigned if the executes test is not Modal or Selective.
					for(int i = 0; i < jawsSpeech.size(); i++) {
						
						joined_speech = joined_speech+jawsSpeech.get(i);
						
						tabbedElement.jaws_speech = joined_speech;
						
					}
					
				}

				
			}else {
				
				//All relevant text gets assigned if the executes test is not Modal or Selective.
				for(int i = 0; i < jawsSpeech.size(); i++) {
					
					joined_speech = joined_speech+jawsSpeech.get(i);
										
				}
				
				tabbedElement.jaws_speech = joined_speech;*/
			
			
			
			//All relevant text gets assigned if the executes test is not Modal or Selective.
			for(int i = 0; i < jawsSpeech.size(); i++) {
				
				joined_speech = joined_speech+jawsSpeech.get(i);
								
			}
			
			tabbedElement.jaws_speech = joined_speech;

			

			if (tabbedElement.jaws_speech.length() < 1) {

				tabbedElement.bug_detect = true;

			}

		}

	}

	// Converts and returns passed date-time into unix time.
	public static long getUnixTime(String format, String dateInput) throws ParseException {

		DateFormat date = new SimpleDateFormat(format);

		Date newDate = date.parse(dateInput);

		long unixTime = newDate.getTime();

		return unixTime;

	}

	// Gets the current date-time. The format must be passed in.
	public static String getCurrentDate(String format) {

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		String newDate = sdf.format(date).toString();

		return newDate;

	}

	// Checks if element in focus is visible. If element is invisible, sets the
	// "tab_element.bug_detect = true"
	// Logs the error as "tab_element.error_log.add("Unable to check if element is
	// visible.");"
	public static void checkIfVisible(TabElement tab_element) {

		WebElement element = tab_element.element;

		try {
			if (!element.isDisplayed()) {

				tab_element.error_log.add("Element is invisible.");

				tab_element.bug_detect = true;

				tab_element.is_visible = true;

			}
		} catch (Exception e) {

			tab_element.error_log.add("Unable to check if element is visible.");

			tab_element.bug_detect = true;
		}

	}

	// Checks if element in focus has icon "i" or image "img" tags. If these tags
	// exist, checks for existence of "title" and "alt" attributes.
	// If "title" or "alt" attributes are not found, sets the
	// "tab_element.bug_detect = true".
	// Logs the error as "tab_element.error_log.add("Element's alt (alternative)
	// text is missing.")"
	public static void textCheckIconImage(TabElement tab_element) {

		WebElement element = tab_element.element;

		WebElement icon = null;

		try {

			try {

				icon = element.findElement(By.tagName("i"));

			} catch (Exception e_one) {

				icon = element.findElement(By.tagName("img"));

			}

			if (icon != null) {

				String element_text = element.getText().trim();

				if (element_text.length() < 3) {

					String element_title = element.getAttribute("title");

					if (element_title != null) {

						element_title = element_title.trim();

					}

					if (element_title == null || element_title.length() < 1) {

						String element_alt = element.getAttribute("alt");

						if (element_alt != null) {

							element_alt = element_alt.trim();

						}

						if (element_alt == null || element_alt.length() < 1) {

							tab_element.error_log.add("Element's alt (alternative) text is missing.");

							tab_element.bug_detect = true;

						}

					}

				}

			}

		} catch (Exception e) {
		}

	}

	// Analyzes the JAWS' speech. If speech "text" is less than 2 words, it logs bug
	// as "element.bug_detect = true"
	// sets the "element.error_log.add("Insufficient description of Jaws speech.")"
	public static void analyzeJawsSpeech(TabElement element) {

		String jaws_speech = element.jaws_speech.trim();

		String[] speech_list = jaws_speech.split(" ");

		List<String> targetList = Arrays.asList(speech_list);

		if (targetList.size() < 2) {

			element.bug_detect = true;

			element.error_log.add("Insufficient description of Jaws speech.");

		}

	}

	// Gets and returns the process ID of the running web driver. The process id is
	// used to bring the browser to front by VBS script.
	public static int getChromeDriverProcessID(int aPort) throws IOException, InterruptedException {
		String[] commandArray = new String[3];

		if (SystemUtils.IS_OS_LINUX) {
			commandArray[0] = "/bin/sh";
			commandArray[1] = "-c";
			commandArray[2] = "netstat -anp | grep LISTEN | grep " + aPort;
		} else if (SystemUtils.IS_OS_WINDOWS) {
			commandArray[0] = "cmd";
			commandArray[1] = "/c";
			commandArray[2] = "netstat -aon | findstr LISTENING | findstr " + aPort;
		} else {
			System.out.println("platform not supported");
			System.exit(-1);
		}

		System.out.println("running command " + commandArray[2]);

		Process p = Runtime.getRuntime().exec(commandArray);
		p.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		String result = sb.toString().trim();

		System.out.println("parse command response line:");
		System.out.println(result);

		return SystemUtils.IS_OS_LINUX ? ParseChromeDriverLinux(result) : ParseChromeDriverWindows(result);
	}

	public static int getChromeProcesID(int chromeDriverProcessID) throws IOException, InterruptedException {
		String[] commandArray = new String[3];

		if (SystemUtils.IS_OS_LINUX) {
			commandArray[0] = "/bin/sh";
			commandArray[1] = "-c";
			commandArray[2] = "ps -efj | grep google-chrome | grep " + chromeDriverProcessID;
		} else if (SystemUtils.IS_OS_WINDOWS) {
			commandArray[0] = "cmd";
			commandArray[1] = "/c";
			commandArray[2] = "wmic process get processid,parentprocessid,executablepath | find \"chrome.exe\" |find \""
					+ chromeDriverProcessID + "\"";
		} else {
			System.out.println("platform not supported");
			System.exit(-1);
		}

		System.out.println("running command " + commandArray[2]);

		Process p = Runtime.getRuntime().exec(commandArray);
		p.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			if (SystemUtils.IS_OS_LINUX && line.contains("/bin/sh")) {
				continue;
			}

			sb.append(line + "\n");
		}

		String result = sb.toString().trim();

		System.out.println("parse command response line:");
		System.out.println(result);

		return SystemUtils.IS_OS_LINUX ? parseChromeLinux(result) : ParseChromeWindows(result);
	}

	private static int parseChromeLinux(String result) {
		String[] pieces = result.split("\\s+");

		return Integer.parseInt(pieces[1]);
	}

	private static int ParseChromeWindows(String result) {
		String[] pieces = result.split("\\s+");

		return Integer.parseInt(pieces[pieces.length - 1]);
	}

	private static int ParseChromeDriverLinux(String netstatResult) {
		String[] pieces = netstatResult.split("\\s+");
		String last = pieces[pieces.length - 1];

		return Integer.parseInt(last.substring(0, last.indexOf('/')));
	}

	private static int ParseChromeDriverWindows(String netstatResult) {
		String[] pieces = netstatResult.split("\\s+");

		return Integer.parseInt(pieces[pieces.length - 1]);
	}

	public static WebDriver startChrome()
			throws IOException, InterruptedException, org.json.simple.parser.ParseException {

		try {
			
			// "\\\\10.25.8.10\\chromedriver\\chromedriver.exe" shared folder.
			System.setProperty("webdriver.chrome.driver", ConfigFile.getChromeDriverPath());

			ChromeOptions options = new ChromeOptions();

			options.addArguments("start-maximized");

			options.addArguments("disable-infobars");

			options.addArguments("--disable-extensions");

			// process id
			ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();

			WebDriver driver = new ChromeDriver(chromeDriverService, options);

			driver.navigate().to("http://www.google.com");

			return driver;
			
		} catch (Exception e) {}

		return null;
	}

	public static WebElement checkLabel(WebDriver driver, String xpath) {

		WebElement element = driver.findElement(By.xpath(xpath));

		WebElement parent_element = null;

		int parent_count = 0;

		boolean parent_displayed = false;

		if (!element.isDisplayed()) {

			while (parent_displayed == false && parent_count < 10) {

				try {

					xpath = xpath + "/parent::node()";

					parent_element = driver.findElement(By.xpath(xpath));

					if (parent_element.isDisplayed()) {

						parent_displayed = true;

						break;

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

				parent_count++;

			}

		}

		if (parent_displayed == true) {

			return parent_element;

		} else {

			return element;

		}

	}

	public static void killJawsProcesses() {

		List<String> process_names = Arrays.asList("jhookldr.exe", "Inspect.exe", "fsWow64Proxy.exe",
				"fsWow64Proxy.exe", "JAWSInspectCleanup.exe", "jfw.exe", "InspectHookLoader32.exe", "fsSynth32.exe",
				"cmd.exe");

		for (String proc : process_names) {

			try {
				Runtime.getRuntime().exec("taskkill /F /IM " + proc);
			} catch (IOException e) {
			}

		}

	}

	public static void resetTabIndex(WebDriver driver) {

		for (FocusableElements element : SelectiveTest.unselected_area_elements) {

			try {

				WebElement unselected_element = driver.findElement(By.xpath(element.xpath));

				((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('tabindex');",
						unselected_element);

			} catch (Exception e) {
			}

		}

		for (TabIndex tab_index : SelectiveTest.retain_tab_index) {

			if (tab_index.tab_index != "") {

				WebElement tab_index_element = driver.findElement(By.xpath(tab_index.xpath));

				String js_reset = "arguments[0].setAttribute('tabindex', '" + tab_index.tab_index + "');";

				((JavascriptExecutor) driver).executeScript(js_reset, tab_index_element);

			}

		}

	}

	public static boolean confirmSelectedElement(String current_xpath,
			List<FocusableElements> unselected_area_elements) {

		boolean unfocused_element = false;

		for (FocusableElements element : unselected_area_elements) {

			// check if current xpath matches the unfocused xpath.
			if (splitXpath(element.xpath).equals(splitXpath(current_xpath))) {

				unfocused_element = true;

				break;

			}

		}

		return unfocused_element;

	}

	public static List<String> splitXpath(String input_xpath) {

		if (input_xpath.startsWith("/")) {

			input_xpath = input_xpath.substring(1);

		}

		input_xpath = input_xpath.replaceAll("[\\[\\]]", "");

		List<String> xpathList = Arrays.asList(input_xpath.split("/"));

		return xpathList;

	}
	
	public static String removeAlphaChars(String input) {
		
		return input.replaceAll("[^\\d.]", "");
		
	}

}
