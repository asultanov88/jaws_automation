package objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class TabElement {
	
	//This object is used to store tabbed element's properties. 
	
	public int cordX;
	
	public int cordY;

	public int width;
	
	public int height;
	
	public WebElement element;
	
	public File element_screenshot = null;
	
	public File background_screenshot = null;
	
	public String timestamp;
	
	public long unix_timestamp;
	
	public String jaws_speech = "";
	
	public boolean is_visible = false;
	
	public boolean bug_detect = false;
	
	public List<String> error_log =  new ArrayList();
	
	public String xpath = "";

}
