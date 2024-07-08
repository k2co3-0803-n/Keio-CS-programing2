import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Here, define the UI of Lecture List
public class LectureList {
	private DefaultListModel<String> listModel;
	private JList<String> list;

	// Here, create component of Home UI
	public Component createComponent() {
		listModel = new DefaultListModel<String>(); // logical list

		list = new JList<String>(listModel);
		list.setVisibleRowCount(15);

		JScrollPane scrollPanel = new JScrollPane(list);
		scrollPanel.createVerticalScrollBar();
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// テスト部分
		JLabel label1 = new JLabel("講義リスト");

		JPanel mainPanel = new JPanel();
		mainPanel.add(label1);
		mainPanel.add(scrollPanel);

		return mainPanel;
	}
	public static void createLectureList() {
		
	}
}
