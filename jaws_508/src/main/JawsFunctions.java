package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import objects.ConfigFile;
import objects.FocusableElements;
import objects.TabElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;


public class JawsFunctions {
	
			public static File mainPageScreenshot = null;
	
			static String tempImageRepository = "";
	
			static String fileName = "";

			static String reportAbsolutePath = "";

			public static String reportDirectoryPath = "";

			static List<Integer> cords = new ArrayList();

			static List<Integer> size = new ArrayList();

			static int lastRow = 0;

			static int cycleCounter = 1;

			static boolean reportInit = false;

			static boolean headerExists = false;		
			
			static String global_user_directory = "";
			
			
			public static void resetVariables(){
				
				mainPageScreenshot = null;
				
				tempImageRepository = "";
		
				fileName = "";

				reportAbsolutePath = "";

				reportDirectoryPath = "";

				cords.clear();
				
				size.clear();

				lastRow = 0;

				cycleCounter = 1;

				reportInit = false;

				headerExists = false;			
				
				ImageFunctions.image = null;
				
				main.SelectiveTest.selective_test = false;
				
				main.SelectiveTest.cords.clear();
				
				main.StartTest.error = false;
				
				main.StartTest.error_text = "";
				
				main.SelectiveTest.error = false;
				
				main.SelectiveTest.error_text = "";
				
				main.StartTest.selective_xpath = "";
				
				SelectiveTest.retain_tab_index.clear();
				
				SelectiveTest.unselected_area_elements.clear();
				
				objects.ConfigFile.modal_focus = false;
				
				main.StartTest.selective_test = false;
				
				System.out.println("variable reset - success");					
				
			}

			
			public static String getFirstElement(WebDriver driver){
			//Grabs the first element in focus to identify the starting point of the page loop.	

				WebElement element = driver.switchTo().activeElement();
				
				String element_xpath = HelperFunctions.getXpath(element, driver);

				return element_xpath;
			}
			
			public static TabElement pressTab(WebDriver driver, String mainImagePath) throws AWTException, InterruptedException, IOException{
				//Presses the tab key to tab to the next element. Captures the previous and the new element in focus, get the time stamp of tabbing.

				String timeStamp = HelperFunctions.getCurrentDate("MM/dd/yyyy h:mm:ss a");

				long unixTimeStamp = new Date().getTime();
				
				Thread.sleep(2000);

				robotTab();
				
				Thread.sleep(2000);

				WebElement newElement = driver.switchTo().activeElement();	
				
				String tag_name = newElement.getTagName().toLowerCase().trim();
				
				if(!tag_name.equals("jaws_placeholder") && !tag_name.equals("body")) {
					
					String xpath = HelperFunctions.getXpath(newElement, driver);				
					
					newElement = driver.findElement(By.xpath(xpath));
											
					WebElement highlight_element = HelperFunctions.checkLabel(driver, xpath);

					HelperFunctions.jsHighlightElement(driver, highlight_element, "5", false);
					
					TabElement tabElement = new objects.TabElement();

					captureDetectedScreenshot(driver, highlight_element, tabElement);
					
					Thread.sleep(2000);				
					
					tabElement.element = newElement;
					
					main.HelperFunctions.checkIfVisible(tabElement);
					
					main.HelperFunctions.textCheckIconImage(tabElement);
										
					tabElement.timestamp = timeStamp;
					
					tabElement.unix_timestamp = unixTimeStamp;
					
					tabElement.xpath = HelperFunctions.getXpath(tabElement.element, driver);
					
					captureBackgroundScreenshot(driver, tabElement);
					
					HelperFunctions.resetAllElementStyle(driver);				
				
					return tabElement;
					
				}else {
					
					TabElement tabElement = new objects.TabElement();
					
					tabElement.element = newElement;
					
					tabElement.xpath = HelperFunctions.getXpath(tabElement.element, driver);
					
					return tabElement;
					
				}
				


			}
			
