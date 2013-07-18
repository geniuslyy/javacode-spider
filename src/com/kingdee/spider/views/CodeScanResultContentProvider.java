package com.kingdee.spider.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.kingdee.spider.model.IJavaCodeElement;
import com.kingdee.spider.model.ITreeNode;

public class CodeScanResultContentProvider extends ArrayContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(final Object parentElement) {
		IJavaCodeElement javaCodeModel = (IJavaCodeElement) parentElement;
		return javaCodeModel.getChildren().toArray(new ITreeNode[] {});
	}

	@Override
	public Object getParent(final Object element) {
		IJavaCodeElement javaCodeModel = (IJavaCodeElement) element;
		return javaCodeModel.getParent();
	}

	@Override
	public boolean hasChildren(final Object element) {
		IJavaCodeElement javaCodeModel = (IJavaCodeElement) element;
		return javaCodeModel.hasChildren();
	}
}