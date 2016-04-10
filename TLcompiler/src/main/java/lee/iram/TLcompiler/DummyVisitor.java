/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 *
 */
public class DummyVisitor implements Visitor {

	@Override
	public void visit(Program program) {
		for(int i = 0; i < program.decList.size(); i++ ){
			program.decList.get(i).accept(this);
		}
		program.stmntList.accept(this);
	}

	@Override
	public void visit(Decl decl) {
		decl.id.accept(this);
	}

	@Override
	public void visit(Identifier identifier) {
		return;
	}

	@Override
	public void visit(StmntSeq stmntSeq) {
		for(int i = 0; i < stmntSeq.statmentList.size(); i++){
			stmntSeq.statmentList.get(i).accept(this);
		}
	}

	@Override
	public void visit(Assignment assignment) {
		assignment.id.accept(this);
		assignment.exp.accept(this);
	}

	@Override
	public void visit(IfStmnt ifStmnt) {
		ifStmnt.ifExp.accept(this);
		ifStmnt.thenStmnts.accept(this);
		ifStmnt.elseStmnts.accept(this);
	}

	@Override
	public void visit(WhileStmnt whileStmnt) {
		whileStmnt.exp.accept(this);
		whileStmnt.stmnts.accept(this);
	}

	@Override
	public void visit(WriteInt writeInt) {
		writeInt.exp.accept(this);
	}

	@Override
	public void visit(Expression expression) {
		return;
	}

	@Override
	public void visit(Compare compare) {
		compare.exp.accept(this);
	}

	@Override
	public void visit(Additive additive) {
		additive.exp.accept(this);
	}

	@Override
	public void visit(Multiplicative multiplicative) {
		multiplicative.exp.accept(this);
		
	}

	@Override
	public void visit(Factor factor) {
		factor.exp.accept(this);
		factor.ident.accept(this);
		
	}

	@Override
	public void visit(Statement statement) {
		statement.assign.accept(this);
		statement.ifSt.accept(this);
		statement.wStmnt.accept(this);
		statement.writeInt.accept(this);
		statement.exp.accept(this);
	}
	
	
}
