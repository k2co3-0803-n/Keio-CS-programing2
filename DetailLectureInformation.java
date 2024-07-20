import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// git stash test

public class DetailLectureInformation extends MyFrame {
	public DetailLectureInformation(String frameName, Lecture lecture) {
		super(frameName);
		JLabel idLabel = new JLabel("ID: " + lecture.getLectureID());
		JLabel lectureNameLabel = new JLabel("Lecture Name");
		JLabel classRoomLabel = new JLabel("Classroom");
		JLabel dayOfWeekLabel = new JLabel("Day of Week");
		JLabel periodLabel = new JLabel("Period");
		JLabel professorLabel = new JLabel("Professor ID");

		// TextField
		JTextField lectureName = new JTextField(lecture.getLectureName());
		JTextField classRoom = new JTextField(lecture.getClassRoom());
		JTextField dayOfWeek = new JTextField(lecture.getDayOfWeek());
		JTextField period = new JTextField(lecture.getPeriod());
		JTextField professorID = new JTextField(lecture.getProfessorInCharge());

		JButton editButton = new JButton("edit");
		EditButtonAction buttonListener = new EditButtonAction(lectureName, classRoom, dayOfWeek, period,
				lecture.getLectureID(), professorID);
		editButton.addActionListener(buttonListener);
		JButton deleteButtton = new JButton("delete");
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(lecture);
		deleteButtton.addActionListener(deleteButtonActionListener);

		// dayofweek combobox
		// Combo box for registering day of week
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
		// end of dayofweek combobox
		//

		// professor Combobox
		// Combo box for registering professor
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
		pane1.setLayout(new GridLayout(1, 0));
		pane1.add(idLabel);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(lectureNameLabel);
		pane2.add(lectureName);
		JPanel pane3 = new JPanel(centerLayout);
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(classRoomLabel);
		pane3.add(classRoom);

		JPanel panePeriod = new JPanel(centerLayout);
		panePeriod.setLayout(new GridLayout(1, 0));
		panePeriod.add(periodLabel);
		panePeriod.add(period);
		// end of professor registration combobox

		// taking students list
		String[][] taking_students = DB.selectStudentsByLecture(lecture.getLectureID());
		JPanel pane6 = new JPanel(centerLayout);
		pane6.setLayout(new GridLayout(0, 1));
		JLabel studentLabelTitle = new JLabel("Enrolled Students");
		pane6.add(studentLabelTitle);
		// for (String[] student : taking_students) {
		// 	JLabel studentLabel = new JLabel("ID: " + student[2] + " Name: " + student[3]);
		// 	pane7.add(studentLabel);
		// }
		Object[][] data = new Object[taking_students.length][2];
		for (int i = 0; i < taking_students.length; i++) {
			data[i][0] = taking_students[i][2];
			data[i][1] = taking_students[i][3];
		}

		String[] columnNames = {"ID", "Name"};

		JTable table = new JTable(data, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		pane6.add(scrollPane);

		int rowHeight = table.getRowHeight();
		int visibleRowCount = 5;
		int tableHeight = (visibleRowCount * rowHeight) + table.getIntercellSpacing().height;

		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, tableHeight));

		pane6.revalidate();
		pane6.repaint();

		JPanel pane7 = new JPanel(centerLayout);
		pane7.setLayout(new GridLayout(2, 0));
		pane7.add(editButton);
		pane7.add(deleteButtton);

		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(paneDayOfWeek);
		mainPane.add(panePeriod);
		mainPane.add(paneProfessor);
		mainPane.add(pane6);
		mainPane.add(pane7);

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
			// Changes to the professor are here, it's good to add
			// DB.insertIntoTeachingLectures separately. Use the dropdown to assign the id
			if (!professorID.getText().equals("")) {
				DB.deleteFromTeachingLectures(Integer.parseInt(lectureID));
				DB.insertIntoTeachingLectures(professorID.getText(), Integer.parseInt(lectureID));
			}
			if (professorID.getText().equals("")) {
				DB.deleteFromTeachingLectures(Integer.parseInt(lectureID));
			}
			MyApp.initData();
			dispose();
		}
	}

	class DeleteButtonAction implements ActionListener {
		private Lecture lecture; // Field to hold lecture information

		// Constructor to receive lecture information
		public DeleteButtonAction(Lecture lecture) {
			this.lecture = lecture;
		}

		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this lecture?",
					"Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				DB.deleteFromLectures(Integer.parseInt(lecture.getLectureID()));
				MyApp.initData();
				dispose();
			}
		}
	}

	public static void createDetailLectureInformation(Lecture lecture) {
		DetailLectureInformation detailLectureInformation = new DetailLectureInformation("Detail Lecture Information",
				lecture);
		detailLectureInformation.setVisible(true);
	}
}
