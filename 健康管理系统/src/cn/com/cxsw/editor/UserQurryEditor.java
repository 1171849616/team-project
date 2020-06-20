package cn.com.cxsw.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.util.DbUtil;
import cn.com.cxsw.util.MsgUtil;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class UserQurryEditor extends EditorPart {

	public static final String ID = "cn.com.cxsw.editor.UserQurryEditor"; //$NON-NLS-1$
	private DbUtil db = new DbUtil();
	//定义页数
	private int page = 1;
	//定义每页显示行数
	private int pageSize = 5;
	private Table table;
	//private 
	public UserQurryEditor() {
		setContentDescription("\u67E5\u770B\u6570\u636E");
	}
	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/\u67E5\u8BE24.jpg"));
		composite.setBackground(SWTResourceManager.getColor(185, 209, 234));
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//上一页
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page > 1){
					page --;
					queryAll1();
				}else{
					MsgUtil.showMsgInfo("不能再翻啦!");
				}
			}
		});
		button_1.setBounds(485, 758, 114, 34);
		button_1.setText("\u4E0A\u4E00\u9875");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//下一页
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page < getTotalPage()){
					page++;
					queryAll1();
				}else{
					MsgUtil.showMsgInfo("不能再翻啦!");
				}
			}
		});
		button_2.setBounds(760, 758, 114, 34);
		button_2.setText("\u4E0B\u4E00\u9875");
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//尾页
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page != getTotalPage()){
					page = getTotalPage();
					queryAll1();
				}else{
					MsgUtil.showMsgInfo("已经是最后一页了!");
				}
			}
		});
		button_3.setBounds(984, 758, 114, 34);
		button_3.setText("\u5C3E\u9875");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//首页
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page != 1){
					page = 1;
					queryAll1();
				}else{
					MsgUtil.showMsgInfo("已经是第一页啦!");
				}
			}
		});
		button.setBounds(264, 758, 114, 34);
		button.setText("\u9996\u9875");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		table.setBounds(171, 57, 1143, 505);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(107);
		tableColumn.setText("  \u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(123);
		tableColumn_1.setText("\u59D3\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(70);
		tableColumn_2.setText("\u4F53\u6E29");
		
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
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u663E\u793A\u8BE6\u60C5");
		menuItem.setImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/open.gif"));
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("\u5220\u9664");
		menuItem_1.setImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/tcxt.gif"));
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.setText("\u4FEE\u6539");
		menuItem_2.setImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/xgmm.gif"));
		//用户信息查询
		queryAll1();
	}
	//计算总页数
		public int getTotalPage(){
			List<Map<String,Object>> list=db.query("SELECT COUNT(*) AS num FROM user WHERE u_state = 1");
			int totalCount=Integer.parseInt(list.get(0).get("num").toString());
			if(totalCount % pageSize == 0){
				return totalCount/pageSize;
			}else{
				return totalCount/pageSize + 1;
			}
		}
		//封装查询方法
				String id = LoginDialog.id;
			public void queryAll1() {
				table.removeAll();//清空table
				String sql2 = "select * from date where d_state = 1 limit "+(page-1)*pageSize+","+pageSize+"";
				List<Map<String, Object>> list = db.query(sql2, id);
				for (int i = 0; i < list.size(); i++) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
					String[] text = {
							list.get(i).get("u_id").toString(),
							list.get(i).get("u_name").toString(),
							list.get(i).get("d_tiwen").toString(),
							list.get(i).get("d_xueya").toString(),
							list.get(i).get("d_maibo").toString(),
							list.get(i).get("d_high").toString(),
							list.get(i).get("d_wight").toString(),
							list.get(i).get("d_health").toString(),
							list.get(i).get("d_go").toString(),
							list.get(i).get("d_jianyi").toString(),
							list.get(i).get("d_date").toString()
							
					};
					tableItem.setText(text);
					//将当前用户的id绑定在当前的TableItem上（隐藏）
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
