# TreeScanner Methods

## List of Methods with examples

```java
public class aa {

    @BadAnnotation
    public static void main(String[] args) {
        int i = 0;
        i = i + 4;
        i += 2;

        if(true) {
            throw new RuntimeException();
        }

        for(int j = 0; j < 2; j++) {

        }

        System.out.println(i);
    }
}
```



| **Method**                                                   | **JC equivalent**       | **Example**s                                |
| ------------------------------------------------------------ | ----------------------- | ------------------------------------------- |
| `visitAnnotatedType(AnnotatedTypeTree, Void)`                | `JCAnnotatedType`       |                                             |
| `visitAnnotation(AnnotationTree, Void)`                      | `JCAnnotation`          | `@BadAnnotation()`                          |
| `visitArrayAccess(ArrayAccessTree, Void)`                    | `JCArrayAccess`         |                                             |
| `visitArrayType(ArrayTypeTree, Void)`                        | `JCArrayTypeTree`       | `String[]`                                  |
| `visitAssert(AssertTree, Void)`                              | `JCAssert`              |                                             |
| `visitAssignment(AssignmentTree, Void)`                      | `JCAssign`              | `i = i + 2`                                 |
| `visitBinary(BinaryTree, Void)`                              | `JCBinary`              | `i + 4`                                     |
| `visitBlock(BlockTree, Void)`                                | `JCBlock`               | `{ /*code*/ }`                              |
| `visitBreak(BreakTree, Void)`                                | `JCBreak`               |                                             |
| `visitCase(CaseTree, Void)`                                  | `JCCase`                |                                             |
| `visitCatch(CatchTree, Void)`                                | `JCCatch`               |                                             |
| `visitClass(ClassTree, Void)`                                | `JCClassDecl`           |                                             |
| `visitCompilationUnit(CompilationUnitTree, Void)`            | `JCCompilationUnit`     |                                             |
| `visitCompoundAssignment(CompoundAssignmentTree, Void)`      | `JCAssignOp`            | `i += 2`                                    |
| `visitConditionalExpression(ConditionalExpressionTree, Void)` | `JCConditional`         |                                             |
| `visitContinue(ContinueTree, Void)`                          | `JCContinue`            |                                             |
| `visitDoWhileLoop(DoWhileLoopTree, Void)`                    | `JCDoWhileLoop`         |                                             |
| `visitEmptyStatement(EmptyStatementTree, Void)`              | `JCSkip`                |                                             |
| `visitEnhancedForLoop(EnhancedForLoopTree, Void)`            | `JCEnhancedForLoop`     |                                             |
| `visitErroneous(ErroneousTree, Void)`                        | `JCErroneous`           |                                             |
| `visitExpressionStatement(ExpressionStatementTree, Void)`    | `JCExpressionStatement` | `i = i + 2;` <br />`System.out.println(i);` |
| `visitForLoop(ForLoopTree, Void)`                            | `JCForLoop`             | `for (int j = 0; j < 2; j++) {
<br/>}`       |
| `visitIdentifier(IdentifierTree, Void)`                      | `JCIdent`               | `System`<br /> `i`<br /> `String`           |
| `visitIf(IfTree, Void)`                                      | `JCIf`                  |                                             |
| `visitImport(ImportTree, Void)`                              | `JCImport`              |                                             |
| `visitInstanceOf(InstanceOfTree, Void)`                      | `JCInstanceOf`          |                                             |
| `visitIntersectionType(IntersectionTypeTree, Void)`          | `JCTypeIntersection`    |                                             |
| `visitLabeledStatement(LabeledStatementTree, Void)`          | `JCLabeledStatement`    |                                             |
| `visitLambdaExpression(LambdaExpressionTree, Void)`          | `JCLambda`              |                                             |
| `visitLiteral(LiteralTree, Void)`                            | `JCLiteral`             | `2`<br />`true`                             |
| `visitMemberReference(MemberReferenceTree, Void)`            | `JCMemberReference`     |                                             |
| `visitMemberSelect(MemberSelectTree, Void)`                  | `JCFieldAccess`         | `System.out.println`<br />`System.out`      |
| `visitMethod(MethodTree, Void)`                              | `JCMethodDecl`          |                                             |
| `visitMethodInvocation(MethodInvocationTree, Void)`          | `JCMethodInvocation`    | `System.out.println(i)`                     |
| `visitModifiers(ModifiersTree, Void)`                        | `JCModifiers`           | `public`<br /> `static`                     |
| `visitNewArray(NewArrayTree, Void)`                          | `JCNewArray`            |                                             |
| `visitNewClass(NewClassTree, Void)`                          | `JCNewClass`            | `new RuntimeException()`                    |
| `visitOther(Tree, Void)`                                     | `JCTree`                |                                             |
| `visitParameterizedType(ParameterizedTypeTree, Void)`        | `JCTypeApply`           |                                             |
| `visitParenthesized(ParenthesizedTree, Void)`                | `JCParens`              | `(true)`                                    |
| `visitPrimitiveType(PrimitiveTypeTree, Void)`                | `JCPrimitiveTypeTree`   | `void`<br /> `int`                          |
| `visitReturn(ReturnTree, Void)`                              | `JCReturn`              |                                             |
| `visitSwitch(SwitchTree, Void)`                              | `JCSwitch`              |                                             |
| `visitSynchronized(SynchronizedTree, Void)`                  | `JCSynchronized`        |                                             |
| `visitThrow(ThrowTree, Void)`                                | `JCThrow`               | `throw new RuntimeException();`             |
| `visitTry(TryTree, Void)`                                    | `JCTry`                 |                                             |
| `visitTypeCast(TypeCastTree, Void)`                          | `JCTypeCast`            |                                             |
| `visitTypeParameter(TypeParameterTree, Void)`                | `JCTypeParameter`       |                                             |
| `visitUnary(UnaryTree, Void)`                                | `JCUnary`               | `i++`                                       |
| `visitUnionType(UnionTypeTree, Void)`                        | `JCTypeUnion`           |                                             |
| `visitVariable(VariableTree, Void)`                          | `JCVariableDecl`        | `int i = 0`<br /> `String[] args`           |
| `visitWhileLoop(WhileLoopTree, Void)`                        | `JCWhileLoop`           |                                             |
| `visitWildcard(WildcardTree, Void)`                          | `JCWildcard`            |                                             |

```java
blah
```