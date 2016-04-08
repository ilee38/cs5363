/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class IfStmnt extends Statement {
	
	public Expression ifExp;
	public StmntSeq thenStmnts = new StmntSeq();
	StmntSeq elseStmnts = new StmntSeq();
	
	
/*
* Class Constructor
* */
	public IfStmnt(){
		super();
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
	
}
