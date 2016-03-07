/**
 * 
 */
package lee.iram.TLcompiler;

import java.io.InputStream;

/**
 * @author iramlee
 * Main class of the program, entry point to the compiler
 *
 */
public class Main {
	private String fileName;
	
	public static void main(String[] args) throws Exception{
		String srcCodeFileName = "";
		try{
			srcCodeFileName = args[0];
		} catch(Exception e){}
		new Main(srcCodeFileName).start();
	}
	
	public Main(String fileName){
		this.fileName = fileName;
	}
	
	public void start() throws Exception{
		Scanner scan = new Scanner();
		scan.writeTokenList(fileName);
	}

}
