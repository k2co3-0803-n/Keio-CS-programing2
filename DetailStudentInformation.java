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

		// 履修登録用のテキストフィールド.あとでドロップダウンにするかも
		JLabel registerLectureIdLabel = new JLabel("履修登録する講義ID: ");
		JTextField registerLectureId = new JTextField();

		// 履修解除用のテキストフィールド.あとでドロップダウンにするかも
		JLabel unregisterLectureIdLabel = new JLabel("履修解除する講義ID: ");
		JTextField unregisterLectureId = new JTextField();

		String[][] taking_lectures = DB.selectLecturesByStudent(student.getStudentID());

		// TODO add JTable which shows the list of lectures the student is taking.

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
		pane1.setBackground(Color.red);
		JPanel pane2 = new JPanel(centerLayout);
		pane2.setLayout(new GridLayout(1, 0));
		pane2.add(studentNameLabel);
		pane2.add(studentName);

		// taking lectures list
		JPanel pane3 = new JPanel(centerLayout);
		pane3.setLayout(new GridLayout(0, 1));
		for (String[] lecture : taking_lectures) {
			JLabel lectureLabel = new JLabel("ID: " + lecture[0] + " 講義名: " + lecture[1]);
			pane3.add(lectureLabel);
		}

		// JPanel paneRegister = new JPanel(centerLayout);
		// paneRegister.setLayout(new GridLayout(1, 0));
		// paneRegister.add(registerLectureIdLabel);
		// paneRegister.add(registerLectureId);

		// 登録用コンボボックス
		// registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		paneRegister.add(registerLectureComboBox);

		// コンボボックス終わり
		//
		// 履修解除用コンボボックス
		// registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// registeredLectureArray = MyApp.lectures.toArray(registeredLectureArray);
		// DB.selectLecturesByStudent(student.getStudentID());を使って、履修中の講義リストをString[][]で取得
		// それを使って、JComboBoxを作成
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

		// 履修中の授業リストを作成
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
		paneUnregister.add(unregisterLectureComboBox);

		// 履修解除用コンボボックス終わり

		// JPanel paneUnregister = new JPanel(centerLayout);
		// paneUnregister.setLayout(new GridLayout(1, 0));
		// paneUnregister.add(unregisterLectureIdLabel);
		// paneUnregister.add(unregisterLectureId);

		JPanel pane6 = new JPanel(centerLayout);
		pane6.setLayout(new GridLayout(2, 0));
		pane6.add(editButton);
		pane6.add(deleteButton);
		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		// mainPane.add(paneRegister);
		// comboboxを追加する
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
