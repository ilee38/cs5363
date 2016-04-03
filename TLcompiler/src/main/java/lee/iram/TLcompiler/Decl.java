/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author iramlee
 *	Contains a list of Decl
 */
public class Decl extends ASTNode {
	ArrayList<Identifier> declList = new ArrayList<Identifier>();
	  
	/*
	 * Class constructor
	 * @param stream 
	 * Accepts a stream of tokens
	 * */
	public Decl(){
	}
	
	
	/*
	 * Assigns the identifier object to the declaration
	 * Note: the identifier contains a "name" and a "type"
	 * */
/*	public void setID(Identifier ident){
		this.ident = ident; 
	} */
	 
}
