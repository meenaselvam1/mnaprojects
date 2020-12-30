package com.practicaljava.javatechtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/* TestEngine class handles events from the  GUI. 
 * Checks answers for correctness and shows dialogs accordingly.
 */
public class TestEngine implements ActionListener {

	View parent; // a reference to the View

	// Constructor stores the reference to the
	// View window in the member variable parent
	TestEngine(View parent) {
		this.parent = parent;
	}
	/*
	 * Check the source of the event, and handle the event accordingly
	 * Possible sources are the answerList Combo box or the Next Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == parent.getAnswerList()) {
			String enteredAnswer;
			JComboBox cb = (JComboBox)e.getSource();
			String answer = (String)cb.getSelectedItem();
			if ((!answer.equals("Select")) && (TechTest.questionNum < TechTest.numberOfQuestions))
			{
				if (answer.equals("True")) 
					enteredAnswer = "T";
				else			
					enteredAnswer = "F";
				verifyandFeedback(  enteredAnswer);
				cb.setSelectedIndex(0);
			}
		}
		if (e.getSource() == parent.getButtonNext() ) {
			// Be ready to display next question
			if (TechTest.questionNum < TechTest.numberOfQuestions)
				TechTest.questionNum++;
			JButton clickedButton = (JButton) e.getSource();
			if (clickedButton.getText().equals("NEXT")) {
				if (TechTest.questionNum  < TechTest.numberOfQuestions) 
					// Display the next question
					parent.display((String) TechTest.questionSet.get(TechTest.questionNum) );
				else 
					JOptionPane.showMessageDialog(null,"You finished the test. Close the window.",
							                           "Message", JOptionPane.PLAIN_MESSAGE);
			}
		}

	} //actionPerformed

	/* Check if the user answered correctly and show dialogs accordingly */
	void verifyandFeedback(
			String enteredAnswer) {
		String answer;
		try {
			if (((String) TechTest.answerSet.get(TechTest.questionNum) != null) && (enteredAnswer != null)){
				answer = (String) TechTest.answerSet.get(TechTest.questionNum);
				if (answer.toString()
						.equals(enteredAnswer.toString()) ) 
					JOptionPane.showMessageDialog(null, "You answered correct. Click Next for the next question.",
							                             "Message", JOptionPane.PLAIN_MESSAGE);
				else 
					JOptionPane.showMessageDialog(null, "Incorrect answer. Click Next for the next question.",
							                            "Message", JOptionPane.PLAIN_MESSAGE);
			}

		}
		catch (NullPointerException e) { 
			JOptionPane.showMessageDialog(null, "Answer is null: " + e.getMessage(),
					                            "Exception", JOptionPane.PLAIN_MESSAGE);
		}
	}


}
