package objects;

import io.github.bonigarcia.wdm.config.Config;

public class HtmlReport {
	
	public static String html_top = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> " + 
			"<html lang=\"en\"> " + 
			  
			"<head> " + 
			"  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"> " + 
			"  <title>Automated JAWS Test Results</title> " + 
			"  <link href=\"https://fonts.googleapis.com/css2?family=Raleway&family=Roboto&family=Source+Sans+Pro&display=swap\" "+  
			"    rel=\"stylesheet\"> " + 
			"  <style> " + 
			"    body { " + 
			  
			"      font-family: 'Roboto', sans-serif; " + 
			"      background: #fcfcfc; " + 
			"    } " +			  
			"    .report-title { " + 
			"      font-size: 20px; " + 
			"      color: red; " + 
			"      font-weight: 700; " + 
			"      margin-left: 10px; " + 
			"      margin-bottom: 30px; " + 
			"      position: relative; " + 
			"    } " +			  
			"    .container h1 { " + 
			"      text-align: center; " + 
			"      margin-bottom: 45px; " + 
			"      color: #424242; " + 
			"    } " +			  
			"    .tab-tracker { " + 
			"      /* position: absolute; " + 
			"      right: 0; " + 
			"      top: 4px; */ " + 
			"    } " +			  
			"    .main-table { " + 
			"      width: 100%; " + 
			"      text-align: left; " + 
			"      border: 1px solid #c7c7c7; " + 
			"      border-spacing: 0; " + 
			"      background-color: #ffffff; " + 
			"      margin-bottom: 40px; " + 
			"    } " +			  
			"    .header { " + 
			"      vertical-align: middle; " + 
			"      border-collapse: collapse; " + 
			"      width: 15%; " + 
			"    } " +			  
			"    .value { " + 
			"      vertical-align: middle; " + 
			"      font-size: 15px; " + 
			"      border-collapse: collapse; " + 
			"      width: 60%;  " + 
			"    } " +			  
			"    .image { " + 
			"      width: 280px; " + 
			"      height: 125px; " + 
			"      border-right: none !important; " + 
			"      text-align: center; " + 
			"	   color:blue;"+
			"    } " +
			"    .error-image { " + 
			"      width: 280px; " + 
			"      height: 125px; " + 
			"      border-right: none !important; " + 
			"      text-align: center; " + 			
			"    } " +	
			"    img { " + 
			"      max-height: 125px; " +
			"      max-width: 100%; " + 
			"    } " +			  
			"    .main-table tr:nth-child(even) { " + 
			"      background-color: #e5e5e5; " + 
			"    } " + 			  
			"    .main-table tr td { " + 
			"      padding: 10px; " +
			"      max-width: 380px; " + 
			"      word-wrap:break-word; " + 
			"    } " +			  
			"    .main-table tr td { " + 
			"      border-right: 1px solid #c7c7c7; " + 
			"    } " +
			"    .container { " + 
			"      width: 90%; " + 
			"      max-width: 1200px; " + 
			"      margin: 25px auto; " + 
			"      padding-left: 5px; " + 
			"      padding-top: 5px; " + 
			"      padding-bottom: 5px; " + 
			"    } " +			  
			"    .main-table .log-name { " + 
			"      color: #2d44ba; " + 
			"      font-size: 15px; " + 
			"      font-weight: 700; " + 
			"    } " +	
			"    .main-table .error-name { " + 
			"      color:#ff0505; " + 
			"      font-size: 18px; " + 
			"      font-weight: 700; " + 
			"    } " +	
			"    .title-table { " + 
			"      position: relative; " + 
			"      border: none; " + 
			"      background-color: #fcfcfc; " + 
			"      color: red; " + 
			"    } " +			  
			"    .title-table tr { " + 
			"      background: none !important; " + 
			"    } " +			  
			"    .title-table td { " + 
			"      border-right: none !important; " + 
			"      padding: 5px !important; " + 
			"    } " +			  
			"    .title-table td .log-name { " + 
			"      color: red !important; " + 
			"    } " + 			  
			"    .title-table .tab-tracker { " + 
			"      position: absolute; " + 
			"      right: 0; " + 
			"      top: 10px; " + 
			"      font-size: 15px; " + 
			"      font-weight: bold; " + 
			"    } " +
			"	.error-flag{ "+
			"		background-color: #cc2208;" +
			"		width: 5px;" +
			"		min-width: 5px;" +
			"	 } " +
			"	.bug_link_container{ "+
			"		margin: 10px;"+
			"	 } " +
			"	.bug_link{ "+
			"		color: blue;"+
			"		text-decoration:none;"+
			"		font-size: 17px;"+
			"		font-weight: 700;"+
			"	 } " +
			"	a {"+
			"		text-decoration: none;"+
			"		cursor:pointer;"+
			"	}"+
			"	.copy{"+
			"		margin-left: 5px;"+
			"		margin-right: 5px;"+
			"		display: none;"+
			"	}"+
			

