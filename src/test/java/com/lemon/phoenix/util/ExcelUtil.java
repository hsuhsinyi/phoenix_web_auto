package com.lemon.phoenix.util;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Vlookup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	/**
	 * 读取excel文档
	 * 
	 * @param excelPath
	 *            excel的路径
	 * @param startRow
	 *            读取的开始的行号
	 * @param endRow
	 *            结束的行号
	 * @param startCell
	 *            开始的列号
	 * @param endCell
	 *            结束的列号
	 */
	// 2 7 1 4 ： 6行4列
	// 2 8 2 4: 7行3列
	public static Object[][] readExcel(String excelPath, int startRow, int endRow, int startCell, int endCell) {
		// 创建一个二维数组，保存我们遍历的数据
		Object[][] datas = new Object[endRow - startRow + 1][endCell - startCell + 1];
		try {
			// 得到一个工作簿--》面向接口编程
			Workbook workbook = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(excelPath));
			// 得到第一个sheet
			Sheet sheet = workbook.getSheetAt(0);
			// 循环获得每一行
			for (int i = startRow; i <= endRow; i++) {
				// 获得第i行
				Row row = sheet.getRow(i - 1);
				// 获得该行中的每一列
				for (int j = startCell; j <= endCell; j++) {
					// 获得第j列：把空的列作为一个空字符串的列
					Cell cell = row.getCell(j - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// 设置cell的类型为String类型
					cell.setCellType(CellType.STRING);
					// 获得该列string类型的值
					String value = cell.getStringCellValue();
					// startRow 1000 endRow 1100
					// startCell 20 endCell = 50
					// 第一行
					// 第一个列设值，i=1000，j=20：datas[0][0]=datas[?][?]
					// 第二个列设值，i=1000，j=21：datas[0][1]=datas[?][?]
					// 第三个列设值，i=1000，j=22：datas[0][2]=datas[?][?]

					// 第二行
					// 第一个列设值，i=1001，j=20：datas[1][0]=datas[?][?]
					// 第二个列设值，i=1001，j=21：datas[1][1]=datas[?][?]
					// 第三个列设值，i=1001，j=22：datas[1][2]=datas[?][?]
					datas[i - startRow][j - startCell] = value;
					// 当i+1的时候，到第二行去了

				}
			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch blockXAQ
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	/**
	 * 前瞻  1：项目的每一个功能对应一个excel文档 
	 * 		2:每个测试用例（检查点相同的测试用例）对应一个sheet
	 * 
	 * @param excelPath
	 */
	public static void read2007Excel(String excelPath) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ExcelUtil.class.getResourceAsStream(excelPath));
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				// 最大的列号
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j <= lastCellNum; j++) {
					XSSFCell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// 设置cell的类型为String类型
					cell.setCellType(CellType.STRING);
					// 获得该列string类型的值
					String value = cell.getStringCellValue();
					System.out.print(value + ",");
				}
				System.out.println("-------------");
			}

			// System.out.println("有"+lastRowNum+"行");
			// System.out.println("有"+sheet.getRow(0).getLastCellNum()+"列");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

/*		Object[][] datas = readExcel("/register.xlsx", 2, 7, 1, 4);
		for (Object[] objects : datas) {
			System.out.println("--------");
			for (Object object : objects) {
				System.out.print(object);
				System.out.print(",");
			}
			System.out.println();
		}*/

		read2007Excel("/login.xlsx");
	}

}
