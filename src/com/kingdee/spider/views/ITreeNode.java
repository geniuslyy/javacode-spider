package com.kingdee.spider.views;

public interface ITreeNode {

	Object[] getChildren();

	Object getParent();

	boolean hasChildren();
}