			".modal {"+
			  "display: none;"+ 
			  "position: fixed;"+ 
			  "z-index: 1;"+ 
			  "padding-top: 100px;"+ 
			  "left: 0;"+
			  "top: 0;"+
			  "width: 100%;"+ 
			  "height: 100%;"+ 
			  "overflow: auto;"+ 
			  "background-color: rgb(0,0,0);"+ 
			  "background-color: rgba(0,0,0,0.4);"+			 
			"}"+
			
			".modal-content {"+
			  "background-color: #fefefe;"+
			  "margin: auto;"+
			  "padding: 20px;"+
			  "border: 1px solid #888;"+
			  "width: 60%;"+
			  "height:60%;"+
			  "position:relative;"+
			"}"+
			  
			".close {"+
			  "color: #aaaaaa;"+
			  "float: right;"+
			  "font-size: 28px;"+
			  "font-weight: bold;"+
			"}"+

			".close:hover,"+
			".close:focus {"+
			 "color: #000;"+
			 "text-decoration: none;"+
			 "cursor: pointer;"+
			"}"+
			 
			
			".div-table {"+       
			"	  width: 80%;"+         
			"	  background-color: #eee;"+         
			"	  border: 1px solid #666666;"+         
			"	  border-spacing: 5px;"+ 
			"	  margin: 70px 70px 20px 110px;"+
			"	  max-height: 60%;"+
			"	  overflow-y: scroll;"+
			"	  padding: 20px;"+
			"	}"+
			"	.div-table-row {"+
			"	  display: table-row;"+
			"	  width: auto;"+
			"	  clear: both;"+
			"	}"+
			"	.div-table-col-one {"+
			"	  float: left;"+ 
			"	  display: table-column;"+         
			"	  width: 70px;"+
			"	  text-align: left;"+	
			"	}"+
			
			"	.div-table-col-two {"+
			"	  float: left;"+ 
			"	  display: table-column;"+         
			"	  width: 800px;"+
			"	  text-align: left;"+
			"	}"+
			"	.step_description{"+
			"	 width:99%;"+
			"	 margin: 5px 0;"+
			"	}"+
			"	.add_next_step{"+
			"	float:right;"+
			"   margin: 20px 5px 10px;"+
			"	}"+
			".modal_buttons{"+

				"position: absolute;"+
				"bottom: 30px;"+
		   		"right: 30px;"+
			"}"+

			"  </style> " + 
			"</head> " + 	
			"<body> " + 		
			"  <div class=\"container\"> " + 
			"    <h1>Screen Reader Report</h1> " +			  
			"    <table class=\"main-table title-table\"> " + 			  
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Report for: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"          %window_title% " + 
			"        </td> " + 
			"        <td class=\"image\" rowspan=\"6\"> " + 
			"          <a href=\"./images/%tab_tracker%\" target=\"_blank\" rel=\"noopener noreferrer\" class=\"tab-tracker\">View Tab Tracker</a> " + 
						
					"<label for=\"filter\" style=\"margin-left: 5px;font-weight: 700;font-size: 15px;display: block;margin-top: 40px;text-align: right;\">Report Filter:<br><select onclick=\"_filter(event)\" id=\"filter\" name=\"jira_project\" style=\"margin-top: 5px;\">"+
					"<option selected value=\"0\"> Show all </option>"+
					"<option value=\"1\"> Show bugs </option>"+
					"</select></label>"+
		
					"<label id=\"label_project\" for=\"jira_project\" style=\"margin-left: 5px;font-weight: 700;font-size: 15px;display: block;margin-top: 20px;text-align: right;\">Select Jira Project:<br><select onclick=\"_selectProject(event)\" id=\"project_select\" name=\"jira_project\" style=\"margin-top: 5px;\">"+
					"<option disabled selected value=\"0\"> --Select Project-- </option>"+
					"%project_options%"+
					"</select></label>"+
					
					"<label for='add_step' style='margin-left: 5px;font-weight: 700;font-size: 15px;display: block;margin-top: 20px;text-align: right;'>Steps to reproduce:</label>"+
					"<button id='add_step' onclick='_addStep()' name='add_step' style='margin-top: 5px; float: right;'>Add Step</button>"+
			
					"<br><label for='version' style='margin-left: 5px;font-weight: 700;font-size: 15px;display: block;margin-top: 20px;text-align: right;'>Version Number:</label>"+
					"<input onclick='_inputVersion()' id='version_number' name='version' style='margin-top: 5px; float: right; width:130px; max-width: 130px!important;'>"+


					"<div id='myModal' class='modal'>"+
					
					  "<div class='modal-content'>"+
					    "<span id='close_modal' onclick='_closeAddStep()' class='close'>&times;</span>"+
					  
						"				<h3>Steps to reproduce</h3>"+
					      
					      "<div class='div-table' id='steps_container'>"+
							"	             <div class='div-table-row'>"+
						
