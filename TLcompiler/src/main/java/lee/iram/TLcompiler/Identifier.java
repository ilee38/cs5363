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
	String identName;
	String identType;
	/*
	 * Class Constructor
	 * */
	public Identifier(){
	}
	
	
	/*
	 * Sets the identifier's name
	 * */
	public void setName(String id){
		this.identName = id;
	}
	
	
	/*
	 * Sets the identifier's type
	 * */
	public void setType(String type){
		this.identType = type;
	}

}
