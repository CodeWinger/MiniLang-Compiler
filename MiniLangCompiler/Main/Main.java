package MiniLangCompiler.Main;

import MiniLangCompiler.parser.*;
import MiniLangCompiler.lexer.*;
import MiniLangCompiler.node.*;
import java.io.*;
 
class Main {
  public static void main(String args[]) {


    boolean debug = false;
    for (int i = 0; i < args.length; i++)
    {
        if (args[i].equals("-debug"))
          debug = true;
    }

    try {
      System.out.println("Type in a tiny exp folowed by one or two Ctrl-d's:");
      Parser p = 
         new Parser (
           new Lexer (
              new PushbackReader(new InputStreamReader(new FileInputStream(args[1])))));
      
      Start tree = p.parse();
      System.out.println("Valid Program");
      /* pretty-print */
      //System.out.println("\nThe result of evaluating:");
      //PrettyPrinter.print(tree);

      /* evaluate */
      //System.out.println("\nis: " + Evaluator.eval(tree));

    } 
   catch(Exception e) 
    { 
        System.out.println("Invalid Program ");
        if (debug)
          System.out.println(e);
    }
   }
}