/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Expression extends ASTNode {
	
	public Expression leftExp;
	public Expression rightExp;
	
/*
* Class Constructor
**/
	public Expression(){
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
		//this.leftExp.accept(v);
		//this.rightExp.accept(v);
	}
	
}
