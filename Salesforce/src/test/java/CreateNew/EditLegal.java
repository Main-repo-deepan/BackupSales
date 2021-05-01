package CreateNew;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditLegal extends BaseClass{

	@Test(dataProvider="fromEditLegalExcel")
	public void edit(String company1 ,String Description,String status1) {
		
		WebDriverWait wait= new WebDriverWait(driver, 20);
		
		
		
		//Click Toggle and ViewAll
		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		sleepDriver(2000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
        
		//Select Legal Entities
		driver.findElement(By.xpath("//label[text()='Search apps or items...']/following::input")).sendKeys("Legal Entities");;
		driver.findElement(By.xpath("//mark[text()='Legal Entities']")).click();
		
		//passing my name in search
		

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search this list...']"))).sendKeys("Salesforce automation by Barathi");
		
//		wait.until(ExpectedConditions.visibilityOf(By.xpath("//input[@placeholder='Search this list...']"))).sendKeys("Salesforce automation by Barathi");
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(Keys.ENTER);
		
		sleepDriver(4000);		
		WebElement drpDwn = driver.findElement(By.xpath("//span[text()='Show Actions']/ancestor::a[1]"));
		
		driver.executeScript("arguments[0].click();", drpDwn);

    	driver.findElement(By.xpath("//a[@title='Edit']")).click();
    	WebElement company = driver.findElement(By.xpath("//span[text()='Company Name']/following::input[1]"));
    	company.clear();
    	company.sendKeys(company1);
    	
		company=driver.findElement(By.xpath("//span[text()='Description']/following::textarea"));
		company.clear();
		company.sendKeys(Description);
		
		
		WebElement Status = driver.findElement(By.xpath("//h2[text()='Edit SalesForce Automation By Barathi']/following::span[text()='Status']/following::a"));
		Status.click();
		driver.findElement(By.linkText(status1)).click();
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		sleepDriver(8000);
		driver.findElement(By.xpath("//a[@title='SalesForce Automation By Barathi']")).click();
		String text = driver.findElement(By.xpath("(//span[text()='Status'])[2]/following::span")).getText();
		System.out.println("the status is"+ " " +text);
	
	}
	
	@DataProvider(name = "EditLegalFetchData")
	public String[][] testdata()
	{
		String[][] data = new String[2][3];
		
		
		data[0][0] = "Amazon";
		data[0][1] = "testleaf";
		data[0][2] = "Active";
		
		data[1][0] = "testleaf";
		data[1][1] = "Salesforce";
		data[1][2] = "Inactive";
		
		return data;
	}
	
	@DataProvider(name = "fromEditLegalExcel")
	public String[][] readexcel() throws IOException, InvalidFormatException
	{
		//Creating a new workbook
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("./ExcelFile/EditLegal.xlsx"));
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowNum = sheet.getLastRowNum(); 
		int colNum = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowNum][colNum];
		
		for (int i = 1; i <= rowNum; i++) 
		{
			XSSFRow curRow = sheet.getRow(i);
			for (int j = 0; j < colNum; j++) 
			{
				data[i-1][j] = curRow.getCell(j).getStringCellValue();
			}
			
		}
		
//		wb.close();
		return data;

	}

}
