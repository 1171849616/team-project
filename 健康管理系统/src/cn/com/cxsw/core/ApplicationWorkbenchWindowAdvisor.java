package cn.com.cxsw.core;

/*控制看到的界面*/
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.util.MyInput;
import cn.com.cxsw.view.AdminView;
import cn.com.cxsw.view.AdminView1;
import cn.com.cxsw.view.TopView;
import cn.com.cxsw.view.TopView1;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	// 在窗口打开之前做初始化工作
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1920, 1080));// 控制窗口的大小
		configurer.setShowCoolBar(true);// 是否显示工具栏
		configurer.setShowStatusLine(true);// 是否显示状态栏
		configurer.setTitle("健康管理系统"); //$NON-NLS-1$
	}
	}
	


