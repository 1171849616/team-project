package cn.com.cxsw.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.util.DbUtil;
import cn.com.cxsw.util.MsgUtil;
import org.eclipse.swt.widgets.Label;

public class MyDataEditor extends EditorPart {

	public static final String ID = "cn.com.cxsw.editor.MyDataEditor"; //$NON-NLS-1$
	private Table table;
	private DbUtil db = new DbUtil();
	//����ҳ��
	private int page = 1;
	//����ÿҳ��ʾ����
	private int pageSize = 5;
	public MyDataEditor() {
		setContentDescription("\u6211\u7684\u6570\u636E");
	}
	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(null);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		table.setBounds(97, 138, 1143, 505);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(107);
		tableColumn.setText("  \u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setText("\u59D3\u540D");
		tableColumn_1.setWidth(123);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setText("\u4F53\u6E29");
		tableColumn_2.setWidth(70);
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setImage(SWTResourceManager.getImage(MyDataEditor.class, "/cn/com/cxsw/imgs/open.gif"));
		menuItem.setText("\u663E\u793A\u8BE6\u60C5");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			//ɾ���¼�
			@Override
			public void widgetSelected(SelectionEvent e) {
				//1����ȡѡ�е���
				TableItem[] items = table.getSelection();
				if (items.length == 0) {
					MsgUtil.showMsgInfo("û��ѡ��Ŷ");
					return;
				}
				//ȷ�Ͽ�
				int res = MsgUtil.showAskBox("��ȷ��Ҫɾ��������");
				if (res == 32) {
					String ids = "";
					for(TableItem item:items){
						ids += item.getData()+",";
					}
					ids = ids.substring(0, ids.length()-1);
					String sql = "UPDATE date SET d_state = 0 WHERE d_id in("+ids+")";
					int result = db.update(sql);
					if (result == items.length) {
						MsgUtil.showMsgOK("ɾ���ɹ�����");
						//ˢ��
						queryAll();
					}else{
						MsgUtil.showMsgOK("ɾ��ʧ������");
					}
				}
			}
		});
		menuItem_1.setImage(SWTResourceManager.getImage(MyDataEditor.class, "/cn/com/cxsw/imgs/tcxt.gif"));
		menuItem_1.setText("\u5220\u9664");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.setImage(SWTResourceManager.getImage(MyDataEditor.class, "/cn/com/cxsw/imgs/xgmm.gif"));
		menuItem_2.setText("\u4FEE\u6539");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(88);
		tableColumn_3.setText("\u8840\u538B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u8109\u640F");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u8EAB\u9AD8");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u4F53\u91CD");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u5065\u5EB7\u72B6\u51B5");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("\u51FA\u9662\u5EFA\u8BAE");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("\u533B\u5631");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("\u65F6\u95F4");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//��һҳ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page > 1){
					page --;
					queryAll();
				}else{
					MsgUtil.showMsgInfo("�����ٷ���!");
				}
			}
		});
		button_1.setBounds(468, 724, 114, 34);
		button_1.setText("\u4E0A\u4E00\u9875");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//��һҳ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page < getTotalPage()){
					page++;
					queryAll();
				}else{
					MsgUtil.showMsgInfo("�����ٷ���!");
				}
			}
		});
		button_2.setBounds(764, 724, 114, 34);
		button_2.setText("\u4E0B\u4E00\u9875");
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//βҳ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page != getTotalPage()){
					page = getTotalPage();
					queryAll();
				}else{
					MsgUtil.showMsgInfo("�Ѿ������һҳ��!");
				}
			}
		});
		button_3.setBounds(991, 724, 114, 34);
		button_3.setText("\u5C3E\u9875");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//��ҳ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page != 1){
					page = 1;
					queryAll();
				}else{
					MsgUtil.showMsgInfo("�Ѿ��ǵ�һҳ��!");
				}
			}
		});
		button.setBounds(230, 724, 114, 34);
		button.setText("\u9996\u9875");
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u6211\u7684\u6570\u636E");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		label.setBounds(69, 33, 210, 48);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(21, 87, 1294, 2);
		//�û��ղس�Ƭ��ѯ
		queryAll();
	}
	//������ҳ��
		public int getTotalPage(){
			String sql1 = "SELECT COUNT(*) AS num FROM date WHERE d_state = 1 and u_id = ?";
			List<Map<String, Object>> list = db.query(sql1, id);
			int totalCount=Integer.parseInt(list.get(0).get("num").toString());
			if(totalCount % pageSize == 0){
				return totalCount/pageSize;
			}else{
				return totalCount/pageSize + 1;
			}
		}
	//��װ��ѯ����
		String id = LoginDialog.id;
	public void queryAll() {
		table.removeAll();//���table
		String sql2 = "SELECT * FROM date where d_state = 1 limit "+(page-1)*pageSize+","+pageSize+"";
		List<Map<String, Object>> list = db.query(sql2, id);
		for (int i = 0; i < list.size(); i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
			String[] text = {
					list.get(i).get("d_id").toString(),
					list.get(i).get("d_tiwen").toString(),
					list.get(i).get("d_xueya").toString(),
					list.get(i).get("d_maibo").toString(),
					list.get(i).get("d_high").toString(),
					list.get(i).get("d_wight").toString(),
					list.get(i).get("d_health").toString(),
					list.get(i).get("d_go").toString(),
					list.get(i).get("d_jianyi").toString(),
					list.get(i).get("d_date").toString(),

					
			};
			tableItem.setText(text);
			//����ǰ�û���id���ڵ�ǰ��TableItem�ϣ����أ�
			tableItem.setData(list.get(i).get("u_id"));
		}
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
