package cn.com.cxsw.util;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class ValidatePassWordUtil {
	//У�����루��λ���֣�
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
		//�û���������Ϊ�ա��ظ�
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
