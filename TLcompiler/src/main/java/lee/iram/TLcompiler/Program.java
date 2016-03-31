/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *	Starting node of the AST, contains children Decl and StmntSeq
 */
public class Program extends ASTNode{
	public Decl decList;
	public StmntSeq stmntList;
	
	/*
	 * Class Constructor
	 * */
	public Program(){
	}
}
