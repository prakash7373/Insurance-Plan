package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelDataFile {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	/* Constructor*/
	public ReadExcelDataFile(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/************* Returns the data from a excel file *******************/
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			DataFormatter formatter = new DataFormatter();
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = formatter.formatCellValue(cell);
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
			        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			        cellText= df.format(cell.getDateCellValue());
				}
				
				return cellText;
			} 
			else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		}
		catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}
	
}