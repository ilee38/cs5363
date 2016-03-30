/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author ilee
 *
 */
public class Statement extends ASTNode {
	
	Assignment assign;
	IfStmnt ifSt;
	WhileStmnt whileSt;
	WriteInt writeInt;
	/*
	 * Class constructor
	 * */
	public Statement(){
	}
}
