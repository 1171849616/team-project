package cn.com.cxsw.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.List;
import java.util.Map;

import org.eclipse.osgi.internal.messages.Msg;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import cn.com.cxsw.util.DbUtil;
import cn.com.cxsw.util.MsgUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

public class LoginDialog extends Dialog {

	protected boolean result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private DbUtil db = new DbUtil();
	public static String power;
	public static String id;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("登录");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open() {
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
		shell.setImage(SWTResourceManager.getImage(LoginDialog.class, "/cn/com/cxsw/imgs/logo.PNG"));
		shell.setSize(1495, 796);
		shell.setText(getText());
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(LoginDialog.class, "/cn/com/cxsw/imgs/\u80CC\u666F2.PNG"));
		composite.setBounds(0, 0, 746, 754);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(LoginDialog.class, "/cn/com/cxsw/imgs/\u80CC\u666F1.PNG"));
		composite_1.setBounds(743, 288, 746, 466);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(262, 95, 56, 24);
		label.setText("\u8D26\u53F7");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(338, 92, 182, 30);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(321, 163, 64, 24);
		label_1.setText("  \u5BC6\u7801");
		
		text_1 = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(404, 163, 81, 24);
		
		Button button_2 = new Button(composite_1, SWT.RADIO);
		button_2.setBounds(244, 242, 159, 24);
		button_2.setText("\u7BA1\u7406\u5458");
		
		Button button_3 = new Button(composite_1, SWT.RADIO);
		button_3.setBounds(404, 242, 141, 24);
		button_3.setText("\u7528\u6237");
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setBounds(212, 356, 114, 34);
		button.addSelectionListener(new SelectionAdapter() {
			//登录
			@Override
			public void widgetSelected(SelectionEvent e) {
				String code = text.getText().trim();
				String psw = text_1.getText().trim();
				if (button_3.getSelection()) {
					String sql= "SELECT * FROM user WHERE u_id = ? AND u_pword = ? AND u_state = 1";
					List<Map<String,Object>> list = db.query(sql, code,psw);
					if(list.size() == 1){
						MsgUtil.showMsgInfo("BIU~安全着陆！");
						power = list.get(0).get("u_power").toString();
						id = list.get(0).get("u_id").toString();
						result = true;
						shell.close();
					}else {
						result = false;
						MsgUtil.showMsgInfo("阿欧，飘远了！");
					}
				}else if(button_2.getSelection()){
					String sql = "SELECT * FROM keeper WHERE k_id = ? AND k_pword = ? AND k_state = 1";
					List<Map<String,Object>> list = db.query(sql, code,psw);
					if(list.size() == 1){
						MsgUtil.showMsgInfo("BIU~安全着陆！");
						power = list.get(0).get("k_power").toString();
						result = true;
						shell.close();
					}else {
						result = false;
						MsgUtil.showMsgInfo("阿欧，飘远了！");
					}
				}
			}
		});
		button.setText("\u767B\u5F55");
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//打开注册的dialog
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserAddDialog ua = new UserAddDialog(shell, SWT.NONE);
				ua.open();
			}
		});
		button_1.setBounds(469, 356, 114, 34);
		button_1.setText("\u6CE8\u518C");
		
		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(LoginDialog.class, "/cn/com/cxsw/imgs/12345.PNG"));
		composite_2.setBounds(743, 34, 743, 259);
		
		Label lbllisten = new Label(composite_2, SWT.NONE);
		lbllisten.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.BOLD | SWT.ITALIC));
		lbllisten.setForeground(SWTResourceManager.getColor(128, 128, 128));
		lbllisten.setBounds(173, 87, 428, 65);
		lbllisten.setText("        \u5065\u5EB7\u7BA1\u7406\u7CFB\u7EDF");
		
		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setBackgroundImage(SWTResourceManager.getImage(LoginDialog.class, "/cn/com/cxsw/imgs/12345.PNG"));
		composite_3.setBounds(751, 0, 728, 38);

	}
}
