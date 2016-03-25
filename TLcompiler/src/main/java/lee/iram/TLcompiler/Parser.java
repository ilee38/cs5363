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

	/*
	 * Parser Constructor
	 * */
	public Parser(ArrayList<String> tokenList){
		this.tokenStream = tokenList;
	}
	
	
	/*
	 * Perform the recursive descent
	 * */
	public void performDescent(){
		System.out.println("Entering Parser. printing token list");
		System.out.println(tokenStream.toString());
	}

}
