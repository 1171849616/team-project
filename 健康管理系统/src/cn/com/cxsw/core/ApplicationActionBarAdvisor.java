package cn.com.cxsw.core;
import org.eclipse.jface.action.ICoolBarManager;
/*创建菜单栏、工具栏*/
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.com.cxsw.action.OpenAction;
import cn.com.cxsw.action.SaveAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	//声明Action
	private OpenAction oa;
	private SaveAction sa;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    /*//创建Action
    protected void makeActions(IWorkbenchWindow window) {
    	oa = new OpenAction();
    	oa.setText("我的");
    	sa = new SaveAction();
    	sa.setText(" 321 ");
    	//添加图标
    	//sa.setImageDescriptor(org.eclipse.wb.swt.ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class));
    }
    //添加菜单栏
    protected void fillMenuBar(IMenuManager menuBar) {
    	//1、创建菜单
    	IMenuManager oMenu = new MenuManager("LISTENER");
    	//2、绑定菜单项
    	oMenu.add(oa);
    	//3、将菜单添加到菜单栏
    	menuBar.add(oMenu);
    }
    //添加工具栏
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	//1、创建工具
    	IToolBarManager saveTool = new ToolBarManager();
    	//2、绑定Action
    	saveTool.add(sa);
    	//3、将工具添加到工具栏
    	coolBar.add(saveTool);
    }*/
    
}
