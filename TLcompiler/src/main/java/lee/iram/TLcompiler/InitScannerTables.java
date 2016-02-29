/**
 * 
 */
package lee.iram.TLcompiler;

import java.util.*;

/**
 * @author iramlee
 * Creates the tables used by the scanner: State Transition table, a token type table for
 * token classification, and a list of all the allowed keywords in the language.
 * Note: the state transition table includes only the transitions from the initial state, 
 * once the scanner leaves the initial state, the transition to other states is "direct coded"
 * in the Scanner class. 
 */
public class InitScannerTables {
	
	private Map<String, String> initStateTransTable = new HashMap<String, String>();
	private Map<String, String> tokenTypeTable = new HashMap<String, String>();
	private Set<String> keywords = new HashSet<String>();
	
//Constructor
	public InitScannerTables(){}
	
//Creates the transition table for state 0 (initial state)
		public Map<String, String> getTransTable(){
			initStateTransTable.put("(", "1");
			initStateTransTable.put(")", "2");
			initStateTransTable.put(":", "3");
			initStateTransTable.put(";", "5");
			initStateTransTable.put("*", "6");
			initStateTransTable.put("+", "7");
			initStateTransTable.put("-", "8");
			initStateTransTable.put("=", "9");
			initStateTransTable.put("!", "10");
			initStateTransTable.put("<", "12");
			initStateTransTable.put(">", "14");
			initStateTransTable.put("lc", "16");	//lc = any lower case character
			initStateTransTable.put("UC", "17");	//UC = any upper case character
			initStateTransTable.put("num", "18");	//num = any number from 1-9
			initStateTransTable.put("0", "19");
			return initStateTransTable;
		}
		
//Creates a table to match the type of token that was recognized
//Associates the ending state of the token to the token type (see Scanner transition table
//Excel file)
		public Map<String, String> getTokenTypeTable(){
			tokenTypeTable.put("1", "LP");
			tokenTypeTable.put("2", "RP");
			tokenTypeTable.put("4", "ASGN");
			tokenTypeTable.put("5", "SC");
			tokenTypeTable.put("6", "MULTIPLICATIVE(*)");
			tokenTypeTable.put("7", "ADDITIVE(+)");
			tokenTypeTable.put("8", "ADDITIVE(-)");
			tokenTypeTable.put("9", "COMPARE(=)");
			tokenTypeTable.put("11", "COMPARE(!=)");
			tokenTypeTable.put("12", "COMPARE(<)");
			tokenTypeTable.put("13", "COMPARE(<=)");
			tokenTypeTable.put("14", "COMPARE(>)");
			tokenTypeTable.put("15", "COMPARE(>=)");
			tokenTypeTable.put("16", "KEYWORD");	//should return the keyword itself
			tokenTypeTable.put("17", "ident");	//should return ident+(xyz..)
			tokenTypeTable.put("18", "num");	//should return num+(123..)
			tokenTypeTable.put("19", "num");	//state for number zero "0" Should return num+(0)
			return tokenTypeTable;
		}
		
//Creates a set with all the keywords in the language. Used to check if token is a valid
//keyword. Also contains the special keywords of the different types:
//boollit = "false", "true"
//MULTIPLICATIVE = "div", "mod"
//"writeint" and "readint"
		public Set<String> getKeywordTable(){
			keywords.add("false");
			keywords.add("true");
			keywords.add("div");
			keywords.add("mod");
			keywords.add("if");
			keywords.add("then");
			keywords.add("else");
			keywords.add("begin");
			keywords.add("end");
			keywords.add("while");
			keywords.add("do");
			keywords.add("program");
			keywords.add("var");
			keywords.add("as");
			keywords.add("int");
			keywords.add("bool");
			keywords.add("writeint");
			keywords.add("readint");
			return keywords;
		}
}
