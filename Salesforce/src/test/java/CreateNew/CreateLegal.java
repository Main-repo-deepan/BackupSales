package CreateNew;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLegal extends BaseClass
{
	@Test(dataProvider = "fromExcel")
	public  void newlegal(String userTitle, String userCompany, String userDescription) throws InterruptedException {
		
		//Click Toggle and ViewAll
		driver.findElement(By.xpath("(//div[@data-aura-class='uiTooltip']//div)[3]")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		//Select Legal Entities
		driver.findElement(By.xpath("//label[text()='Search apps or items...']/following::input")).sendKeys("Legal Entities");;
		driver.findElement(By.xpath("//mark[text()='Legal Entities']")).click();
		
		driver.findElement(By.xpath("//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New Legal Entity']"))).click();
		//click new legal entity
		WebElement findElement = driver.findElement(By.xpath("//span[text()='New Legal Entity']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", findElement);
		
		driver.findElement(By.xpath("//span[text()='*']/following::input")).sendKeys(userTitle);
		driver.findElement(By.xpath("//span[text()='Company Name']/following::input[1]")).sendKeys(userCompany);
		driver.findElement(By.xpath("//span[text()='Description']/following::textarea")).sendKeys(userDescription);
		
		
		js.executeScript("document.querySelector('.select').text = 'Active'");
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		String text = driver.findElement(By.xpath("//div[@class='toastContent slds-notify__content']")).getText();
        System.out.println(text);
//        assertTrue(text.equals("Legal Entity 'Priya' was created."));
	}
    
    @DataProvider(name = "LegalFetchData")
	public String[][] testdata()
	{
		String[][] data = new String[2][3];
		
		
		data[0][0] = "priya";
		data[0][1] = "testleaf";
		data[0][2] = "Salesforce";
		
		data[1][0] = "barathi";
		data[1][1] = "amazon";
		data[1][2] = "SalesforceLite";
		
		return data;
	}
    
    @DataProvider(name = "fromExcel")
	public String[][] readexcel() throws IOException, InvalidFormatException
	{
		//Creating a new workbook
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("./ExcelFile/CreateLegal.xlsx"));
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
