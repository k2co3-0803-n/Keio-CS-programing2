import javax.swing.JFrame;

// Here, defining the very basic information of frame.
// The reason of the existance of this class is, later we will create a lot of the same JFrame.
// MyFrame class inherits JFrame as a super class.
public class MyFrame extends JFrame {
	public MyFrame(String frameName) {
		setTitle(frameName);
		setSize(900,600);
		setLocationRelativeTo(null); // Here, instruct the location of the window.
	}
}