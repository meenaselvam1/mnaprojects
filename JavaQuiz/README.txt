The subfolders under the folder src contain the java source code for the quiz program.
JavaQuiz project contains java source code packaged in the com.practicaljava.javatechtest package. The source code is kept in appropriate folder structure, according to this package.

To run the program: 
1. Move the JavaTest.jar and the javaquestionsAns.txt 
into any folder of your choice.
2. Then from this folder, issue the command : 
java -classpath JavaTest.jar com.practicaljava.javatechtest.TechTest <argument>
(TechTest is the Main class)

Argument is optional (an optional quiz filename). Default quiz 
file name "javaquestionsAns.txt" will be used if argument is omitted.

The program is a True Or False Quiz.
 
All True or false questions and answers are put in the default 
text file named javaquestionsAns.txt. The quiz file should be placed 
in the folder from where you are running the program.

Here is the content of a sample javaquestionsAns.txt file.

Interface can have static methods defined
T
Interface cannot have defender methods defined
F
Abstract class can have some methods implemented
T
Abstract class can be instantiated
F
You can't force garbage collection but you can request it
T
Constructors can return a value
F
Casting cannot be performed between objects of different types.
T
Java class can be inherited from two classes
F

NOTE: The program was compiled using java version 15.0.2.
Java(TM) SE Runtime Environment (build 15.0.2+7-27). You might 
get errors if you are using a much older java version
