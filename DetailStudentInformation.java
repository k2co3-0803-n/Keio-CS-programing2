import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

		JLabel registerLectureIdLabel = new JLabel("Courses to register for: ");
		JTextField registerLectureId = new JTextField();

		JLabel unregisterLectureIdLabel = new JLabel("Courses to drop: ");
		JTextField unregisterLectureId = new JTextField();

		String[][] taking_lectures = DB.selectLecturesByStudent(student.getStudentID());

		JButton editButton = new JButton("edit");
		EditButtonAction editButtonListener = new EditButtonAction(student.getStudentID(), studentName,
				registerLectureId, unregisterLectureId);
		editButton.addActionListener(editButtonListener);
		JButton deleteButton = new JButton("delete");
		DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(student);
		deleteButton.addActionListener(deleteButtonActionListener);

		FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

		JPanel pane1 = new JPanel(centerLayout);
		pane1.setLayout(new GridLayout(1, 0));
		pane1.add(idLabel);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(1, 0));
		pane2.add(studentNameLabel);
		pane2.add(studentName);

		// taking lectures list
		JPanel pane3 = new JPanel(centerLayout);
		JLabel takingLecturesLabel = new JLabel("Taking Lectures: ");
		pane3.add(takingLecturesLabel);
		pane3.setLayout(new GridLayout(0, 1));
		for (String[] lecture : taking_lectures) {
			JLabel lectureLabel = new JLabel("ID: " + lecture[0] + " Lecture Name: " + lecture[1]);
			pane3.add(lectureLabel);
		}

		// register lecture combobox
		Lecture[] lectureArray = new Lecture[MyApp.lectures.size()];
		lectureArray = MyApp.lectures.toArray(lectureArray);
		JComboBox<Lecture> registerLectureComboBox = new JComboBox<>(lectureArray);
		registerLectureComboBox.setSelectedIndex(-1);

		registerLectureComboBox.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			Lecture selectedLecture = (Lecture) cb.getSelectedItem();
			registerLectureId.setText(selectedLecture.getLectureID());
		});
		this.add(registerLectureComboBox);
		this.pack();
		this.setVisible(true);

		JPanel paneRegister = new JPanel(centerLayout);
		paneRegister.setLayout(new GridLayout(1, 0));
		paneRegister.add(registerLectureIdLabel);
		paneRegister.add(registerLectureComboBox);

		// end of register lecture combobox

		// unregister lecture combobox
		class SubLecture {
			private String lectureID;
			private String lectureName;

			public SubLecture(String lectureID, String lectureName) {
				this.lectureID = lectureID;
				this.lectureName = lectureName;
			}

			@Override
			public String toString() {
				return this.lectureName;
			}
		}

		// registered lecture combobox
		String[][] registeredLectures = DB.selectLecturesByStudent(student.getStudentID());
		SubLecture[] registeredLectureFormatArray = new SubLecture[registeredLectures.length];
		for (int i = 0; i < registeredLectures.length; i++) {
			SubLecture subLecture = new SubLecture(registeredLectures[i][0], registeredLectures[i][1]);
			registeredLectureFormatArray[i] = subLecture;
		}

		JComboBox<SubLecture> unregisterLectureComboBox = new JComboBox<>(registeredLectureFormatArray);
		unregisterLectureComboBox.setSelectedIndex(-1);
		unregisterLectureComboBox.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			SubLecture selectedLecture = (SubLecture) cb.getSelectedItem();
			unregisterLectureId.setText(selectedLecture.lectureID);
		});
		this.add(unregisterLectureComboBox);
		this.pack();
		this.setVisible(true);
		JPanel paneUnregister = new JPanel(centerLayout);
		paneUnregister.setLayout(new GridLayout(1, 0));
		paneUnregister.add(unregisterLectureIdLabel);
		paneUnregister.add(unregisterLectureComboBox);

		// end of register lecture combobox

		JPanel pane6 = new JPanel(centerLayout);
		pane6.setLayout(new GridLayout(2, 0));
		pane6.add(editButton);
		pane6.add(deleteButton);
		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(paneRegister);
		mainPane.add(paneUnregister);
		mainPane.add(pane6);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	class EditButtonAction implements ActionListener {
		private String id;
		private JTextField nameField;
		private JTextField registerLectureId;
		private JTextField unregisterLectureId;

		public EditButtonAction(String id, JTextField nameField, JTextField registerLectureId,
				JTextField unregisterLectureId) {
			this.id = id;
			this.nameField = nameField;
			this.registerLectureId = registerLectureId;
			this.unregisterLectureId = unregisterLectureId;
		}

		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			DB.updateStudent(id, name);
			if (!registerLectureId.getText().equals("")) {
				DB.insertIntoTakingLectures(id, Integer.parseInt(registerLectureId.getText()));
			}
			if (!unregisterLectureId.getText().equals("")) {
				DB.deleteFromTakingLectures(id, Integer.parseInt(unregisterLectureId.getText()));
			}
			MyApp.initData();
			dispose();
		}
	}

	class DeleteButtonAction implements ActionListener {
		private Student student;

		public DeleteButtonAction(Student student) {
			this.student = student;
		}

		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?",
					"Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				DB.deleteFromStudents(student.getStudentID());
				MyApp.initData();
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