							"	                <div  class='div-table-col-one'> Step # </div>"+
							"                <div  class='div-table-col-two'> Step Description</div>"+
							"	             </div>"+
							"	            <div class='div-table-row'>"+								              
							"	                <div class='div-table-col-one'>1</div>"+
							"	                <div class='div-table-col-two'><input maxlength='100' class='step_description' spellcheck='true'></input></div>"+
							"	            </div>"+								            
							"	      </div>"+
					    
					   
					   "<div class='modal_buttons'>"+
					   
					    "<button class='add_next_step' onclick='_addNextStep()'>Add step</button>"+
					    "<button class='add_next_step' onclick='_closeAddStep()' style='background-color: limegreen; color: blue;'>Save</button>"+
					    
					  "</div>"+  
					  "</div>"+
					
					"</div>"+
			
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">URL: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"page_url\"> " + 
			"          %url% " + 
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Execution date: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"errorLog\"> " + 
			"          %current_date% " + 
			"        </td> " + 
			"      </tr> " +
			"		<tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Executed by: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"          %username% " + 
			"        </td> " + 
			"      </tr> " + 
			"		<tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Total bugs found: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"total_bugs\"> " + 
			"          0 " + 
			"        </td> " + 
			"      </tr> " + 
			"    </table>";
	
	
	public static String html_loop_table = "<table class=\"main-table elements\" id=\"%element_number_%\"> " + 			
			"      <tr class=\"row\"> " +
			
			//check-box to select the table.
			"<td rowspan=\"5\" class=\"checkbox_container\"><input class=\"display_hide_box\" type=\"checkbox\" onclick=\"_displayInputBoxes('%element_number_%')\"></td>"+
			
			
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Tab order: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " +
			"		 <value_tab_order class=\"value\">"+		
			"          %tab_order_index% " + 
			"		 </value_tab_order>"+	
			"        </td> " + 
			"        <td class=\"image\" rowspan=\"5\"> " + 
			"          <br><br><a class='el_screenshot' href=\"./images/%background_image%\" target=\"_blank\" rel=\"noopener noreferrer\"> " + 
			"            <img src=\"./images/%element_image%\" alt=\"\"> " + 
			"          </a> " +			
			
			
			"<div class=\"bug_link_container\">"+
			
			"<a  onclick=\"_createBug('%element_number_%', '%link_number_%')\" class=\"bug_link\" id=\"%link_number_%\" style=\"visibility:hidden;\">Create Jira Bug</a>"+
			
			"</div>"+
			
			"<div class='jira_link' style='margin: 10px; display: none;'><label class='label_jira_number' for='jira_number'>Jira #: </label><input onclick='_jiraTicket(event)' class='jira_number' name='jira_number' style='width: 100px; max-width: 100px!important;'></div>"+
			
			//Mark as reported.
			"<label class=\"mark_reported\" for='reported' style='display: none;margin-top: 20px;color: lightgrey;font-weight: 700;'>"+
			"Mark as reported"+
			"<input disabled class=\"report-checkbox\" style=\"display:block;margin: 5px auto;\" name='reported' type='checkbox' onclick=\"_markReported('%element_number_%', '%link_number_%', event)\"></label>"+
			
			//Copy buttons.
			"<div style='display:block; margin-top: 20px; margin-bottom: 10px;'>"+
			"<button class='copy_path copy' onclick=\"_getFilePath(event)\">Copy File Path</button>"+
			"<button class='copy_file copy' onclick=\"_getFileName('%element_number_%', event)\" >Copy File Name</button>"+
			"</div>"+
			
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Jaws speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"		 <value_jaws_speech class=\"value\">"+   
			"          %jaws_speech_transcript% " + 
			"		 </value_jaws_speech>"+	
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Element xpath: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"errorLog\"> " + 
			"		 <value_xpath class=\"value\">"+	
			"          %xpath% " + 
			"		 </value_xpath>"+
			"        </td> " + 
			"      </tr> " + 
			
			//Free text fields.
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Expected speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value expected-speech\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"expected_speech\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Other comments: </span> " + 
			"        </td> " + 
			"        <td class=\"value other-comments\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"comments\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
						
			"    </table>";
	
	public static String html_loop_table_bug = "    <table style=\"border-radius: 3px; border: 5px solid orange;\" class=\"main-table elements\" id=\"%element_number_%\">" + 	
	
			"      <tr class=\"row\"> " +
			
			//check-box to select the table.
			"<td style=\"background-color: orange;\" rowspan=\"6\" class=\"checkbox_container\"><input class=\"display_hide_box\" type=\"checkbox\" onclick=\"_displayInputBoxes('%element_number_%')\"></td>"+

			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Tab order: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"		 <value_tab_order class=\"value\">"+
			"          %tab_order_index% " + 
			"		 </value_tab_order>"+
			"        </td> " + 
			"        <td class=\"image\" rowspan=\"6\"> " + 
			"          <a class='el_screenshot' href=\"./images/%background_image%\" target=\"_blank\" rel=\"noopener noreferrer\"> " + 
			"            <img src=\"./images/%element_image%\" alt=\"\"> " + 
			"          </a> " + 
			
