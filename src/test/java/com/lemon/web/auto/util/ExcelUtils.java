package com.lemon.web.auto.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	/**
	 * 
	 * @param excelPath  excel的路径: 类路径的根路径开始找
	 * @param sheetNum  sheet编号，从1开始,传1表示第1个sheet，即索引为0的sheet
	 * @return
	 */
	public static Object[][] readExcel(String excelPath, int sheetNum) {
		//创建一个Object类型的二维数组，保存从excel解析出来的行列数据
		Object[][] datas = null;
		InputStream inp = null;
		Workbook workbook = null;
		try {
			inp = ExcelUtils.class.getResourceAsStream(excelPath);
			//获得工作簿对象
			workbook = WorkbookFactory.create(inp);
			//获得第一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNum - 1);
			//遍历--》思路应该怎么样？？
			//通过遍历拿到所有的行--》通过遍历拿到每一行的列
			//获得最后的行号(行的索引，从0开始)
			int lastRowNum = sheet.getLastRowNum();
			//获得最大的列号
			Row firstRow = sheet.getRow(0);
			//获得最大的列数
			int lastCellNum = firstRow.getLastCellNum();

			datas = new Object[lastRowNum][];
			//遍历每一行(i相当于行号,第一行不要)
			for (int i = 1; i <= lastRowNum; i++) {
				//获得索引对应的行
				Row row = sheet.getRow(i);
				//创建一个一维数组，保存该行的所有列信息
				Object[] cellValueArray = new Object[lastCellNum];
				// 遍历每一列（j相当于列号）
				for (int j = 0; j < lastCellNum; j++) {
					//获得当前行的每一列
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置列的类型
					cell.setCellType(CellType.STRING);
					//获得该列的值
					String cellValue = cell.getStringCellValue();
					//把当前列的数据添加到cellValueArray
					cellValueArray[j] = cellValue;
				}
				//每遍历一行，要把该行对应的一维数组添加进去
				datas[i - 1] = cellValueArray;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return datas;
	}

	//example :一定要在工具类写一个main方法--》方便调试和后期的维护
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] datas = readExcel("/testCase/register/register.xlsx", 1);
		for (Object[] cellValueArray : datas) {
			for (Object cellValue : cellValueArray) {
				System.out.print("["+cellValue + "]    ");
			}
			System.out.println();
		}

	}
}
