package com.neo.Utill;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadExcelUtill2 implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(ReadExcelUtill2.class);

    public List<String> getData() {

        List column = new ArrayList();
        try {
            InputStream in = ReadExcelUtill2.class.getClassLoader().getResourceAsStream("execl/APPBSC功能关闭商户明细T.xls");
            Workbook wb = null;
            try {
                wb = new XSSFWorkbook(in);
            } catch (IOException e1) {
                logger.error("读取文件异常");
                e1.printStackTrace();
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cellOne = row.getCell(0);
                        column.add(cellOne.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return column;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
     /*   String excelPath = "E:\\work\\nineTask\\document\\商户服务电话登记 (1).xlsx";
        Map<String, Object> map = getData(excelPath);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println(map.size());
    }*/
    }
}
