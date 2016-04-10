/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class Identifier extends Expression {
	public String identName;
	public String identType;
	
/*
* Class Constructor
* */
	public Identifier(){
		super();
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}	

}
