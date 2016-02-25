package lee.iram.TLcompiler;

import java.io.*;

/**
 * @author iramlee
 *	Scanner class. Performs the lexical analysis on the source code file
 */
public class Scanner {
	String fileName;
	
	//Scanner constructor
	public Scanner(String file){
		fileName = file;
	}
	
	//Generate text file containing the list of tokens
	public void getTokenList(){
		BufferedReader reader = openFile(fileName);
	}
	
	//Return the file reader object
	public BufferedReader openFile(String fileName){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(fileName));
		}catch (IOException ex){
			System.out.println("Can't open that file");
		}
		return rd;
	}
}

