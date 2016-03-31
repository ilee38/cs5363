/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Additive extends Expression {
	
	String addSign;		//e.g. + or -
	String id;
	String num;
	Expression rightExp;
	Expression LeftExp;
	
	/*
	 *  Class Constructor
	 * */
	public Additive(){
	}
}
