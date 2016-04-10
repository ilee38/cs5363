/**
 * 
 */
package lee.iram.TLcompiler;

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
		this.type = "decl";
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
	

	 
}
