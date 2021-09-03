package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestOne {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
			
			WebDriver driver = new ChromeDriver();
			driver.get("http://localhost:8080/justThinkItEsame/view/creaEventoGenerale.jsp");
			
		
			driver.findElement(By.xpath("//*[@id=\"NomeEvento\"]")).sendKeys("Rock in Liri");
			driver.findElement(By.xpath("/html/body/div/form/div[1]/div[2]/div/div[1]/div[3]/input"));
			driver.findElement(By.xpath("//*[@id=\"NoteEvento\"]")).sendKeys("Abbiamo pensato di raccogliere cibo e vestiti con il sound del rock.");
	
			Thread.sleep(2000);
			
			driver.close();
			
		}
	
}
