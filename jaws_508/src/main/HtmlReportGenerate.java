package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;

import objects.ConfigFile;
import objects.TabElement;

public class HtmlReportGenerate {
	
	static String htmlReportPath;

	//Generates HTML file to store report.
	public static void generateHtmlFile() throws IOException {
		
		htmlReportPath = JawsFunctions.reportDirectoryPath+"jaws_html_report.html";
		
		File html_report = new File(htmlReportPath);
		
		html_report.createNewFile();
		
	}
	
	//Generates the header portion of the HTML file.
	public static void generateHtmlHeader(WebDriver driver, String tabTrackerPath) throws IOException, ParseException {
		
		File tabTracker = new File(tabTrackerPath);
		
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		String headerTemplate = objects.HtmlReport.html_top;	
		
		List<String> project_list = ConfigFile.getProjectId();
		
		String options = "";
		
		for(int i=0; i<project_list.size(); i++) {
			
			String project = project_list.get(i);
			
			String[] entry_split = project.split(":");
			
			String key = " "+entry_split[0]+" ";
			
			String id = entry_split[1];
			
			if(!key.equals("KEY") && !id.equals("ID")) {
				
				String element = "<option value=\""+id+"\">"+key+"</option>";
				
				options = options+element;
				
			}
			
		}
		
		headerTemplate = headerTemplate.replace("%project_options%", options);
		
		headerTemplate = headerTemplate.replace("%tab_tracker%", tabTracker.getName());
		
		headerTemplate = headerTemplate.replace("%window_title%", getWindowTitle(driver));
		
		headerTemplate = headerTemplate.replace("%url%", getUrl(driver));
		
		headerTemplate = headerTemplate.replace("%current_date%", HelperFunctions.getCurrentDate("MM-dd-yyyy, HH:mm:ss"));
		
		headerTemplate = headerTemplate.replace("%username%", getUserName());

		pw.println(headerTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();				
		
	}
	
	//Generates the report portion only if infinite loop is encoutered.
	public static void addInfiniteLoopError(WebDriver driver, TabElement element, int order_number) throws IOException {
		//Preparing dynamic variables.
		String bodyTemplate = objects.HtmlReport.html_infinite_loop;
		
		String element_image = element.element_screenshot.getName();	
		
		String background_image = element.background_screenshot.getName();
		
		String jaws_speech_transcript = element.jaws_speech;
		
		if(jaws_speech_transcript.length() <1) {
			
			jaws_speech_transcript = "***JAWS speech not detected for this element. Recheck this element manually using JAWS.***";
			
		}
		
		
		//Assigning dynamiv variables.
		
		bodyTemplate = bodyTemplate.replace("%link_number_%", "link_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_number_%", "element_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_image%", element_image);
		
		bodyTemplate = bodyTemplate.replace("%background_image%", background_image);
		
		bodyTemplate = bodyTemplate.replace("%jaws_speech_transcript%", jaws_speech_transcript);
		
		bodyTemplate = bodyTemplate.replace("%xpath%", element.xpath);
		
		
		//Writing to html report.
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		pw.println(bodyTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();	
		
	}
	
	//Generates the error (bug) portion of HTML report. Only used if element has been flagged.
	public static void generateHtmlBodyError(WebDriver driver, TabElement element, int order_number) throws IOException {
		
		//Preparing dynamic variables.
		String bodyTemplate = objects.HtmlReport.html_loop_table_bug;
		
		String element_image = element.element_screenshot.getName();
		
		String background_image = element.background_screenshot.getName();
		
		String tab_order_index = Integer.toString(order_number);
		
		String jaws_speech_transcript = element.jaws_speech;
		
		if(jaws_speech_transcript.length() <1) {
			
			jaws_speech_transcript = "***JAWS speech not detected for this element. Recheck this element manually using JAWS.***";
			
		}
		
		
		//Assigning dynamiv variables.
		bodyTemplate = bodyTemplate.replace("%link_number_%", "link_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_number_%", "element_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_image%", element_image);
		
		bodyTemplate = bodyTemplate.replace("%background_image%", background_image);
		
		bodyTemplate = bodyTemplate.replace("%tab_order_index%", tab_order_index);
		
		bodyTemplate = bodyTemplate.replace("%jaws_speech_transcript%", jaws_speech_transcript);
		
		bodyTemplate = bodyTemplate.replace("%xpath%", element.xpath);
		
		String error_text = "";
		
		for(String var : element.error_log) {
			
			error_text = error_text + var + "<br>";
			
		}
		
		bodyTemplate = bodyTemplate.replace("%error_log%", error_text);
		
		//Writing to html report.
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		pw.println(bodyTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();	
		
	}
	
	//Generates normal HTML report content. Used for generic tabbed elements that were not flagged as bug.
	public static void generateHtmlBody(WebDriver driver, TabElement element, int order_number) throws IOException {
		
		//Preparing dynamic variables.
		String bodyTemplate = objects.HtmlReport.html_loop_table;
		
		String element_image = element.element_screenshot.getName();
		
		String background_image = element.background_screenshot.getName();
		
		String tab_order_index = Integer.toString(order_number);
		
		String jaws_speech_transcript = element.jaws_speech;
		
		if(jaws_speech_transcript.length() <1) {
			
			jaws_speech_transcript = "***JAWS speech not detected for this element. Recheck this element manually using JAWS.***";
			
		}
		
		//Assigning dynamic variables.		
		bodyTemplate = bodyTemplate.replace("%link_number_%", "link_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_number_%", "element_number_"+Integer.toString(order_number));
		
		bodyTemplate = bodyTemplate.replace("%element_image%", element_image);
		
		bodyTemplate = bodyTemplate.replace("%background_image%", background_image);
		
		bodyTemplate = bodyTemplate.replace("%tab_order_index%", tab_order_index);
		
		bodyTemplate = bodyTemplate.replace("%jaws_speech_transcript%", jaws_speech_transcript);
		
		bodyTemplate = bodyTemplate.replace("%xpath%", element.xpath);
		
		//Writing to html report.
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		pw.println(bodyTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();	
		
	}
	
	//Generates the colsure tags of HTML report.
	public static void generateHtmlClosuse() throws IOException, ParseException {
		
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		String headerTemplate = objects.HtmlReport.html_bottom;
		
		headerTemplate = headerTemplate.replace("%project_instance%", ConfigFile.getProjectInstance());
		
		headerTemplate = headerTemplate.replace("%issue_type%", ConfigFile.getIssueType());
		
		String email_domain = ConfigFile.getEmailDomain();
		
		if(email_domain.contains("@")) {
			
			email_domain = email_domain.replaceAll("@", "");
			
		}
		
		headerTemplate = headerTemplate.replace("%reporter%", System.getProperty("user.name").toLowerCase()+"@"+email_domain);
		
		headerTemplate = headerTemplate.replace("%username%", System.getProperty("user.name").toLowerCase());

		pw.println(headerTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();		
		
	}
	
	//Adds the Tab Tracker to HTML report.
	public static void addTabTracker() throws IOException {
		
		FileReader fr = null;
		
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		
		PrintWriter pw = null;
		
		fw = new FileWriter(htmlReportPath, true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);
		
		String headerTemplate = objects.HtmlReport.html_bottom;

		pw.println(headerTemplate);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();		
		
	}
	
	
	//Gets the browser window title. Used to populate the top portion of HTML report.
	public static String getWindowTitle(WebDriver driver) {
		
		String windowTitle = driver.getTitle();
		
		return windowTitle;
		
	}
	
	//Gets the URL of the browser. Used to populate the top portion of HTML report.
	public static String getUrl(WebDriver driver) {
		
		String url = driver.getCurrentUrl();
		
		return url;
		
	}
	
	//Gets the current user name. Used to populate the top portion of HTML report.
	public static String getUserName() {
		
		String username = System.getProperty("user.name");
		
		return username;
		
	}


	
}
