package com.kingdee.spider.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class CodeScanResultView extends ViewPart {

	private Text txtResult;
	private TableViewer viewer;

	private TableColumn typeColumn;
	private TableColumn nameColumn;
	private TableColumn locationColumn;

	public CodeScanResultView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		txtResult = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		final Table table = viewer.getTable();

		typeColumn = new TableColumn(table, SWT.LEFT);
		typeColumn.setText("");
		typeColumn.setWidth(18);

		nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText("Name");
		nameColumn.setWidth(200);

		locationColumn = new TableColumn(table, SWT.LEFT);
		locationColumn.setText("Location");
		locationColumn.setWidth(450);

		table.setHeaderVisible(true);
		table.setLinesVisible(false);

		viewer.setLabelProvider(new CodeScanResultLabelProvider());
		viewer.setContentProvider(new CodeScanResultContentProvider());
//		viewer.setInput(getViewSite());
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public Text getResultText() {
		return txtResult;
	}
}