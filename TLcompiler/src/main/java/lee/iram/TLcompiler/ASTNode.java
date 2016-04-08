/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public abstract class ASTNode{
	public abstract void accept(Visitor v);
	
}
