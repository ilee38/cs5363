/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.ArrayList;

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
