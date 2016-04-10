/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author ilee
 * Parser class. Implements a Recursive Descent parser, based on the Parse table
 * and the TL15 BNF grammar. The Parser generates an Abstract Syntax Tree 
 * representing the program's structure
 */
public class Parser {
	private ArrayList<String> tokenStream;
	private ArrayList<String> lexemeStream;
	int next = 0;	//"pointer" to the next token on the tokenStream
	String token, lexeme, outputFileName;
	boolean parseError = false;

	/*
	 * Parser Constructor
	 * */
	public Parser(ArrayList<String> tokenList, ArrayList<String> lexemeList, String filename){
		this.tokenStream = tokenList;
		this.lexemeStream = lexemeList;
		this.outputFileName = filename;
	}
	
	
	/*
	 * Performs the recursive descent. A method for each non-terminal in the TL15 language
	 * is specified.
	 * */
	public void performDescent(){
		token = tokenStream.get(next);
		Program tree = new Program();
		tree = program();
		
		SymbolTableVisitor v = new SymbolTableVisitor();
		tree.accept(v);
		HashMap<String, Identifier> symbolTab = v.getSymbolTable();
		System.out.println(symbolTab.toString());
		TypeCheckVisitor visitType = new TypeCheckVisitor(symbolTab);
		tree.accept(visitType);
		GraphDrawVisitor graphVisit = new GraphDrawVisitor(outputFileName);
		tree.accept(graphVisit);
		
		if(!parseError){	
			System.out.println("PARSE success");
		}else{
			System.out.println("PARSER error");
		}
	}
	
