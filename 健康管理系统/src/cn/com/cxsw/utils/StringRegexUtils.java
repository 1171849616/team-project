package cn.com.cxsw.utils;


public class StringRegexUtils {
	
	/**
	 * ƥ��windows�¾���·�� <br>
	 * 
	 * ��ʽ: c:/admins4512.gif
	 * 
	 * ƥ�� : c:/temp/admins4512.gif <br>
	 * 
	 * ��ƥ��: /admin/admins.gif
	 * 
	 */
	
	public static final String abs_url_regexp = "[a-zA-Z]:(((\\\\)|/)\\w+)(\\2\\w+)*(\\.\\w+)?";

	/**
	 * ƥ��ͼ�� <br>
	 * 
	 * ��ʽ: /���·��/�ļ���.��׺ (��׺Ϊgif,dmp,png)
	 * 
	 * ƥ�� : /forum/head_icon/admini2005111_ff.gif �� admini2005111.dmp<br>
	 * 
	 * ��ƥ��: c:/admins4512.gif
	 * 
	 */
	public static final String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

	/**
	 * ƥ��email��ַ <br>
	 * 
	 * ��ʽ: XXX@XXX.XXX.XX
	 * 
	 * ƥ�� : foo@bar.com �� foobar@foobar.com.au <br>
	 * 
	 * ��ƥ��: foo@bar �� $$$@bar.com
	 * 
	 */
	public static final String email_regexp = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";

