/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author ilee
 * Contains a list of Statements
 *
 */
public class StmntSeq extends ASTNode {
	
	public ArrayList<Statement> statmentList = new ArrayList<Statement>();
	
/*
* Constructor
* */
	public StmntSeq(){
	}
	
/*
* accept method for Visitors
* 
* */	
	public void accept(Visitor v){
		v.visit(this);
	}
	
	
}
