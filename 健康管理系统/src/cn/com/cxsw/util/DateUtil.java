package cn.com.cxsw.util;

import org.eclipse.swt.widgets.DateTime;

public class DateUtil {

	public static String getDateTime(DateTime dateTime){
		return dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
	}

}