			"<div class=\"bug_link_container\">"+
			
			"<a  onclick=\"_createBug('%element_number_%', '%link_number_%')\" class=\"bug_link\" id=\"%link_number_%\" style=\"visibility:hidden;\">Create Jira Bug</a>"+
			
			"</div>"+
			
			"<div class='jira_link' style='margin: 10px; display: none;'><label class='label_jira_number' for='jira_number'>Jira #: </label><input onclick='_jiraTicket(event)' class='jira_number' name='jira_number' style='width: 100px; max-width: 100px!important;'></div>"+

			//Mark as reported.
			"<label class=\"mark_reported\" for='reported' style='display: none;margin-top: 20px;color: lightgrey;font-weight: 700;'>"+
			"Mark as reported"+
			"<input disabled class=\"report-checkbox\" style=\"display:block;margin: 5px auto;\" name='reported' type='checkbox' onclick=\"_markReported('%element_number_%', '%link_number_%', event)\"></label>"+

			//Copy buttons.
			"<div style='display:block; margin-top: 20px; margin-bottom: 10px;'>"+
			"<button class='copy_path copy' onclick=\"_getFilePath(event)\">Copy File Path</button>"+
			"<button class='copy_file copy' onclick=\"_getFileName('%element_number_%', event)\" >Copy File Name</button>"+
			"</div>"+
			
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Jaws speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"		 <value_jaws_speech class=\"value\">"+
			"          %jaws_speech_transcript% " +
			"		 </value_jaws_speech>"+	
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Element xpath: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"errorLog\"> " + 
			"		 <value_xpath class=\"value\">"+
			"          %xpath% " + 
			"		 </value_xpath>"+
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Error log: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"errorLog\"> " + 
			"          %error_log% " + 
			"        </td> " + 
			"      </tr> " + 
			
			//Free text fields.
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Expected speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value expected-speech\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"expected_speech\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Other comments: </span> " + 
			"        </td> " + 
			"        <td class=\"value other-comments\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"comments\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
			"    </table>";
	
	
	
	public static String html_infinite_loop = "    <table style=\"border-radius: 3px; border: 5px solid orange;\" class=\"main-table elements\" id=\"%element_number_%\">" + 			
			"      <tr class=\"row\"> " +
			
			//check-box to select the table.
			"<td style=\"background-color: orange;\" rowspan=\"7\" class=\"checkbox_container\"><input class=\"display_hide_box\" type=\"checkbox\" onclick=\"_displayInputBoxes('%element_number_%')\"></td>"+

			"        <td class=\"header\"> " + 
			"          <span class=\"error-name\">Error </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"          <span class=\"error-name\">"+
			"		 <value_tab_order class=\"error-name\">"+
			"			Infinite loop encountered. Forced execution stop."+
			"		 </value_tab_order>"+
			"			</span> " + 
			"        </td> " + 
			"        <td class=\"error-image\" rowspan=\"7\"> " + 
			"          <a class='el_screenshot' href=\"./images/%background_image%\" target=\"_blank\" rel=\"noopener noreferrer\"> " + 
			"            <img src=\"./images/%element_image%\" alt=\"\"> " + 
			"          </a> " +
			
			"<div class=\"bug_link_container\">"+
			
			"<a  onclick=\"_createBug('%element_number_%', '%link_number_%')\" class=\"bug_link\" id=\"%link_number_%\" style=\"visibility:hidden;\">Create Jira Bug</a>"+
			
			"</div>"+
			
			"<div class='jira_link' style='margin: 10px; display: none;'><label class='label_jira_number' for='jira_number'>Jira #: </label><input onclick='_jiraTicket(event)' class='jira_number' name='jira_number' style='width: 100px; max-width: 100px!important;'></div>"+
			
			//Mark as reported.
			"<label class=\"mark_reported\" for='reported' style='display: none;margin-top: 20px;color: lightgrey;font-weight: 700;'>"+
			"Mark as reported"+
			"<input disabled class=\"report-checkbox\" style=\"display:block;margin: 5px auto;\" name='reported' type='checkbox' onclick=\"_markReported('%element_number_%', '%link_number_%', event)\"></label>"+

			//Copy buttons.
			"<div style='display:block; margin-top: 20px; margin-bottom: 10px;'>"+
			"<button class='copy_path copy' onclick=\"_getFilePath(event)\">Copy File Path</button>"+
			"<button class='copy_file copy' onclick=\"_getFileName('%element_number_%', event)\" >Copy File Name</button>"+
			"</div>"+
			
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"error-name\">Jaws speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value\"> " + 
			"		 <value_jaws_speech class=\"value\">"+ 
			"          %jaws_speech_transcript% " + 
			"		 </value_jaws_speech>"+	
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"error-name\">Element xpath: </span> " + 
			"        </td> " + 
			"        <td class=\"value\" id=\"errorLog\"> " + 
			"		 <value_xpath class=\"value\">"+
			"          %xpath% " +
			"		 </value_xpath>"+
			"        </td> " + 
			"      </tr> " + 
			
