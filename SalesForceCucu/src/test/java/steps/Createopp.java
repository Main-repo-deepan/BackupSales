package steps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Createopp extends BaseClass
{
	//public static WebDriver driver;
	
//	@Given("Launch the chrome browser")
//	public static void launchBrowser() 
//	{
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-notifications");
//		driver = new ChromeDriver(options);
//		
//	}
//	
//	@And("Login salesforce with valid credentials")
//	public static void loginsafesforce() 
//	{
//		driver.get("https://login.salesforce.com");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.findElement(By.id("username")).sendKeys("makaia@testleaf.com");
//		driver.findElement(By.id("password")).sendKeys("India@123");
//		driver.findElement(By.id("Login")).click();
//	}

	@And("Navigate to opputunity tab")
	public static void Togglebutton() 
	{
		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		driver.findElement(By.xpath("//div[@class='slds-grid slds-grid_pull-padded slds-wrap app-dnd-identifier']//one-app-launcher-app-tile//span//p[text()='Sales']")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]")).click();
	}
	
	@And("Create new oppurtunity with title as {string}")
	public static void opputunity(String entertitle)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='slds-button slds-button--neutral']//div[@title='New'][text()='New']"))).click();		
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(entertitle);
		SimpleDateFormat dformat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String format = dformat.format(date);
		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(format);
		driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input[@role='combobox'])[3]")).click();
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
	}
	
	@When("click Save")
	public static void Save() 
	{
		driver.findElement(By.xpath("//button[@name='SaveAndNew']")).click();
	}
	
	@Then("Verify Oppurtunity name")
	public static void Verifyopp()
	{
		driver.findElement(By.xpath("//button[@title='Close this window']")).click();



		String VerifyName = driver.findElement(By.xpath("//slot[@name='primaryField']//lightning-formatted-text")).getText();
		//Assert.assertEquals(VerifyName, entertitle,"title didnt match");
	}

}

