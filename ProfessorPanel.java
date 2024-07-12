import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Here, define the UI of Lecture List
public class ProfessorPanel extends JPanel{

	// public static LecturePanel createLecturePanel() {
	// 	LecturePanel lecturePanel = new LecturePanel();

    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {

    //         }
    //     });
	// 	return lecturePanel;
    // }

	public static ProfessorPanel createProfessorPanel() {
		ProfessorPanel professorPanel = new ProfessorPanel();

		DefaultListModel model = new DefaultListModel();
		// temporary data
		for (int i = 0; i < 100; i++) {
			model.addElement("Professor " + (i+1));
		}

		JList list = new JList(model);
		list.setCellRenderer(new ButtonRenderer());

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.createVerticalScrollBar();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(800, 800)); // Set your desired size here
		professorPanel.add(scrollPane);

		return professorPanel;
	}

	static class ButtonRenderer extends JPanel implements ListCellRenderer {
		JLabel label;
		JButton button;

		public ButtonRenderer() {
			setLayout(new BorderLayout());
			label = new JLabel();
			button = new JButton("詳細");
			button.setPreferredSize(new Dimension(100, 20));
			add(label, BorderLayout.CENTER);
			add(button, BorderLayout.EAST);
		}

		// implements the abstract method
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			label.setText((String) value);
			return this;
		}
	}
}
