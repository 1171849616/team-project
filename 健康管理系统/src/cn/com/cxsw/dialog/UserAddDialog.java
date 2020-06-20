package cn.com.cxsw.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.Date;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.cxsw.util.DateUtil;
import cn.com.cxsw.util.DbUtil;
import cn.com.cxsw.util.MsgUtil;
import cn.com.cxsw.util.ValidatePassWordUtil;
import cn.com.cxsw.utils.ImageUtils;
import cn.com.cxsw.utils.StringRegexUtils;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class UserAddDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_3;
	private Text text_4;
	private DbUtil db=new DbUtil();
	private String sourcePath;
	private Text text_5;


	/**
	 * Create the dialog.
	 * @param 
	 * @param style
	 */
	public UserAddDialog(Shell parent, int style) {
		super(parent, style);
		setText("用户注册");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shell.setBackgroundImage(SWTResourceManager.getImage(UserAddDialog.class, "/cn/com/cxsw/imgs/logo.PNG"));
		shell.setSize(1390, 885);
		shell.setText(getText());
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD));
		composite.setBackgroundImage(SWTResourceManager.getImage(UserAddDialog.class, "/cn/com/cxsw/imgs/32.PNG"));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite.setBounds(0, 0, 1384, 845);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setText("\u6027\u522B\uFF1A");
		label_1.setBounds(123, 376, 48, 24);
		
		Button button = new Button(composite, SWT.RADIO);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setText("男");
		button.setBounds(202, 376, 61, 24);
		
		Button button_1 = new Button(composite, SWT.RADIO);
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_1.setText("女");
		button_1.setBounds(299, 376, 57, 24);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setText("\u6240\u5728\u5730\u533A\uFF1A");
		label_2.setBounds(579, 413, 82, 24);
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setItems(new String[] {"\u5170\u5DDE\u5E02", "\u5317\u4EAC\u5E02", "\u5357\u4EAC\u5E02", "\u4E0A\u6D77\u5E02", "\u82CF\u5DDE\u5E02", "\u53A6\u95E8\u5E02"});
		combo.setBounds(694, 410, 161, 32);
		combo.select(2);
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setBounds(202, 170, 196, 30);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setText("\u59D3\u540D\uFF1A");
		label_3.setBounds(123, 173, 64, 24);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setText("\u5BC6\u7801\uFF1A");
		label_7.setBounds(123, 284, 57, 24);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.addFocusListener(new FocusAdapter() {
			
		});
		label_8.setText(" *");
		label_8.setForeground(SWTResourceManager.getColor(255, 99, 71));
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		label_8.setBounds(404, 284, 57, 24);
		
		text_3 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 校验密码（数字，六位）
				String passWord = text_3.getText().trim();
				if (ValidatePassWordUtil.validateUserPassWord(passWord)) {
					label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					label_8.setText(" √ ");
				} else {
					label_8.setForeground(SWTResourceManager.getColor(255, 99, 71));
					label_8.setText(" * ");
				}
			}
		});
		text_3.setBounds(202, 281, 196, 30);
		
		Label label_14 = new Label(composite, SWT.NONE);
		label_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_14.setText(" *");
		label_14.setForeground(SWTResourceManager.getColor(255, 99, 71));
		label_14.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		label_14.setBounds(873, 495, 57, 24);
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String sql1 = "";
				String name = text_5.getText().trim();
				String password = text_3.getText().trim();
				String sex = "";
				if (button.getSelection()) {
					sex = "男";
				}else if(button_1.getSelection()){
					sex = "女";
				}
				Date date=new Date();
				String d=date.toLocaleString();
				String city = combo.getText();
				String phone = text_4.getText().trim();

				//校验电话号码
				boolean res1 = StringRegexUtils.isRegexpValidate(phone, StringRegexUtils.mobil_regexp);
				if (!res1) {
					MsgUtil.showMsgInfo("电话号码不合法！");
					return;
				}
				//上传照片
				if (sourcePath == null) {
					MsgUtil.showMsgInfo("请先选择头像!");
					return;
				}
				String fileName = ImageUtils.uploadImg(sourcePath, "images");
				//执行保存sql
				String sql = "INSERT INTO USER(u_name,u_p"
						+ "word,u_sex,u_birdate,u_imgs,u_city,u_phone) VALUES(?,?,?,?,?,?,?)";
				int res = db.update(sql,name,password,sex,d,fileName,city,phone);
				if (res > 0) {
					MsgUtil.showMsgOK("注册成功！");
					shell.close();
				}else{
					MsgUtil.showMsgError("注册失败！");
				}
				
			}
		});
		button_3.setText("\u6CE8\u518C");
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		button_3.setBounds(471, 648, 114, 34);
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setText("\u7535\u8BDD\u53F7\u7801\uFF1A");
		label_10.setBounds(579, 495, 90, 24);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//校验电话号码
				String phone = text_4.getText().trim();
				if (StringRegexUtils.isRegexpValidate(phone, StringRegexUtils.mobil_regexp)) {
					label_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					label_14.setText(" √ ");
				} else {
					label_14.setForeground(SWTResourceManager.getColor(255, 99, 71));
					label_14.setText(" * ");
				}
			}
		});
		text_4.setBounds(688, 492, 167, 30);
		
		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_11.setText("\u7F16\u8F91\u4E2A\u4EBA\u4FE1\u606F");
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label_11.setBounds(124, 24, 210, 48);
		
		Label label_12 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_12.setBounds(35, 78, 1294, 2);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("\u51FA\u751F\u5E74\u6708\uFF1A");
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(123, 485, 82, 24);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(225, 485, 135, 33);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setText("\u5934\u50CF\uFF1A");
		label_5.setBounds(574, 157, 61, 24);
		
		Label label_6 = new Label(composite, SWT.BORDER);
		label_6.setBounds(661, 143, 194, 174);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//预览本地照片
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
					sourcePath = fd.open();
					if (sourcePath != null) {
						ImageUtils.showImg(label_6, sourcePath);
				}
			}
		});
		button_2.setText("\u9884\u89C8\u5934\u50CF");
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		button_2.setBounds(712, 334, 114, 34);
		
		
		
	}
}
