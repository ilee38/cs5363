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
	public Identifier id = new Identifier();  
	/*
	 * Class constructor
	 * @param stream 
	 * Accepts a stream of tokens
	 * */
	public Decl(String name){
		this.id.identName = name;
	}
	
	
	/*
	 * Assigns the identifier object to the declaration
	 * Note: the identifier contains a "name" and a "type"
	 * */
/*	public void setID(Identifier ident){
		this.ident = ident; 
	} */
	 
}
