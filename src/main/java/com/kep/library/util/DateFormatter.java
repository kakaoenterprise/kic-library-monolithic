package com.kep.library.util;

import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter  {

    private static final String pattern = "yyyyMMdd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    public static String getDateToString(Date date) {
        return formatter.format(date);
    }
}

