package cn.com.cxsw.core;

/*���ƿ����Ľ���*/
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.editor.ShowEditor;
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

	// �ڴ��ڴ�֮ǰ����ʼ������
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1920, 1080));// ���ƴ��ڵĴ�С
		configurer.setShowCoolBar(true);// �Ƿ���ʾ������
		configurer.setShowStatusLine(true);// �Ƿ���ʾ״̬��
		configurer.setTitle("�����ͳ��ϵͳ"); //$NON-NLS-1$
	}
	}
	


