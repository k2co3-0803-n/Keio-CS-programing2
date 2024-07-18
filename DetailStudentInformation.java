import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;

public class DetailStudentInformation extends MyFrame {
	public DetailStudentInformation(String frameName, Student student) {
		super(frameName);
		JLabel idLabel = new JLabel("Student ID: " + student.getStudentID());
		JLabel studentNameLabel = new JLabel("Student Name: ");
		JTextField studentName = new JTextField(student.getName());

		String[][] taking_lectures = DB.selectLecturesByStudent(student.getStudentID());

		// TODO add JTable which shows the list of lectures the student is taking.

		JButton editButton = new JButton("edit");
		EditButtonAction editButtonListener = new EditButtonAction(student.getStudentID(), studentName);
		editButton.addActionListener(editButtonListener);
		JButton deleteButton = new JButton("delete");
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(student);
		deleteButton.addActionListener(deleteButtonActionListener);

		FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

		JPanel pane1 = new JPanel(centerLayout);
		pane1.setLayout(new GridLayout(1, 0));
		pane1.add(idLabel);
		pane1.setBackground(Color.red);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(1, 0));
		pane2.add(studentNameLabel);
		pane2.add(studentName);

		JPanel pane3 = new JPanel(centerLayout);
		pane3.setLayout(new GridLayout(0, 1));
		for (String[] lecture : taking_lectures) {
			JLabel lectureLabel = new JLabel("ID: " + lecture[0] + " 講義名: " + lecture[1]);
			pane3.add(lectureLabel);
		}

		JPanel pane6 = new JPanel(centerLayout);
		pane6.setLayout(new GridLayout(2, 0));
		pane6.add(editButton);
		pane6.add(deleteButton);
		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(pane6);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	class EditButtonAction implements ActionListener {
		private String id;
		private JTextField nameField;

		public EditButtonAction(String id, JTextField nameField) {
			this.id = id;
			this.nameField = nameField;
		}

		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			System.out.println(id + name + "で更新する！！！");
			DB.updateStudent(id, name);
			MyApp.initdata();
			dispose();
		}
	}

	class DeleteButtonAction implements ActionListener {
		private Student student; // 講義情報を保持するフィールド

		// コンストラクタで講義情報を受け取る
		public DeleteButtonAction(Student student) {
			this.student = student;
		}

		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(null, "本当にこの生徒を削除しますか？", "確認", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				DB.deleteFromStudents(student.getStudentID());
				MyApp.initdata();
				dispose();
			}
		}

	}

	public static void createDetailStudentInformation(Student student) {
		DetailStudentInformation detailStudentInformation = new DetailStudentInformation("Detail Student Information",
				student);
		detailStudentInformation.setVisible(true);
	}
}
