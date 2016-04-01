/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *	Starting node of the AST, contains children Decl and StmntSeq
 */
public class Program extends ASTNode{
	
	/*
	 * Class Constructor
	 * */
	public Program(){
		Decl decList = new Decl();
		StmntSeq stmntList = new StmntSeq();
	}
}
