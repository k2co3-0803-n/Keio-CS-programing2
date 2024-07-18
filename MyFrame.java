import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame(String frameName) {
		setTitle(frameName);
		setSize(900,600);
		setLocationRelativeTo(null); // Here, instruct the location of the window.
	}
}