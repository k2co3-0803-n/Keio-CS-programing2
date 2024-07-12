import javax.swing.Box;
import javax.swing.JButton;
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
		JLabel label1 = new JLabel("講義管理システム");
		JLabel label2 = new JLabel("ラクタ");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		Font font1 = label1.getFont();
		label1.setFont(font1.deriveFont(Font.BOLD, 24));
		Font font2 = label2.getFont();
		label2.setFont(font2.deriveFont(Font.BOLD, 24));

		JButton lectureButton = new JButton("講義");
		JButton studentButton = new JButton("学生");
		JButton professorButton = new JButton("教員");
		ButtonAction buttonActionListener = new ButtonAction();
		lectureButton.addActionListener(buttonActionListener);
		lectureButton.setActionCommand("講義");
		studentButton.addActionListener(buttonActionListener);
		studentButton.setActionCommand("学生");
		professorButton.addActionListener(buttonActionListener);
		professorButton.setActionCommand("教員");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,0));
		buttonPanel.add(lectureButton);
		buttonPanel.add(studentButton);
		buttonPanel.add(professorButton);

		JPanel mainPannel = new JPanel();
		mainPannel.setLayout(new GridLayout(0,1));
		mainPannel.add(label1);
		mainPannel.add(label2);
		mainPannel.add(buttonPanel);

		LecturePanel lecturePanel = LecturePanel.createLecturePanel();
		StudentPanel studentPanel = StudentPanel.createStudentPanel();
		ProfessorPanel professorPanel = ProfessorPanel.createProfessorPanel();

		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.add(lecturePanel, "講義");
        cardPanel.add(studentPanel, "学生");
        cardPanel.add(professorPanel, "教員");

		Container contentPane = getContentPane();
		contentPane.add(mainPannel, BorderLayout.PAGE_START);
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
	public static void createHome() {
		Home home = new Home("講義管理システム"); // This panel is a super panel among all panel in the Home screen.
		home.setVisible(true);
	}
}