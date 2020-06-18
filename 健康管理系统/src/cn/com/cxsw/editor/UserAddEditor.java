package cn.com.cxsw.editor;

import java.awt.Image;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.osgi.internal.messages.Msg;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.util.DateUtil;
import cn.com.cxsw.util.DbUtil;
import cn.com.cxsw.util.MsgUtil;
import cn.com.cxsw.util.ValidatePassWordUtil;
import cn.com.cxsw.utils.ImageUtils;
import cn.com.cxsw.utils.StringRegexUtils;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

public class UserAddEditor extends EditorPart {

	public static final String ID = "cn.com.cxsw.editor.UserAddEditor"; //$NON-NLS-1$
	private Text text;
	private Text text_3;
	private DbUtil db=new DbUtil();
	private String sourcePath;
	private Text text_5;
	public String fileName = "";
	

	public UserAddEditor() {
		
	}
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));

		parent.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD));
		Composite container = new Composite(parent, SWT.V_SCROLL);
		container.setBackgroundImage(SWTResourceManager.getImage(UserAddEditor.class, "/cn/com/cxsw/imgs/32.PNG"));
		container.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD));
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setImage(SWTResourceManager.getImage(UserAddEditor.class, "/cn/com/cxsw/imgs/\u80CC\u666F.jpg"));
		label.setBounds(123, 275, 48, 24);
		label.setText("\u5B66\u53F7\uFF1A");
		
		Button button_1 = new Button(container, SWT.RADIO);
		button_1.setText("\u662F");
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_1.setBounds(570, 318, 57, 24);
		
		Button button_2 = new Button(container, SWT.RADIO);
		button_2.setText("\u5426");
		button_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_2.setBounds(671, 318, 61, 24);
		
		Button button_3 = new Button(container, SWT.RADIO);
		button_3.setText("\u5426");
		button_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_3.setBounds(671, 461, 61, 24);
		
		Button button_4 = new Button(container, SWT.RADIO);
		button_4.setText("\u662F");
		button_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_4.setBounds(570, 461, 57, 24);
		
		text = new Text(container, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
		});
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("获得了焦点");
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		text.setBounds(205, 272, 188, 30);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(123, 466, 81, 24);
		label_2.setText("\u6240\u5728\u5730\u533A\uFF1A");
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setItems(new String[] {"\u5170\u5DDE\u5E02", "\u5317\u4EAC\u5E02", "\u5357\u4EAC\u5E02", "\u4E0A\u6D77\u5E02", "\u82CF\u5DDE\u5E02", "\u53A6\u95E8\u5E02"});
		combo.setBounds(223, 458, 143, 32);
		combo.select(2);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(552, 393, 184, 24);
		label_4.setText("\u662F\u5426\u5DF2\u7ECF\u786E\u8BA4\u88AB\u611F\u67D3\uFF1A");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(552, 263, 180, 24);
		label_5.setText("\u662F\u5426\u6709\u75AB\u60C5\u7591\u4F3C\u75C7\u72B6\uFF1A");
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setBounds(123, 337, 73, 24);
		label_7.setText("\u586B\u62A5\u4EBA\uFF1A");
		text_3 = new Text(container, SWT.BORDER | SWT.PASSWORD);
		text_3.addFocusListener(new FocusAdapter() {
		});
		text_3.setBounds(205, 334, 161, 30);
		
		String id = LoginDialog.id;
		text_5 = new Text(container, SWT.BORDER);
		text_5.setBounds(214, 393, 167, 30);
		Button button = new Button(container, SWT.NONE);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button.addSelectionListener(new SelectionAdapter() {
			//保存操作
			public void widgetSelected(SelectionEvent e) {
				//1、获取用户信息
				String id = text.getText().trim();
				String name = text_3.getText().trim();
				String phone = text_5.getText().trim();	
				String city = combo.getText();
				String ys = "";
				if (button_1.getSelection()) {
					ys = "是";
				}else if(button_2.getSelection()){
					ys = "否";
				}
				String sure = "";
				if (button_4.getSelection()) {
					sure = "是";
				}else if(button_3.getSelection()){
					sure = "否";
				}
				Date date=new Date();
				String d=date.toLocaleString();
				System.out.println(d);
				//2、执行保存sql
				String sql="update user set u_num= ?,u_city= ?,u_ys= ?,u_sure= ?,u_date= ? where u_id = ?";
				int res=db.update(sql, phone,city,ys,sure,date,id);
				if(res > 0){
					MsgUtil.showMsgOK("提交成功!");
				}else{
					MsgUtil.showMsgError("提交失败!");
				}
			}
		});
		button.setBounds(418, 609, 114, 34);
		button.setText("\u63D0\u4EA4");
		
		//查询数据
		String sql="select * from user where u_id = ?";
		List<Map<String,Object>> list=db.query(sql,id);
		if(list.size() == 1){
			Map<String,Object> map=list.get(0);
			text.setText(map.get("u_id").toString());
			text_3.setText(map.get("u_name").toString());
			String ys=map.get("u_ys").toString();
			if("是".equals(ys)){
				button_1.setSelection(true);
			}else if("否".equals(ys)){
				button_2.setSelection(true);
			}else if(" ".equals(ys)){
				return;
			}
			String sure=map.get("u_sure").toString();
			if("是".equals(ys)){
				button_4.setSelection(true);
			}else if("否".equals(ys)){
				button_3.setSelection(true);
			}else if(" ".equals(sure)){
				return;
			}
			combo.setText(ValidatePassWordUtil.validateObjectIsNull(map.get("u_city")));
			text_5.setText(map.get("u_num").toString());
		}
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_9.setBounds(123, 399, 84, 24);
		label_9.setText("\u7535\u8BDD\u53F7\u7801\uFF1A");
		
		
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_10.setBounds(124, 24, 337, 48);
		label_10.setText("\u5B66\u751F\u75AB\u60C5\u60C5\u51B5\u6BCF\u65E5\u4E0A\u62A5\u8868");
		
		Label label_11 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(35, 78, 1294, 2);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 19, SWT.NORMAL));
		label_1.setBounds(117, 143, 905, 57);
		label_1.setText("\u8BF7\u4E8E\u6BCF\u65E510:00\u4E4B\u524D\u5BF9\u4E2A\u4EBA\u5065\u5EB7\u4FE1\u606F\u8FDB\u884C\u586B\u5199\u5E76\u63D0\u4EA4\uFF01");
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
