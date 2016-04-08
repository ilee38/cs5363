/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 * Defines a Visitor pattern to perform operations on the AST by 
 * "visiting" its elements.
 */
public interface Visitor {
	public void visit(Program program);
	public void visit(Decl decl);
	public void visit(Identifier identifier);
	public void visit(StmntSeq stmntSeq);
	public void visit(Assignment assignment);
	public void visit(IfStmnt ifStmnt);
	public void visit(WhileStmnt whileStmtn);
	public void visit(WriteInt writeInt);
	public void visit(Expression expression);
	public void visit(Compare compare);
	public void visit(Additive additive);
	public void visit(Multiplicative multiplicative);
	public void visit(Factor factor);
	public void visit(Statement statement);
	
}
