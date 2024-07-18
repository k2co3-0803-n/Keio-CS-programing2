import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

// git stash test

public class DetailLectureInformation extends MyFrame {
	public DetailLectureInformation(String frameName, Lecture lecture) {
		super(frameName);
		JLabel idLabel = new JLabel("ID" + lecture.getLectureID());
		JLabel lectureNameLabel = new JLabel("講義名");
		JLabel classRoomLabel = new JLabel("教室");
		JLabel dayOfWeekLabel = new JLabel("曜日");
		JLabel periodLabel = new JLabel("時限");
		JLabel professorLabel = new JLabel("担当教員ID"); // 一旦IDで、あとでドロップダウン

		// TextField
		JTextField lectureName = new JTextField(lecture.getLectureName());
		JTextField classRoom = new JTextField(lecture.getClassRoom());
		JTextField dayOfWeek = new JTextField(lecture.getDayOfWeek());
		JTextField period = new JTextField(lecture.getPeriod());
		// 教員を変更する機構はまだ
		JTextField professorID = new JTextField(lecture.getProfessorInCharge());

		// TODO add JTable which shows the list of students taking this class.
		// StudentTableModel model = new
		// StudentTableModel(lecture.getEnrolledStudents());
		// JTable table = new JTable(model);
		// JScrollPane scrollPane = new JScrollPane(table);

		JButton editButton = new JButton("edit");
		EditButtonAction buttonListener = new EditButtonAction(lectureName, classRoom, dayOfWeek, period,
				lecture.getLectureID(), professorID);
		editButton.addActionListener(buttonListener);
		JButton deleteButtton = new JButton("delete");
		// pass lecture information to the action listener by using constructor
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(lecture);
		deleteButtton.addActionListener(deleteButtonActionListener);

		// dayofweekのcombobox
		// 曜日登録用コンボボックス
		JComboBox<DayOfWeek> dayOfWeekComboBox = new JComboBox<DayOfWeek>(MyApp.dayOfWeeks);
		dayOfWeekComboBox.setSelectedIndex(-1);
		dayOfWeekComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				DayOfWeek selectedDayOfWeek = (DayOfWeek) cb.getSelectedItem();
				dayOfWeek.setText(selectedDayOfWeek.toString());
			}
		});
		this.add(dayOfWeekComboBox);
		this.pack();
		this.setVisible(true);

		JPanel paneDayOfWeek = new JPanel();
		paneDayOfWeek.setLayout(new GridLayout(1, 0));
		paneDayOfWeek.add(dayOfWeekLabel);
		paneDayOfWeek.add(dayOfWeekComboBox);
		// dayofweekのcombobox終わり
		//

		// professorのCombobox
		// 教員登録用コンボボックス
		Professor[] professorArray = new Professor[MyApp.professors.size()];
		professorArray = MyApp.professors.toArray(professorArray);
		JComboBox<Professor> professorComboBox = new JComboBox<>(professorArray);
		professorComboBox.setSelectedIndex(-1);
		professorComboBox.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			Professor selectedProfessor = (Professor) cb.getSelectedItem();
			professorID.setText(selectedProfessor.getProfessorID());
		});
		this.add(professorComboBox);
		this.pack();
		this.setVisible(true);
		JPanel paneProfessor = new JPanel();
		paneProfessor.setLayout(new GridLayout(1, 0));
		paneProfessor.add(professorLabel);
		paneProfessor.add(professorComboBox);

		FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

		JPanel pane1 = new JPanel(centerLayout);
		pane1.setLayout(new GridLayout(2, 0));
		pane1.add(idLabel);
		pane1.setBackground(Color.red);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(lectureNameLabel);
		pane2.add(lectureName);
		JPanel pane3 = new JPanel(centerLayout);
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(classRoomLabel);
		pane3.add(classRoom);

		JPanel panePeriod = new JPanel(centerLayout);
		panePeriod.setLayout(new GridLayout(2, 0));
		panePeriod.add(periodLabel);
		panePeriod.add(period);
		// 教員登録用combobox終わり

		// JPanel pane5 = new JPanel(centerLayout);
		// pane5.setLayout(new GridLayout(2, 0));
		// pane5.add(professorLabel);
		// pane5.add(professorID);

		JPanel pane6 = new JPanel(centerLayout);
		pane6.setLayout(new GridLayout(2, 0));

		// taking students list
		String[][] taking_students = DB.selectStudentsByLecture(lecture.getLectureID());
		JPanel pane7 = new JPanel(centerLayout);
		pane7.setLayout(new GridLayout(0, 1));
		for (String[] student : taking_students) {
			JLabel studentLabel = new JLabel("ID: " + student[2] + " 氏名: " + student[3]);
			pane7.add(studentLabel);
		}

		pane6.add(editButton);
		pane6.add(deleteButtton);
		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(paneDayOfWeek);
		mainPane.add(panePeriod);
		mainPane.add(paneProfessor);
		// mainPane.add(scrollPane);
		mainPane.add(pane7);
		mainPane.add(pane6);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	class EditButtonAction implements ActionListener {
		private JTextField lectureName;
		private JTextField classRoom;
		private JTextField dayOfWeek;
		private JTextField period;

		private String lectureID;

		private JTextField professorID;

		public EditButtonAction(JTextField lectureName, JTextField classRoom, JTextField dayOfWeek, JTextField period,
				String lectureID, JTextField professorID) {
			this.lectureName = lectureName;
			this.classRoom = classRoom;
			this.dayOfWeek = dayOfWeek;
			this.period = period;
			this.lectureID = lectureID;
			this.professorID = professorID;
		}

		public void actionPerformed(ActionEvent e) {
			DB.updateLecture(Integer.parseInt(lectureID), lectureName.getText(), classRoom.getText(),
					DayOfWeek.convertIntToYoubi(dayOfWeek.getText()),
					period.getText());
			// 先生の変更はここに、別でDB.insertIntoTeachingLecturesを追記するのが良いと思う。ドロップダウンで、idを代入させるようにする
			if (!professorID.getText().equals("")) {
				DB.deleteFromTeachingLectures(Integer.parseInt(lectureID));
				DB.insertIntoTeachingLectures(professorID.getText(), Integer.parseInt(lectureID));
			}
			if (professorID.getText().equals("")) {
				DB.deleteFromTeachingLectures(Integer.parseInt(lectureID));
			}
			MyApp.initdata();
			dispose();
		}
	}

	class DeleteButtonAction implements ActionListener {
		private Lecture lecture; // 講義情報を保持するフィールド

		// コンストラクタで講義情報を受け取る
		public DeleteButtonAction(Lecture lecture) {
			this.lecture = lecture;
		}

		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(null, "本当にこの講義を削除しますか？", "確認", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				DB.deleteFromLectures(Integer.parseInt(lecture.getLectureID()));
				MyApp.initdata();
				dispose();
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

		private void deleteLecture() {
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

	public static void createDetailLectureInformation(Lecture lecture) {
		DetailLectureInformation detailLectureInformation = new DetailLectureInformation("Detail Lecture Information",
				lecture);
		detailLectureInformation.setVisible(true);
	}
}
