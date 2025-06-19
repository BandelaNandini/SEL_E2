package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author B.Nandini This class is used to access Excel file
 */
public class ExcelFileUtility {

	public FileInputStream fis = null;
	public Workbook wb = null;

	/**
	 * This method is used to fetch single data from excel file
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcelFile(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/VTigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex).toString();
		return data;

	}

	/**
	 * This method is used to write back the data to excel
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param value
	 * @throws Exception
	 */
	public void writeDataBackToExcelFile
	(String sheetname, int rowindex, int cellindex, String value) throws Exception {
		fis = new FileInputStream("./src/test/resources/VTigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Cell c = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex);
		c.setCellValue(value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VTigerTestData.xlsx");
		wb.write(fos);

	}

	/**
	 * This method is used to fetch all the data from excel
	 * 
	 * @param sheetname
	 * @return
	 * @throws Exception
	 */
	public String fetchMutipleDataFromExcel(String sheetname) throws Exception {
		String data = null;
		fis = new FileInputStream("./src/test/resources/VTigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		for (int i = 0; i <= sh.getLastRowNum(); i++) {
			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
				data = sh.getRow(i).getCell(j).toString();
			}
		}
		return data;

	}

	/**
	 * This method is used to close the excel workbook
	 * 
	 * @throws IOException
	 */
	public void closeExcelWorkbook() throws IOException {

		wb.close();
	}

}
