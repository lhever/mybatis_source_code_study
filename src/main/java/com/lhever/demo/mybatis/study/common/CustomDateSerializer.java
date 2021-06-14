package com.lhever.demo.mybatis.study.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends DateSerializer {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(toISO8601DateString(value));
    }

    public static String toISO8601DateString(Date date) {
        return toISO8601DateString(date, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    /**
     * 将日期对象转换成iso8601格式字符串
     *
     * @param date
     * @return
     * @author lihong10 2018/12/27 16:17
     * @modificationHistory=========================逻辑或功能性重大变更记录
     * @modify by user: {修改人} 2018/12/27 16:17
     * @modify by reason:{原因}
     */
    public static String toISO8601DateString(Date date, String format) {
//		TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat df = new SimpleDateFormat(format);
//		df.setTimeZone(tz);
        String nowAsISO = null;
        try {
            nowAsISO = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowAsISO;
    }
}