	//<program> 
	private Program program(){
		if(tokenStream.get(next).equals("PROGRAM")){
			Program prog = new Program();
			while(true){
				prog.decList.add(declarations(prog));
				if(!tokenStream.get(next+1).equals("VAR"))break;	//check for more VARs
			}
			if(terminal("BEGIN")){
				StmntSeq stList = new StmntSeq();
				prog.stmntList = statementSequence(stList);
			}else{
				return null;
			}
			if(terminal("END")){
				return prog;
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		/*	if(token.equals("PROGRAM")){	//lookahead token
			return terminal("PROGRAM") && declarations() && terminal("BEGIN") && statementSequence() && terminal("END");
		}else{
			return false;
		} */
	}
	
	//<declarations>
	private Decl declarations(Program prog){
		int pos;
		if(terminal("VAR")){
			pos = next+1;
			if(terminal("ident") && terminal("AS")){
				Decl dec = new Decl(lexemeStream.get(pos));
				if(tokenStream.get(next+1).equals("INT") || tokenStream.get(next+1).equals("BOOL")){
					dec.id.identType = lexemeStream.get(next+1);
				}
				next++;
				if(terminal("SC")){
					return dec;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		/*	token = tokenStream.get(next+1);
		if(token.equals("VAR")){
			return terminal("VAR") && terminal("ident") && terminal("AS") && type() && terminal("SC") 
					&& declarations();
		}else if(token.equals("BEGIN")){
			return epsilon();
		}else{
			return false;
		} */
	}
	
	//<type>
	private boolean type(){
		token = tokenStream.get(next+1);
		if(token.equals("INT")){
			return terminal("INT");
		}else if(token.equals("BOOL")){
			return terminal("BOOL");
		}else{
			return false;
		}
	}
	
	//<statementSequence> 
	private StmntSeq statementSequence(StmntSeq stmntList){
		Statement st;
		token = tokenStream.get(next+1);
		if(token.equals("IF") || token.equals("WHILE") || token.equals("WRITEINT") || token.equals("ident")){
			st = statement();
			if(terminal("SC")){
				stmntList.statmentList.add(st);
				statementSequence(stmntList);
				return stmntList;
			}else{
				parseError = true;
				return stmntList = null;
			}
		}else if(token.equals("END") || token.equals("ELSE")){
			return stmntList;
		}else{
			parseError = true;
			return stmntList = null;
		}
		
		/*	token = tokenStream.get(next+1);
		if(token.equals("IF") || token.equals("WHILE") || token.equals("WRITEINT")
				|| token.equals("ident")){
			return statement() && terminal("SC") && statementSequence();
		}else if(token.equals("END") || token.equals("ELSE")){
			return epsilon();
		}else{
			return false;
		} */
	}
	
	//<statement>
	private Statement statement(){
		token = tokenStream.get(next+1);
		Statement st;
		if(token.equals("WRITEINT")){
			WriteInt wrInt = new WriteInt();
			st = writeInt(wrInt);
			return st;
		}else if(token.equals("IF")){
			IfStmnt ifSt = new IfStmnt();
			st = ifStatement(ifSt);
			return st;
		}else if(token.equals("WHILE")){
			WhileStmnt wSt = new WhileStmnt();
			st = whileStatement(wSt);
			return st;
		}else if(token.equals("ident")){
			Assignment asgn = new Assignment();
			st = assignment(asgn);
			return st;
		}else{
			parseError = true;
			return st = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("WRITEINT")){
			return writeInt();
		}else if (token.equals("IF")){
			return ifStatement();
		}else if(token.equals("WHILE")){
			return whileStatement();
		}else if(token.equals("ident")){
			return assignment();	
		}else{
			return false;
		} */
	}
	
	//<assignment>
	private Assignment assignment(Assignment asgn){
		int pos = next+1;
		if(terminal("ident") && terminal("ASGN")){
			asgn.id.identName = lexemeStream.get(pos);
			assign(asgn);
			return asgn;
		}else{
			parseError = true;
			return asgn = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("ident")){
			return terminal("ident") && terminal("ASGN") && assign();
		}else{
			return false;
		} */
	}


	//<assign>
	private void assign(Assignment asgn){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident") || token.equals("boollit")){
			asgn.exp = expression();
		}else if(terminal("READINT")){
			asgn.readint = true;
		}else{
			parseError = true;
		}
	}

	//<ifStatement>
	private IfStmnt ifStatement(IfStmnt ifSt){
		if(terminal("IF")){
			ifSt.exp = expression();
			if(terminal("THEN")){
				StmntSeq thenList = new StmntSeq();
				ifSt.thenStmnts = statementSequence(thenList);
				StmntSeq elseList = new StmntSeq();
				ifSt.elseStmnts = elseClause(elseList);
				if(terminal("END")){
					return ifSt;
				}else{
					parseError = true;
					return ifSt = null;
				}
			}else{
				parseError = true;
				return ifSt = null;
			}
		}else{
			parseError = true;
			return ifSt = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("IF")){
			return terminal("IF") && expression() && terminal("THEN") && statementSequence() && elseClause()
					&& terminal("END");
		}else{
			return false;
		} */
	}

	
	//<elseClause>
	private StmntSeq elseClause(StmntSeq elseList){
		if(terminal("ELSE")){
			StmntSeq stList = new StmntSeq();
			elseList = statementSequence(stList);
			return elseList;
		}else if(tokenStream.get(next).equals("END")){		//Epsilon transition on "END"
			return elseList;
		}else{
			parseError = true;
			return elseList = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("ELSE")){
			return terminal("ELSE") && statementSequence();
		}else if(token.equals("END")){
			return epsilon();
		}else{
			return false;
		}  */
	} 
	
	//<whileStatement>
	private WhileStmnt whileStatement(WhileStmnt wSt){
		if(terminal("WHILE")){
			wSt.exp = expression();
			if(terminal("DO")){
				StmntSeq doList = new StmntSeq();
				wSt.stmnts = statementSequence(doList);
			}else{
				parseError = true;
				return wSt = null;
			}
			if(terminal("END")){
				return wSt;
			}else{
				parseError = true;
				return wSt = null;
			}
		}else{
			parseError = true;
			return wSt = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("WHILE")){
			return terminal("WHILE") && expression() && terminal("DO") && statementSequence() && terminal("END");
		}else{
			return false;
		} */
	}
		
	//<writeInt>
	private WriteInt writeInt(WriteInt wrInt){
		if(terminal("WRITEINT")){
			wrInt.exp = expression();
			return wrInt;
		}else{
			parseError = true;
			return wrInt = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("WRITEINT")){
			return terminal("WRITEINT") && expression();
		}else{
			return false;
		} */
	}
	
	//<expression>
	private Expression expression(){
		Expression exp = new Expression();
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit")){
			exp.leftExp = factor();
			token = tokenStream.get(next+1);	//update token
			if(token.equals("COMPARE")){
				exp.rightExp = comp();
			}else if(token.equals("ADDITIVE")){
				exp.rightExp = add();
			}else if(token.equals("MULTIPLICATIVE")){
				exp.rightExp = multi();
			}
			return exp;
		}else{
			parseError = true;
			return exp = null;
		}
		
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return simpleExpression() && comp();
		}else{
			return false;
		}  */
	}  

	//<comp>
	private Compare comp(){
		token = tokenStream.get(next+1);
		Compare compExp = new Compare();
		if(terminal("COMPARE")){
			compExp.compOp = lexemeStream.get(next);
			compExp.exp = expression();
			return compExp;
		}else if(token.equals("RP") || token.equals("SC") || token.equals("THEN") || token.equals("DO")){
			return compExp;		//epsilon transition
		}else{
			parseError = true;
			return compExp = null;
		}
		
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("COMPARE")){
			return terminal("COMPARE") && expression();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("THEN") || token.equals("DO")){
			return epsilon();
		}else{
			return false;
		} */
	}
	
	
/*	
	//<simpleExpression>
	private boolean simpleExpression(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return term() && add();
		}else{
			return false;
		}
	}  */
	
	//<add>
	private Additive add(){
		token = tokenStream.get(next+1);
		Additive addExp = new Additive();
		if(terminal("ADDITIVE")){
			addExp.sign = lexemeStream.get(next);
			addExp.exp = expression();
			return addExp;
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN")
				|| token.equals("DO")){
			return addExp;		//epsilon transition
		}else{
			parseError = true;
			return addExp = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("ADDITIVE")){
			return terminal("ADDITIVE") && simpleExpression();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN")
				|| token.equals("DO")){
			return epsilon();
		}else{
			return false;
		}  */
	}

/*	
	//<term>
	private boolean term(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return factor() && multi();
		}else{
			return false;
		}
	} */
	
	//<multi>
	private Multiplicative multi(){
		token = tokenStream.get(next+1);
		Multiplicative multExp = new Multiplicative();
		if(terminal("MULTIPLICATIVE")){
			multExp.sign = lexemeStream.get(next);
			multExp.exp = expression();
			return multExp;
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN") 
				|| token.equals("DO") || token.equals("ADDITIVE")){
			return multExp;		//epsilon transition
		}else{
			parseError = true;
			return multExp = null;
		}
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("MULTIPLICATIVE")){
			return terminal("MULTIPLICATIVE") && term();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN") 
				|| token.equals("DO") || token.equals("ADDITIVE")){
			return epsilon();
		}else{
			return false;
		}  */
	}
	
