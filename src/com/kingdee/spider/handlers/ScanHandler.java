package com.kingdee.spider.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.kingdee.spider.PluginConstants;
import com.kingdee.spider.views.CodeScanResultView;

public class ScanHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		try {
			ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
			if (selection instanceof IStructuredSelection) {
				IJavaProject javaProject = (IJavaProject) ((IStructuredSelection) selection).getFirstElement();
				execute(javaProject);
			}
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage(), e);
		}
		return null;
	}

	private void execute(final IJavaProject javaProject) throws JavaModelException, PartInitException {
		final ICompilationUnit[] sourceCompilationUnits = getSourceCompilationUnits(javaProject);
		final StringBuilder message = new StringBuilder(String.format("Source files count:%d%s", sourceCompilationUnits.length, StringUtil.LINE_SEPARATOR));
		for (final ICompilationUnit compilationUnit : sourceCompilationUnits) {
			final ASTParser parser = ASTParser.newParser(AST.JLS4);
			parser.setSource(compilationUnit);
			final CompilationUnit ast = (CompilationUnit) parser.createAST(null);
			ast.accept(new AccessASTVisitor(message));
		}
		showMessage(message.toString());
	}

	private void showMessage(final String message) throws PartInitException {
		CodeScanResultView view = getActiveView();
//		view.setInput(message);
		view.getResultText().setText(message);
	}

	/**
	 * 如果View已加载到IWorkbenchPage中，则需要使用findView()。<br />
	 * 如果全都调用showView()，会导致Package Explorer意外失去焦点而不去响应此界面的任何事件动作
	 */
	private CodeScanResultView getActiveView() throws PartInitException {
		final CodeScanResultView view = (CodeScanResultView) getActivePage().findView(PluginConstants.CODESCAN_RESULTVIEW_ID);
		if (view != null) {
			getActivePage().activate(view);
			return view;
		}
		return (CodeScanResultView) getActivePage().showView(PluginConstants.CODESCAN_RESULTVIEW_ID);
	}

	private IWorkbenchPage getActivePage() {
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		return window.getActivePage();
	}

	/**
	 * 从JavaProject中得到源码的可编译单元
	 */
	private ICompilationUnit[] getSourceCompilationUnits(final IJavaProject javaProject) throws JavaModelException {
		final List<ICompilationUnit> result = new ArrayList<ICompilationUnit>();
		final List<IPackageFragmentRoot> sourcePackageFragmentRoots = getSourcePackageFragmentRoots(javaProject);
		for (final IPackageFragmentRoot iPackageFragmentRoot : sourcePackageFragmentRoots) {
			result.addAll(getSourceCompilationUnits(iPackageFragmentRoot));
		}
		return result.toArray(new ICompilationUnit[] {});
	}

	/**
	 * 从源码包中得到源码的可编译单元
	 */
	private List<ICompilationUnit> getSourceCompilationUnits(final IPackageFragmentRoot iPackageFragmentRoot) throws JavaModelException {
		final List<ICompilationUnit> result = new ArrayList<ICompilationUnit>();
		for (final IJavaElement childElement : iPackageFragmentRoot.getChildren()) {
			if (childElement instanceof IPackageFragment) {
				final IPackageFragment childPackage = (IPackageFragment) childElement;
				result.addAll(Arrays.asList(childPackage.getCompilationUnits()));
			}
		}
		return result;
	}

	/**
	 * 从JavaProject中得到源码包
	 */
	private List<IPackageFragmentRoot> getSourcePackageFragmentRoots(final IJavaProject javaProject) throws JavaModelException {
		final List<IPackageFragmentRoot> result = new ArrayList<IPackageFragmentRoot>();
		final IPackageFragmentRoot[] packageFragmentRoots = javaProject.getPackageFragmentRoots();
		for (final IPackageFragmentRoot iPackageFragmentRoot : packageFragmentRoots) {
			if (!iPackageFragmentRoot.isArchive()) {
				result.add(iPackageFragmentRoot);
			}
		}
		return result;
	}
}