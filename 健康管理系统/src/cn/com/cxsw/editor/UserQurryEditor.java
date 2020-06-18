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
	private Table table;
	private DbUtil db = new DbUtil();
	//定义页数
	private int page = 1;
	//定义每页显示行数
	private int pageSize = 5;
	//private 
	public UserQurryEditor() {
	}
	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/\u67E5\u8BE24.jpg"));
		composite.setBackground(SWTResourceManager.getColor(185, 209, 234));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		table.setBounds(173, 67, 1214, 600);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(67);
		tableColumn.setText("  \u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(114);
		tableColumn_1.setText("      \u5B66\u53F7");
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.NONE);
		tableColumn_11.setWidth(84);
		tableColumn_11.setText("  \u59D3\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(61);
		tableColumn_2.setText(" \u6027\u522B");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(122);
		tableColumn_3.setText("     \u5E74\u7EA7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(141);
		tableColumn_4.setText("       \u5B66\u9662");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(143);
		tableColumn_5.setText("      \u73ED\u7EA7");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(109);
		tableColumn_8.setText("  \u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(96);
		tableColumn_6.setText(" \u6240\u5728\u5730\u533A");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(85);
		tableColumn_7.setText("\u662F\u5426\u7591\u4F3C");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			//删除事件
			@Override
			public void widgetSelected(SelectionEvent e) {
				//1、获取选中的行
				TableItem[] items = table.getSelection();
				if (items.length == 0) {
					MsgUtil.showMsgInfo("没有选中哦");
					return;
				}
				//确认框
				int res = MsgUtil.showAskBox("您确定要删除此项吗？");
				if (res == 32) {
					//Object id = items[0].getData();//单行
					String ids = "";
					for(TableItem item:items){
						ids += item.getData()+",";
					}
					ids = ids.substring(0, ids.length()-1);
					String sql = "UPDATE USER SET u_state = 0 WHERE u_id in("+ids+")";
					int result = db.update(sql);
					if (result == items.length) {
						MsgUtil.showMsgOK("删除成功啦！");
						//刷新
						queryAll();
					}else{
						MsgUtil.showMsgOK("删除失败啦！");
					}
				}
			}
		});
		menuItem_1.setImage(SWTResourceManager.getImage(UserQurryEditor.class, "/cn/com/cxsw/imgs/tcxt.gif"));
		menuItem_1.setText("\u5220\u9664");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(84);
		tableColumn_9.setText("\u662F\u5426\u786E\u8BCA");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(120);
		tableColumn_10.setText("\u4E0A\u62A5\u65F6\u95F4");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//上一页
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(page > 1){
					page --;
					queryAll();
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
					queryAll();
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
					queryAll();
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
					queryAll();
				}else{
					MsgUtil.showMsgInfo("已经是第一页啦!");
				}
			}
		});
		button.setBounds(264, 758, 114, 34);
		button.setText("\u9996\u9875");
		//用户信息查询
		queryAll();
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
	public void queryAll() {
		table.removeAll();//清空table
		List<Map<String,Object>> list = db.query("select * from user where u_state = 1 limit "+(page-1)*pageSize+","+pageSize+"");
		for (int i = 0; i < list.size(); i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
			String[] text = {
					list.get(i).get("u_code").toString(),
					list.get(i).get("u_id").toString(),
					list.get(i).get("u_name").toString(),
					list.get(i).get("u_sex").toString(),
					list.get(i).get("u_nj").toString(),
					list.get(i).get("u_xy").toString(),
					list.get(i).get("u_bj").toString(),
					list.get(i).get("u_num").toString(),
					list.get(i).get("u_city").toString(),
					list.get(i).get("u_ys").toString(),
					list.get(i).get("u_sure").toString(),
					list.get(i).get("u_date").toString()
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
