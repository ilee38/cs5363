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
	 * */
	public Program(){
		
	}
/*
	/*
	 * Adds a Decl object to the decl list
	 * *
	public void addDecl(Decl decl){
		this.declList.add(decl);
	}
	
	/*
	 * Adds a Statement object to the stmnt list
	 * *
	public void addStmnt(Statement stmnt){
		this.stmntList.add(stmnt);
	}
*/
}
