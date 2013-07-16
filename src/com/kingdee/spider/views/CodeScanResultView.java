package com.kingdee.spider.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class CodeScanResultView extends ViewPart {

	private TreeViewer viewer;

	public CodeScanResultView() {
	}

	@Override
	public void createPartControl(final Composite parent) {
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
}