package CreateNew;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class ReadExcelConfig 
{
	@Parameters("createlegalExcel")
	@DataProvider(name="fromExcelReadCreateLegal")
	public String[][] readExcel(String path) throws FileNotFoundException, IOException
	{
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(path));
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
			
		}
		
		wb.close();
		return data;
	}
}
