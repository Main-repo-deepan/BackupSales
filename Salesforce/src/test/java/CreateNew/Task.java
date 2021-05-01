package CreateNew;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task extends BaseClass {

    @Test(dataProvider = "fromTaskExcel" )
	public void EditTask(String prior) 
	{
		
		WebDriverWait wait= new WebDriverWait(driver, 30);
		
		//Click toggle and view all
		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		//driver.findElement(By.xpath("//button[@class='slds-button slds-show']")).click();
		driver.findElement(By.xpath("//button [text()='View All']")).click();

		//pass sales in search and click sales
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		driver.findElement(By.xpath("(//mark[text()='Sales'])[3]")).click();


		//Click task tab
		sleepDriver(1000);
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
				
		driver.findElement(By.xpath("//div/a[@title='Show 13 more actions']")).click();
			
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
	 
		
		
		WebElement validateDate = driver.findElement(By.xpath("//input[@class='inputDate input']"));
		validateDate.clear();
		validateDate.sendKeys(TodayDate());
		
		
    	
    	WebElement priority = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("\"(//h2[text()='Edit bootcamp']/following::span[text()='Priority']/following::a)\"")));
    	priority.click();
    	driver.findElement(By.linkText(prior)).click();
    	
    	
    	
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		String text = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='toastContent slds-notify__content']")))).getText();
		System.out.println(text);
		text = text.replaceAll("[^a-zA-Z]", "");
		assertEquals(text, "TaskBootcampwassaved");
		//driver.close();
	}

    
    @DataProvider(name = "TaskPriority")	
	public String[][] testdata()
	{

		String[][] data = new String[2][1];
		

		data[0][0] = "High";
		

		data[1][0] = "Normal";
		

		return data;
	}
    
    @DataProvider(name = "fromTaskExcel")
	public String[][] readexcel() throws IOException, InvalidFormatException
	{
		//Creating a new workbook
		
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("./ExcelFile/CreateTask.xlsx"));
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum(); 
		int colNum = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowNum][colNum];
		
		for (int i = 1; i <= rowNum; i++) 
		{
			XSSFRow curRow = sheet.getRow(i);
			data[i-1][0] = curRow.getCell(0).getStringCellValue();
			
			
		}
		
		wb.close();
		return data;

	}
}