			//Free text fields.
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Expected speech: </span> " + 
			"        </td> " + 
			"        <td class=\"value expected-speech\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"expected_speech\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
			"      <tr class=\"row freetext\" style=\"display:none;\"> " + 
			"        <td class=\"header\"> " + 
			"          <span class=\"log-name\">Other comments: </span> " + 
			"        </td> " + 
			"        <td class=\"value other-comments\" id=\"errorLog\"> " + 
						"<textarea  maxlength=\"200\" rows=\"2\" style=\"width:100%;\" class=\"comments\" oninput=\"_enableBugLink('%element_number_%')\"></textarea>" +
			"        </td> " + 
			"      </tr> " + 
			"    </table>";
	
	public static String html_bottom = "  </div> " + 
			"</body> " +
			"<script>"+
		
			//warning before closing.
        "window.addEventListener('beforeunload', function (e) {"+
         "   e.preventDefault();"+
         "  e.returnValue = '';"+
        "});"+
			
			"function _createBug(table_id, link_id){"+
			
			"let project_select = document.getElementById('project_select');"+
			"let project_value = project_select.value;"+
			"if(project_value !== '0' && _stepsExist() === true && _checkVersion() === true){"+
			

				
				"_changeLinkText(link_id, table_id);"+
			
				"let jira_project_id = project_value;"+
				"const jira_project_instance = '%project_instance%';"+
				"const issue_type = '%issue_type%';"+
				"const reporter = 'Reporter: %reporter%%0A';"+
				"const summary = '508-';"+
				"const origin = 'Origin: Jaws Automation Tool%0A';"+
				"const username = '%username%';"+
				
				
				"let el_table = document.getElementById(table_id);"+
				"let tab_order = 'Tab Order: '+_urlEncode(el_table.getElementsByTagName('value_tab_order')[0].innerText)+'%0A';"+
				"let jaws_speech = 'Actual Jaws Speech: '+_urlEncode(el_table.getElementsByTagName('value_jaws_speech')[0].innerText)+'%0A';"+
				"let xpath = 'Element Xpath: '+_urlEncode(el_table.getElementsByTagName('value_xpath')[0].innerText)+'%0A';"+
				"let page_url = 'Page URL: '+_urlEncode(document.getElementById('page_url').innerText)+'%0A';"+
				"let expected_speech = 'Expected Speech: '+_urlEncode(el_table.getElementsByClassName('expected_speech')[0].value)+'%0A';"+
				"let comments = 'Other Comments: '+_urlEncode(el_table.getElementsByClassName('comments')[0].value)+'%0A';"+
				"let steps_to_reproduce = _getStepsToReproduce();"+
				"let version_number = _getVersionNumber();"+
				
				
				"let jira_link = `https://${jira_project_instance}/secure/CreateIssueDetails!init.jspa?`+"
				+ "`pid=${jira_project_id}`+"
				+ "`&issuetype=${issue_type}`+"
				+ "`&labels=Jaws_Automation`+"
				+ "`&reporter=${username}`+"
				+ "`&summary=${summary}`+"
				+ "`&customfield_10400=10202`+"
				+ "`&priority=3`+"
				+ "`&customfield_10401=10210`+"
				+ "`&description=__Actual%20results__%0A${tab_order}${jaws_speech}${xpath}${page_url}${reporter}${version_number}`+"
				+ "`${origin}%0A%0A__Expected%20results__%0A${expected_speech}${comments}`+"
				+ "`${steps_to_reproduce}`+"
				//customfield_10301 - Acceptance criteria.
				+ "`&customfield_10301=Expected%20results%0A${expected_speech}${comments}`;"+
				
					
				"el_table.getElementsByClassName('jira_link')[0].style.display = 'block';"+
				
				"window.open(jira_link, '_blank');"+			
			
			"}else{"+
			
				
				"if(project_value === '0'){"+
				"project_select.style.borderColor = 'red';"+
				"project_select.style.borderWidth = '3px';"+
				"setTimeout(function(){"+
					"alert('You must select a Jira project to log a new bug.');"+
				"}, 1000);"+
				"}else if(_stepsExist() === false){"+
				
					"document.getElementById('add_step').style.borderColor = 'red';"+					
					"setTimeout(function(){"+
					"alert('You must add steps to reproduce to log a new bug.');"+
				"}, 1000);"+
				
				"}else if(_checkVersion() === false){"+
				
					"document.getElementById('version_number').style.borderColor = 'red';"+
					"setTimeout(function(){"+
					"alert('You must enter the version number to log a new bug.');"+
				"}, 1000);"+
				
				"}"+
				
				
			
			"}"+
						
			"}"+
			
			
			
			//Changes the "Create Jira Bug" text to "Bug Reported" and disables the click.		
			"function _changeLinkText(link_id, table_id){"+
			
