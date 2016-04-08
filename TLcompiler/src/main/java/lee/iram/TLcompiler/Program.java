/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;
/**
 * @author iramlee
 *	Starting node of the AST, contains children Decl and StmntSeq
 */
public class Program extends ASTNode{
	public ArrayList<Decl> decList = new ArrayList<Decl>();
	public StmntSeq stmntList;
	
/*
 * Class Constructor
 * 
 * */
	public Program(){
	}
	
	
/*
 * accept method for Visitors
 * 
 * */	
	public void accept(Visitor v){
		v.visit(this);
	}

}