	//<factor>
	private Factor factor(){
		token = tokenStream.get(next+1);
		Factor factorExp = new Factor();
		if(token.equals("LP")){
			if(terminal("LP")){
				factorExp.exp = expression();
				if(terminal("RP")){
					return factorExp;
				}else{
					parseError = true;
					return factorExp = null;
				}
			}else{
				parseError = true;
				return factorExp = null;
			}
		}else if(token.equals("num")){
			if (terminal("num")){
				factorExp.num = lexemeStream.get(next);
				factorExp.type = "int";
				return factorExp;
			}else{
				parseError = true;
				return factorExp = null;
			}
		}else if(token.equals("ident")){
			if(terminal("ident")){
				factorExp.ident.identName = lexemeStream.get(next);
				return factorExp;
			}else{
				parseError = true;
				return factorExp = null;
			}
		}else if(token.equals("boollit")){
			if(terminal("boollit")){
				factorExp.boollit = lexemeStream.get(next);
				factorExp.type = "bool";
				return factorExp;
			}else{
				parseError = true;
				return factorExp = null;
			}
		}else{
			parseError = true;
			return factorExp = null;
		}
		
		
		/*
		Factor factorExp = new Factor();
		if(terminal("LP")){
			factorExp.exp = expression();
			if(terminal("RP")){
				return factorExp;
			}else{
				parseError = true;
				return factorExp = null;
			}
		}else if (terminal("num")){
			factorExp.num = lexemeStream.get(next);
			return factorExp;
		}else if(terminal("ident")){
			factorExp.ident = lexemeStream.get(next);
			return factorExp;
		}else if(terminal("boollit")){
			factorExp.boollit = lexemeStream.get(next);
			return factorExp;
		}else{
			parseError = true;
			return factorExp = null;
		} */
		
		/*
		token = tokenStream.get(next+1);
		if(token.equals("LP")){
			return terminal("LP") && expression() && terminal("RP");
		}else if(token.equals("num")){
			return terminal("num");
		}else if(token.equals("ident")){
			return terminal("ident");
		}else if(token.equals("boollit")){
			return terminal("boollit");
		}else{
			return false;
		}  */
	}
	
	
	/*
	 * Method terminal()
	 * Returns true or false on whether the passed Keyword matches a legal Keyword in the language
	 * */
	private boolean terminal(String tok){
		next++;
		token = tokenStream.get(next);
		return tok.equals(token);
	}
	
	/*
	 * Method for epsilon transitions on the parse table.
	 * */
	private boolean epsilon(){
		return true;
	}
}
