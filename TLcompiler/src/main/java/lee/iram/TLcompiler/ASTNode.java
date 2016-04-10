/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public abstract class ASTNode{
	
	String type;
	
	public abstract void accept(Visitor v);
	
}
