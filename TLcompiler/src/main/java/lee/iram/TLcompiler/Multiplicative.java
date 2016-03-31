/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Multiplicative extends Expression {
	
	String multSign;	//e.g. *, "div" or "mod"
	String id;
	String num;
	Expression exp;
	
	/*
	 * Class Constructor
	 * */
	public Multiplicative(){
	}
}
