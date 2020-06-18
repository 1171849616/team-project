package cn.com.cxsw.core;
/*��������Ĳ���λ��*/
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.com.cxsw.dialog.LoginDialog;
import cn.com.cxsw.view.AdminView;
import cn.com.cxsw.view.AdminView1;
import cn.com.cxsw.view.TopView;
import cn.com.cxsw.view.TopView1;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		/*
		 * viewId:Ҫ�򿪵�view��Id
		 * relationship:view�򿪵�λ��
		 * ratio��view������ҳ��ı���
		 * refId
		 * */
		//���û�б����view
		String power = LoginDialog.power;
		if ("0".equals(power)) {
			layout.addStandaloneView(TopView.ID, false, IPageLayout.TOP, 0.12f, layout.getEditorArea());
			layout.addView(AdminView.ID,IPageLayout.LEFT, 0.20f, layout.getEditorArea());
		}else if ("1".equals(power)) {
			layout.addStandaloneView(TopView1.ID, false, IPageLayout.TOP, 0.12f, layout.getEditorArea());
			layout.addView(AdminView1.ID,IPageLayout.LEFT, 0.20f, layout.getEditorArea());
		}
		
	}
}
