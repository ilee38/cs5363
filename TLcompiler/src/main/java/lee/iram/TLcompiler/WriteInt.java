/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class WriteInt extends Statement{
	
	public Expression exp;
	
/*
* Class Constructor 
*/
	public WriteInt(){
		super();
		this.type = "writeint";
	}
	
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
}
