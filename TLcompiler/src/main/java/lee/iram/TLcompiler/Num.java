/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Num extends Expression {
	
	String numValue;
	
	/*
	 * Class Constructor
	 * */
	public Num(String num){
		this.numValue = num;	//need to extract the actual number i.e. 
	}							//i.e. extract number from num(123)	
}
