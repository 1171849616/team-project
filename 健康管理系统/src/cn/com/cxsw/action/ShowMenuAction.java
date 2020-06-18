package cn.com.cxsw.action;

import java.awt.Window;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.osgi.framework.AdminPermission;

import cn.com.cxsw.view.AdminView;


public class ShowMenuAction extends Action{
	//在Action中打开View
	private IWorkbenchWindow Window;
	public ShowMenuAction(IWorkbenchWindow Window) {
		this.Window = Window;
	}
	@Override
	public void run() {
		try {
			Window.getActivePage().showView(AdminView.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
