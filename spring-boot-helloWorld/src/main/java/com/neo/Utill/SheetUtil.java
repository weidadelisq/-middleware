package com.neo.Utill;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * Created by jianfree on 11/8/15.
 */
public class SheetUtil {
    private HSSFSheet sheet;
    private int currentRow = 0;
    private int currentCol = 0;

    public SheetUtil(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void appendRow(List values) {
        int lastRowNum = sheet.getLastRowNum();
        if(sheet.getRow(lastRowNum) != null){
            lastRowNum = lastRowNum + 1;
        }
        HSSFRow row = sheet.createRow(lastRowNum);
        for(Object value: values){
            if(value instanceof Number){
                row.createCell(currentCol++).setCellValue(((Number) value).doubleValue());
            }else if(value instanceof  String){
                row.createCell(currentCol++).setCellValue((String)value);
            } else {
                row.createCell(currentCol++).setCellValue(value==null?null:value.toString());
            }
        }
        setCurrentRow(lastRowNum);
        currentCol = 0;
    }

    public void mergeCell(int rowStart, int colStart, int rowEnd, int colEnd) {
        sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, colStart, colEnd));
    }

    public void setCellAlignment(int row, int col, short halign, short valign) {
        HSSFCell cell = sheet.getRow(row).getCell(col);
        HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    public void setCellWordColor(int row, int col) {
        HSSFCell cell = sheet.getRow(row).getCell(col);
        HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cell.setCellStyle(cellStyle);
        HSSFFont font = sheet.getWorkbook().createFont();
        font.setColor(Font.COLOR_RED);// 红字
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }


}
