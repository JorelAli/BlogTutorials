package io.github.jorelali.varchangelogger;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.stream.Collectors;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

public class ASTHelper {
	
	/**
	 * Resolves the JCExpression from a given name.
	 * @param context The JavacTask context
	 * @param path A valid path, for example java.lang.reflect.Modifier
	 * @return a JCExpression with the resolved name
	 * 
	 * @Examples
	 * Using resolveName() to get method declarations:
	 * <pre><code>JCMethodInvocation sysout = 
	 * 	TreeMaker.instance(context).Apply(List.nil(),
	 * 	ASTHelper.resolveName(context, "java.lang.System.out.println"),
	 *	List.of(maker.Literal("hello!")));</code>
	 * </pre>
	 * Using resolveName() to get object variables:
	 * <pre><code>JCExpression fieldClass = ASTHelper.resolveName(context, "java.lang.reflect.Field");
	 *JCVariableDecl field = maker.VarDef(..., ..., fieldClass, ...);
	 * </code></pre>
	 */
	public static JCExpression resolveName(Context context, String path) {
		TreeMaker maker = TreeMaker.instance(context);
		Names names = Names.instance(context);
		String[] identNames = path.split("\\.");
		Stack<String> identNamesStack = Arrays.stream(identNames).collect(Collectors.toCollection(Stack::new));
		
		switch(identNames.length) {
			case 0:
				throw new RuntimeException("Could not resolve name: " + path);
			case 1:
				return maker.Ident(names.fromString(identNames[0]));
			case 2:
				return maker.Select(maker.Ident(names.fromString(identNames[0])), names.fromString(identNames[1]));
			default:
				String topElement = identNamesStack.pop();
				return maker.Select(resolveName(context, String.join(".", identNamesStack)), names.fromString(topElement));
		}
	}
	
	/**
	 * Checks if a specific annotation is present in the ModifiersTree.
	 * @param tree A tree of modifiers
	 * @param annotation The class of the annotation to check
	 * @return Whether a boolean exists in the ModifiersTree
	 * 
	 * @Example
	 * <pre>
	 * <code>@Override
	 * public Void visitVariable(VariableTree tree, Void param) {
	 * 	if(ASTHelper.isAnnotationPresent(variableTree.getModifiers(), MyAnnotation.class)) {
	 * 		//code
	 * 	}
	 * }</code></pre>
	 * 
	 */
	public static boolean isAnnotationPresent(ModifiersTree tree, Class<? extends Annotation> annotation) {
		return tree.getAnnotations().stream()
			.filter(o -> o.getAnnotationType().toString().equals(annotation.getSimpleName()))
			.findAny()
			.isPresent();
	}
	
	/**
	 * Instruments a method by inserting a list of statements after a given tree
	 * @param context The JavacTask context
	 * @param currentMethodTree The current method to instrument
	 * @param listOfStatements The list of statements to insert 
	 * @param targetTree The tree to insert the list of statements after
	 */
	public static void instrument(Context context, MethodTree currentMethodTree, List<JCStatement> listOfStatements, Tree targetTree) {
		TreeMaker maker = TreeMaker.instance(context);
		JCBlock newStatementBlock = maker.Block(0, listOfStatements);

		JCBlock currentMethodBody = (JCBlock) currentMethodTree.getBody();
		
		java.util.List<JCStatement> newBlockStats = new ArrayList<>(currentMethodBody.stats);
		ListIterator<JCStatement> itr = newBlockStats.listIterator();
		while(itr.hasNext()) {
			JCStatement next = itr.next();
			
			if(targetTree instanceof JCExpression) {
				if(next.toString().equals(maker.Exec((JCExpression) targetTree).toString())) {
					itr.add(newStatementBlock);
				}
			} else {
				if(next.toString().equals(targetTree.toString())) {
					itr.add(newStatementBlock);
				}
			}
		}
		
		currentMethodBody.stats = List.from(newBlockStats);
	}
	
}
