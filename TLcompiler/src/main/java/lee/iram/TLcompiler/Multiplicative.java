/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Multiplicative extends Expression {
	
	String sign;	//e.g. *, "div" or "mod"
	Identifier id;
	String num;
	Expression exp;
	
	/*
	 * Class Constructor
	 * */
	public Multiplicative(){
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
