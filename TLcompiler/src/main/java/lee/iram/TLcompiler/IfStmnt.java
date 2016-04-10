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
	public StmntSeq elseStmnts = new StmntSeq();
	
	
/*
* Class Constructor
* */
	public IfStmnt(){
		super();
		this.type = "if";
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
	
}
