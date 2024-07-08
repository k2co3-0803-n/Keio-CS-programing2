import javax.swing.JFrame;

// Here, defining the very basic information of frame.
// The reason of the existance of this class is, later we will create a lot of the same JFrame.
// Here, inherit JFrame as a super class.
public class MyFrame extends JFrame {
	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,600);
		this.setLocationRelativeTo(null); // Here, instruct the location of the window.
		setVisible(true);
	}
}