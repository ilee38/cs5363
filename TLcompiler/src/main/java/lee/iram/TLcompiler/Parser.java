/**
 * 
 */
package lee.iram.TLcompiler;

import java.io.*;
import java.util.*;

/**
 * @author ilee
 * Parser class. Implements a Recursive Descent parser
 */
public class Parser {
	private ArrayList<String> tokenStream;
	int next = 0;	//"pointer" to the next token on the tokenStream
	String token;

	/*
	 * Parser Constructor
	 * */
	public Parser(ArrayList<String> tokenList){
		this.tokenStream = tokenList;
	}
	
	
	/*
	 * Performs the recursive descent. A method for each non-terminal in the TL15 language
	 * is specified.
	 * */
	public void performDescent(){
		token = tokenStream.get(next);
		if(program()){
			System.out.println("PARSE success");
		}else{
			System.out.println("PARSER error");
		}	
	}
	
	//<program> 
	private boolean program(){
		if(token.equals("PROGRAM")){
			Program progTree = new Program();	//Create Starting node of the AST
			return declarations(progTree) && terminal("BEGIN") && statementSequence() && terminal("END");
		}else{
			return false;
		}
	}
	
	//<declarations>
	private boolean declarations(ASTNode progTree){
		token = tokenStream.get(next+1);
		if(token.equals("VAR")){
			Decl declarations = new Decl();		//Create declarations Node
			return terminal("VAR") && terminal("ident") && terminal("AS") && type() && terminal("SC") 
					&& declarations(progTree);
		}else if(token.equals("BEGIN")){
			return epsilon();
		}else{
			return false;
		}
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
	private boolean statementSequence(){
		token = tokenStream.get(next+1);
		if(token.equals("IF") || token.equals("WHILE") || token.equals("WRITEINT")
				|| token.equals("ident")){
			return statement() && terminal("SC") && statementSequence();
		}else if(token.equals("END") || token.equals("ELSE")){
			return epsilon();
		}else{
			return false;
		}
	}
	
	//<statement>
	private boolean statement(){
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
		}
	}
	
	//<assignment>
	private boolean assignment(){
		token = tokenStream.get(next+1);
		if(token.equals("ident")){
			return terminal("ident") && terminal("ASGN") && assign();
		}else{
			return false;
		}
	}
	
	//<assign>
	private boolean assign(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident") || token.equals("boollit")){
			return expression();
		}else if(token.equals("READINT")){
			return terminal("READINT");
		}else{
			return false;
		}
	}
	
	//<ifStatement>
	private boolean ifStatement(){
		token = tokenStream.get(next+1);
		if(token.equals("IF")){
			return terminal("IF") && expression() && terminal("THEN") && statementSequence() && elseClause()
					&& terminal("END");
		}else{
			return false;
		}
	}
	
	//<elseClause>
	private boolean elseClause(){
		token = tokenStream.get(next+1);
		if(token.equals("ELSE")){
			return terminal("ELSE") && statementSequence();
		}else if(token.equals("END")){
			return epsilon();
		}else{
			return false;
		}
	} 
	
	//<whileStatement>
	private boolean whileStatement(){
		token = tokenStream.get(next+1);
		if(token.equals("WHILE")){
			return terminal("WHILE") && expression() && terminal("DO") && statementSequence() && terminal("END");
		}else{
			return false;
		}
	}
	
	//<writeInt>
	private boolean writeInt(){
		token = tokenStream.get(next+1);
		if(token.equals("WRITEINT")){
			return terminal("WRITEINT") && expression();
		}else{
			return false;
		}
	}
	
	//<expression>
	private boolean expression(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return simpleExpression() && comp();
		}else{
			return false;
		}
	}
	
	//<comp>
	private boolean comp(){
		token = tokenStream.get(next+1);
		if(token.equals("COMPARE")){
			return terminal("COMPARE") && expression();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("THEN") || token.equals("DO")){
			return epsilon();
		}else{
			return false;
		}
	}
	
	//<simpleExpression>
	private boolean simpleExpression(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return term() && add();
		}else{
			return false;
		}
	}
	
	//<add>
	private boolean add(){
		token = tokenStream.get(next+1);
		if(token.equals("ADDITIVE")){
			return terminal("ADDITIVE") && simpleExpression();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN")
				|| token.equals("DO")){
			return epsilon();
		}else{
			return false;
		}
	}
	
	//<term>
	private boolean term(){
		token = tokenStream.get(next+1);
		if(token.equals("LP") || token.equals("num") || token.equals("ident")
				|| token.equals("boollit") ){
			return factor() && multi();
		}else{
			return false;
		}
	}
	
	//<multi>
	private boolean multi(){
		token = tokenStream.get(next+1);
		if(token.equals("MULTIPLICATIVE")){
			return terminal("MULTIPLICATIVE") && term();
		}else if(token.equals("RP") || token.equals("SC") || token.equals("COMPARE") || token.equals("THEN") 
				|| token.equals("DO") || token.equals("ADDITIVE")){
			return epsilon();
		}else{
			return false;
		}
	}
	
	//<factor>
	private boolean factor(){
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
		}
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
