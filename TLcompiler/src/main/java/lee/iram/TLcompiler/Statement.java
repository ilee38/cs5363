/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author ilee
 *
 */
public class Statement extends ASTNode {
	
	public Assignment assign;
	public IfStmnt ifSt;
	public WhileStmnt wStmnt;
	public WriteInt writeInt;
	public Expression exp;
	
/*
* Class constructor
* */
	public Statement(){
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
}
