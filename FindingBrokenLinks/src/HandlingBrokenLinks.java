import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingBrokenLinks{
	public static void main(String[] args) throws IOException {
		
		//System.setProperty("webdriver.chrome.driver", "path to chromedriver");
		
		// Initialize Webdriver Object
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/test/newtours/");
        

    	// Store all link elements (anchor tag elements in html) in a list
    	List<WebElement> links = driver.findElements(By.tagName("a"));
    	System.out.println(links.size());
        
    	for(int i=1;i<links.size();i++) {
    		
    		WebElement elem=links.get(i);
    		
    		//Printing text of all the links
     		System.out.println(elem.getText());
     		 
    		//Getting url of links
    		String linkUrl = elem.getAttribute("href");
    		
    		// Call Verify Links method
    		
    		    verifyLinks(linkUrl);
    		
    	}
    	
    	
    	driver.quit();
	}
	public static void verifyLinks(String websiteLink) throws IOException  {
		
		// Create URL object and pass website link 
		URL url =new URL(websiteLink);
		
		// Create URL connection and Get the response code
		HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
		httpURLConnect.setConnectTimeout(5000);
		httpURLConnect.connect();
		
		// Verify Response code
		if(httpURLConnect.getResponseCode() >= 400){
			System.out.println(websiteLink+" - "
					+httpURLConnect.getResponseMessage()+" is a broken link");
		}    
		else {
			System.out.println(websiteLink+" - "
					+httpURLConnect.getResponseMessage()+" is a valid link");
		}

		// Disconnect URL Connection
		httpURLConnect.disconnect();
		
	}
}