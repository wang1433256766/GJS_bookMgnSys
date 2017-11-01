package cn.com.miaoto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);

	public static String getCurrentTimeStr() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(new Date());
	}

	public static String addDayOfCurrent(int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
	}

	public static String addDayOfTime(String time, int day) {
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			LOGGER.error("transform str to date failed");
			return null;
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
	}
}
