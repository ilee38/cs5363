/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Expression extends ASTNode {
	
	Expression leftExp;
	Expression rightExp;
	
	
/*
* Class Constructor
**/
	public Expression(){
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
