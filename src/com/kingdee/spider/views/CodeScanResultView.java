package com.kingdee.spider.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class CodeScanResultView extends ViewPart {

	private Text txtResult;
	private TreeViewer viewer;

	public CodeScanResultView() {
	}

	@Override
	public void createPartControl(final Composite parent) {
		txtResult = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);

		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setLabelProvider(new CodeScanResultLabelProvider());
		viewer.setContentProvider(new CodeScanResultContentProvider());
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public final void setInput(final Object input) {
		viewer.setInput(input);
	}

	public Text getResultText() {
		return txtResult;
	}
}