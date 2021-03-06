package MiniLangCompiler.Main;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map.Entry;

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

public class TypeChecker extends DepthFirstAdapter 
{

	private static Hashtable<String, Type> symbolTable = new Hashtable<String, Type>();
    public static Hashtable<Node, Type> types = new Hashtable<Node, Type>();
    public static enum Type { VOID, INT, FLOAT, STRING}; 
    public static boolean isInvalid = false;
    

    public TypeChecker() 
    {
        super();
        isInvalid = false;
    }
    
    private static StringBuilder st = new StringBuilder(1000);
	private static void puts(String s)
	{
		st.append(s);
	}
    
    public static String check(Node start) 
    {
        start.apply(new TypeChecker());
        for (String k : symbolTable.keySet()) 
           puts(k + " : " + symbolTable.get(k).toString() + "\n");
        
        if(isInvalid) 
            System.out.println("The typing is not correct");
        else
            System.out.println("The typing is correct");
        	
        return st.toString();
    }
  
    /*@Override
    public void outAProgramProg(AProgramProg node) {
        if(isInvalid) 
            System.out.println("The typing is not correct");
        else
            System.out.println("The typing is correct");
    }*/
	
	@Override
	public void inAFloatDecl(AFloatDecl node) 
	{
		if (isInvalid) return;
		if (!symbolTable.containsKey(node.getId().getText()))
			symbolTable.put(node.getId().getText(), Type.FLOAT);
		else
			isInvalid = true;
	}

	@Override
	public void inAIntDecl(AIntDecl node) 
	{
		if (isInvalid) return;

		if (!symbolTable.containsKey(node.getId().getText()))
			symbolTable.put(node.getId().getText(), Type.INT);
		else
			isInvalid = true;
	}

	@Override
	public void inAStringDecl(AStringDecl node) 
	{
		if (isInvalid) return;

		if (!symbolTable.containsKey(node.getId().getText()))
			symbolTable.put(node.getId().getText(), Type.STRING);
		else
			isInvalid = true;
	}

	
	@Override
	public void outAWhileStmt(AWhileStmt node) 
	{
		if (isInvalid) return;

		Node exp = node.getL();
		
		if ( types.containsKey(exp) && types.get(exp) == Type.INT)
			types.put(node, Type.VOID);
		else
			isInvalid = true;
	}

	@Override
	public void outAIfelseStmt(AIfelseStmt node) 
	{
		if (isInvalid) return;

		Node n = node.getL();
        if (types.containsKey(n) && types.get(n) == Type.INT)      
            types.put(node, Type.VOID);
        else 
            isInvalid = true;
	}

	@Override
	public void outAIfStmt(AIfStmt node) 
	{
		if (isInvalid) return;

		Node n = node.getL();
        if (types.containsKey(n) && types.get(n) == Type.INT)      
            types.put(node, Type.VOID);
        else 
            isInvalid = true;
	}

	@Override
	public void outAPrintStmt(APrintStmt node) 
	{
		
		if (isInvalid) return;

		//void accepted
        Node n = node.getExp();
        if (types.containsKey(n))         
            types.put(node, Type.VOID);
        else 
            isInvalid = true;
        
	}

	@Override
	public void outAReadStmt(AReadStmt node) 
	{
		if (isInvalid) return;

		//void accepted
        Node n = node.getId();
        if (symbolTable.containsKey(node.getId().getText())) {           
            types.put(n, symbolTable.get(node.getId().getText()));
            types.put(node, Type.VOID);
        }
        else
            isInvalid = true;
	}

	@Override
	public void outAAssignStmt(AAssignStmt node) 
	{
		if (isInvalid) return;

		//get expression on the rhs
        Node n = node.getR();
        Type type;
        if(symbolTable.containsKey(node.getL().getText()))
            type = symbolTable.get(node.getL().getText());
        else
        {
            isInvalid = true;
            return;
        }
        
        
        if (type == Type.FLOAT)
            //int can go to float
            if(types.containsKey(n) && (types.get(n) == Type.INT || types.get(n) == Type.FLOAT))  
                types.put(node, Type.VOID);
            else 
                isInvalid = true;
            
        else if (types.containsKey(n) && types.get(n) == type)          
            types.put(node, Type.VOID);
            //types.remove(n);
        
        else
            isInvalid = true;
        
	}

