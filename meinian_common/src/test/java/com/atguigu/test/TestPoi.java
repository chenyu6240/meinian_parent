package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;

public class TestPoi {
    @Test
    public void readExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook("C:/Users/cy/Desktop/atguigu.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                System.out.println("value = " + value);
            }
        }
        workbook.close();
    }
}
