package com.kingdee.spider.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ITreeNode {

	private List<ITreeNode> children;
	private ITreeNode parent;

	public ITreeNode() {
		super();
		children = new ArrayList<ITreeNode>();
	}

	public List<ITreeNode> getChildren() {
		return this.children;
	}

	public void addChild(final ITreeNode treeNode) {
		children.add(treeNode);
		treeNode.setParent(this);
	}

	public ITreeNode getParent() {
		return this.parent;
	}

	private void setParent(final ITreeNode parent) {
		this.parent = parent;
	}

	public boolean hasChildren() {
		return this.children.size() > 0;
	}
}