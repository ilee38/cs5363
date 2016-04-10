/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Factor extends Expression {
	Identifier ident = new Identifier();
	String num;
	String boollit;
	Expression exp;
	
	
/*
*  Class Constructor
* */
	public Factor(){
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
