/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public abstract class Expression extends ASTNode {
	
	Expression leftExp;
	Expression rightExp;
	
	/*
	 * Class Constructor
	 **/
	public Expression(){
	}
}
