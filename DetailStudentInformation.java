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

public class DetailStudentInformation extends MyFrame {
	public DetailStudentInformation(String frameName, Student student) {
		super(frameName);
		JLabel idLabel = new JLabel("Student ID: " + student.getStudentID());
		JLabel studentNameLabel = new JLabel("Student Name: " + student.getName());

		String[][] taking_lectures = DB.selectLecturesByStudent(student.getStudentID());

		// TODO add JTable which shows the list of lectures the student is taking.

		JButton editButton = new JButton("edit");
		ButtonAction buttonListener = new ButtonAction();
		editButton.addActionListener(buttonListener);
		JButton deleteButton = new JButton("delete");
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(student);
		deleteButton.addActionListener(deleteButtonActionListener);

		FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

		JPanel pane1 = new JPanel(centerLayout);
		pane1.setLayout(new GridLayout(2, 0));
		pane1.add(idLabel);
		pane1.setBackground(Color.red);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(studentNameLabel);

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

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
				deleteStudent();
			}
		}

		// static class StudentTableModel extends AbstractTableModel {
		// private List<Student> students;
		// private String[] columnNames = {"ID", "Name"};

		// // Constructor
		// public StudentTableModel(ArrayList<Student> students) {
		// this.students = students;
		// }

		// public int getRowCount() {
		// return students.size();
		// }

		// public int getColumnCount() {
		// return columnNames.length;
		// }

		// public Object getValueAt(int rowIndex, int columnIndex) {
		// Student student = students.get(rowIndex);
		// switch (columnIndex) {
		// case 0:
		// return student.getStudentID();
		// case 1:
		// return student.getStudentName();
		// default:
		// return "Details";
		// }
		// }

		// public String getColumnName(int column) {
		// return columnNames[column];
		// }
		// }

		private void deleteStudent() {
			// Database.deleteLecture(lecture.getLectureID());

			// String lectureID = lecture.getLectureID()
			// int rowIndex = -1; // 初期値は見つからないことを示す-1
			// for (int i = 0; i < tableModel.getRowCount(); i++) {
			// if (tableModel.getValueAt(i, idColumnIndex).equals(lectureID)) {
			// rowIndex = i;
			// break;
			// }
			// }
			// if (rowIndex == -1) {
			// return;
			// }

			// if (rowIndex != -1) {
			// tableModel.removeRow(rowIndex);
			// table.repaint();
			dispose();
			// }
		}
	}

	public static void createDetailStudentInformation(Student student) {
		DetailStudentInformation detailStudentInformation = new DetailStudentInformation("Detail Student Information",
				student);
		detailStudentInformation.setVisible(true);
	}
}
