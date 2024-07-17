import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class DetailLectureInformation extends MyFrame{
	public DetailLectureInformation(String frameName, Lecture lecture) {
		super(frameName);
		JLabel idLabel = new JLabel("Lecture ID: " + lecture.getLectureID());
		JLabel lectureNameLabel = new JLabel("Lecture Name: " + lecture.getLectureName());
		JLabel classRoomLabel = new JLabel("Lecture Room: " + lecture.getClassRoom());
		JLabel dayAndTimeLabel = new JLabel("Lecture Time: " + lecture.getLectureDayAndTime());
		JLabel professorLabel = new JLabel("Lecture Professor: " + lecture.getProfessorInCharge());

		// TODO add JTable which shows the list of students taking this class.

		JButton editButton = new JButton("edit");
		ButtonAction buttonListener = new ButtonAction();
		editButton.addActionListener(buttonListener);
		JButton deleteButtton = new JButton("delete");
		deleteButtton.addActionListener(buttonListener);

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
		pane6.add(editButton);
		pane6.add(deleteButtton);
		JPanel mainPane = new JPanel(centerLayout);
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(pane4);
		mainPane.add(pane5);
		mainPane.add(pane6);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	public static void createDetailLectureInformation(Lecture lecture) {
		DetailLectureInformation detailLectureInformation = new DetailLectureInformation("Detail Lecture Information", lecture);
		detailLectureInformation.setVisible(true);
	}
}
