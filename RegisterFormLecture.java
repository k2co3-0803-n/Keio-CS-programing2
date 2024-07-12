import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Here, define UI of Register form of new Lectures
public class RegisterFormLecture {
	private static String labelPrefix = "New String: ";
	private JLabel label;
	private JTextField text;

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText(labelPrefix + text.getText());
		}
	}
	public Component createComponents() {
		JButton button = new JButton("Move!");
		label = new JLabel(labelPrefix);
		text = new JTextField();

		ButtonAction buttonListener = new ButtonAction();
		button.addActionListener(buttonListener);

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		pane.setLayout(new GridLayout(0, 1));
		pane.add(text);
		pane.add(button);
		pane.add(label);

		return pane;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Prob103");
		Prob103 app = new Prob103();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
}
