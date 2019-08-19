package com.neo.Utill;

import ch.qos.logback.core.util.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReadExcelUtill implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public Map getData() {
        Map column = new HashMap();
        try {
            ClassPathResource resource = new ClassPathResource("商户服务电话登记.xlsx");
            File excel = resource.getFile();
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在
                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    logger.error("文件类型错误");
                    return column;
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
                            Cell cell1Two = row.getCell(1);
                            column.put(cellOne.toString(), cell1Two.toString());
                        }
                    }
                }
            } else {
                logger.error("找不到指定的文件");
                return column;
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
