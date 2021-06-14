package com.lhever.demo.mybatis.study.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDateDeSerializer extends DateDeserializers.DateDeserializer {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p != null) {
            String rawDateStr = p.getText();

            Date date = toDate(rawDateStr);
            if (date != null) {
                return date;
            }
        }
        return super.deserialize(p, ctxt);
    }

    private static boolean isBlank(String str) {
        return (str == null) || (str.trim().length() == 0);

    }

    private static boolean isNotBlank(String str) {
        return !isBlank(str);

    }

    public static boolean matchRegex(String s, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }


    public static Date toDate(String dateStr) {

        if (isBlank(dateStr)) {
            return null;
        }

        String[][] patterns = new String[][]{
                new String[]{"^(\\d{4}-\\d{2}-\\d{2}[T]{1}\\d{2}:\\d{2}:\\d{2}[Z]{1})$", "yyyy-MM-dd'T'HH:mm:ssXXX"},
                new String[]{"(^(\\d{4}-\\d{2}-\\d{2}[T]{1}\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[Z]{1})$)", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"},
                new String[]{"(^(\\d{4}-\\d{2}-\\d{2}[T]{1}\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[+-]{1}\\d{2}:\\d{2})$)", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"},
                new String[]{"(^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})$)", "yyyy-MM-dd HH:mm:ss"},
                new String[]{"(^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})$)", "yyyy-MM-dd HH:mm:ss.SSS"},
                new String[]{"(^(\\d{4}-\\d{2}-\\d{2}[T]{1}\\d{2}:\\d{2}:\\d{2}\\.\\d{6})$)", "yyyy-MM-dd'T'HH:mm:ss.SSS"},
        };

        Date date = null;
        for (String[] pattern : patterns) {
            if (!matchRegex(dateStr, pattern[0])) {
                continue;
            }
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern[1]);
                date = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("日期" + dateStr + "格式转换错误", e);
            }

            return date;
        }

        try {
            date = new Date(Long.parseLong(dateStr));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("日期" + dateStr + "不是long型", e);
        }
        return date;
    }
}