			"let link = document.getElementById(link_id);"+
			//"link.text = 'Bug Reported';"+
			"link.style.color = '#CF25CE';"+
			//"link.style.pointerEvents = 'none';"+
			
			"let table = document.getElementById(table_id);"+
			"let mark_reported_box = table.getElementsByClassName('report-checkbox')[0];"+
			"mark_reported_box.disabled = false;"+
			"let mark_reported_label = table.getElementsByClassName('mark_reported')[0];"+
			"mark_reported_label.style.color = 'green';"+
			
			"}"+
			
			//Disables the free text areas.
			"function _disableTextAreas(table_id){"+
			
			"let table = document.getElementById(table_id);"+
			
			
			"let td_expected_speech = table.getElementsByClassName('value expected-speech')[0];"+
			"let expected_speech = table.getElementsByClassName('expected_speech')[0];"+	
			"let expected_text = expected_speech.value;"+
			
			"expected_speech.remove();"+
			"td_expected_speech.innerHTML = expected_text;"+
			
			"let td_expected_comments = table.getElementsByClassName('value other-comments')[0];"+
			"let comments = table.getElementsByClassName('comments')[0];"+
			"let expected_comments = comments.value;"+
			
			"comments.remove();"+
			"td_expected_comments.innerHTML = expected_comments;"+
			
			"}"+
			
			
			//Enables the bug link on text entry into "Expected Speech" field
			"function _enableBugLink(table_id){"+
			"let table = document.getElementById(table_id);"+
			"let expected_speech = table.getElementsByClassName('expected_speech')[0].value.replace(/ /g,'');"+
			"let comments = table.getElementsByClassName('comments')[0].value.replace(/ /g,'');"+
			"if(expected_speech.length > 0 || comments.length > 0){"+
			
			"let link = table.getElementsByClassName('bug_link')[0];"+
			
			"if(link.style.visibility !== 'visible'){"+
			"link.style.visibility = 'visible';"+
			"let mark_reported = table.getElementsByClassName('mark_reported')[0];"+
			"mark_reported.style.display = 'block';"+
			
			//Enable copy buttons.
			"let copy_path = table.getElementsByClassName('copy_path copy')[0];"+
			"let copy_name = table.getElementsByClassName('copy_file copy')[0];"+
			"copy_path.style.display = 'inline';"+
			"copy_name.style.display = 'inline';"+
			
			"}"+
			
			"}else{"+
			
			"let link = table.getElementsByClassName('bug_link')[0];"+
			"if(link.style.visibility !== 'hidden' && link.text !== 'Bug Reported'){"+
			"link.style.visibility = 'hidden';"+
			"let mark_reported = table.getElementsByClassName('mark_reported')[0];"+
			"mark_reported.style.display = 'none';"+
			
			//disable copy buttons.
			"let copy_path = table.getElementsByClassName('copy_path copy')[0];"+
			"let copy_name = table.getElementsByClassName('copy_file copy')[0];"+
			"copy_path.style.display = 'none';"+
			"copy_name.style.display = 'none';"+
			
			"}"+
			
			"}"+
			


			"}"+
			
			
			
			
			//Retains only failing elements.
			"function _showBugs(){"+
			
			"let tables = document.getElementsByClassName('main-table');"+
			"Array.prototype.forEach.call(tables, function(table) {"+
	
			    "let current_table = table;"+
				"let bug_link = '';"+
				"try{bug_link = current_table.getElementsByClassName('bug_link')[0].text;}catch(error){}"+
				"if(bug_link !== '' && bug_link !== 'Bug Reported'){"+
				"current_table.style.display = 'none';"+		
				"}"+
			"});"+
			"}"+
			
			//Displays all tested elements.
			"function _showAll(){"+
			
			"let tables = document.getElementsByClassName('main-table');"+
			"Array.prototype.forEach.call(tables, function(table) {"+
			    // Do stuff here
			    "let current_table = table;"+
			    "current_table.style.removeProperty('display');"+						
				
			"});"+
			"}"+
			
			//Updates the total number of bugs found.
			"function _updateTotalBug(){"+

			"let total = document.getElementById('total_bugs');"+
			"let total_num = parseInt(total.innerHTML);"+
			"let increment = total_num + 1;"+
			"total.innerHTML = increment.toString();"+
			
			"}"+
			
			//Removes the checkbox after bug creation.
			"function _hideCheckBox(table_id){"+
			
			"let table = document.getElementById(table_id);"+
			"let checkbox = table.getElementsByClassName('display_hide_box')[0];"+
			"checkbox.disabled = true;"+
			
			"let entry_field = table.getElementsByClassName('row freetext');"+
			"for(let i=0; i<entry_field.length; i++){"+
			"entry_field[i].style.removeProperty('display');"+
			"}"+
			
			
			"}"+
			
			//Display input fields.
			"function _displayInputBoxes(table_id){"+
			
			"let all_tables = document.getElementsByClassName('main-table elements');"+
			"for(let i=0; i<all_tables.length; i++){"+
			
