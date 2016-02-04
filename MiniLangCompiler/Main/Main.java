package MiniLangCompiler.Main;

import MiniLangCompiler.parser.*;
import MiniLangCompiler.lexer.*;
import MiniLangCompiler.node.*;

import java.io.*;
 
class Main 
{
  public static void main(String args[]) 
  {


    boolean debug = false;
    for (int i = 0; i < args.length; i++)
    {
        if (args[i].equals("-debug"))
          debug = true;
    }

    try {
      Parser p = 
         new Parser (
           new Lexer (
              new PushbackReader(new InputStreamReader(new FileInputStream(args[1])))));
      
      Start tree = p.parse();
      
      /* pretty-print */
      String[] tks = args[1].split("\\."); //{foo.min} becomes {foo,min}
     
      
      print(tks[0] + ".pretty." + tks[1], PrettyPrinter.print(tree));
      
      /* symbol table*/
      print(tks[0] +".symbol.txt", TypeChecker.check(tree));
      
      //generate code only if type system is valid
      if ( !TypeChecker.isInvalid)
	      /*c code generation*/
	      print(tks[0]+ ".c", CodeGenerator.generate(tree,  TypeChecker.types));
      
      
    } 
   catch(Exception e) 
    { 
        System.out.println("Invalid Program ");
        if (debug)
          System.out.println(e);
    }
   }
  
  public static void print(String filename, String content)
  {
	PrintWriter out;
	try 
	{
		out = new PrintWriter(filename);
		out.println(content);
	    out.flush();
	    out.close();
	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	} 
  }
}