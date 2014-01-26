package com.apress.prospring3.ch16.xml.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {

	private static String dateFormatPattern;
	
	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}
	
	public Object convertUponGet(Object value) {
		Date date = (Date) value;
		return format(date);
	}
	
	public Object convertUponSet(Object value) {
		String dateString = (String) value;
		return parse(dateString);
	}
	
	public Class<Date> getFieldType() {
		return Date.class;
	}
	
	protected static String format(final Date date) {
		String dateString = "";
		
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
			dateString = simpleDateFormat.format(date);
		}
		
		return dateString;
	}
	
	protected static Date parse(final String dateString) {
		Date date = new Date();
		
		if (dateString != null) {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
				date = simpleDateFormat.parse(dateString);
			} catch (Exception e) {
			}
		}
		
		return date;
	}
	
}
