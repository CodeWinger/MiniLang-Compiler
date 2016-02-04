package MiniLangCompiler.Main;

import java.util.HashMap;
import java.util.Hashtable;

import MiniLangCompiler.Main.TypeChecker.Type;
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
import MiniLangCompiler.node.AReadStmt;
import MiniLangCompiler.node.AStringDecl;
import MiniLangCompiler.node.AStringExp;
import MiniLangCompiler.node.AUnaryExp;
import MiniLangCompiler.node.AWhileStmt;
import MiniLangCompiler.node.Node;
import MiniLangCompiler.node.Start;

public class CodeGenerator extends DepthFirstAdapter 
{

	private static String reverse = "char* reverse(char *str) \n{\n"+
		       "\t"+"//Helper function to reverse a string [Generated]\n"
		      +"\t"+"char* t = (char*) malloc(120*sizeof(char));\n"
		      +"\t"+" strcpy(t, str);"+"\n"     
		      +"\t"+" char *p1, *p2;"+"\n"
		      +"\t"+" if (! t || ! *t)"+"\n"
		      +"\t"+"       return t;"+"\n"
		      +"\t"+" for (p1 = t, p2 = t + strlen(t) - 1; p2 > p1; ++p1, --p2) \n\t{\n"
		      +"\t\t"+" *p1 ^= *p2;"+"\n"
		      +"\t\t"+" *p2 ^= *p1;"+"\n"
		      +"\t\t"+" *p1 ^= *p2; \n\t}\n"
		      +"\t"+" return t; \n}\n\n";
	
	 Hashtable<Node, Type> types;
	 
	 static StringBuilder code = new StringBuilder(1000);
	 
	 public void puts(String s)
	 {
		 code.append(s);
		 if ( s.endsWith("\n"))
			 code.append("\t");
	 }
	
	public CodeGenerator(Hashtable<Node, Type> hashtable)
	{
		this.types = hashtable;
	}
	
	public static String generate(Node n, Hashtable<Node, Type> hashtable)
	{
		//start opening file for code
		 code.append("#include <stdio.h>\n");
		 code.append("#include <stdlib.h>\n");
		 code.append("#include <string.h>\n\n");
         code.append(reverse);
         code.append("int main(void) \n{\n\t");
         
         //traverse tree
		n.apply(new CodeGenerator( hashtable));
		
		//append last brace
		code.append("\n}");
		
		//return the code
		return code.toString();
	}
	
	@Override
	public void caseStart(Start node) 
	{
		super.caseStart(node);
		//System.out.println("hello");
	}

	@Override
	public void caseAWhileStmt(AWhileStmt node)
	{
		puts("while (");
        node.getL().apply(this);
        puts(") \n\t{ \n");
        
        for(Node n : node.getR()) 
        {
            puts("\t");
            n.apply(this); 
        }
        puts("\n\t} \n");
	}

	@Override
	public void caseAIfelseStmt(AIfelseStmt node) 
	{
		puts("if (");
        node.getL().apply(this);
        puts(") \n\t{ \n");
        
        for(Node n : node.getM()) 
        {
            puts("\t");
            n.apply(this); 
        }
        
        puts("\n\t} \n\telse \n\t{ \n");
        
        for(Node n : node.getR()) 
        {
            puts("\t");
            n.apply(this); 
        }
        
        puts("\n\t} \n");
	}

	@Override
	public void caseAIfStmt(AIfStmt node) 
	{
		puts("if (");
        node.getL().apply(this);
        puts(")\n\t{ \n");
        
        for(Node n : node.getR()) 
        {
            puts("\t");
            n.apply(this); 
        }	        
	    puts("\n\t} \n");
	}

	@Override
	public void caseAPrintStmt(APrintStmt node) 
	{
		puts("printf( ");
        switch (types.get(node.getExp())) 
        {
            case INT:
                puts("\"%d\" , ");
                break;
            case STRING:
                puts("\"%s\" , ");
                break;
           case FLOAT:
                puts("\"%f\" , ");
                break;
		case VOID:
			break;
		default:
			break;
        }
        node.getExp().apply(this);
        puts("); \n");
	}

	@Override
	public void caseAReadStmt(AReadStmt node) 
	{
		puts("scanf( ");
	        switch (types.get(node.getId())) 
	        {
	            case INT:
	                puts("\"%d\" , &");
	                break;
	            case STRING:
	                puts("\"%s\" , ");
	                break;
	            case FLOAT:
	                puts("\"%f\" , &");
			            break;
				case VOID:
					break;
				default:
					break;
        }
        puts(node.getId().getText());
        puts("); \n");
	}

	@Override
	public void caseAAssignStmt(AAssignStmt node) 
	{
		
		//non strings
		if (types.get(node.getR()) != TypeChecker.Type.STRING) 
		{
            puts(node.getL().getText());
            puts(" = ");
            node.getR().apply(this);
            puts(";\n");
        }
        else //dealing with strings
        {
            puts("strcpy( ");
            puts(node.getL().getText());
            puts(" , ");
            node.getR().apply(this);
            puts(" );\n");
        }
	}

	@Override
	public void caseAFloatDecl(AFloatDecl node) 
	{
		puts("float " + node.getId().getText() + ";\n");
	}

	@Override
	public void caseAIntDecl(AIntDecl node) 
	{
		puts("int " + node.getId().getText() + ";\n");
	}

	@Override
	public void caseAStringDecl(AStringDecl node) 
	{
		puts("char* " + node.getId().getText() + " = (char*) malloc(1000 * sizeof(char));\n");
	}

	@Override
	public void caseAPlusExp(APlusExp node) 
	{
		//non strings
		if (types.get(node.getL()) != TypeChecker.Type.STRING) 
		{
            node.getL().apply(this);
            puts(" + ");
            node.getR().apply(this);
        }
        else //strings
        {
            puts("strcat(");
            node.getL().apply(this);
            puts(" , ");
            node.getR().apply(this);
            puts(")");
        }
	}

	@Override
	public void caseAMinusExp(AMinusExp node) 
	{
		if (types.get(node.getL()) != TypeChecker.Type.STRING) 
		{
            node.getL().apply(this);
            puts(" - ");
            node.getR().apply(this);
        }
		else 
		{
            puts("strcat(");
            node.getL().apply(this);
            puts(" , ");
            //change Right to unary minus
            Node newR = new AUnaryExp(node.getR());
            newR.apply(this);
            puts(")");
        }
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
		//strings
		if (types.get(node.getExp()) != TypeChecker.Type.STRING) 
		{
            puts("- ");
            node.getExp().apply(this);
        } 
		//non strings
		else 
		{
            puts("reverse(");
            node.getExp().apply(this);
            puts(")");
        }
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
