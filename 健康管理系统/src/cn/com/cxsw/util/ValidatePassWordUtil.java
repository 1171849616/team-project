package cn.com.cxsw.util;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class ValidatePassWordUtil {
	//校验密码（六位数字）
	public static boolean validateUserPassWord(String passWord){
		if (passWord.length() == 6) {
			char ch = 'A';
			for(int i=0;i<passWord.length();i++){
				ch = passWord.charAt(i);
				if (ch < '0' || ch > '9') {
					return false;
				}else if (i == passWord.length() - 1) {
					return true;
				}
			}
		}else {
			return false;
		}
		
		return false;	
	}
	public static boolean validateName(String name){
		//用户名不可以为空、重复
		if (name == null) {
			return true;
		} else {
			return false;
		}
	}
	public static String validateObjectIsNull(Object obj){
		return obj == null?"":obj.toString();
	}
}
