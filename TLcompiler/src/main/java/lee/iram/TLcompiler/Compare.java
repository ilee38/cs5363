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
	Identifier id;
	String num;
	String boollit;
	Expression exp;
	
/*
* Class Constructor
* */
	public Compare(){
		super();
		this.type = "bool";
	}
	
/*
* accept method for Visitors
* 
* */	
		public void accept(Visitor v){
			v.visit(this);
		}
}
