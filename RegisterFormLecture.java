import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Here, define UI of Register form of new Lectures
public class RegisterFormLecture extends MyFrame {
	JTextField title;
	JTextField id;
	JTextField lectureName;
	JTextField classRoom;
	JTextField dayAndTime;
	JTextField professor;

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	// Constructor
	public RegisterFormLecture(String frameName) {
		super(frameName);
		JLabel titleLabel = new JLabel("新しい講義を追加");
		JLabel idLabel = new JLabel("講義ID");
		JLabel lectureNameLabel = new JLabel("講義名");
		JLabel classRoomLabel = new JLabel("講義教室");
		JLabel dayAndTimeLabel = new JLabel("講義時間");
		JLabel professorLabel = new JLabel("担当教員");

		id = new JTextField();
		lectureName = new JTextField();
		classRoom = new JTextField();
		dayAndTime = new JTextField();
		professor = new JTextField();

		JButton addButton = new JButton("追加");
		ButtonAction buttonListener = new ButtonAction();
		addButton.addActionListener(buttonListener);

		JPanel pane1 = new JPanel();
		pane1.add(titleLabel);
		JPanel pane2 = new JPanel();
		pane2.setLayout(new GridLayout(2, 0));
		pane2.add(idLabel);
		pane2.add(id);
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(lectureNameLabel);
		pane3.add(lectureName);
		JPanel pane4 = new JPanel();
		pane4.setLayout(new GridLayout(2, 0));
		pane4.add(classRoomLabel);
		pane4.add(classRoom);
		JPanel pane5 = new JPanel();
		pane5.setLayout(new GridLayout(2, 0));
		pane5.add(dayAndTimeLabel);
		pane5.add(dayAndTime);
		JPanel pane6 = new JPanel();
		pane6.setLayout(new GridLayout(2, 0));
		pane6.add(professorLabel);
		pane6.add(professor);
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(pane4);
		mainPane.add(pane5);
		mainPane.add(pane6);
		mainPane.add(addButton);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}
	public static void createRegisterFormLecture() {
		RegisterFormLecture registerFormLecture = new RegisterFormLecture("新しい講義を追加");
		registerFormLecture.setVisible(true);
	}
}
