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
	public StmntSeq stmnts = new StmntSeq();
	
	
/*
* Class Constructor 
*/
	public WhileStmnt(){
		super();
		this.type = "while";
	}
	
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
	
}
