/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Assignment extends Statement {
	
	Identifier id = new Identifier();
	Expression exp = new Expression();
	boolean readint = false;
	
/*
* Class Constructor
* */
	public Assignment(){
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