			"let table_item = all_tables[i];"+
			"let table_checkbox = table_item.getElementsByClassName('display_hide_box')[0];"+
			

			
			"if(table_item.id !== table_id && table_checkbox.checked && !table_checkbox.disabled){"+
			
			"table_checkbox.checked = false;"+
	      	"table_item.style.removeProperty('border-radius');"+
	      	"table_item.style.removeProperty('border');"+
	      	
			"let exp_speech = table_item.getElementsByClassName('expected_speech')[0].value.replace(/ /g,'');"+
			"let comments = table_item.getElementsByClassName('comments')[0].value.replace(/ /g,'');"+
			
			"if(exp_speech.length < 1 && comments.length < 1){"+
			
				"let copy_path = table_item.getElementsByClassName('copy_path copy')[0].style.display = 'none';"+
					"let copy_file = table_item.getElementsByClassName('copy_file copy')[0].style.display = 'none';"+
				"let entry_field = table_item.getElementsByClassName('row freetext');"+
				"for(let i=0; i<entry_field.length; i++){"+
				
				"entry_field[i].style.display = 'none';"+
					
				"}"+
			
	      	"}"+
			
	      	
			
			"}"+
			
			"let table = document.getElementById(table_id);"+
			"let checkbox_checked = table.getElementsByClassName('display_hide_box')[0].checked;"+
			
		    "if(checkbox_checked){"+
		        
				"table.style.borderRadius = '3px';"+
				"table.style.border = '3px solid blue';"+
		        "let entry_field = table.getElementsByClassName('row freetext');"+
		        "for(let i=0; i<entry_field.length; i++){"+
		        "entry_field[i].style.removeProperty('display');"+
		        "}"+
		        
		      "}"+
		      "else{"+
		        
		      	"table.style.removeProperty('border-radius');"+
		      	"table.style.removeProperty('border');"+
		      	
		      	
			"let exp_speech = table.getElementsByClassName('expected_speech')[0].value.replace(/ /g,'');"+
			"let comments = table.getElementsByClassName('comments')[0].value.replace(/ /g,'');"+
			
			"if(exp_speech.length < 1 && comments.length < 1){"+
			
		        "let entry_field = table.getElementsByClassName('row freetext');"+
		        "for(let i=0; i<entry_field.length; i++){"+
		        
		        	"entry_field[i].style.display = 'none';"+
		        
		        "}"+
			
			"}"+ 		        

		     "}"+


						
			"}"+
			"}"+
			
			"function _markReported(table_id, link_id, event){"+
			
			"let jira_number = document.getElementById(table_id).getElementsByClassName('jira_number')[0].value.trim();"+
			
			"if(jira_number.length > 0){"+
			"let link = document.getElementById(link_id);"+
			"link.text = 'Bug Reported';"+
			"link.style.color = 'red';"+
			"link.style.pointerEvents = 'none';"+
			
			"if(event.target.checked){"+
			
			"_updateTotalBug();"+
			"_hideCheckBox(table_id);"+
			
			"_disableTextAreas(table_id);"+
			
			"let table = document.getElementById(table_id);"+
			"table.style.borderRadius = '3px';"+
			"table.style.border = '5px solid red';"+
			"table.getElementsByClassName('checkbox_container')[0].style.backgroundColor = 'red';"+
			
			"let mark_reported_box = table.getElementsByClassName('report-checkbox')[0];"+
			"mark_reported_box.disabled = true;"+
						
			"let mark_reported_label = table.getElementsByClassName('mark_reported')[0];"+
			"mark_reported_label.style.color = 'lightgrey';"+
			"document.getElementById(table_id).getElementsByClassName('jira_number')[0].disabled = true;"+
			"document.getElementById(table_id).getElementsByClassName('label_jira_number')[0].style.color = 'red';"+
			
			"}"+
			
			"}else{"+
			
			"event.target.checked = false;"+
			"document.getElementById(table_id).getElementsByClassName('jira_number')[0].style.borderColor = 'red';"+
				"setTimeout(function(){"+
				"alert('You must enter a Jira ticket number first.');"+
			"}, 1000);"+
			
			"}"+
			
			"}"+
			
			"function _selectProject(event){"+
			
			"let select_value = event.target.value;"+
			
			"if(select_value !== '0'){"+
			
			"let select_box = document.getElementById('project_select');"+
			//"select_box.disabled = true;"+
			"select_box.style.removeProperty('border-color');"+
			"select_box.style.removeProperty('border-width');"+
			
			//"document.getElementById('label_project').style.color = 'lightgrey';"+
			
			"}"+
			
			"}"+
			
			"function _filter(event){"+
			
				"if(event.target.value === '0'){"+
				
					"_showAll();"+
				
				"}else if(event.target.value === '1'){"+
				
					"_showBugs();"+
				
				"}"+
			
			"}"+
			
			//Enters new line(%0A) between specified indexes to allwo linebreak in 
			"function _splitString(value, index){"+
			
				    
				    "let new_string = '';"+
			
