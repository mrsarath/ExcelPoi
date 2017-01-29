package com.sarath.mytestproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * * A dirty simple program that reads an Excel file. * @author www.codejava.net
 * *
 */
public class ReadExcel {

	public static void main(String[] args) throws IOException {

		ReadExcel ex = new ReadExcel();
		ex.readExcelFile();

	}

	void readExcelFile() throws IOException {
		String excelFilePath = "src/main/resources/Book.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheet("Employee");
		Iterator<Row> iterator = firstSheet.iterator();

		int NameColIdx = CellReference.convertColStringToIndex("B");
		int DeptcolIdx = CellReference.convertColStringToIndex("D");

		List<Employee> empList = new ArrayList<>();

		// validate
		if (!"Department".equalsIgnoreCase((String) readCellValue(firstSheet.getRow(0).getCell(DeptcolIdx)))
				|| !"Name".equalsIgnoreCase((String) readCellValue(firstSheet.getRow(1).getCell(DeptcolIdx)))) {
			System.out.print("Wrong Excel");
			return;
		}

		//skip first two rows
		int i = 0;
		while (i<2) {
			iterator.next();
			i++;
		}
		
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();

			Cell empCell = nextRow.getCell(NameColIdx);
			Cell deptCell = nextRow.getCell(DeptcolIdx);

			if (empCell != null && deptCell != null) {
				Employee emp = new Employee((String) readCellValue(empCell), (String) readCellValue(deptCell));
				empList.add(emp);
			}

		}

		System.out.print(empList.toString());

		workbook.close();
		inputStream.close();
	}

	Object readCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return ((Double) cell.getNumericCellValue()).intValue();
		}

		return "";
	}
}