	@Override
	public void outAPlusExp(APlusExp node) 
	{

		if (isInvalid) return;

        Node l = node.getL();
        Type lt = types.get(l);
        Node r = node.getR();
        Type rt = types.get(r);
        if (l != null && lt == Type.INT) 
            if(r != null && rt == Type.INT) 
                types.put(node, Type.INT);
            else if(r != null && rt == Type.FLOAT) 
                types.put(node, Type.FLOAT);
            else 
                isInvalid = true;
        else if (l != null && lt == Type.FLOAT) 
            if(r != null && (rt == Type.INT || rt == Type.FLOAT)) 
                types.put(node, Type.FLOAT);
             else 
                isInvalid = true;
        else if (l != null && lt == Type.STRING) 
            if(r != null && rt == Type.STRING) 
                types.put(node, Type.STRING);
             else 
                isInvalid = true;
        else 
            isInvalid = true;
	}

	@Override
	public void outAMinusExp(AMinusExp node) 
	{
		if (isInvalid) return;

        Node l = node.getL();
        Type lt = types.get(l);
        Node r = node.getR();
        Type rt = types.get(r);
        if (l != null && lt == Type.INT) 
            if(r != null && rt == Type.INT) 
                types.put(node, Type.INT);
            else if(r != null && rt == Type.FLOAT) 
                types.put(node, Type.FLOAT);
            else 
                isInvalid = true;
        else if (l != null && lt == Type.FLOAT) 
            if(r != null && (rt == Type.INT || rt == Type.FLOAT)) 
                types.put(node, Type.FLOAT);
             else 
                isInvalid = true;
        else if (l != null && lt == Type.STRING) 
            if(r != null && rt == Type.STRING) 
                types.put(node, Type.STRING);
             else 
                isInvalid = true;
        else 
            isInvalid = true;
	}

	@Override
	public void outAMultExp(AMultExp node) 
	{
		if (isInvalid) return;

        Node l = node.getL();
        Type lt = types.get(l);
        Node r = node.getR();
        Type rt = types.get(r);
        if (l != null && lt == Type.INT)
            if(r != null && rt == Type.INT)
                types.put(node, Type.INT);
            else if(r != null && rt == Type.FLOAT)
                types.put(node, Type.FLOAT);     
            else
                isInvalid = true;
        else if (l != null && lt == Type.FLOAT) 
            if(r != null && (rt == Type.INT || rt == Type.FLOAT))
                types.put(node, Type.FLOAT);   
            else 
                isInvalid = true;
        else 
            isInvalid = true;
	}

	@Override
	public void outADivdExp(ADivdExp node)
	{
		if (isInvalid) return;

        Node l = node.getL();
        Type lt = types.get(l);
        Node r = node.getR();
        Type rt = types.get(r);
        if (l != null && lt == Type.INT)
            if(r != null && rt == Type.INT)
                types.put(node, Type.INT);
            else if(r != null && rt == Type.FLOAT)
                types.put(node, Type.FLOAT);     
            else
                isInvalid = true;
        else if (l != null && lt == Type.FLOAT) 
            if(r != null && (rt == Type.INT || rt == Type.FLOAT))
                types.put(node, Type.FLOAT);   
            else 
                isInvalid = true;
        else 
            isInvalid = true;
	}

	@Override
	public void outAUnaryExp(AUnaryExp node) 
	{
		if (isInvalid) return;

		Node n = node.getExp();
        if (types.containsKey(n) && types.get(n) != Type.VOID)         
            types.put(node, types.get(n));
        else
            isInvalid = true;
	}

	@Override
	public void outAParenExp(AParenExp node) 
	{
		if (isInvalid) return;

		Node n = node.getExp();
        if (types.containsKey(n) && types.get(n) != Type.VOID)         
            types.put(node, types.get(n));
        else
            isInvalid = true;
	}

	@Override
	public void outAIdExp(AIdExp node) 
	{
		if (isInvalid) return;

		if (symbolTable.containsKey(node.getId().getText()))
            types.put(node, symbolTable.get(node.getId().getText()));
        else 
            isInvalid = true;
	}

	@Override
	public void outAIntExp(AIntExp node) 
	{
		if (isInvalid) return;

		types.put(node.getIntNumber(), Type.INT);
        types.put(node, Type.INT);
	}

	@Override
	public void outAFloatExp(AFloatExp node) 
	{
		if (isInvalid) return;

		types.put(node.getFloatNumber(), Type.FLOAT);
        types.put(node, Type.FLOAT);	
	}

	@Override
	public void outAStringExp(AStringExp node) 
	{
		if (isInvalid) return;

		types.put(node.getStringValue(), Type.STRING);
        types.put(node, Type.STRING);
	}
}