				    "while(value.length > 0){"+
			
				        "try {"+
				            
				            "let chunk = value.substring(0, index);"+
			
				            "new_string = new_string+chunk+'%0A';"+
			
				            "value = value.substring(index);"+
			
				        "} catch (error) {"+
			
				            "new_string = new_string+value+'%0A';"+
				            "value='';"+
				            
				        "}"+
			
				    "}"+
				    
				    "return new_string;"+
			
				"}"+
		
		//Replaces the special character for URI encoding.		
		"function _urlEncode(value){"+

		"return encodeURIComponent(value);"+

		"}"+
		
		
			//Gets image path.
			"function _getFilePath(event){"+
			
			"let html_path = window.location.pathname;"+
		    
		    "html_path = html_path.substring(1, html_path.lastIndexOf('/'))+'\\\\images';"+

		    "html_path = html_path.split('/').join('\\\\');"+

		    //This is the folder path to the image.
		    "html_path = decodeURIComponent(html_path);"+

			"(navigator.clipboard.writeText(html_path))? event.target.innerHTML = 'Path Copied!' : alert('Unable to copy the file path.');"+
			
			

					
			"}"+
			
			
			//Gets image name.
			"function _getFileName(table_id, event){"+
			
 			"let table = document.getElementById(table_id);"+

		    "let el_img_href = table.getElementsByClassName('el_screenshot')[0].href;"+

		    //This is the element screenshot filename.
		    "let el_img_filename = el_img_href.substring(el_img_href.lastIndexOf('/')+1);"+
		    
			"(navigator.clipboard.writeText(el_img_filename)) ? event.target.innerHTML = 'Name Copied!' : alert('Unable to copy file name.');"+
		  
			
			"}"+
			
			"function _addStep(){"+
			
			"document.getElementById('add_step').style.removeProperty('border-color');"+	
			
			"let modal = document.getElementById('myModal');"+
			
			"modal.style.display = 'block';"+
			
			"}"+
			
			
			"function _closeAddStep(){"+
			
			"let close_modal = document.getElementById('close_modal');"+
			
			"let modal = document.getElementById('myModal');"+
			
			"modal.style.display = 'none';"+
			
			"}"+
			
			"function _addNextStep(){"+
			
			"let row = document.createElement('div');"+
			"row.setAttribute('class', 'div-table-row');"+
			"let col_one = document.createElement('div');"+
			"col_one.setAttribute('class', 'div-table-col-one');"+
			"let total_rows = document.getElementById('steps_container').getElementsByClassName('div-table-row').length;"+
			"col_one.textContent = total_rows.toString();"+
			"let col_two = document.createElement('div');"+
			"col_two.setAttribute('class', 'div-table-col-two');"+
			"let input = document.createElement('input');"+
			"input.setAttribute('class', 'step_description');"+
			"input.setAttribute('spellcheck', 'true');"+
			"input.setAttribute('maxlength', '100');"+
			"col_two.appendChild(input);"+
			"row.appendChild(col_one);"+
			"row.appendChild(col_two);"+
		    "let main_container = document.getElementById('steps_container');"+
		    "main_container.appendChild(row);"+
		    "main_container.scrollTop = main_container.scrollHeight;"+		
			
			"}"+
			
			
			"function _stepsExist(){"+
			
			"let steps_container = document.getElementById('steps_container');"+
			"let description_box = steps_container.getElementsByClassName('step_description');"+
			"for(let i=0; i<description_box.length; i++){"+
			
				"if(description_box[i].value.trim().length>0){return true;}else{return false;}"+
			"}"+
			
			"}"+
			
			"function _getStepsToReproduce(){"+
			
			"let steps = '%0A%0A__Steps%20to%20reproduce__%0A';"+
			"let steps_container = document.getElementById('steps_container');"+
			"let description_box = steps_container.getElementsByClassName('step_description');"+
			"for(let i=0; i<description_box.length; i++){"+
			
				"if(description_box[i].value.length>0){"+
				
				"steps = steps+(i+1).toString()+'.%20'+_urlEncode(description_box[i].value)+'%0A';"+
				
				"}"+	
				
			"}"+
			
			"return steps;"+
						
			"}"+
			
			"function _inputVersion(){"+
			
			"document.getElementById('version_number').style.removeProperty('border-color');"+
			
			"}"+
			
			"function _checkVersion(){"+
			
			"let version = document.getElementById('version_number');"+
			
			"if(version.value.trim().length > 0){return true;}else{return false;}"+
			
			"}"+
			
			"function _getVersionNumber(){"+
			
			"let version = document.getElementById('version_number').value.trim();"+
			
			"version  = 'Version%20number:%20'+_urlEncode(version)+'%0A';"+
			
			"return version;"+
			
			"}"+
			
			"function _jiraTicket(event){"+
			
			"event.target.style.removeProperty('border-color');"+
			
			"}"+
			
			//Save as PDF.
			
			"</script>"+
			"</html>";	

}
