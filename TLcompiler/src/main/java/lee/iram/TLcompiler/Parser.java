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
	 * Performs the recursive descent
	 * */
	public void performDescent(){
		token = tokenStream.get(next);
		if(program()){
			System.out.println("Parse success");
		}else{
			System.out.println("PARSER error");
		}	
	}
	
	//<program> 
	private boolean program(){
		if(token == "PROGRAM"){
			return declarations() && term("BEGIN") && statementSequence() && term("END");
		}else{
			return false;
		}
	}
	
	//<declarations>
	private boolean declarations(){
		next++;
		token = tokenStream.get(next);
		if(token == "VAR"){
			return term("ident") && 
		}
	}
		
}
