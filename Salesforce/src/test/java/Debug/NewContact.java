package Debug;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import CreateNew.BaseClass;

public class NewContact extends BaseClass{
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		Authentication
		driver.findElement(By.id("username")).sendKeys("makaia@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("India@123");
		driver.findElement(By.id("Login")).click();

		
		
//		Click SVG Icon
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='uiMenu']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Contact']"))).click();
		
		String lastName = "you1";
		driver.findElement(By.xpath("//input[contains(@class,'firstName compoundBorderBottom')]")).sendKeys("Miffy");
	    driver.findElement(By.xpath("//input[contains(@class,'lastName compoundBLRadius')]")).sendKeys(lastName);
	    driver.findElement(By.xpath("//input[@inputmode='email']")).sendKeys("adc@gmail.com");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Maximize']//lightning-primitive-icon[1]"))).click();
	    
	   driver.findElement(By.xpath("(//input[contains(@class,'default input')])[2]")).click();
	   driver.findElement(By.xpath("//div[@title='Credits']")).click();
	   
	   driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
	    
	   Thread.sleep(10000);
	   driver.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys(lastName);
	   driver.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
	
	}


}