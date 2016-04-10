/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Additive extends Expression {
	
	String sign;		//e.g. + or -
	Identifier id;
	String num;
	Expression exp;
	
	/*
	 *  Class Constructor
	 * */
	public Additive(){
		super();
		this.type = "int";
	}
	
	/*
	* accept method for Visitors
	* 
	* */	
		public void accept(Visitor v){
			v.visit(this);
		}
}
