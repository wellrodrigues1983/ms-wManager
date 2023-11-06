package com.wmanager.auth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public final static String DATE_FORMAT = "dd/MM/yyyy";
	public final static String DATE_FORMAT_SQL_SERVER = "yyyy/MM/dd";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
	public static final String DATE_FORMAT_DDMMYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	static Locale LOCALE_PT_BR = new Locale("pt", "BR");

	public static Date formatarData(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DDMMYYYHHMMSS, LOCALE_PT_BR);
        String dataFormatada = sdf.format(data);
        return sdf.parse(dataFormatada);
    }

}
