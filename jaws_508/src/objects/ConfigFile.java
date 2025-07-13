package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigFile {
	
	static String global_email_domain = "";
	
	static String global_issue_type = "";

	static List<String> global_project_id = new ArrayList<>();
	
	static String global_project_instance = "";
	
	static String global_jaws_path = "";

	static String global_chrome_path = "";

	static int global_loop_limit = 0;

	public static boolean modal_focus = false;

	static String include_table = "";
	
	static String order_number = "";

	static String config_file = System.getProperty("user.dir") + "\\config.json";
	
	static String readme_file = System.getProperty("user.dir") + "\\README.txt";

	public static void checkFile(String file_name) throws IOException {

		if(file_name.equals("config")) {
			
			File config = new File(config_file);

			if (!config.exists()) {

				generateConfigFile();

			}
			
		}else if(file_name.equals("readme")) {
			
			File readme = new File(readme_file);

			if (!readme.exists()) {

				generateReadmeFile();

			}
			
		}

	}
	
	public static void generateReadmeFile() throws IOException {

		String readme = 
				
		"\t**--------CONFIG FILE SETUP-----**\r\n"+
		"\t1) go to https://jira.dssinc.com/rest/api/2/project/*PROJECT_KEY*\r\n"+
		"\t	GET THE FOLLOWING DATA FROM THE RESPONSE:\r\n"+
		"\t	- project key\r\n"+
		"\t	- project id\r\n"+
		"\t2) Default issue type is: 10102\r\n"+
		"\t3) Default jira instance is: jira.dssinc.com\r\n"+
		"\t4) default email domain is: dssinc.com\r\n"+
		"\t**--------EXAMPLE-------------**\r\n"+
		"\t\"jira_project_id\":[\"RXGU:10620\"],\r\n"+
		"\t\"jira_instance\":\"jira.dssinc.com\",\r\n"+
		"\t\"issue_type\":\"10102\",\r\n"+
		"\t\"email_domain\":\"dssinc.com\",\r\n"+
		"\t**----------------------------**";

		File file = new File(readme_file);

		file.createNewFile();

		// Writing into new readme file.
		FileWriter fw = null;

		BufferedWriter bw = null;

		PrintWriter pw = null;

		fw = new FileWriter(file.getPath(), true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);

		pw.println(readme);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();

	}

	public static void generateConfigFile() throws IOException {

		String config = "{\n" + "\t\"config\":{\n" +

				"\t\t\"jaws_path\":\"C:\\Program Files\\Freedom Scientific\\Inspect\\Inspect.exe\",\n" +

				"\t\t\"chrome_path\":\"C:\\chromedriver\\chromedriver.exe\",\n" +

				"\t\t\"loop_limit\":\"200\",\n" +
				
				"\t\t\"include_tables\":\"true\",\n" +
				
				"\t\t\"order_number\":\"true\",\n" +
				
				"\t\t\"jira_project_id\":[\"KEY:ID\"],\n" +
				
				"\t\t\"jira_instance\":\"place_instance_here\",\n" +
				
				"\t\t\"issue_type\":\"place_issue_type_here\",\n" +
				
				"\t\t\"email_domain\":\"place_email_domain_here\",\n" +

				"\t}\n" +

				"}\n";

		File file = new File(config_file);

		file.createNewFile();

		// Writing into new configuration file.
		FileWriter fw = null;

		BufferedWriter bw = null;

		PrintWriter pw = null;

		fw = new FileWriter(file.getPath(), true);

		bw = new BufferedWriter(fw);

		pw = new PrintWriter(bw);

		pw.println(config);

		pw.flush();

		pw.close();

		bw.close();

		fw.close();

	}
	
	public static String getOrderNumber() throws FileNotFoundException, IOException, ParseException {

		if (order_number.length() == 0) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String ord_num = ((String) config.get("order_number")).trim().toLowerCase();

			if (ord_num.contains("true")) {

				order_number = "true";

			}else {
				
				order_number = "false";
				
			}

			return order_number;

		} else {

			return order_number;

		}

	}
	
	public static String getEmailDomain() throws FileNotFoundException, IOException, ParseException {

		if (global_email_domain.length() < 1) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String email_domain = (String) config.get("email_domain");

			global_email_domain = email_domain.trim();

			return global_email_domain;

		} else {

			return global_email_domain;

		}

	}
	
	public static String getIssueType() throws FileNotFoundException, IOException, ParseException {

		if (global_issue_type.length() < 1) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String issue_type = (String) config.get("issue_type");

			global_issue_type = issue_type.trim();

			return global_issue_type;

		} else {

			return global_issue_type;

		}

	}
	
	public static String getProjectInstance() throws FileNotFoundException, IOException, ParseException {

		if (global_project_instance.length() < 1) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String project_instance = (String) config.get("jira_instance");

			global_project_instance = project_instance.trim();

			return global_project_instance;

		} else {

			return global_project_instance;

		}

	}
	
	public static List<String> getProjectId() throws FileNotFoundException, IOException, ParseException {

		if (global_project_id.size() < 1) {

		     JSONParser parser = new JSONParser();
		  
		         Object obj = parser.parse(new FileReader(config_file));
		         JSONObject jsonObject = (JSONObject)obj;		
		         JSONObject config = (JSONObject) jsonObject.get("config");
		         JSONArray projects = (JSONArray)config.get("jira_project_id");
		         Iterator iterator = projects.iterator();
		         
		         List<String>project_list = new ArrayList<>();
		         
		         while (iterator.hasNext()) {
		        	 
		            String entry = iterator.next().toString();
		            project_list.add(entry);
		            
		         }

			global_project_id = project_list;

			return global_project_id;

		} else {

			return global_project_id;

		}

	}

	public static String getJawsExePath() throws FileNotFoundException, IOException, ParseException {

		if (global_jaws_path.length() < 1) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String jaws_path = (String) config.get("jaws_path");

			global_jaws_path = jaws_path.trim();

			return global_jaws_path;

		} else {

			return global_jaws_path;

		}

	}

	public static String getChromeDriverPath() throws FileNotFoundException, IOException, ParseException {

		if (global_chrome_path.length() < 1) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String chrome_path = (String) config.get("chrome_path");

			global_chrome_path = chrome_path.trim();

			return global_chrome_path;

		} else {

			return global_chrome_path;

		}

	}

	public static Integer getLoopLimit() throws FileNotFoundException, IOException, ParseException {

		if (global_loop_limit == 0) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String loop_limit = (String) config.get("loop_limit");

			int limit = Integer.parseInt(loop_limit);

			global_loop_limit = limit;

			return global_loop_limit;

		} else {

			return global_loop_limit;

		}

	}

	public static Boolean getModalFocus() throws FileNotFoundException, IOException, ParseException {

		return modal_focus;

	}
	
	public static String getIncludeTable() throws FileNotFoundException, IOException, ParseException {

		if (include_table.length() == 0) {

			JSONParser parser = new JSONParser();

			Object obj_file = parser.parse(new FileReader(config_file));

			JSONObject jsonObject = (JSONObject) obj_file;

			JSONObject config = (JSONObject) jsonObject.get("config");

			String inc_table = ((String) config.get("include_tables")).trim().toLowerCase();

			if (inc_table.contains("true")) {

				include_table = "true";

			}else {
				
				include_table = "false";
				
			}

			return include_table;

		} else {

			return include_table;

		}

	}



}
