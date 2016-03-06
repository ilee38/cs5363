package lee.iram.TLcompiler;

import java.io.*;
import java.util.*;

/**
 * @author iramlee
 *	Scanner class. Performs the lexical analysis on the source code file
 *	Generates a text file containing the list of tokens in the source code
 */
public class Scanner {
	
	private final String OUTPUT_FILE_NAME = "ScannerOutput.txt"; 
	
	private String fileName;
	private boolean syntaxError = false;
	private int lineCount = 0;
	private ArrayList<HashMap<String, Integer>> scannerTable = new ArrayList<HashMap<String, Integer>>();
	private Map<Integer, String> tokenType;
	private Map<String, String> keywordList;
	private ArrayList<String> bufferBlock = new ArrayList<String>();
	private PrintWriter writer = createFile(OUTPUT_FILE_NAME);
	
/*	
 * Scanner constructor
 * 
 */
	public Scanner(){	
	}
	
/*
 * Starting method of the Scanner class: Generates a text file containing the list of tokens
 * 
 */
	public void writeTokenList(String file){
		this.fileName = file;
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
 * error
 * 
 */
	private void findTokens(ArrayList<String> bufferBlock){
		String curChar, token, readChar; String lexeme = "";
		Integer curState;
	//*	Stack<String> rememberedChars = new Stack<String>();
	//*	Stack<Integer> rememberedState = new Stack<Integer>();
		curState = 0; token = "";						//Initialize current state to initial state
		for(int i = 0; i < bufferBlock.size(); i++){
			if(syntaxError)return;
			readChar = bufferBlock.get(i);
			curChar = updateChar(readChar);																		//check if read char is lower case, uper case or digit
			if(scannerTable.get(curState).containsKey(curChar) || curChar.equals("#")){							//Check if character is valid or sentinel for end of buffer block
				if(tokenType.containsKey(curState) && (readChar.equals(" ") || i == bufferBlock.size()-1)){		//Check if accepting state and if lookahead char is space or sentinel
					//recognize Token
			 		token = tokenType.get(curState);
					i--;								//unread current char (i.e. decrease index of bufferBlock)
					lookupLexeme(token, lexeme);		
					curState = 0;						//restart curState
					lexeme = "";						//clear lexeme for next token
				}else{
					//take transition
				//*	if(tokenType.containsKey(curState)){
				//*		rememberedState.push(curState);
				//*		rememberedChars.push("");
				//*	}
				//*	rememberedChars.push(readChar);
					curState = scannerTable.get(curState).get(curChar);
					lexeme += readChar;
				}
			}else{
				//check Error
			//*	if(!rememberedState.empty()){
			//*		token = tokenType.get(rememberedState.pop());
			//*		String delChar = rememberedChars.pop();		//unread remembered chars
					//remove remembered chars from lexeme??
			//*		curState = 0;
			//*	}
				System.out.println("SYNTAX ERROR: found on line " + lineCount);
				syntaxError = true;
			}
		}
	}

/*	
 * Checks if the input character is a lower case letter, upper case letter or a digit from 1 to 9
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
 * Checks if the lexeme is a keyword, identifier or digit. Then outputs the token on appropriate format based
 * on the BNF grammar. The token is saved on the output file.
 * 
 */
	private void lookupLexeme(String token, String lexeme){
		lexeme = lexeme.trim();			//remove white spaces from lexeme
		if(token.equals("KEYWORD")){
			writer.println(keywordList.get(lexeme));
		}else if (token.equals("ident") || token.equals("num") || token.equals("MULTIPLICATIVE")
				|| token.equals("ADDITIVE") || token.equals("COMPARE")){
			writer.println(token + "("+lexeme+")");
		}else{
			writer.println(token);
		}
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
			wr = new PrintWriter(new FileWriter("ScannerOutput.txt"));
		} catch(IOException ex){
			System.out.println("Error writing file");
		}
		return wr;
	}

/*	
 * Creates the 3 tables for the scanner
 * 
 */
	private void initializeTables(){
		InitScannerTables scannerTables = new InitScannerTables();
		scannerTable = scannerTables.getScanTable();
		tokenType = scannerTables.getTokenTypeTable();
		keywordList = scannerTables.getKeywordTable();
	}
}

