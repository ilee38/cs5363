/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Compare extends Expression {
	
	String compSign;	//(i.e. !=, <, >, <=, >=, =)
	String id;
	String num;
	String boollit;
	Expression exp;
	
	/*
	 * Class Constructor
	 * */
	public Compare(){
	}
}
