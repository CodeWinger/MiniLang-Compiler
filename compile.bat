@ clean
del /F /Q "MiniLangCompiler\*.class"
del /F /Q "MiniLangCompiler\lexer\*" 
del /F /Q "MiniLangCompiler\parser\*" 
del /F /Q "MiniLangCompiler\node\*" 
del /F /Q "MiniLangCompiler\analysis\*"
del /F /Q "MiniLangCompiler\Main\Main.class"
ECHO "cleaned"
	
REM sablecc
START sablecc ml3.gr
ECHO "generated tree"
PAUSE

javac MiniLangCompiler\Main\*.java MiniLangCompiler\lexer\*.java MiniLangCompiler\parser\*.java MiniLangCompiler\node\*.java MiniLangCompiler\analysis\*.java 
ECHO "compiled"
PAUSE

java MiniLangCompiler.Main.Main -debug
ECHO "executed"