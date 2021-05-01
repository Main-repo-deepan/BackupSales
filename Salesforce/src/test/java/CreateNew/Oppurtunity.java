package CreateNew;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Oppurtunity extends BaseClass 
{

	@Test(dataProvider = "OppFetchData")
	public void Newopp(String entertitle, String format) 
	{

		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		sleepDriver(2000);
		driver.findElement(By.xpath("//div[@class='slds-grid slds-grid_pull-padded slds-wrap app-dnd-identifier']//one-app-launcher-app-tile//span//p[text()='Sales']")).click();


		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='slds-button slds-button--neutral']//div[@title='New'][text()='New']"))).click();		
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(entertitle);

		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(format);

		driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input[@role='combobox'])[3]")).click();
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();

		driver.findElement(By.xpath("//button[@name='SaveAndNew']")).click();

		sleepDriver(2000);
		driver.findElement(By.xpath("//button[@title='Close this window']")).click();



		String VerifyName = driver.findElement(By.xpath("//slot[@name='primaryField']//lightning-formatted-text")).getText();
		//System.out.println(entertitle + "  " + VerifyName);
		Assert.assertEquals(VerifyName, entertitle,"title didnt match");

	}

	@DataProvider(name = "OppFetchData")	
	public String[][] testdata()
	{

		String[][] data = new String[2][2];
		String date = BaseClass.TodayDate();

		data[0][0] = "Salesforce Automation by Barathi";
		data[0][1] = date;

		data[1][0] = "Salesforce Automation by priya";
		data[1][1] = date;

		return data;
	}

	@DataProvider(name = "fromExcel")
	public String[][] readexcel() throws IOException, InvalidFormatException
	{
		//Creating a new workbook
		String date = BaseClass.TodayDate();
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("./ExcelFile/CreateOpp.xlsx"));
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum(); 
		int colNum = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowNum][colNum+1];
		
		for (int i = 1; i <= rowNum; i++) 
		{
			XSSFRow curRow = sheet.getRow(i);
			int j = 0;
			for (; j < colNum; j++) 
			{
				data[i-1][j] = curRow.getCell(j).getStringCellValue();
			}
			data[i-1][j] = date;
			
		}
		
		wb.close();
		return data;

	}
}
