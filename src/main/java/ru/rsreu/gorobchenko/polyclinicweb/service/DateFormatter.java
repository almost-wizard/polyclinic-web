package ru.rsreu.gorobchenko.polyclinicweb.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
    private DateFormatter() {
    }

    public static Date parse(String date, DateFormat format) throws ParseException {
        return new Date(new SimpleDateFormat(format.get()).parse(date).getTime());
    }

    public static String format(Date date, DateFormat format) {
        return new SimpleDateFormat(format.get()).format(new Date(date.getTime()));
    }
}
