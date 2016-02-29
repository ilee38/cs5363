package lee.iram.TLcompiler;

import java.io.*;
import java.util.*;

/**
 * @author iramlee
 *	Scanner class. Performs the lexical analysis on the source code file
 *	Generates a text file containing the list of tokens in the source code
 */
public class Scanner {
	private String fileName;
	private Map<String, String> transitionTable;
	private Map<String, String> tokenType;
	private Set<String> keywordList;
	private ArrayList<String> bufferBlock = new ArrayList<String>();
	
//Scanner constructor
	public Scanner(String file){
		this.fileName = file;
	}
	
//Starting method of the Scanner class: Generates a text file containing the list of tokens
	public void writeTokenList(){
		String inputLine;
		initializeTables();
		BufferedReader reader = openFile(fileName);
		try {
			while(true){
				inputLine = reader.readLine();
				if (inputLine == null)break;
				bufferBlock = fillBuffer(inputLine);
				findTokens(bufferBlock);
			}
		} catch (IOException e) {
			System.out.println("File error");
			e.printStackTrace();
		}
		
	}
	
//Analyzes a line of source code to find the available tokens. Outputs each token
//into a text file, or generates a Scanner Error message if there is a lexical
//error
	private void findTokens(ArrayList<String> bufferBlock){
		String curChar, curState, nextChar, nextState, token;
		curState = "0";									//Initialize current state to initial state
		for(int i = 0; i < bufferBlock.size(); i++){
			curChar = bufferBlock.get(i);
			if(transitionTable.containsKey(curChar)){		//Check if valid input for state 0
				nextState = transitionTable.get(curChar);
			}else{
				System.out.println("SYNTAX ERROR");
			}
			if(tokenType.containsKey(nextState)){		//Check if next state is an accepting state
				nextChar = bufferBlock.get(i+1);		//do lookahead 1 character
				if((nextChar == " ") || (nextChar == "#")){
					token = curChar;
					saveToken(token);
				}else{
					System.out.println("SYNTAX ERROR");
				}
			}else{
				token += curChar;
				curState = nextState;
				findNextState(token, curState, i);	//Find transition for non-initial state
			}
		}
	}
	
//Determines the transitions for states other than the initial state.
	private void findNextState(String token, String curState, int blockIndex){
		String curChar, nextState;
		curChar = bufferBlock.get(blockIndex + 1);
		switch (curState){
			case "3":
				if(curChar == "="){
					nextState = "4";
					token += curChar;
					doLookahead(nextState, token);
				}else{
					System.out.println("SYNTAX ERROR");
				}
				break;
			case "10":
				if(curChar == "="){
					nextState = "11";
					token += curChar;
					doLookahead(nextState, token);
				}else{
					System.out.println("SYNTAX ERROR");
				}
				break;
			case "12":
				if(curChar == "="){
					nextState = "13";
					token += curChar;
					doLookahead(nextState, token);
				}else if (curChar == " "){
					nextState = "12";
					doLookahead(nextState, token);
				}else{
					System.out.println("SYNTAX ERROR");
				}
				break;
			case "14":
				if(curChar == "="){
					nextState = "15";
					token += curChar;
					doLookahead(nextState, token);
				}else if (curChar == " "){
					nextState = "14";
					doLookahead(nextState, token);
				}else{
					System.out.println("SYNTAX ERROR");
				}
				break;
			case "16":
				int charNum = curChar.charAt(0);	//Convert the character to its integer representation
				if((charNum >= 'a') && (charNum <= 'z')){
					nextState = "16";
					token += curChar;
					
				}
		}
	}
	
//Performs lookahead
	private void doLookahead(String nextState, String token){
		
	}

//Returns an ArrayList with each character in the input line of code
	private ArrayList<String> fillBuffer(String inputLine){
		ArrayList<String> bufBlock = new ArrayList<String>();
		for(int i = 0; i < inputLine.length(); i++){
			if((i+1) > inputLine.length())break;
			bufBlock.add(inputLine.substring(i, i+1));
		}
		bufBlock.add("#");	//Sentinel to indicate end of buffer
		return bufBlock;
	}
	
//Returns a file reader object
	private BufferedReader openFile(String fileName){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(fileName));
		}catch (IOException ex){
			System.out.println("Can't open that file");
		}
		return rd;
	}
	
//Creates the 3 tables for the scanner
	private void initializeTables(){
		InitScannerTables scannerTables = new InitScannerTables();	
		transitionTable = scannerTables.getTransTable();
		tokenType = scannerTables.getTokenTypeTable();
		keywordList = scannerTables.getKeywordTable();
	}
}

