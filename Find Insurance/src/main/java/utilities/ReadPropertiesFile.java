package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	
		static Properties prop = new Properties();// properties object created
		InputStream readFile = null;//initialize read file to null
		public ReadPropertiesFile()throws Exception{
			/*pass the input.properties file location and load in try and catch block*/
			 try {
				 readFile = new FileInputStream("input.properties");
				 prop.load(readFile);
			} 
			 catch(IOException io){
			io.printStackTrace();
			 }
			 /*finally block to close the input.Properties file*/
			 finally {
			if(readFile!=null){
				try {
					readFile.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				}
			 }
		}
		
		 /*getUrl() to get the URL from the input.properties file*/
		 public static String getUrl()throws Exception{
		 String url = prop.getProperty("URL");
		 return url;
		 }
		 
		/*getbrowser() to get the Browser in which you want to do from the input.properties file*/
		 public static String getbrowser()throws Exception{
			 String browser = prop.getProperty("Browser");
			 return browser;
		 }
		 
		
}
