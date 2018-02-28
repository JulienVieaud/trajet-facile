package com.poe.trajetfacile.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeConverter implements Converter<String, Date> {

	private static final Logger LOG = LoggerFactory.getLogger(DatetimeConverter.class);

	private final String dateFormat;

	public DatetimeConverter(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public Date convert(String dateToParse) {
		if (dateToParse == null || dateToParse.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(dateToParse);
		} catch (ParseException e) {
			LOG.error(e.toString(), e);
		}
		return parsedDate;
	}
}
