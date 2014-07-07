package com.kjksoft.mcdesigner.client.gwt.time;

import java.util.Date;

import com.google.gwt.core.client.Duration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class TimeUtil {
	private static final DateTimeFormat timeFormatter = DateTimeFormat.getFormat(PredefinedFormat.TIME_FULL);
	
	private static final NumberFormat msFormat = NumberFormat.getFormat("000");
	
	public static final String time() {
		return "[" + timeFormatter.format(new Date()) + "]";
	}
	
	public static final String elapsedStr(Duration d) {
		int s = d.elapsedMillis()/1000;
		int ms = d.elapsedMillis()%1000;
		return "[" + s + "." + msFormat.format(ms) + "]";
	}
}
