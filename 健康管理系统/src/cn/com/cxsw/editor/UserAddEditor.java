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
	private Text text_1;
	private Text text_2;
	private Text text_4;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	

	public UserAddEditor() {
		setContentDescription("\u63D0\u4EA4\u6570\u636E");
		
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
		label.setBounds(123, 151, 48, 24);
		label.setText("\u7F16\u53F7\uFF1A");
		
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
		text.setBounds(205, 151, 167, 30);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(557, 552, 81, 24);
		label_2.setText("\u51FA\u9662\u5EFA\u8BAE\uFF1A");
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setItems(new String[] {"\u7559\u9662\u89C2\u5BDF", "\u5EFA\u8BAE\u51FA\u9662"});
		combo.setBounds(684, 544, 143, 32);
		combo.select(2);
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setBounds(123, 231, 73, 24);
		label_7.setText("\u59D3\u540D\uFF1A");
		text_3 = new Text(container, SWT.BORDER);
		text_3.addFocusListener(new FocusAdapter() {
		});
		text_3.setBounds(205, 228, 167, 30);
		
		String id = LoginDialog.id;
		text_5 = new Text(container, SWT.BORDER);
		text_5.setBounds(205, 315, 167, 30);
		Button button = new Button(container, SWT.NONE);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button.addSelectionListener(new SelectionAdapter() {
			//保存操作
			public void widgetSelected(SelectionEvent e) {
				//1、获取用户信息
				String id = text.getText().trim();
				String name = text_3.getText().trim();
				String tiwen = text_5.getText().trim();
				String xueya = text_1.getText().trim();
				String maibo = text_2.getText().trim();
				String high = text_4.getText().trim();
				String wight = text_6.getText().trim();
				String health = text_7.getText().trim();
				String segust = text_8.getText().trim();
				String go = combo.getText();
				Date date=new Date();
				String d=date.toLocaleString();
				System.out.println(d);
				//2、执行保存sql
				
				String sql="INSERT INTO DATE(u_id,u_name,d_tiwen,d_xueya,d_maibo,d_high,d_wight,d_health, d_jianyi,d_go, d_date) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				int res=db.update(sql, id,name,tiwen,xueya,maibo,high,wight,health,segust,go,d);
				if(res > 0){
					MsgUtil.showMsgOK("提交成功!");
				}else{
					MsgUtil.showMsgError("提交失败!");
				}
			}
		});
		button.setBounds(429, 648, 114, 34);
		button.setText("\u63D0\u4EA4");
		
		
		
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_9.setBounds(123, 318, 73, 24);
		label_9.setText("\u4F53\u6E29\uFF1A");
		
		
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_10.setBounds(124, 24, 337, 48);
		label_10.setText("\u60A3\u8005\u6570\u636E\u63D0\u4EA4");
		
		Label label_11 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(35, 78, 1294, 2);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u8840\u538B\uFF1A");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(123, 390, 73, 24);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(205, 387, 167, 30);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("\u8109\u640F\uFF1A");
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(123, 471, 73, 24);
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setBounds(205, 468, 167, 30);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("\u8EAB\u9AD8\uFF1A");
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(123, 552, 73, 24);
		
		text_4 = new Text(container, SWT.BORDER);
		text_4.setBounds(205, 549, 167, 30);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("\u4F53\u91CD\uFF1A");
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(548, 148, 73, 24);
		
		text_6 = new Text(container, SWT.BORDER);
		text_6.setBounds(630, 145, 167, 30);
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setText("\u5065\u5EB7\u72B6\u51B5\uFF1A");
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setBounds(548, 234, 90, 24);
		
		text_7 = new Text(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_7.setBounds(665, 231, 176, 140);
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setText("\u533B\u751F\u5EFA\u8BAE\uFF1A");
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setBounds(557, 407, 90, 24);
		
		text_8 = new Text(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_8.setBounds(665, 387, 176, 140);
		
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
