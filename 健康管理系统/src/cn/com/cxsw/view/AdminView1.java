package cn.com.cxsw.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.cxsw.editor.MyDataEditor;
import cn.com.cxsw.editor.UserAddEditor;
import cn.com.cxsw.editor.UserQurryEditor;
import cn.com.cxsw.util.MyInput;

public class AdminView1 extends ViewPart {

	public static final String ID = "cn.com.cxsw.view.AdminView1"; //$NON-NLS-1$

	public AdminView1() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button button_5 = new Button(container, SWT.NONE);
		button_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 21, SWT.NORMAL));
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MyInput input = new MyInput();
				input.setName("上传数据");
				input.setToolTipText("上传数据");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, UserAddEditor.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.setBounds(37, 135, 322, 134);
		button_5.setText("\u4E0A\u4F20\u6570\u636E");
		
		Button button_6 = new Button(container, SWT.NONE);
		button_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 21, SWT.NORMAL));
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MyInput input = new MyInput();
				input.setName("查看患者数据");
				input.setToolTipText("查看患者数据");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, UserQurryEditor.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
					});
		button_6.setText("\u67E5\u770B\u60A3\u8005\u6570\u636E");
		button_6.setBounds(37, 341, 322, 134);
		
		Button button_7 = new Button(container, SWT.NONE);
		button_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 21, SWT.NORMAL));
		button_7.addSelectionListener(new SelectionAdapter() {
			
			/*@Override
			public void widgetSelected(SelectionEvent e) {
				MyInput input = new MyInput();
				input.setName("留言回复");
				input.setToolTipText("留言回复");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, UserQurryEditor.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}*/
		});
		button_7.setText("\u7559\u8A00\u56DE\u590D");
		button_7.setBounds(37, 563, 322, 134);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			/*@Override
			public void widgetSelected(SelectionEvent e) {
				MyInput input = new MyInput();
				input.setName("查找");
				input.setToolTipText("查找");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, UserQurryEditor.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}*/
		});
		button.setText("\u67E5\u627E");
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 21, SWT.NORMAL));
		button.setBounds(37, 756, 322, 134);

		createActions();
		initializeToolBar();
		initializeMenu();
	}
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
