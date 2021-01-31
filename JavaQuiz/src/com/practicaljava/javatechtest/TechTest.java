package com.practicaljava.javatechtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * TechTest Class is the driver class parsing file containing 
 * questions and answers. Populates the questionSet and answerSet
 * Vectors. Delegates the display of GUI to the View class.
 * Shows dialog for errors and exceptions.
 */
public class TechTest {
	static int numberOfQuestions = 0;
	static int questionNum = 0;
	static Vector  questionSet = new Vector();
	static Vector answerSet = new Vector();
	static boolean noFile = false;
	static boolean incorrectAnswerFormat = false;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String questionAnsFileName = "javaQuestionsAns.txt"; // default quiz file
				if (args.length >= 1) 
					questionAnsFileName = args[0];
					
				JOptionPane.showMessageDialog(null, "Using the quiz file:  " + questionAnsFileName, 
						"Message", JOptionPane.PLAIN_MESSAGE);
				
				parseQuestionAnswerFile(questionAnsFileName);
				if ((!noFile) &&  (!incorrectAnswerFormat)) {
					if (!questionSet.isEmpty()) { 
						View view = new View(); 
						view.display((String) questionSet.get((questionNum)));
					}
					else
						JOptionPane.showMessageDialog(null,"Questions are missing. Check the " + questionAnsFileName + " file", "Error",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

	}
	
	// Nonblocking IO (nio) is used
	static void parseQuestionAnswerFile(String questionAnsFile) {

		Path qAnsFile = Paths.get(questionAnsFile);

		try (BufferedReader reader = 
				Files.newBufferedReader(qAnsFile, StandardCharsets.UTF_8)){
			String question;
			String answer;
			while (true) {
				question = reader.readLine();
				if (question == null) 
					break;
				if (!question.isEmpty()) {
					questionSet.add(question);
					numberOfQuestions++;
				}
				answer = reader.readLine();
				if (answer == null) 
					break;

				if (!answer.isEmpty()) {
					if (( (answer.equals("T"))) || ((answer.equals("F")))) 
						answerSet.add(answer);
					else {
						JOptionPane.showMessageDialog(null,"Answer not in correct format : T or F", 
								                     "Error ",JOptionPane.PLAIN_MESSAGE);
						incorrectAnswerFormat = true;
					}
				}
			}
		}
		// Catch exceptions while reading the File with questions and answers
		catch (NoSuchFileException e)
		{
			noFile = true;
			JOptionPane.showMessageDialog(null, questionAnsFile + ": File not found", 
					                      "Exception",JOptionPane.PLAIN_MESSAGE);
		}
		
		catch (IOException ioe){ 
			noFile = true;
			JOptionPane.showMessageDialog(null, "Error while reading " + questionAnsFile + " " + 
			                              ioe.toString(),"Exception",JOptionPane.PLAIN_MESSAGE);
		}
	}
}
