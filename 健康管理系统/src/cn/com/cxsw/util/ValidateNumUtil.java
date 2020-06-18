package cn.com.cxsw.util;

public class ValidateNumUtil {
	//唱片数量校验
	public static boolean validateNum(String num){
		char ch = 'A';
		for(int i=0;i<num.length();i++){
			ch = num.charAt(i);
			if (ch < '0' || ch > '9') {
				return false;
			}else if (i == num.length() - 1) {
				return true;
			}
		
	}
		return false;
}
	
}