	/**
	 * ƥ��ƥ�䲢��ȡurl <br>
	 * 
	 * ��ʽ: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
	 * 
	 * ƥ�� : http://www.suncer.com ��news://www<br>
	 * 
	 * ��ȡ(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
	 * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
	 * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
	 * matchResult.group(4) = /index.html?login=true
	 * 
	 * ��ƥ��: c:\window
	 * 
	 */
	public static final String url_regexp = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * ƥ�䲢��ȡhttp <br>
	 * 
	 * ��ʽ: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX �� ftp://XXX.XXX.XXX ��
	 * https://XXX
	 * 
	 * ƥ�� : http://www.suncer.com:8080/index.html?login=true<br>
	 * 
	 * ��ȡ(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
	 * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
	 * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
	 * matchResult.group(4) = /index.html?login=true
	 * 
	 * ��ƥ��: news://www
	 * 
	 */
	public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * ƥ������ <br>
	 * 
	 * ��ʽ(��λ��Ϊ0): XXXX-XX-XX �� XXXX XX XX �� XXXX-X-X <br>
	 * 
	 * ��Χ:1900--2099 <br>
	 * 
	 * ƥ�� : 2005-04-04 <br>
	 * 
	 * ��ƥ��: 01-01-01
	 * 
	 */
	public static final String date_regexp = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";// ƥ������

	/**
	 * ƥ��绰 <br>
	 * 
	 * ��ʽΪ: 0XXX-XXXXXX(10-13λ��λ����Ϊ0) ��0XXX XXXXXXX(10-13λ��λ����Ϊ0) �� <br>
	 * (0XXX)XXXXXXXX(11-14λ��λ����Ϊ0) �� XXXXXXXX(6-8λ��λ��Ϊ0) ��
	 * XXXXXXXXXXX(11λ��λ��Ϊ0) <br>
	 * 
	 * ƥ�� : 0371-123456 �� (0371)1234567 �� (0371)12345678 �� 010-123456 ��
	 * 010-12345678 �� 12345678912 <br>
	 * 
	 * ��ƥ��: 1111-134355 �� 0123456789
	 * 
	 */
	public static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

	/**
	 * ƥ�����֤ <br>
	 * 
	 * ��ʽΪ: XXXXXXXXXX(10λ) �� XXXXXXXXXXXXX(13λ) �� XXXXXXXXXXXXXXX(15λ) ��
	 * XXXXXXXXXXXXXXXXXX(18λ) <br>
	 * 
	 * ƥ�� : 0123456789123 <br>
	 * 
	 * ��ƥ��: 0123456
	 * 
	 */
	public static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

	/**
	 * ƥ���ʱ���� <br>
	 * 
	 * ��ʽΪ: XXXXXX(6λ) <br>
	 * 
	 * ƥ�� : 012345 <br>
	 * 
	 * ��ƥ��: 0123456
	 * 
	 */
	public static final String ZIP_regexp = "^[0-9]{6}$";// ƥ���ʱ����

	/**
	 * �����������ַ���ƥ�� (�ַ����в��������� ��ѧ�η���^ ������' ˫����" �ֺ�; ����, ñ��: ��ѧ����- �Ҽ�����> �������< ��б��\
	 * ���ո�,�Ʊ��,�س����� )<br>
	 * 
	 * ��ʽΪ: x �� һ��һ�ϵ��ַ� <br>
	 * 
	 * ƥ�� : 012345 <br>
	 * 
	 * ��ƥ��: 0123456
	 * 
	 */
	public static final String non_special_char_regexp = "^[^'\"\\;,:-<>\\s].+$";// ƥ���ʱ����

	/**
	 * ƥ��Ǹ������������� + 0)
	 */
	public static final String non_negative_integers_regexp = "^\\d+$";

	/**
	 * ƥ�䲻������ķǸ������������� > 0)
	 */
	public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";

	/**
	 * 
	 * ƥ��������
	 * 
	 */
	public static final String positive_integer_regexp = "^[0-9]*[1-9][0-9]*$";

	/**
	 * 
	 * ƥ����������������� + 0��
	 * 
	 */
	public static final String non_positive_integers_regexp = "^((-\\d+)|(0+))$";

	/**
	 * 
	 * ƥ�为����
	 * 
	 */
	public static final String negative_integers_regexp = "^-[0-9]*[1-9][0-9]*$";

	/**
	 * 
	 * ƥ������
	 * 
	 */
	public static final String integer_regexp = "^-?\\d+$";

	/**
	 * 
	 * ƥ��Ǹ����������������� + 0��
	 * 
	 */
	public static final String non_negative_rational_numbers_regexp = "^\\d+(\\.\\d+)?$";

	/**
	 * 
	 * ƥ����������
	 * 
	 */
	public static final String positive_rational_numbers_regexp = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

	/**
	 * 
	 * ƥ��������������������� + 0��
	 * 
	 */
	public static final String non_positive_rational_numbers_regexp = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

	/**
	 * 
	 * ƥ�为������
	 * 
	 */
	public static final String negative_rational_numbers_regexp = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

	/**
	 * 
	 * ƥ�両����
	 * 
	 */
	public static final String rational_numbers_regexp = "^(-?\\d+)(\\.\\d+)?$";

	/**
	 * 
	 * ƥ����26��Ӣ����ĸ��ɵ��ַ���
	 * 
	 */
	public static final String letter_regexp = "^[A-Za-z]+$";

	/**
	 * 
	 * ƥ����26��Ӣ����ĸ�Ĵ�д��ɵ��ַ���
	 * 
	 */
	public static final String upward_letter_regexp = "^[A-Z]+$";

	/**
	 * 
	 * ƥ����26��Ӣ����ĸ��Сд��ɵ��ַ���
	 * 
	 */
	public static final String lower_letter_regexp = "^[a-z]+$";

	/**
	 * 
	 * ƥ�������ֺ�26��Ӣ����ĸ��ɵ��ַ���
	 * 
	 */
	public static final String letter_number_regexp = "^[A-Za-z0-9]+$";

	/**
	 * 
	 * ƥ�������֡�26��Ӣ����ĸ�����»�����ɵ��ַ���
	 * 
	 */
	public static final String letter_number_underline_regexp = "^\\w+$";

	/**
	 * ����ƥ�� <br>
	 * 
	 */
	public static final String china_regexp = "[\u4E00-\u9FA5]*";
	
	/**
	 * �ֻ�����ƥ��
	 */
	public static final String mobil_regexp = "^(?:13\\d|15[012356789]|18[012356789]|147)-?\\d{5}(\\d{3}|\\*{3})$";
	
	/**
	 * QQ������֤ ��С5λ�������11λ
	 */
	public static final String qq_regexp = "^[1-9]\\d{4,12}$";
	
	/**
	 * ���ô˷���   ����ҪУ����ַ�����������ʽ��������ʽֱ�ӵ��ñ����ж������Ӧ������
	 * ���磺Ҫ��֤�ֻ������Ƿ���ȷ��
	 * String phone="18393918132";
	 * if(StringRegexUtils.isRegexpValidate(phone,StringRegexUtils.mobil_regexp)){
	 * 	System.out.println("�绰�����ʽ��ȷ");
	 * }else{
	 * 	System.out.println("�绰�����ʽ����ȷ");
	 * }
	 */
	public static boolean isRegexpValidate(String source, String regexp) {
		return source.matches(regexp);
	}

}
