package CreateNew;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class DeleteTask extends BaseClass{
    
	@Test
	public void TaskDel() {
		
		WebDriverWait wait= new WebDriverWait(driver, 30);
		

		//Click toggle and view all
		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		//driver.findElement(By.xpath("//button[@class='slds-button slds-show']")).click();
		driver.findElement(By.xpath("//button [text()='View All']")).click();

		//pass sales in search and click sales
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		driver.findElement(By.xpath("(//mark[text()='Sales'])[3]")).click();


		//Click task tab
		sleepDriver(2000);
		WebElement taskElement = driver.findElement(By.xpath("(//span[@class='slds-truncate'])[5]"));
		Actions action = new Actions(driver);
		action.moveToElement(taskElement).click().perform();


		//select recent view from dropdwn
		driver.findElement(By.xpath("//a[@title='Select List View']")).click();
		List<WebElement> Recent = driver.findElements(By.xpath("//div[@role='dialog']//ul[@role='listbox']//li[@role='presentation']"));
		for (WebElement i: Recent) 
		{

			if(i.getText().replaceAll("[^a-zA-Z]", "").matches("SelectedRecentlyViewedPinnedlist"))
			{
				i.click();
				break;
			}
		}

		WebElement bc = driver.findElement(By.xpath("//input[@placeholder='Search this list...']"));
		bc.sendKeys("Bootcamp");
		bc.sendKeys(Keys.ENTER);

		List<WebElement> bc1 = driver.findElements(By.xpath("(//span[text()='Bootcamp'])"));
		for (WebElement i : bc1)
		{

			String text = i.getText().toLowerCase();
			if(text.matches("bootcamp"))
			{
				i.click();
				break;
			}


		}

		//        Actions sel = new Actions(driver);
		//        sel.moveToElement(bc1).click().perform();
		driver.findElement(By.xpath("//a[@role='button' and @title='Show 13 more actions']")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		WebElement delmess = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		delmess.getText();
		System.out.println(delmess.getText());
	}

}
