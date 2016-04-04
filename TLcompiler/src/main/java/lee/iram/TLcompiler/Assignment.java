/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Assignment extends Statement {
	
	Identifier id = new Identifier();
	Expression exp;
	boolean readint = false;
	
	/*
	 * Class Constructor
	 * */
	public Assignment(){
		super();
	}
	
}
