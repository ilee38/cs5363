/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author ilee
 *
 */
public abstract class Statement extends ASTNode {
	
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
}
