package com.kingdee.spider.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class CodeScanResultContentProvider extends ArrayContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(final Object parentElement) {
		IJavaCodeModel javaCodeModel = (IJavaCodeModel) parentElement;
		return javaCodeModel.getChildren();
	}

	@Override
	public Object getParent(final Object element) {
		IJavaCodeModel javaCodeModel = (IJavaCodeModel) element;
		return javaCodeModel.getParent();
	}

	@Override
	public boolean hasChildren(final Object element) {
		IJavaCodeModel javaCodeModel = (IJavaCodeModel) element;
		return javaCodeModel.hasChildren();
	}
}