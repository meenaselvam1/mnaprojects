import java.awt.FlowLayout;
import javax.swing.*;

// Singleton class View
public class View {

	private JButton buttonNext;
	private JComboBox answerList;
	private JTextField displayField;

	/* 
	 * View class creates and shows the GUI. Displays the questions.
	 * Registers Next button  and AnswerList Combobox with the 
	 * listener Class TestEngine. Shows dialog for any exception while 
	 * displaying the question.
	 */
	public View()  {

		JPanel windowContent;
		JLabel label = new JLabel("Select your answers True or False");

		windowContent = new JPanel();
		FlowLayout fl = new FlowLayout();
		// Set the layout manager for this panel
		windowContent.setLayout(fl);

		// displayField displays the quiz questions
		displayField = new JTextField(40);
		windowContent.add(displayField);
		windowContent.add(label);

		String [] answerStrings = {"Select", "True", "False" };

		//Create the combo box, select the item at index 0.
		//Indices start at 0, so 0 specifies the Select.
		answerList = new JComboBox(answerStrings);
		answerList.setSelectedIndex(0);
		windowContent.add(answerList);


		buttonNext =new JButton("NEXT"); 
		windowContent.add(buttonNext);

		//Create the frame and set its content pane	               
		JFrame frame = new JFrame("TechTest");

		frame.setContentPane(windowContent);

		// Set the size of the window to be big enough to accommodate all controls 		
		frame.pack(); 
		// enable the window's close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);

		// TestEngine is the class that listens to events.
		TestEngine testEngine = new TestEngine(this);

		buttonNext.addActionListener(testEngine);
		answerList.addActionListener(testEngine);
	}

	public void display(String question ) {
		// Displaying the question here every time View.display is called
		try {
			displayField.setText((String) question);
		}
		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Null Pointer Exception: Question is missing. Check the javaquestions.properties file", 
					                      "Exception",JOptionPane.PLAIN_MESSAGE);
		}
	}

	// getters for Next Button and answerList combo box
	public JButton getButtonNext() {
		return buttonNext;
	}
	public JComboBox getAnswerList() {
		return answerList;
	}

}


