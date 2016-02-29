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

	public static void main(String[] args) throws Exception{
		String srcCodeFileName = null;
		Scanner scan = new Scanner(srcCodeFileName);
		try{
			srcCodeFileName = args[0];
		} catch(Exception e){
			System.out.println("Invalid file name");
		}
		scan.writeTokenList();
	}

}
