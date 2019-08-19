package com.neo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: lishuangqiang
 * @Date: 2019-01-10
 * @Description: com.wosai.app.utils
 * @version: 1.0
 */
public class TimeUitl {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String stampToDate(Long timeStamp) {

        Date date = new Date(timeStamp);
        String res = simpleDateFormat.format(date);
        return res;
    }

    public static String getDate() {
        Date date = new Date();
        String res = simpleDateFormat.format(date);
        return res;
    }

    /**
     *
     * 功能描述:long类型的数据转为Date类型
     * @auther: lishuangqiang
     * @date: 2019/8/19
     * @param: [timeStamp]
     * @return: java.util.Date
     */
    public static Date formatDate(long timeStamp) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(timeStamp);
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 比较日期（年月日）
     */

    public static boolean dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateFirst = dateFormat.parse(dateFormat.format(date1));
            Date dateLast = dateFormat.parse(dateFormat.format(date2));
            if (dateFirst.after(dateLast)) {
                return true;
            } else if (dateFirst.before(dateLast)) {
                return false;
            }
            return false;
        } catch (ParseException e) {
            return false;
        }
    }
}
