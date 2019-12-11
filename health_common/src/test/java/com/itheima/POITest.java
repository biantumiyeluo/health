package com.itheima;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

public class POITest {
    /*@Test
    public void sheetAt() throws Exception {
        //1.创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("E:\\download\\test.xlsx");
        //2.根据sheet顺序查询
        XSSFSheet sheet = workbook.getSheetAt(0);
        //3.遍历工作簿
        for (Row cells : sheet) {
            //4.遍历行
            for (Cell cell : cells) {
                String cellValue = cell.getStringCellValue();
                System.out.println(cellValue);
            }
        }
        //5.释放资源
        workbook.close();
    }

    @Test
    public void sheetRowNum() throws Exception {
        //1.创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("E:\\download\\test.xlsx");
        //2.根据sheet顺序查询
        XSSFSheet sheet = workbook.getSheetAt(0);
        //3.工作表最后一行
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            //4.行号获取对象
            XSSFRow row = sheet.getRow(i);
            //5.获取单元格
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.println(cellValue);
            }
        }
        //6.释放资源
        workbook.close();
    }

    @Test
    public void creatSheet() throws Exception {
        //1.创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2.创建表格名称
        XSSFSheet sheet = workbook.createSheet("user");//指定名称
        //3.创建行
        XSSFRow row = sheet.createRow(0);//第一行
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");
        XSSFRow row1 = sheet.createRow(1);//第二行
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("war");
        row1.createCell(2).setCellValue("16");
        //4.输出流写入磁盘
        FileOutputStream fs = new FileOutputStream("E:\\download\\user.xlsx");
        //5.写入文件
        workbook.write(fs);
        fs.flush();//刷新
        fs.close();//释放资源
        workbook.close();
    }*/

}
