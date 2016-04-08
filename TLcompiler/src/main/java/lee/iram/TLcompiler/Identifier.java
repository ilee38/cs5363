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
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}	
	
	
	/*
	 * Sets the identifier's name
	 * */
	public void setName(){
	}
	
	
	/*
	 * Sets the identifier's type
	 * */
	public void setType(String type){
		this.identType = type;
	}

}
