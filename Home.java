import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends MyFrame {

	// Instance variable
	public static JPanel cardPanel;
	public static CardLayout layout; // For switching the screen. This is the key point of switching of screen.

	// Constructor
	public Home(String title) {
		super(title); // Constructor of super class

		JLabel label1 = new JLabel("Lecture Manegement System");
		JLabel label2 = new JLabel("Rakuta");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		Font font1 = label1.getFont();
		label1.setFont(font1.deriveFont(Font.BOLD, 24));
		Font font2 = label2.getFont();
		label2.setFont(font2.deriveFont(Font.BOLD, 24));

		JButton lectureButton = new JButton("Lecture");
		JButton studentButton = new JButton("Student");
		JButton professorButton = new JButton("Professor");
		ButtonAction buttonActionListener = new ButtonAction();
		lectureButton.addActionListener(buttonActionListener);
		lectureButton.setActionCommand("Lecture");
		studentButton.addActionListener(buttonActionListener);
		studentButton.setActionCommand("Student");
		professorButton.addActionListener(buttonActionListener);
		professorButton.setActionCommand("Professor");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		buttonPanel.add(lectureButton);
		buttonPanel.add(studentButton);
		buttonPanel.add(professorButton);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		mainPanel.add(label1);
		mainPanel.add(label2);
		mainPanel.add(buttonPanel);

		LecturePanel lecturePanel = LecturePanel.createLecturePanel();
		StudentPanel studentPanel = StudentPanel.createStudentPanel();
		ProfessorPanel professorPanel = ProfessorPanel.createProfessorPanel();

		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.add(lecturePanel, "Lecture");
		cardPanel.add(studentPanel, "Student");
		cardPanel.add(professorPanel, "Professor");

		Container contentPane = getContentPane();
		contentPane.add(mainPanel, BorderLayout.PAGE_START);
		contentPane.add(cardPanel, BorderLayout.CENTER);
	}

	// Iternal class
	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			layout.show(cardPanel, command);
		}
	}

	// class method
	// Here, create home screen as an entity
	// Later, call this class method in main method of MyApp class
	public static void createHome() {
		Home home = new Home("Lecture Management System"); // This panel is a super panel among all panel in the Home screen.
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setVisible(true);
	}
}