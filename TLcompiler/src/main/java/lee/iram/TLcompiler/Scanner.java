package lee.iram.TLcompiler;

import java.io.*;
import java.util.*;

/**
 * @author iramlee
 *	Scanner class. Performs the lexical analysis on the source code file
 *	Generates a text file containing the list of tokens in the source code
 *	The scanner is implemented as a table-driven scanner. 
 *  The main operation of recognizing a token is performed by the findTokens() method.
 *  This method was created based on the example algorithm on page 64 of the
 *  textbook Programming Language Pragmatics 3rd Ed. by Michael L. Scott.
 */
public class Scanner {
	
	private String outputFileName;	//Output file for the token list
	private String fileName;
	private boolean syntaxError = false;
	private int lineCount = 0;
	private ArrayList<HashMap<String, Integer>> scannerTable = new ArrayList<HashMap<String, Integer>>();
	private Map<Integer, String> tokenType;
	private Map<String, String> keywordList;
	private ArrayList<String> bufferBlock = new ArrayList<String>();
	private PrintWriter writer;
	private ArrayList<String> tokenStream = new ArrayList<String>();	//token stream to be used by the Parser
	private ArrayList<String> lexemeStream = new ArrayList<String>();	//lexeme stream to be used by the Parser
																				
/*	
 * Scanner constructor
 * 
 */
	public Scanner(String file){	
		this.fileName = file;
		this.outputFileName = file.substring(0, file.indexOf(".")) + ".tok";
		this.writer = createFile(outputFileName);
	}
	
/*
 * Starting method of the Scanner class: opens the input file for reading and
 * loads each line of code into a buffer for lexical analysis. It also removes
 * the comment lines from the code before sending for scanning
 * 
 */
	public void writeTokenList(){
		String inputLine;
		initializeTables();
		BufferedReader reader = openFile(fileName);
		try {
			while(true){
				inputLine = reader.readLine();
				if (inputLine == null)break;
				if(inputLine.contains("%")){					//check if line of code contains comments
					int index = inputLine.indexOf("%");
					inputLine = inputLine.substring(0, index);	//remove the comment part of the line
				}
				bufferBlock = fillBuffer(inputLine);
				lineCount++;
				if(syntaxError)return;
				findTokens(bufferBlock);
			}
		} catch (IOException e) {
			System.out.println("File error");
			e.printStackTrace();
		}
		writer.close();
	}
	

/*
 * Analyzes a line of source code to find the available tokens. Outputs each token
 * into a text file, or generates a Scanner Error message if there is a lexical
 * error. The line of code is contained on a buffer (array list). 
 * Each character on the buffer is checked against the Scanner table to determine the
 * next transition. If the transition is to an accepting state, the scanner checks if
 * the upcoming character is a white space or has reached the end of the line of code.
 * If this is true, the token is recognized, otherwise the next transition is taken, or
 * if there is a syntax error, the scanner halts and outputs an error message.
 * The scanner keeps the token type and lexeme separate.
 * 
 */
	private void findTokens(ArrayList<String> bufferBlock){
		String curChar, token, readChar; String lexeme = "";
		Integer curState;
		curState = 0; token = "";						//Initialize current state to initial state
		for(int i = 0; i < bufferBlock.size(); i++){
			if(syntaxError)return;
			readChar = bufferBlock.get(i);
			curChar = updateChar(readChar);																		//check if read char is lower case, uper case or digit
			if(scannerTable.get(curState).containsKey(curChar) || curChar.equals("#")){							//Check if character is valid or sentinel for end of buffer block
				if(tokenType.containsKey(curState) && (readChar.equals(" ") || i == bufferBlock.size()-1)){		//Check if accepting state and if lookahead char is white space or sentinel
					//recognize Token
			 		token = tokenType.get(curState);
					i--;								//unread current char (i.e. decrease index of bufferBlock)
					lookupLexeme(token, lexeme);		
					curState = 0;						//restart curState
					lexeme = "";						//clear lexeme for next token
				}else{
					//take transition
					curState = scannerTable.get(curState).get(curChar);
					lexeme += readChar;
				}
			}else{
				//check Error
				System.out.println("SYNTAX ERROR: found on line " + lineCount);
				syntaxError = true;
			}
		}
	}
	

/*	
 * Checks if the input character is a lower case letter, upper case letter or 
 * a digit from 1 to 9. 
 * 
 */
	private String updateChar(String chr){
		if(chr.charAt(0) >= 'a' && chr.charAt(0) <= 'z'){
			return "lc"; 
		}else if(chr.charAt(0) >= 'A' && chr.charAt(0) <= 'Z'){
			return "UC";
		}else if(chr.charAt(0) >= '1' && chr.charAt(0) <= '9'){
			return "digit";
		}else if(chr.equals("\t") || chr.equals("\n")){		//treat tabs or new lines as single white space
			return " ";
		}else{
			return chr;
		}
	}
	

/*
 * Checks if the lexeme is a keyword, identifier or digit. Then outputs the token on appropriate
 * format based on the BNF grammar. The token is saved on the output file.
 * 
 */
	private void lookupLexeme(String token, String lexeme){
		lexeme = lexeme.trim();			//remove white spaces from lexeme
		if(token.equals("KEYWORD")){
			writer.println(keywordList.get(lexeme));
			tokenStream.add(keywordList.get(lexeme));	//write token to list for Parser
			lexemeStream.add(lexeme);					//write lexeme for parser
		}else if (token.equals("ident") || token.equals("num") || token.equals("MULTIPLICATIVE")
				|| token.equals("ADDITIVE") || token.equals("COMPARE")){
			writer.println(token + "("+lexeme+")");
			tokenStream.add(token);		//write token only to list for Parser
			lexemeStream.add(lexeme); 	//write lexeme for parser
		}else{
			writer.println(token);
			tokenStream.add(token);		//write token only to list for Parser
			lexemeStream.add(lexeme);	//write lexeme for parser
		}
	}
	
	
/*
 * Returns the ArrayList containing the token stream generated by the Scanner
 * 
 * */
	public ArrayList<String> getTokenStream(){
		return this.tokenStream;
	}
	

/*
 * Returns the ArrayList containing the lexemes generated by the Scanner
 * Each lexeme corresponds to each token on the tokenStream list
 * */
	public ArrayList<String> getLexemeStream(){
		return this.lexemeStream;
	}
	
/*
 * Returns an ArrayList where each element represents each character in the input line of code
 * 
 */
	private ArrayList<String> fillBuffer(String inputLine){
		ArrayList<String> bufBlock = new ArrayList<String>();
		for(int i = 0; i < inputLine.length(); i++){
			if((i+1) > inputLine.length())break;
			bufBlock.add(inputLine.substring(i, i+1));
		}
		bufBlock.add("#");	//Sentinel to indicate end of buffer
		return bufBlock;
	}
	
	
/*
 * Returns a file reader object
 * 
 */
	private BufferedReader openFile(String fileName){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(fileName));
		}catch (IOException ex){
			System.out.println("Can't open that file");
		}
		return rd;
	}
	

/*	
 * Returns a file writer object
 * 
 */
	private PrintWriter createFile(String fileName){
		PrintWriter wr = null;
		try{
			wr = new PrintWriter(new FileWriter(fileName));
		} catch(IOException ex){
			System.out.println("Error writing file");
		}
		return wr;
	}
	

/*	
 * Creates the 3 tables used by the scanner
 * 
 */
	private void initializeTables(){
		InitScannerTables scannerTables = new InitScannerTables();
		scannerTable = scannerTables.getScanTable();
		tokenType = scannerTables.getTokenTypeTable();
		keywordList = scannerTables.getKeywordTable();
	}
}