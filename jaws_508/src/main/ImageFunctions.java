package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

public class ImageFunctions {
	
public static File image = null;
	
public static String tabDetector(String order_number, String pageScreenshotPath, int xcord, int ycord, int width, int height) {
	
	String newFilePath = "";
	
	if(order_number != "*") {
		
		if(image == null) {
			
			newFilePath = highlighter(order_number, pageScreenshotPath, xcord, ycord, width, height);
			
			if(newFilePath == "error") {
				
				return "Error in highlighter.";
				
			}
			
			image = new File(newFilePath);
			
		}else {
			
			newFilePath = highlighter(order_number, image.getPath(), xcord, ycord, width, height);
			
			if(newFilePath == "error") {
				
				return "Error in highlighter.";
				
			}
			
			image = new File(newFilePath);
			
		}
		
	}else {
		
		String background_img = highlighter(order_number, pageScreenshotPath, xcord, ycord, width, height);
		
		if(background_img == "error") {
			
			return "Error in highlighter.";
			
		}
		
	}
	
	return  "success";
	
}


public static BufferedImage unableToCapture() {	

    String no_image_text = " Unable to capture image. Use the xpath to locate the element. ";

    Font font = new Font("Tahoma", Font.PLAIN, 22);    

    FontRenderContext frc = new FontRenderContext(null, true, true);

    Rectangle2D bounds = font.getStringBounds(no_image_text, frc);
    int w = (int) bounds.getWidth();
    int h = (int) bounds.getHeight();    

    BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);    

    Graphics2D g = image.createGraphics();    

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, w, h);
    g.setColor(Color.BLACK);
    g.setFont(font);
         
    g.drawString(no_image_text, (float) bounds.getX(), (float) -bounds.getY());   
 
    g.dispose();
   
    return image;

}


private static String highlighter(String order_number, String pageScreenshotPath, int xcord, int ycord, int width, int height) {
	
	BufferedImage img = null;
	
	File outputFile = new File(pageScreenshotPath);
	
	try {
		
		img = ImageIO.read(outputFile);
		
	} catch (IOException e) {
		
		e.printStackTrace();
		
		return "error";
		
	}
	
	Graphics2D g2d = img.createGraphics();
	
	g2d.setColor(Color.RED);
	
	g2d.setStroke(new BasicStroke((float) 2.5));
	
	g2d.drawRect(xcord, ycord, width, height);
		
	int fill_width = 0;
	
	int fill_height = 0;
	
	fill_width = 30;
	
	fill_height = 20;
	
	if(order_number != "*") {
		
		g2d.fillRect(xcord, ycord, fill_width, fill_height);
		
	}else {
		
		g2d.fillRect(xcord, ycord, fill_width-15, fill_height-8);
		
	}
	
	g2d.setColor(Color.WHITE);
	   
	g2d.setFont(new Font("Serif", Font.BOLD, 15));
	
	g2d.drawString(order_number, xcord+(fill_width/10),  ycord+12);
	
	g2d.dispose();
	
	try {
		
		ImageIO.write(img, "png", outputFile);		
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	return outputFile.getPath();
	
}


}
