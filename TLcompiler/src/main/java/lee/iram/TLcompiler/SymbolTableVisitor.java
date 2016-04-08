/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author iramlee
 *
 */
public class SymbolTableVisitor extends DummyVisitor {
	HashMap<String, Identifier> table;
	
	
/*
 * Class Constructor
 * */	
	public SymbolTableVisitor(){
		this.table = new HashMap<String, Identifier>();
	}
	
/*
 * Returns the symbol table
 * */
	public HashMap<String, Identifier> getSymbolTable(){
		return this.table;
	}
	
/*
 * Visits declarations to generate symbol table
 * */	
	public void visit(Decl decl){
		if(table.get(decl.id.identName) == null){
			table.put(decl.id.identName, decl.id);
		}else{
			System.out.println("PARSER error: symbol table error");
			return;
		}
	}

}
