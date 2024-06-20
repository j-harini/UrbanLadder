package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	public static FileInputStream file_input;
	public static FileOutputStream file_output;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws InvalidFormatException, IOException
	{
		Sheet sheet = getSheetByName(excelFilePath, sheetName);
		return readSheet(sheet);
	}

	public List<Map<String, String>> getData(String excelFilePath, int sheetNumber) throws InvalidFormatException, IOException
	{
		Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
		return readSheet(sheet);
	}

	private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException
	{
		Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
		return sheet;
	}

	private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException
	{
		Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
		return sheet;
	}

	private Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException
	{
		return WorkbookFactory.create(new File(excelFilePath));
	}

	private List<Map<String, String>> readSheet(Sheet sheet) 
	{
		Row row;
		int totalRow = sheet.getPhysicalNumberOfRows();
		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		int headerRowNumber = getHeaderRowNumber(sheet);
		if (headerRowNumber != -1) {
			int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
			int setCurrentRow = 1;
			for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
				row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
				LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
				}
				excelRows.add(columnMapdata);
			}
		}
		return excelRows;
	}

	private int getHeaderRowNumber(Sheet sheet) 
	{
		Row row;
		int totalRow = sheet.getLastRowNum();
		for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) 
		{
			row = getRow(sheet, currentRow);
			if (row != null)
			{
				int totalColumn = row.getLastCellNum();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++)
				{
					Cell cell;
					cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if (cell.getCellType() == CellType.STRING) 
					{
						return row.getRowNum();

					} 
					else if (cell.getCellType() == CellType.NUMERIC) 
					{
						return row.getRowNum();

					} 
					else if (cell.getCellType() == CellType.BOOLEAN) 
					{
						return row.getRowNum();
					}
					else if (cell.getCellType() == CellType.ERROR)
					{
						return row.getRowNum();
					}
				}
			}
		}
		return (-1);
	}

	private Row getRow(Sheet sheet, int rowNumber) 
	{
		return sheet.getRow(rowNumber);
	}

	private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) 
	{
		LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
		Cell cell;
		if (row == null) {
			if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.getCellType() != CellType.BLANK) 
			{
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();
				columnMapdata.put(columnHeaderName, "");
			}
		} 
		else 
		{
			cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cell.getCellType() == CellType.STRING) 
			{
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) 
				{
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, cell.getStringCellValue());
				}
				
			} else if (cell.getCellType() == CellType.NUMERIC) 
			{
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) 
				{
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
				}
			} 
			else if (cell.getCellType() == CellType.BLANK) 
			{
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, "");
				}
			} 
			else if (cell.getCellType() == CellType.BOOLEAN) 
			{
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) 
				{
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
				}
			} 
			else if (cell.getCellType() == CellType.ERROR) 
			{
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) 
				{
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
				}
			}
		}
		return columnMapdata;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException 
	{
	     file_input = new FileInputStream(xlfile);
	     workbook = new XSSFWorkbook(file_input);
	     sheet = workbook.getSheet(xlsheet);
	     row = sheet.getRow(rownum);
	     cell = row.createCell(colnum);
	    cell.setCellValue(data);

	    // Write changes back to the Excel file
	     file_output = new FileOutputStream(xlfile);
	    workbook.write(file_output);
	    System.out.println("Result Updated in the Row: " + rownum + ", Column: " + colnum);

	    // Close resources
	    workbook.close();
	    file_input.close();
	    file_output.close();
	}

	public static void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		file_input=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(file_input);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		file_output=new FileOutputStream(xlfile);
		workbook.write(file_output);		
		workbook.close();
		file_input.close();
		file_output.close();
	}
	
	
	public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		file_input=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(file_input);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		file_output=new FileOutputStream(xlfile);
		workbook.write(file_output);		
		workbook.close();
		file_input.close();
		file_output.close();
	}

	}
