/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Compare extends Expression {
	
	String compOp;	//(i.e. !=, <, >, <=, >=, =)
	String id;
	String num;
	String boollit;
	Expression exp;
	
/*
* Class Constructor
* */
	public Compare(){
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
