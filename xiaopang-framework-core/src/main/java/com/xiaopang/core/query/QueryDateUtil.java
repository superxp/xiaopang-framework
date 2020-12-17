package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: QueryDateUtil
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:11
 */
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

class QueryDateUtil {
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final FastDateFormat DATE_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat DATE_TIME_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat DATE_TIME_ZONE_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    QueryDateUtil() {
    }

    public static Date parseDate(String dateStr, int dateTimeLength) throws IOException {
        if ("yyyy-MM-dd".length() == dateTimeLength) {
            return toDate(dateStr, DATE_FORMATTER);
        } else if ("yyyy-MM-dd HH:mm:ss".length() == dateTimeLength) {
            return toDate(dateStr, DATE_TIME_FORMATTER);
        } else {
            return "yyyy-MM-dd'T'HH:mm:ss.SSSZ".length() == dateTimeLength ? toDate(dateStr, DATE_TIME_ZONE_FORMATTER) : null;
        }
    }

    public static Date toDate(String text, FastDateFormat formatter) throws IOException {
        try {
            return formatter.parse(text);
        } catch (ParseException var3) {
            throw new IOException(var3);
        }
    }
}

