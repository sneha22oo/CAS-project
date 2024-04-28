package userDefinedLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;

	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	//Getting Row Count From the Sheet
	public int getRowCount(String sheetName) 
	{
		int rowcount=0;
		try {
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
		    rowcount=sheet.getLastRowNum();
		    workbook.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;		
	}
	
	//Getting Cell Count From the Sheet
	public int getCellCount(String sheetName,int rownum)
	{
		int cellcount=0;
		try {
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cellcount=row.getLastCellNum();
			workbook.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellcount;
	}
	
	//Getting Cell Data From the Sheet
	public String getCellData(String sheetName,int rownum,int colnum) 
	{
		try {
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(colnum);
			workbook.close();
			fi.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		return data;
	}
	
	//Writing The Data to The File
	public  void writeData(String filename,String sheetName,int rowNumber,int cellNumber, String cnt) {
				try {
					fi = new FileInputStream(filename);
					workbook = new XSSFWorkbook(fi);
					sheet = workbook.getSheet(sheetName);
					row = sheet.getRow(rowNumber);
					cell = row.createCell(cellNumber);
					cell.setCellValue(cnt);

					fo = new FileOutputStream(filename);
					workbook.write(fo);
					workbook.close();
					fi.close();
					fo.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

}
	
