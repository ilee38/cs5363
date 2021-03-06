/**
 * 
 */
package lee.iram.TLcompiler;

/**
 * @author iramlee
 * Main class of the program containing the main() method. This is the
 * entry point to the compiler. 
 * The main method gets the source code file name from the command line
 * arguments. Then it starts an instance of the Scanner class, which
 * performs the scanning and token output.
 *
 */
public class Main {
	private String fileName;
	
	public static void main(String[] args) throws Exception{
		String srcCodeFileName = "";
		try{
			srcCodeFileName = "sqrt.tl"; //args[0];
		} catch(Exception e){}
		new Main(srcCodeFileName).start();
	}
	
	public Main(String fileName){
		this.fileName = fileName;
	}
	
	public void start() throws Exception{
		Scanner scan = new Scanner(fileName);
		scan.writeTokenList();
		Parser parse = new Parser(scan.getTokenStream(), scan.getLexemeStream(), fileName);
		parse.performDescent();
	}

}