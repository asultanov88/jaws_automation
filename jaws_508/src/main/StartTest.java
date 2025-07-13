package main;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import objects.ConfigFile;
import objects.ReformattedLog;
import objects.TabElement;

public class StartTest {

	public static boolean error = false;

	public static String error_text = "";
	
	public static boolean selective_test = false;

	// This variable gets assigned only when selective test is running.
	// First selected element's xpath gets assigned here.
	public static String selective_xpath = "";

	public static void startTest(WebDriver driver, String module_name, boolean explore_mode) throws IOException,
			InterruptedException, AWTException, ParseException, org.json.simple.parser.ParseException {

		HelperFunctions.killJawsProcesses();

		HelperFunctions.bringToFront();

		try {

			if (module_name.length() > 1) {

				module_name = module_name.toLowerCase();

				module_name = module_name.replaceAll(" ", "_");

			}

			List<String> firstElementXpath = new ArrayList();

			List<String> backupElementXpath = new ArrayList();

			List<String> lastElementXpath = new ArrayList();

			List<TabElement> tabbedElements = new ArrayList();

			List<String> jawsFullLog = new ArrayList();

			List<ReformattedLog> jawsReformmatedLog = new ArrayList();

			JawsFunctions.createReportFile(module_name);

			JawsFunctions.jawsInspectOn();

			Thread.sleep(10000);

			if (selective_xpath.length() > 0) {

				WebElement first_element = driver.findElement(By.xpath(selective_xpath));

				((JavascriptExecutor) driver).executeScript("arguments[0].focus();", first_element);

			} else {

				// I must create 3 dummy elements and pre-pend to the body. This is done because
				// Jaws reads Chrome browser properties on first 3 tabs.
				String dummy_element_id = HelperFunctions.generateDummyElements(driver);

				String first_xpath = "//*[@id='" + dummy_element_id + "']";

				WebElement first_element = driver.findElement(By.xpath(first_xpath));

				((JavascriptExecutor) driver).executeScript("arguments[0].focus();", first_element);

			}

			Thread.sleep(10000);
			
			String mainImagePath = System.getProperty("java.io.tmpdir")
					+ HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss") + "\\";

			JawsFunctions.mainPageScreenshot = new File(mainImagePath);

			if (!JawsFunctions.mainPageScreenshot.exists()) {

				JawsFunctions.mainPageScreenshot.mkdirs();

			}

			int same_element_count = 0;

			main_loop:
			for (int i = 0; i < ConfigFile.getLoopLimit(); i++) {

				if (firstElementXpath.size() < 1) {

					String xpath = JawsFunctions.getFirstElement(driver);

					if (!xpath.contains("jaws_placeholder")) {

						firstElementXpath = HelperFunctions.splitXpath(xpath);

					} else if (backupElementXpath.size() < 1) {

						backupElementXpath = HelperFunctions.splitXpath(xpath);

					}

				}

				TabElement dynamicElement = JawsFunctions.pressTab(driver, mainImagePath);

				List<String> xpathList = HelperFunctions.splitXpath(dynamicElement.xpath);

				if (lastElementXpath.equals(xpathList)) {

					same_element_count++;

				} else {

					lastElementXpath.clear();

					for (String var : xpathList) {

						lastElementXpath.add(var);

					}

					same_element_count = 0;

				}

				// Conditions to stop the test.
				if (firstElementXpath.size() > 0) {

					if (firstElementXpath.equals(xpathList) || backupElementXpath.equals(xpathList)) {// stop test if
																										// focus loops
																										// back to the
																										// starting
																										// element.
						System.out.println("Stopping test. The focus is back on the first element.");
						break main_loop;

					} else if (xpathList.size() == 2) {// stop test if focus is on address bar or directly on body tag.

						System.out.println("Stopping test. The focus is on address bar or directly on body tag.");
						break main_loop;

					} else if (same_element_count >= 5) {// stop test if focus keeps falling on the same element 5
															// times.
						System.out.println("Stopping test. The focus keeps falling on the same element 5.");
						break main_loop;

					} else if (selective_xpath.length() > 0) {// stop test if focus falls outside the selective area.
																// Used only when selective test is running
																// "selective_xpath.length() > 0".

						if (HelperFunctions.confirmSelectedElement(dynamicElement.xpath,
								SelectiveTest.unselected_area_elements) == true) {

							System.out.println("Stopping test. The focus is outside the selective area.");
							break main_loop;

						}

					}

				}
				tabbedElements.add(dynamicElement);

			}

			JawsFunctions.jawsInspectOff();

			jawsFullLog = JawsFunctions.readCsvFile();

			// The first line of the log contains column names, thus must be removed before
			// parsing.
			jawsFullLog.remove(0);

			int logLength = jawsFullLog.size();

			for (int i = 0; i < logLength; i++) {

				String[] logList = jawsFullLog.get(i).split("\",\"");

				int listSize = logList.length;

				for (int e = 0; e < listSize; e++) {

					String var = logList[e].trim();

					if (var.endsWith("\"")) {

						var = var.substring(0, var.length() - 1);

					}

					if (var.endsWith(".")) {

						var = var.substring(0, var.length() - 1);

					}

					if (var.startsWith("\"")) {

						var = var.substring(1);

					}

					logList[e] = var;

				}

				long logDate = HelperFunctions.getUnixTime("MM/dd/yyyy h:mm:ss a", logList[0]);

				ReformattedLog rfLog = new ReformattedLog();

				rfLog.time = logDate;

				rfLog.context = logList[1];

				rfLog.text = logList[2];

				jawsReformmatedLog.add(rfLog);

			}
			
			//This clears out the dummy elements form the list.
			List<TabElement> filtered_elements = new ArrayList();

			for (TabElement element : tabbedElements) {

				List<String> xpathList = HelperFunctions.splitXpath(element.xpath);

				// if xpath contains tag "jaws_placeholder", it must be removed from the list
				// (report).
				if (xpathList.size() > 2 && !element.xpath.contains("jaws_placeholder")) {

					filtered_elements.add(element);

				}

			}

			tabbedElements.clear();

			tabbedElements = new ArrayList<TabElement>(filtered_elements);
			
			//This maps the Jaws speech with the tabbed elements.
			HelperFunctions.mapJawsText(jawsReformmatedLog, tabbedElements, 0, tabbedElements.size());

			for (TabElement element : tabbedElements) {

				main.HelperFunctions.analyzeJawsSpeech(element);

			}

			// Add all element highlights
			// Start screenshot.
			int element_count = 1;

			for (TabElement element : tabbedElements) {

				List<String> xpathList = HelperFunctions.splitXpath(element.xpath);

				if (xpathList.size() > 2) {
					
					try {
						
						WebElement tabbed_element = driver.findElement(By.xpath(element.xpath));
					
						HelperFunctions.jsHighlightElement(driver, tabbed_element, "3", false);
					
						// Need to figure out how to skip invisible elements. Enable the orderNumber
						// function once resolved.
						//Comment out if need to remove tab order numbers.
						if(objects.ConfigFile.getOrderNumber().equals("true")) {
							
							HelperFunctions.addOrderNumber(element, driver, String.valueOf(element_count));						
							
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
										
				}

				element_count++;

			}

			String screenshot_after = JawsFunctions.getFullPageScreenshotAfter(driver);

			//Comment out if need to remove tab order numbers.
			if(objects.ConfigFile.getOrderNumber().equals("true")) {
				
				HelperFunctions.removeOrderNumber(driver);	
				
			}			

			HelperFunctions.resetAllElementStyle(driver);
			// End screenshot.
			
			HelperFunctions.removeDummyElements(driver);

			HtmlReportGenerate.generateHtmlFile();

			HtmlReportGenerate.generateHtmlHeader(driver, screenshot_after.toString());

			for (int i = 0; i < tabbedElements.size(); i++) {

				int order_number = i + 1;

				if (tabbedElements.get(i).bug_detect == true) {

					HtmlReportGenerate.generateHtmlBodyError(driver, tabbedElements.get(i), order_number);

				} else {

					HtmlReportGenerate.generateHtmlBody(driver, tabbedElements.get(i), order_number);

				}

			}

			if (same_element_count >= 3) {

				HtmlReportGenerate.addInfiniteLoopError(driver, tabbedElements.get(tabbedElements.size() - 1), tabbedElements.size()+1);

			}

			HtmlReportGenerate.generateHtmlClosuse();

			if (explore_mode == false) {

				driver.close();

			}
			
			//Plays the finish sound.
			HelperFunctions help = new HelperFunctions();
			help.playSound("finish.wav");

			System.out.println("THE END");

		} catch (Exception e) {

			error = true;

			error_text = e.toString();

			System.out.println(e);

			JawsFunctions.jawsInspectOff();

		}
	}

}
