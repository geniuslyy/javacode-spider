package com.kingdee.spider.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.kingdee.spider.model.IJavaCodeElement;

public class CodeScanResultLabelProvider extends LabelProvider {

	@Override
	public Image getImage(final Object element) {
		return super.getImage(element);
	}

	@Override
	public String getText(final Object element) {
		IJavaCodeElement javaCodeModel = (IJavaCodeElement) element;
		return super.getText(javaCodeModel.getText());
	}
}