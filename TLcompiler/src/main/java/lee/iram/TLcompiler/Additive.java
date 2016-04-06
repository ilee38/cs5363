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
	String id;
	String num;
	Expression exp;
	
	/*
	 *  Class Constructor
	 * */
	public Additive(){
		super();
	}
}
