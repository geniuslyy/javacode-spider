package com.kingdee.spider.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class CodeScanResultView extends ViewPart {

	private Label lblResult;

	public CodeScanResultView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		lblResult = new Label(parent, SWT.NONE);
	}

	@Override
	public void setFocus() {
		lblResult.setFocus();
	}

	public Label getResultLabel() {
		return lblResult;
	}
}