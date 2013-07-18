package com.kingdee.spider.handlers;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import com.kingdee.spider.model.JavaCodeBuilder;

public class AccessASTVisitor extends ASTVisitor {

	private final StringBuilder message;
	private final JavaCodeBuilder builder;

	public AccessASTVisitor(final StringBuilder message, final JavaCodeBuilder builder) {
		this.message = message;
		this.builder = builder;
	}

	@Override
	public boolean visit(ExpressionStatement node) {
		message.append("ExpressionStatement:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		message.append("ClassInstanceCreation:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		message.append("ConstructorInvocation:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(Initializer node) {
		message.append("Initializer:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodRef node) {
		message.append("MethodRef:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodRefParameter node) {
		message.append("MethodRefParameter:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(final MethodDeclaration node) {
		message.append("MethodDeclaration:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		this.builder.buildMethodDeclaration(node.getName().toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(final MethodInvocation node) {
		message.append("MethodInvocation:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		this.builder.buildMethodInvocation(node.getName().toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		message.append("SuperConstructorInvocation:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		message.append("SuperMethodInvocation:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}

	@Override
	public boolean visit(final TypeDeclaration node) {
		message.append("TypeDeclaration:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		this.builder.buildTypeDeclaration(node.getName().toString());
		return super.visit(node);
	}

	@Override
	public void endVisit(final TypeDeclaration node) {
		message.append("endVisit TypeDeclaration:\t" + node.getName() + StringUtil.LINE_SEPARATOR);
		this.builder.endBuildTypeDeclaration(node.getName().toString());
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		message.append("TypeDeclarationStatement:\t" + node.toString() + StringUtil.LINE_SEPARATOR);
		return super.visit(node);
	}
}