/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.HashMap;

/**
 * @author iramlee
 *
 */
public class TypeCheckVisitor extends DummyVisitor {
	HashMap<String, Identifier> table;
	
/*
* Class Constructor
* */
	public TypeCheckVisitor(HashMap<String, Identifier> symbolTable){
		this.table = symbolTable;
	}
	
	
	public void visit(Identifier identifier) {
		String id = identifier.identName;
		if(table.containsKey(id)){
			identifier.identType = table.get(id).identType;
			//System.out.println(identifier.identName + ":" + identifier.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
			return;
		}
		
	}
	
/*
	public void visit(Factor factor) {
		factor.exp.accept(this);
		factor.ident.accept(this);
		String id = factor.ident.identName;
		if(table.containsKey(id)){
			factor.ident.identType = table.get(id).identType;
			System.out.println(factor.ident.identName + ":" + factor.ident.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
			return;
		}
		
	} */
	
/*	
	public void visit(Expression expression) {
		String id = expression.rightExp.leftExp.ident.identName;
		if(table.containsKey(id)){
			expression.rightExp.leftExp.ident.identType = table.get(id).identType;
			System.out.println(expression.rightExp.leftExp.ident.identName + ":" + expression.rightExp.leftExp.ident.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
			return;
		}
	} */
	
	
/*	
	public void visit(Assignment assignment) {
		assignment.id.accept(this);
		assignment.exp.accept(this);
		String id = assignment.id.identName;
		if(table.containsKey(id)){
			assignment.id.identType = table.get(id).identType;
			System.out.println(assignment.id.identName + ":" + assignment.id.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
		}
	}
	
	
	public void visit(IfStmnt ifStmnt) {
		ifStmnt.exp.accept(this);
		ifStmnt.thenStmnts.accept(this);
		ifStmnt.elseStmnts.accept(this);
		String id = ifStmnt.exp.leftExp.ident.identName;
		if(table.containsKey(id)){
			ifStmnt.exp.leftExp.ident.identType = table.get(id).identType;
			System.out.println(ifStmnt.exp.leftExp.ident.identName + ":" + ifStmnt.exp.leftExp.ident.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
			return;
		}
	} */
	
/*	
	public void visit(WhileStmnt whileStmnt) {
		whileStmnt.exp.accept(this);
		for(int i = 0; i < whileStmnt.stmnts.statmentList.size(); i++){
			whileStmnt.stmnts.statmentList.get(i).accept(this);
		}
	}
	
/*	
	public void visit(WriteInt writeInt) {
		writeInt.exp.accept(this);
		String id = writeInt.exp.leftExp.ident.identName;
		if(table.containsKey(id)){
			writeInt.exp.leftExp.ident.identType = table.get(id).identType;
			System.out.println(writeInt.exp.leftExp.ident.identName + ":" + writeInt.exp.leftExp.ident.identType);
		}else{
			System.out.println("PARSE error: undeclared identifier");
			return;
		}
	}
*/
			
}
