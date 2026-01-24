@echo off
:: --- Configuration ---
SET JFLEX_BIN=jflex
SET CUP_JAR=lib\java-cup-11b.jar
SET CUP_RUNTIME=lib\java-cup-11b-runtime.jar

SET LEXER_FILEPATH=src\main\lexer
SET PARSER_FILEPATH=src\main\parser
SET SRC_PATH=src\main\java\org\uma

:: Create folders if they don't exist
if not exist gen mkdir gen
if not exist gen\lexer mkdir gen\lexer
if not exist gen\parser mkdir gen\parser
if not exist bin mkdir bin

echo [1/4] Generating Scanner with JFlex...
call %JFLEX_BIN% -d gen\lexer %LEXER_FILEPATH%\PLX.flex

echo [2/4] Generating Parser with CUP...
java -jar %CUP_JAR% -package parser -destdir gen\parser %PARSER_FILEPATH%\PLX.cup

echo [3/4] Compiling Java files...
:: Note the semicolon for Windows classpath
javac -d bin -cp "%CUP_RUNTIME%;gen" gen\lexer\*.java gen\parser\*.java %SRC_PATH%\Main.java

echo [4/4] Running ...
echo ---------------------------------------
:: java -cp "bin;%CUP_RUNTIME%" org.uma.Main
:: java -cp "bin;%CUP_RUNTIME%" org.uma.Main testing\exp.in

::java -cp "bin;%CUP_RUNTIME%" org.uma.Main testing\exp.in testing\exp.out
::javac -d bin -cp "%CUP_RUNTIME%;gen" gen\lexer\*.java gen\parser\*.java %SRC_PATH%\**\*.java
javac -d bin -cp "%CUP_RUNTIME%;gen" gen\lexer\*.java gen\parser\*.java %SRC_PATH%\*.java %SRC_PATH%\ast\*.java %SRC_PATH%\symboltable\*.java

::pause