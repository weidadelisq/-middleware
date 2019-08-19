package com.neo.Utill;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2017/8/14.
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 只读第一页的第一列
     *
     * @return
     */
    public static List<String> getFirstSheetFromExcelFile(MultipartFile file) {
        Workbook workbook = getExcelFromFile(file);
        if (workbook == null || workbook.getNumberOfSheets() < 1) {
            return null;
        }
        return getFirstRowFromSheet(workbook.getSheetAt(0));
    }


    public static List<Map> getListFromSheet(Sheet sheet, Map<String, String> titles) {

        try {
            if (sheet == null || sheet.getPhysicalNumberOfRows() < 1 || titles == null || titles.size() < 1) {
                return null;
            }
            List<Map> data = new ArrayList<>();

            Map<Integer, String> needDataColumnNum = new HashMap<>();
            Map<Integer, String> allRowColumnCn = new HashMap<>();

            int titleRowNum = -1;
            A:
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    String value = toValue(row.getCell(j));
                    if (titles.containsKey(value)) {
                        titleRowNum = i;
                        break A;
                    }
                }
            }
            int rowLength = sheet.getRow(titleRowNum).getPhysicalNumberOfCells();

            Row titleRow = sheet.getRow(titleRowNum);
            for (int i = 0; i < rowLength + 1; i++) {
                String title = toValue(titleRow.getCell(i));
                String columnName = titles.get(title);
                if (StringUtils.isNotBlank(columnName)) {
                    needDataColumnNum.put(i, columnName);
                }
                allRowColumnCn.put(i, title);
            }


            for (int i = titleRowNum + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Map needData = new HashMap();
                LinkedHashMap extraData = new LinkedHashMap();

                Row row = sheet.getRow(i);
                String firstCellValue = toValue(row.getCell(0));

                if (StringUtils.isBlank(firstCellValue)) {
                    return data;
                }
                for (int j = 0; j < rowLength + 1; j++) {
                    String value = toValue(row.getCell(j));
                    if (needDataColumnNum.containsKey(j)) {
                        needData.put(needDataColumnNum.get(j), value);
                    }
                    extraData.put(allRowColumnCn.get(j), value);
                }
                needData.put("extra", JSONArray.toJSONString(extraData));
                data.add(needData);
            }
            return data;
        } catch (Exception e) {
            logger.error("getListFromSheet [{}] titles[{}]", sheet, titles, e);
            return null;
        }
    }

    /**
     * 只读取第一列
     *
     * @param sheet
     * @return
     */
    public static List<String> getFirstRowFromSheet(Sheet sheet) {
        try {
            if (sheet == null || sheet.getPhysicalNumberOfRows() < 1) {
                return null;
            }
            List<String> data = new ArrayList<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String value = toValue(row.getCell(0));
                data.add(value);
            }
            return data;
        } catch (Exception e) {
            logger.error("getListFromSheet [{}] ", sheet, e);
            return null;
        }
    }


    /**
     * 第一层 sheet
     * 第二层 row
     * 第三层 cell
     *
     * @param file
     * @return
     */
    public static List<List<List<String>>> getListFromExcelFile(MultipartFile file) {
        Workbook workbook = getExcelFromFile(file);
        if (workbook == null || workbook.getNumberOfSheets() < 1) {
        }
        List<List<List<String>>> data = new ArrayList<>();
        try {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                data.add(getListFromSheet(workbook.getSheetAt(i)));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 第一层 sheet
     * 第二层 row
     * 第三层 cell
     *
     * @param file
     * @return
     */
    public static List<List<Map>> getListFromExcelFile(MultipartFile file, List<String> titles) {
        Workbook workbook = getExcelFromFile(file);
        List<List<Map>> data = new ArrayList<>();
        try {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                data.add(getListFromSheet(workbook.getSheetAt(i), titles));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 所有每行个数以第一行为准
     *
     * @param sheet
     * @return
     */
    public static List<Map> getListFromSheet(Sheet sheet, List<String> titles) {
        try {
            if (sheet == null || sheet.getPhysicalNumberOfRows() < 1 || titles == null || titles.size() < 1) {
                return null;
            }
            List<Map> data = new ArrayList<>();
            int rowLength = titles.size();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Map d = new HashMap();
                Row row = sheet.getRow(i);
                for (int j = 0; j < rowLength; j++) {
                    d.put(titles.get(j), toValue(row.getCell(j)));
                }
                data.add(d);
            }
            return data;
        } catch (Exception e) {
            logger.error("getListFromSheet [{}] titles[{}]", sheet, titles, e);
            return null;
        }
    }


    /**
     * 所有每行个数以第一行为准
     *
     * @param sheet
     * @return
     */
    public static List<List<String>> getListFromSheet(Sheet sheet) {
        try {
            if (sheet == null || sheet.getPhysicalNumberOfRows() < 1) {
                return null;
            }
            List<List<String>> data = new ArrayList<>();
            int rowLength = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                List<String> d = new ArrayList<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < rowLength; j++) {
                    d.add(toValue(row.getCell(j)));
                }
                data.add(d);
            }
            return data;
        } catch (Exception e) {
            logger.error("getListFromSheet [{}]", sheet, e);
            return null;
        }
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String toValue(Cell obj) {
        if (obj == null) {
            return null;
        }
        String v = "";
        if (obj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            v = NumberToTextConverter.toText(obj.getNumericCellValue());
        } else {
            v = obj.getStringCellValue();
        }
        if (StringUtils.isEmpty(v)) {
            return null;
        }
        return v;
    }

    public static Workbook getExcelFromFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int lastIndex = fileName.lastIndexOf(".");
        String type = fileName.substring(lastIndex + 1, fileName.length()).toLowerCase();
        InputStream is = null;
        Workbook workbook = null;
        try {
            is = file.getInputStream();
            if ("xls".equals(type)) {
                workbook = new HSSFWorkbook(is);
            } else if ("xlsx".equals(type)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return workbook;
    }

    /**
     * 创建excel文件
     *
     * @param list
     * @param sheetName
     * @param headers
     * @param keys
     * @return
     */
    public static HSSFWorkbook buildExcelDetail(List<Map> list, String sheetName, List<String> headers, List<String> keys) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
//        buildExcelDetail(sheet, headers, list, keys);
        return workbook;
    }

    /*public static void buildExcelDetail(HSSFSheet sheet, List<String> headers, List<Map> list, List<String> keys) {
        SheetUtil sheetUtil = new SheetUtil(sheet);
        if (headers != null) {
            sheetUtil.appendRow(headers);
        }
        if (list != null) {
            for (Map map : list) {
                List values = new ArrayList();
                if (keys != null) {
                    for (String key : keys) {
                        values.add(BeanUtil.getProperty(map, key));
                    }
                } else {
                    map = map == null ? new HashMap() : map;
                    Set<String> mKeys = map.keySet();
                    for (String key : mKeys) {
                        values.add(BeanUtil.getProperty(map, key));
                    }
                }
                sheetUtil.appendRow(values);
            }
        }
    }*/

    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        try {
            String s = obj.toString();
            if (s.indexOf(".") > 0) {
                s = s.substring(0, s.indexOf(".")); // 防止解析为小数，去掉0
            }
            int value = Integer.valueOf(s);
            return value < 0 ? 0 : value;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int toInt(Object obj, int default_value) {
        if (obj == null) {
            return default_value;
        }
        try {
            String s = obj.toString();
            if (s.indexOf(".") > 0) {
                s = s.substring(0, s.indexOf(".")); // 防止解析为小数，去掉0
            }
            int value = Integer.valueOf(s);
            return value;
        } catch (Exception e) {
            return default_value;
        }
    }

    public static Integer toInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String s = obj.toString();
            if (s.trim().length() == 0) {
                return null;
            }
            if (s.indexOf(".") > 0) {
                s = s.substring(0, s.indexOf(".")); // 防止解析为小数，去掉0
            }
            int value = Integer.valueOf(s);
            return value < 0 ? 0 : value;
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer toMultiply100Integer(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String s = obj.toString();
            if (s.trim().length() == 0) {
                return null;
            }
            int idx = s.indexOf(".");
            int len = s.length();
            if (idx > 0) {
                s = s.substring(0, idx) + s.substring(idx + 1, (len - idx - 1) > 2 ? (idx + 3) : len);
            } else {
                s = s + "00";
            }
            int value = Integer.valueOf(s);
            return value < 0 ? 0 : value;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     *  标题列
     * @return
     */
    public static List<String> getFirstRowFromExcelFile(MultipartFile file) {
        List<String> titles = new ArrayList<>();
        Workbook workbook = getExcelFromFile(file);
        if (workbook == null || workbook.getNumberOfSheets() < 1) {
            return null;
        }
        Sheet sheet = workbook.getSheetAt(0);     //读取sheet 0
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colNum; i++) {
            row.getCell((short) i).setCellType(Cell.CELL_TYPE_STRING);
            titles.add((row.getCell((short) i)).getRichStringCellValue().getString());
        }
        return titles;
    }
}
