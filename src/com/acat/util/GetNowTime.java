package com.acat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNowTime {
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String value = sdf.format(new Date());
		return value;
	}
}
