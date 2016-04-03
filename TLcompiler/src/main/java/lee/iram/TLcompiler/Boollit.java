/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Boollit extends Expression{
	
	String logicVal;
	
	/*
	 * Class Constructor
	 * */
	public Boollit(String bLit){
		this.logicVal = bLit;
	}
}
