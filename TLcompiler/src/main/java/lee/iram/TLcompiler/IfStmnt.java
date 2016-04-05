/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class IfStmnt extends Statement {
	
	public Expression ifExp; //= new Expression();
	public StmntSeq thenStmnts = new StmntSeq();
	StmntSeq elseStmnts = new StmntSeq();
	/*
	 * Class Constructor
	 * */
	public IfStmnt(){
		super();
	}
}
