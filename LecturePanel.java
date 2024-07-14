import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Here, define the UI of Lecture List
public class LecturePanel extends JPanel{
	static JList list;

	// class method
	public static LecturePanel createLecturePanel() {
		DefaultListModel model = new DefaultListModel();

		// temporary data
		for (int i = 0; i < 100; i++) {
			model.addElement("Lecure " + (i+1));
		}

		list = new JList(model);
		list.setCellRenderer(new ButtonRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				ButtonRenderer renderer = (ButtonRenderer) list.getCellRenderer();
				Rectangle bounds = list.getCellBounds(index, index);
				Point point = e.getPoint();
				point.translate(-bounds.x, -bounds.y);
				if (renderer.button.getBounds().contains(point)) {
					renderer.button.doClick();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.createVerticalScrollBar();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(800, 800)); // Set your desired size here
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton buttonRegister = new JButton("新しい講義を追加");
		RegisterButtonAction registerButtonActionListener = new RegisterButtonAction();
		buttonRegister.addActionListener(registerButtonActionListener);

		LecturePanel lecturePanel = new LecturePanel();
		//lecturePanel.setLayout(new BoxLayout(lecturePanel, BoxLayout.Y_AXIS));
		lecturePanel.add(buttonRegister, BorderLayout.CENTER);
		lecturePanel.add(scrollPane, BorderLayout.CENTER);

		return lecturePanel;
	}

	public static class RegisterButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RegisterFormLecture.createRegisterFormLecture();
		}
	}

	public static class ButtonRenderer extends JPanel implements ListCellRenderer {
		JLabel label;
		JButton button;

		public ButtonRenderer() {
			setLayout(new BorderLayout());
			label = new JLabel();
			button = new JButton("詳細");
			DetailButtonAction detailButtonActionListener = new DetailButtonAction();
			button.addActionListener(detailButtonActionListener);
			button.setPreferredSize(new Dimension(100, 20));
			add(label, BorderLayout.CENTER);
			add(button, BorderLayout.EAST);
		}

		// implements the abstract method(must)
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			label.setText(value.toString());
			return this;
		}

		class DetailButtonAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(list, "None selected!", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
