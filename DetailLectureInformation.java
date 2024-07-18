import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class DetailLectureInformation extends MyFrame  {
	public DetailLectureInformation(String frameName, Lecture lecture) {
		super(frameName);
		JLabel idLabel = new JLabel("Lecture ID: " + lecture.getLectureID());
		JLabel lectureNameLabel = new JLabel("Lecture Name: " + lecture.getLectureName());
		JLabel classRoomLabel = new JLabel("Lecture Room: " + lecture.getClassRoom());
		JLabel dayAndTimeLabel = new JLabel("Lecture Time: " + lecture.getDayOfWeek() + lecture.getPeriod());
		JLabel professorLabel = new JLabel("Lecture Professor: " + lecture.getProfessorInCharge());

		// TODO add JTable which shows the list of students taking this class.
		// StudentTableModel model = new StudentTableModel(lecture.getEnrolledStudents());
		// JTable table = new JTable(model);
		// JScrollPane scrollPane = new JScrollPane(table);

		JButton editButton = new JButton("edit");
		EditButtonAction buttonListener = new EditButtonAction();
		editButton.addActionListener(buttonListener);
		JButton deleteButtton = new JButton("delete");
		// pass lecture information to the action listener by using constructor
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(lecture);
		deleteButtton.addActionListener(deleteButtonActionListener);

		FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

		JPanel pane1 = new JPanel(centerLayout);
		pane1.setLayout(new GridLayout(2, 0));
		pane1.add(idLabel);
		pane1.setBackground(Color.red);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(lectureNameLabel);
		JPanel pane3 = new JPanel(centerLayout);
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(classRoomLabel);
		JPanel pane4 = new JPanel(centerLayout);
		pane4.setLayout(new GridLayout(2, 0));
		pane4.add(dayAndTimeLabel);
		JPanel pane5 = new JPanel(centerLayout);
		pane5.setLayout(new GridLayout(2, 0));
		pane5.add(professorLabel);
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
		mainPane.add(pane4);
		mainPane.add(pane5);
		// mainPane.add(scrollPane);
		mainPane.add(pane7);
		mainPane.add(pane6);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	class EditButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
