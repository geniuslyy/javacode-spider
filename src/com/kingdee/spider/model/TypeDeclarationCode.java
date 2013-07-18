package com.kingdee.spider.model;

public class TypeDeclarationCode extends IJavaCodeElement {

	private String name;

	public TypeDeclarationCode(final String name) {
		this.name = name;
	}

	@Override
	public Object getText() {
		return name;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + "]";
	}
}