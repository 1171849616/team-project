package cn.com.cxsw.core;
import org.eclipse.jface.action.ICoolBarManager;
/*�����˵�����������*/
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
	//����Action
	private OpenAction oa;
	private SaveAction sa;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    /*//����Action
    protected void makeActions(IWorkbenchWindow window) {
    	oa = new OpenAction();
    	oa.setText("�ҵ�");
    	sa = new SaveAction();
    	sa.setText(" 321 ");
    	//���ͼ��
    	//sa.setImageDescriptor(org.eclipse.wb.swt.ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class));
    }
    //��Ӳ˵���
    protected void fillMenuBar(IMenuManager menuBar) {
    	//1�������˵�
    	IMenuManager oMenu = new MenuManager("LISTENER");
    	//2���󶨲˵���
    	oMenu.add(oa);
    	//3�����˵���ӵ��˵���
    	menuBar.add(oMenu);
    }
    //��ӹ�����
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	//1����������
    	IToolBarManager saveTool = new ToolBarManager();
    	//2����Action
    	saveTool.add(sa);
    	//3����������ӵ�������
    	coolBar.add(saveTool);
    }*/
    
}
