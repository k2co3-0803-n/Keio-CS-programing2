import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterFormStudent extends MyFrame {
	JTextField title;
	JTextField studentName;
	JTextField student_id;

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DB.insertIntoStudents(student_id.getText(), studentName.getText());
			MyApp.initData();
			dispose();
		}
	}

	// Constructor
	public RegisterFormStudent(String frameName) {
		super(frameName);
		JLabel titleLabel = new JLabel("Register a new student");
		JLabel studentNameLabel = new JLabel("Name");
		JLabel student_idLabel = new JLabel("Student ID");

		studentName = new JTextField();
		student_id = new JTextField();

		JButton addButton = new JButton("Register");
		ButtonAction buttonListener = new ButtonAction();
		addButton.addActionListener(buttonListener);

		JPanel pane1 = new JPanel();
		pane1.add(titleLabel);
		JPanel pane2 = new JPanel();
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(studentNameLabel);
		pane2.add(studentName);
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(student_idLabel);
		pane3.add(student_id);

		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(addButton);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	public static void createRegisterFormStudent() {
		RegisterFormStudent registerFormLecture = new RegisterFormStudent("Register a new student");
		registerFormLecture.setVisible(true);
	}
}
