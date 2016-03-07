package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author iramlee
 * Creates the tables used by the scanner: a state Transition table, a token type table for
 * token classification, based on the ending state of a token, and a list of all the allowed 
 * keywords in the language.
 *  
 */
public class InitScannerTables {
	
	private ArrayList<HashMap<String, Integer>> scanTable = new ArrayList<HashMap<String, Integer>>();
	private Map<Integer, String> tokenTypeTable = new HashMap<Integer, String>();
	private Map<String, String> keywords = new HashMap<String, String>();
	
	private HashMap<String, Integer> state_0 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_1 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_2 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_3 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_4 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_5 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_6 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_7 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_8 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_9 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_10 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_11 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_12 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_13 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_14 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_15 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_16 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_17 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_18 = new HashMap<String, Integer>();
	private HashMap<String, Integer> state_19 = new HashMap<String, Integer>();
	
/*
 * Class Constructor
 * 
 */
	public InitScannerTables(){
	}

	
/*
 * Returns the scanner table containing the state transitions. This is
 * the main table used by the Scanner to determine the next state based on
 * the input character.
 * The data structure representing the table is an ArrayList of HashMaps.
 * Each element in the array list represents the current state, with an
 * associated hash map that indicates the next state based on the input
 * character.
 * 
 */
	public ArrayList<HashMap<String, Integer>> getScanTable(){
		fillStateTables();
		scanTable.add(state_0);
		scanTable.add(state_1);
		scanTable.add(state_2);
		scanTable.add(state_3);
		scanTable.add(state_4);
		scanTable.add(state_5);
		scanTable.add(state_6);
		scanTable.add(state_7);
		scanTable.add(state_8);
		scanTable.add(state_9);
		scanTable.add(state_10);
		scanTable.add(state_11);
		scanTable.add(state_12);
		scanTable.add(state_13);
		scanTable.add(state_14);
		scanTable.add(state_15);
		scanTable.add(state_16);
		scanTable.add(state_17);
		scanTable.add(state_18);
		scanTable.add(state_19);
		return scanTable;
	}
	
	
/*
 * Returns a table to match the type of token that was recognized
 * Associates the ending state of the token to the token type (see Scanner transition table
 * Excel file)
 * 
 */
		public Map<Integer, String> getTokenTypeTable(){
			tokenTypeTable.put(1, "LP");
			tokenTypeTable.put(2, "RP");
			tokenTypeTable.put(4, "ASGN");
			tokenTypeTable.put(5, "SC");
			tokenTypeTable.put(6, "MULTIPLICATIVE");
			tokenTypeTable.put(7, "ADDITIVE");		// +
			tokenTypeTable.put(8, "ADDITIVE");		// -
			tokenTypeTable.put(9, "COMPARE");		// =
			tokenTypeTable.put(11, "COMPARE");		// !=
			tokenTypeTable.put(12, "COMPARE");		// <
			tokenTypeTable.put(13, "COMPARE");		// <=
			tokenTypeTable.put(14, "COMPARE");		// >
			tokenTypeTable.put(15, "COMPARE");		// >=
			tokenTypeTable.put(16, "KEYWORD");		//should return the keyword itself (in lower case)
			tokenTypeTable.put(17, "ident");		//should return ident(XYZ..)
			tokenTypeTable.put(18, "num");			//should return num(123..)
			tokenTypeTable.put(19, "num");			//state for number zero "0" Should return num(0)
			return tokenTypeTable;
		}
		

/*
 * Returns a table with all the keywords in the language. Used to print 
 * each Keyword token per the BNF grammar. Also contains the special keywords 
 * of the different types:
 * 
 * boollit = "false", "true"
 * MULTIPLICATIVE = "div", "mod"
 * "writeint" and "readint"
 * 
 * these are also treated as keywords.
 * 
 */
		public Map<String, String> getKeywordTable(){
			keywords.put("false", "boollit(false)");
			keywords.put("true", "boollit(true)");
			keywords.put("div", "MULTIPLICATIVE(div)");
			keywords.put("mod", "MULTIPLICATIVE(mod)");
			keywords.put("if", "IF");
			keywords.put("then", "THEN");
			keywords.put("else", "ELSE");
			keywords.put("begin", "BEGIN");
			keywords.put("end", "END");
			keywords.put("while", "WHILE");
			keywords.put("do", "DO");
			keywords.put("program", "PROGRAM");
			keywords.put("var", "VAR");
			keywords.put("as", "AS");
			keywords.put("int", "INT");
			keywords.put("bool", "BOOL");
			keywords.put("writeint", "WRITEINT");
			keywords.put("readint", "READINT");
			return keywords;
		}
		
		
/*		
 * Fills out the HashMaps for each state, indicating the next transition on a given
 * input character. Transitions are based on the Scanner transition table
 * (see Excel file on project's documentation)
 *
 */
		private void fillStateTables(){
			state_0.put("(", 1);
			state_0.put(")", 2);
			state_0.put(":", 3);
			state_0.put(";", 5);
			state_0.put("*", 6);
			state_0.put("+", 7);
			state_0.put("-", 8);
			state_0.put("=", 9);
			state_0.put("!", 10);
			state_0.put("<", 12);
			state_0.put(">", 14);
			state_0.put("lc", 16);
			state_0.put("UC", 17);
			state_0.put("digit", 18);
			state_0.put("0", 19);
			state_0.put(" ", 0);
			
			state_1.put(" ", 1);
			state_2.put(" ", 2);
			state_3.put("=", 4);
			state_4.put(" ", 4);
			state_5.put(" ", 5);
			state_6.put(" ", 6);
			state_7.put(" ", 7);
			state_8.put(" ", 8);
			state_9.put(" ", 9);
			state_10.put("=", 11);
			state_11.put(" ", 11);
			
			state_12.put("=", 13);
			state_12.put(" ", 12);
			
			state_13.put(" ", 13);
			
			state_14.put("=", 15);
			state_14.put(" ", 14);
			
			state_15.put(" ", 15);
			
			state_16.put("lc", 16);
			state_16.put(" ", 16);
			
			state_17.put("UC", 17);
			state_17.put("digit", 17);
			state_17.put("0", 17);
			state_17.put(" ", 17);
			
			state_18.put("digit", 18);
			state_18.put("0", 18);
			state_18.put(" ", 18);
			
			state_19.put(" ", 19);
		}
}