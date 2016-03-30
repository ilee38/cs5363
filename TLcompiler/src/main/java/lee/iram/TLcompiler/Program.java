/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *	Starting node of the AST, contains children Decl and StmntSeq
 */
public class Program extends ASTNode{
	Decl decl;
	StmntSeq stmnt;
	
	/*
	 * Class Constructor
	 * */
	public Program(){
	}
}
