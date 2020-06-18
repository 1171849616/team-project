package cn.com.cxsw.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MsgUtil {
	public static void showMsgInfo(String msg){
		MessageBox box = new MessageBox(new Shell(),SWT.ICON_INFORMATION);
		box.setText("ϵͳ��ʾ");
		box.setMessage(msg);
		box.open();
	}
	public static void showMsgError(String msg){
		MessageBox box = new MessageBox(new Shell(),SWT.ICON_INFORMATION);
		box.setText("ϵͳ��ʾ");
		box.setMessage(msg);
		box.open();
	}
	public static void showMsgOK(String msg){
		MessageBox box = new MessageBox(new Shell(),SWT.ICON_WORKING);
		box.setText("ϵͳ��ʾ");
		box.setMessage(msg);
		box.open();
	}
	public static int showAskBox(String msg){
		MessageBox box = new MessageBox(new Shell(), SWT.OK|SWT.CANCEL);
		box.setText("ϵͳ��ʾ");
		box.setMessage(msg);
		return box.open();
	}

}
