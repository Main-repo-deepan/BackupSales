package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;

public class CreateLegal extends BaseClass
{

	@And("Navigate to Legal tab")
	public void Legaltab()
	{
		//Click Toggle and ViewAll
				driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
				driver.findElement(By.xpath("//button[text()='View All']")).click();
				
				//Select Legal Entities
				driver.findElement(By.xpath("//label[text()='Search apps or items...']/following::input")).sendKeys("Legal Entities");;
				driver.findElement(By.xpath("//mark[text()='Legal Entities']")).click();
				driver.findElement(By.xpath("//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']")).click();
				
				
				//click new legal entity
				WebElement findElement = driver.findElement(By.xpath("//span[text()='New Legal Entity']"));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", findElement);
	}
	
	@And("Create new Legal with Company as {string} Description{string} and title as {string} ")
	public void CreateLegal(String usertitle , String enterCom , String Des) 
	{
		driver.findElement(By.xpath("//span[text()='*']/following::input")).sendKeys(usertitle);
		driver.findElement(By.xpath("//span[text()='Company Name']/following::input[1]")).sendKeys(enterCom);
		driver.findElement(By.xpath("//span[text()='Description']/following::textarea")).sendKeys(Des);
	}
	
}
