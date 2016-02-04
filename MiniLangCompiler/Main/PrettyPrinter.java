package MiniLangCompiler.Main;

import MiniLangCompiler.analysis.DepthFirstAdapter;
import MiniLangCompiler.node.AAssignStmt;
import MiniLangCompiler.node.ADivdExp;
import MiniLangCompiler.node.AFloatDecl;
import MiniLangCompiler.node.AFloatExp;
import MiniLangCompiler.node.AIdExp;
import MiniLangCompiler.node.AIfStmt;
import MiniLangCompiler.node.AIfelseStmt;
import MiniLangCompiler.node.AIntDecl;
import MiniLangCompiler.node.AIntExp;
import MiniLangCompiler.node.AMinusExp;
import MiniLangCompiler.node.AMultExp;
import MiniLangCompiler.node.AParenExp;
import MiniLangCompiler.node.APlusExp;
import MiniLangCompiler.node.APrintStmt;
import MiniLangCompiler.node.AProgramProg;
import MiniLangCompiler.node.AReadStmt;
import MiniLangCompiler.node.AStringDecl;
import MiniLangCompiler.node.AStringExp;
import MiniLangCompiler.node.AUnaryExp;
import MiniLangCompiler.node.AWhileStmt;
import MiniLangCompiler.node.Node;
import MiniLangCompiler.node.Start;

public class PrettyPrinter extends DepthFirstAdapter 
{
	
	static StringBuilder sb = new StringBuilder(1000);

	public static String print(Node node)
	{
		 node.apply(new PrettyPrinter());
		 return sb.toString();
	}
	
	void puts(String s)
	{
		sb.append(s);
	}
	
	@Override
	public void caseStart(Start node) 
	{
		super.caseStart(node);
		//System.out.println("hello");
	}

	@Override
	public void caseAProgramProg(AProgramProg node) 
	{
		super.caseAProgramProg(node);
	}

	@Override
	public void caseAWhileStmt(AWhileStmt node) 
	{
		
		puts("while ");
		node.getL().apply(this);
		puts(" do \n");
		
		for(Node n : node.getR())
		{
            puts("\t");
            n.apply(this); 
        }
        
        puts("done \n");
	}

	@Override
	public void caseAIfelseStmt(AIfelseStmt node) 
	{
		
		puts("if ");
        node.getL().apply(this);
        puts(" then \n");
        
        for(Node n : node.getM()) 
        {
            puts("\t");
            n.apply(this); 
        }
        
        puts("else \n");
        
        for(Node n : node.getR()) 
        {
            puts("\t");
            n.apply(this); 
        }
        
        puts("endif \n");
	}

	@Override
	public void caseAIfStmt(AIfStmt node) 
	{
		
		
		 puts("if ");
        node.getL().apply(this);
        puts(" then \n");
        
        for(Node n : node.getR()) 
        {
            puts("\t");
            n.apply(this); 
        }
        
        puts("endif \n");
	}

	@Override
	public void caseAPrintStmt(APrintStmt node) 
	{
		
		puts("print ");
        node.getExp().apply(this);
        puts("; \n");
	}

	@Override
	public void caseAReadStmt(AReadStmt node) 
	{
		
		puts("read ");
        puts(node.getId().getText());
        puts("; \n");
	}

	@Override
	public void caseAAssignStmt(AAssignStmt node) 
	{
		
		puts(node.getL().getText());
        puts(" = ");
        node.getR().apply(this);
        puts(";\n");
	}

	@Override
	public void caseAFloatDecl(AFloatDecl node) 
	{
		 puts("var ");
        puts(node.getId().getText());
        puts(" : float;\n");
	}

	@Override
	public void caseAIntDecl(AIntDecl node)
	{
		   puts("var ");
	        puts(node.getId().getText());
	        puts(" : int;\n");
	}

	@Override
	public void caseAStringDecl(AStringDecl node) 
	{
		puts("var ");
        puts(node.getId().getText());
        puts(" : string;\n");
	}

	@Override
	public void caseAPlusExp(APlusExp node) 
	{
		node.getL().apply(this);
        puts(" + ");
        node.getR().apply(this);
	}

	@Override
	public void caseAMinusExp(AMinusExp node) 
	{
		node.getL().apply(this);
        puts(" - ");
        node.getR().apply(this);
	}

	@Override
	public void caseAMultExp(AMultExp node) 
	{
		node.getL().apply(this);
        puts(" * ");
        node.getR().apply(this);
	}

	@Override
	public void caseADivdExp(ADivdExp node)
	{
		node.getL().apply(this);
        puts(" / ");
        node.getR().apply(this);
	}

	@Override
	public void caseAUnaryExp(AUnaryExp node) 
	{
		puts("-");
        node.getExp().apply(this);
	}

	@Override
	public void caseAParenExp(AParenExp node) 
	{
		puts("(");
        node.getExp().apply(this);
        puts(")");
	}

	@Override
	public void caseAIdExp(AIdExp node) 
	{
        puts(node.getId().getText());
	}

	@Override
	public void caseAIntExp(AIntExp node) 
	{
        puts(node.getIntNumber().getText());
	}

	@Override
	public void caseAFloatExp(AFloatExp node) 
	{
        puts(node.getFloatNumber().getText());
	}

	@Override
	public void caseAStringExp(AStringExp node) 
	{
        puts(node.getStringValue().getText());

	}
}
