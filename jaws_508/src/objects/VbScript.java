package objects;

public class VbScript {
	
	
	public static String vb_script = "set WshShell = CreateObject(\"WScript.Shell\")\r\n" + 
						"WshShell.AppActivate Wscript.Arguments(0)";

}
