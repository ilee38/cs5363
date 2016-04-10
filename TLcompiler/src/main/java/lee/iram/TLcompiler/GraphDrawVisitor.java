/**
 * 
 */
package lee.iram.TLcompiler;

import java.io.*;

/**
 * @author iramlee
 *
 */
public class GraphDrawVisitor extends DummyVisitor {
	
	private PrintWriter writer;
	private String fileName;
	private int nodeNum = 1;	//node counter
	private int parentNum, childNum;
	private String shape = "shape=box]";
	private String label = " [label=";
	
/*
 * Class Constructor
 * */
	public GraphDrawVisitor(String fileName){
		this.fileName = fileName.substring(0, fileName.indexOf(".")) + ".ast" + ".dot";
		this.writer = createWriter(this.fileName); 
	}
	
	
/*
 * Returns a file writer object
 * */
	private PrintWriter createWriter(String file){
		PrintWriter wr = null;
		try{
			wr = new PrintWriter(new FileWriter(file));
		} catch(IOException ex){
			System.out.println("Error writing file");
		}
		return wr;
	}

	
/*
 * 
 * */	
	public void visit(Program program) {
		int myNodeNum = nodeNum;
		writer.println("digraph tl15Ast {");
		writer.println(" ordering=out;");
		writer.println(" node [shape = box, style = filled, fillcolor = \"white\"];");
		writer.println(" n"+nodeNum + label + "\"program\"," + shape);
		parentNum = nodeNum;
		nodeNum++;
		if(program.decList != null){
			writer.println(" n"+nodeNum + label + "\"decl list\"," + shape);
			writer.println(" n"+myNodeNum + " -> " + "n"+nodeNum);
			parentNum = nodeNum;
			nodeNum++;
			for(int i = 0; i < program.decList.size(); i++ ){
				program.decList.get(i).accept(this);
			}
		}else{
			return;
		}
		if(program.stmntList != null){
			writer.println(" n"+nodeNum + label + "\"stmnt list\"," + shape);
			writer.println(" n"+myNodeNum + " -> " + "n"+nodeNum);
			parentNum = nodeNum;
			nodeNum++;
			program.stmntList.accept(this);
		}
		writer.println("}");
		writer.close();
	}
	

/*
 * 
 * */
	public void visit(Decl decl) {
		decl.id.accept(this);
		writer.println(" n"+nodeNum + label + "\"" + decl.type + ":" + decl.id.identName + ":" + decl.id.identType + "\"," + shape);
		writer.println(" n"+parentNum + " -> " + "n"+nodeNum);
		nodeNum++;
	}
	

/*
 * 
 * */
	public void visit(StmntSeq stmntSeq) {
		for(int i = 0; i < stmntSeq.statmentList.size(); i++){
			stmntSeq.statmentList.get(i).accept(this);
			writer.println(" n"+nodeNum + label + "\"" + stmntSeq.statmentList.get(i).type + "\"," + shape);
			writer.println(" n"+parentNum + " -> " + "n"+nodeNum);
			nodeNum++;
		}
	}
	
	
/*
 * 
 * */	
	public void visit(Assignment assignment) {
		int myNodeNum = nodeNum+1;
		assignment.id.accept(this);
		//assignment.exp.accept(this);
		writer.println(" n"+nodeNum + label + "\"" + assignment.id.identName + ":" + assignment.id.identType + "\"," + shape);
		writer.println(" n"+ myNodeNum + " -> " + "n"+nodeNum);
		nodeNum++;
	}
	
	
}
