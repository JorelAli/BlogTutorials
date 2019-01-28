package io.github.jorelali.varchangelogger;

import java.util.ArrayList;

import javax.lang.model.element.Name;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;

public class VarLoggerTreeScanner extends TreeScanner<Void, Void> {

	private final Context context;

	//Keep track of variable scopes
	private java.util.List<Name> scopes;
	
	//Keep track of current method
	private MethodTree currentMethodTree;

	public VarLoggerTreeScanner(JavacTask task) {
		this.scopes = new ArrayList<>();
		this.context = ((BasicJavacTask) task).getContext();
	}

	//Compound assignments, such as
	// i += 2;
	@Override
	public Void visitCompoundAssignment(CompoundAssignmentTree compoundAssignmentTree, Void param) {
		JCIdent var = (JCIdent) compoundAssignmentTree.getVariable();
		if (this.scopes.contains(var.getName())) {
			instrument((JCExpression) compoundAssignmentTree, var.getName());
		}

		return super.visitCompoundAssignment(compoundAssignmentTree, param);
	}

	//Regular assignments, such as
	// i = i + 3;
	@Override
	public Void visitAssignment(AssignmentTree assignmentTree, Void param) {
		JCIdent var = (JCIdent) assignmentTree.getVariable();
		if (this.scopes.contains(var.getName())) {
			instrument((JCExpression) assignmentTree, var.getName());
		}

		return super.visitAssignment(assignmentTree, param);
	}

	//Variable declarations, such as
	// int i = 0;
	@Override
	public Void visitVariable(VariableTree variableTree, Void param) {
		if (ASTHelper.isAnnotationPresent(variableTree.getModifiers(), LogVariable.class)) {
			this.scopes.add(variableTree.getName());
			instrument(variableTree, ((JCVariableDecl) variableTree).getName());
		}

		return super.visitVariable(variableTree, param);
	}

	@Override
	public Void visitMethod(MethodTree methodTree, Void param) {
		this.scopes.clear();
		this.currentMethodTree = methodTree;
		return super.visitMethod(methodTree, param);
	}
	
	private void instrument(Tree tree, com.sun.tools.javac.util.Name identifier) {
		TreeMaker maker = TreeMaker.instance(context);
		
		//System.out.println(identifier)
		JCMethodInvocation sysout = maker.Apply(List.nil(),
				ASTHelper.resolveName(context, "java.lang.System.out.println"),
				List.of(maker.Ident(identifier)));
		
		//Add it to the currentMethodTree. Make sure to use maker.Exec() first!
		ASTHelper.instrument(context, currentMethodTree, List.of(maker.Exec(sysout)), tree);
	}

}