			public static void resetJs(WebDriver driver) {
				
				String js_reset = "window.jaws_confirm = null;" + 
						"window._jaws_get_cords = null;" + 
						"window._jaws_getElements = null;" + 
						"window.jaws_focusable_elements = null;" + 
						"window.jaws_xpath_list = null;" + 
						"window._jaws_get_xpath = null;" + 
						"window.jaws_joined_list = null;";
				
				((JavascriptExecutor) driver).executeScript(js_reset);
				
			}
			

			
			public static void scrollIntoView(WebDriver driver, WebElement element) {
				//Not used at the moment, maybe will be needed later.
		

				String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				                                  + "var elementTop = arguments[0].getBoundingClientRect().top;"
				                                  + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

				((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
				
				
			}


			public static void getCordinates(WebElement element, WebDriver driver){
				
				String js_element_cords = 
						  "    var el = arguments[0];"
						+ "    var cord_rect = el.getBoundingClientRect(),"
						+ "    scrollLeft = window.pageXOffset || document.documentElement.scrollLeft,"
						+ "    scrollTop = window.pageYOffset || document.documentElement.scrollTop;"
						+ "    return { top: cord_rect.top + scrollTop, left: cord_rect.left + scrollLeft }";
				
				String el_cords = ((JavascriptExecutor) driver).executeScript(js_element_cords, element).toString();

				List<String> temp_split = Arrays.asList(el_cords.split(","));

				String left = temp_split.get(0).replaceAll("[^\\d.]", "").trim();

				String top = temp_split.get(1).replaceAll("[^\\d.]", "").trim();				

				int xcordi = Double.valueOf(left).intValue();

				int ycordi = Double.valueOf(top).intValue();
				
				cords.clear();
				
				cords.add(xcordi);
				
				cords.add(ycordi);
			}

			public static void getSize(WebElement element){

				int width = element.getSize().getWidth();

				int height = element.getSize().getHeight();
				
				size.clear();
				
				size.add(width);
				
				size.add(height);
			}
			
			public static void captureBackgroundScreenshot(WebDriver driver, TabElement element) throws IOException {
				
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				//ImageFunctions.tabDetector("*", scrFile.getPath(), element.cordX, element.cordY, element.width, element.height);
				
				File new_dir = new File (JawsFunctions.reportDirectoryPath+"images");
				
				if (!new_dir.exists()){
				
					new_dir.mkdirs();
				   
				}
				
				FileUtils.copyFileToDirectory(scrFile, new_dir);				
				
				File background_screenshot = getLastSaved(new_dir.getPath());
				
				element.background_screenshot = background_screenshot;
				
			}


			public static void captureDetectedScreenshot(WebDriver driver, WebElement newElement, TabElement element) throws IOException{
				
				File new_dir = new File (JawsFunctions.reportDirectoryPath+"images");
				
				if (!new_dir.exists()){
				
					new_dir.mkdirs();
				   
				}
				
				try {
					
					//Shutterbug.shootElement(driver, newElement).save(new_dir.getPath());
					
					File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					
					String file_name = new_dir.getPath()+"\\"+HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss")+".png";
					
					FileUtils.copyFile(screenshot, new File(file_name));
					
					File full_page = new File(file_name);
					
					BufferedImage  fullImg = ImageIO.read(full_page);
					
					String js_get_location = "var viewportOffset = arguments[0].getBoundingClientRect();" + 											
							 "var jaws_top = viewportOffset.top;" + 
							 "var jaws_left = viewportOffset.left;" +
							 "return  {x: jaws_left, y: jaws_top}";
					
					String js_cords = ((JavascriptExecutor) driver).executeScript(js_get_location, newElement).toString().trim();
					
					List<String> temp_split = Arrays.asList(js_cords.split(","));
					
					String string_x = temp_split.get(0).replaceAll("[^\\d.]", "").trim();
					
					String string_y = temp_split.get(1).replaceAll("[^\\d.]", "").trim();
					
					int x = (int) Math.round(Double.valueOf(string_x)); 
					
					int y = (int) Math.round(Double.valueOf(string_y)); 
					
					int eleWidth = newElement.getSize().getWidth();
					
					int eleHeight = newElement.getSize().getHeight();
					
					BufferedImage eleScreenshot= fullImg.getSubimage(x, y, eleWidth, eleHeight);
					
					ImageIO.write(eleScreenshot, "png", full_page);
					
					
				} catch (Exception e) {					
			
					BufferedImage croppedBackgroundImg =  ImageFunctions.unableToCapture();
					
					File new_file = new File(new_dir.getPath()+"\\element_img_"+HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss")+".png");
					
					ImageIO.write(croppedBackgroundImg, "png", new_file);
					
				}			

				element.element_screenshot = getLastSaved(new_dir.getPath());
								
			}
			
			public static String getFullPageScreenshotBefore(WebDriver driver) throws IOException {
				
				String tmpdir = System.getProperty("java.io.tmpdir")+HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss")+"\\";
								
				Shutterbug.shootPage(driver, Capture.FULL,true).save(tmpdir);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
				
				return getLastSaved(tmpdir).getPath();				
	
			}
			
			public static String getFullPageScreenshotAfter(WebDriver driver) throws IOException {				
					
				String tmpdir = JawsFunctions.reportDirectoryPath+"images"+"\\";
				
				Shutterbug.shootPage(driver, Capture.FULL,true).save(tmpdir);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
				
				return getLastSaved(tmpdir).getName();				
	
			}
			
			
			public static File getLastSaved(String directoryFilePath)
			{
			    File directory = new File(directoryFilePath);
			    File[] files = directory.listFiles(File::isFile);
			    long lastModifiedTime = Long.MIN_VALUE;
			    File chosenFile = null;

			    if (files != null)
			    {
			        for (File file : files)
			        {
			            if (file.lastModified() > lastModifiedTime)
			            {
			                chosenFile = file;
			                lastModifiedTime = file.lastModified();
			            }
			        }
			    }
			    
			    String tempFileFolder = chosenFile.getPath();
			    
			    tempFileFolder = tempFileFolder.replace(chosenFile.getName(), "");
			    
			    tempImageRepository = tempFileFolder;

			    return chosenFile;
			}
			


			public static void jawsInspectOn() throws IOException, InterruptedException, ParseException{
				//Turns on JAWS Inspect.

				Runtime runTime = Runtime.getRuntime();
				
				String user_directory = System.getProperty("user.home")+"\\";
				
				global_user_directory = user_directory;
				
				String arg = "/startspeechcapture:"+user_directory+"temp_jawsinspectspeechoutput.csv";

				String exe = "cmd /c \""+ConfigFile.getJawsExePath()+"\" "+arg;
				
				System.out.println(exe);

				runTime.exec(exe);
				
			}

			public static void jawsInspectOff() throws IOException, ParseException{
				//Turns off JAWS Inspect.

				Runtime runTime = Runtime.getRuntime();

				String arg = "/stopspeechcapture";

				String exe = "cmd /c \""+ConfigFile.getJawsExePath()+"\" "+arg;

				runTime.exec(exe);
			}

			public static List<String> readCsvFile() throws InterruptedException{
				//Reads data from the JAWS log file. Must be called when the log file is not locked by JAWS Inspect..

				List<String> dataList = new ArrayList<>();

				boolean fileCheck = false;

				fileCheck = checkFileRelease();

				if(fileCheck == true){

					try{	

						String csvFile = global_user_directory+"temp_jawsinspectspeechoutput.csv";

						BufferedReader br = new BufferedReader(new FileReader(csvFile));

						String str = "";

						while((str = br.readLine()) != null){

							str = str.trim();

							if(str.length()>0 && str != " "){

								dataList.add(str.trim());
							}
						}

						br.close();

					}catch(Exception e){

						System.out.println("Encountered an error when reading JAWS log file.");

					}

					return dataList;
				}
				
				return null;
			}

			
			public static boolean checkFileRelease() throws InterruptedException{
				//Allows explicit wait until files is unlocked by JAWS Inspect. Waits up to 60 seconds, can be changed if needed.

				int count = 0;

				while(count<60){

					try{

						String csvFile = global_user_directory+"temp_jawsinspectspeechoutput.csv";

						File f = new File(csvFile);

						if(f.exists() && !f.isDirectory()) {

							BufferedReader br = new BufferedReader(new FileReader(csvFile));

							System.out.println("checkFileRelease - returning true");

							System.out.println(count);

							return true;
						}
					}catch(Exception e){}

					Thread.sleep(1000);

					count++;
				}

				System.out.println("checkFileRelease - returning false");

				System.out.println(count);

				return false;
			}

			public static boolean checkFileLock() throws InterruptedException{
				//Allows explicit wait until files is locked by JAWS Inspect. Waits up to 60 seconds, can be changed if needed.

				int count = 0;

				while(count<20){

					try{

						String csvFile = global_user_directory+"temp_jawsinspectspeechoutput.csv";

						File f = new File(csvFile);

						if(f.exists() && !f.isDirectory()) {

							BufferedReader br = new BufferedReader(new FileReader(csvFile));
						}
					}catch(Exception e){

						System.out.println("checkFileLock - returning true");

						System.out.println(count);

						return true;
					}

					Thread.sleep(1000);

					count++;
				}

				System.out.println("checkFileLock - returning false");

				System.out.println(count);

				return false;
			}


			public static void minizeAll() throws AWTException{
				//Minimizes All windows on desktop.

				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_WINDOWS);

				robot.keyPress(KeyEvent.VK_D);

				robot.keyRelease(KeyEvent.VK_D);

				robot.keyRelease(KeyEvent.VK_WINDOWS);
			}
			
			public static void robotTab() throws AWTException{//Robot press the tab key.
				
				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_TAB);	
				
				robot.keyRelease(KeyEvent.VK_TAB);
			}
			
			public static void robotShiftTab() throws AWTException{//Robot press the shift + tab key.
				
				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_SHIFT);	
				
				robot.keyPress(KeyEvent.VK_TAB);
				
				robot.keyRelease(KeyEvent.VK_TAB);
				
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

		
			public static void createReportFile(String testCaseName) throws IOException, InterruptedException{

				fileName = testCaseName+'_'+HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss")+".xls";

				String folderName = testCaseName+'_'+HelperFunctions.getCurrentDate("MM_dd_yyyy_HH_mm_ss");

				String reportDirectory = System.getProperty("user.dir")+"\\JAWS Reports\\"+folderName+"\\";

				String localFileName = fileName.replaceAll(" ","_");

				File reportFile = new File(reportDirectory+localFileName);

				reportAbsolutePath = reportDirectory+localFileName;

				reportDirectoryPath = reportDirectory;			

			}

}
