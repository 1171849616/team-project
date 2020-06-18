package cn.com.cxsw.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Link;

public class ShowEditor extends EditorPart {

	public static final String ID = "cn.com.cxsw.editor.ShowEditor"; //$NON-NLS-1$

	public ShowEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.V_SCROLL);
		container.setBackground(SWTResourceManager.getColor(255, 255, 255));
		
		Link link = new Link(container, SWT.NONE);
		link.setBackgroundImage(SWTResourceManager.getImage(ShowEditor.class, "/cn/com/cxsw/imgs/\u4E3B1.PNG"));
		link.setBounds(192, 401, 1055, 322);
		link.setText("<a></a>");
		
		Link link_1 = new Link(container, SWT.NONE);
		link_1.setBackgroundImage(SWTResourceManager.getImage(ShowEditor.class, "/cn/com/cxsw/imgs/\u56FE\u72471.png"));
		link_1.setBounds(369, 110, 707, 200);

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
