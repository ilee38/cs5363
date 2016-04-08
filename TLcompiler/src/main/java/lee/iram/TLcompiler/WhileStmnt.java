/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class WhileStmnt extends Statement {
	
	public Expression exp;
	StmntSeq stmnts = new StmntSeq();
	
	
/*
* Class Constructor 
*/
	public WhileStmnt(){
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
