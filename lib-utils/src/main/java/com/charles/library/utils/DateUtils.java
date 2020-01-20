package com.charles.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 刘奉强 on 2019/3/15 23:05
 * <p>
 * Describe：
 */
public class DateUtils {

    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_DAY = "yyyy-MM-dd";

    public static String format(Date date, String format) {
        if ((date == null) || StringUtils.isEmpty(format)) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }
}
