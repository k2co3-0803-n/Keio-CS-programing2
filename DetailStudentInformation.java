import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class DetailStudentInformation extends MyFrame {
    public DetailStudentInformation(String frameName, Student student) {
        super(frameName);
        JLabel idLabel = new JLabel("Student ID: " + student.getStudentID());
        JLabel studentNameLabel = new JLabel("Student Name: " + student.getName());

        // TODO add JTable which shows the list of lectures the student is taking.

        JButton editButton = new JButton("edit");
        ButtonAction buttonListener = new ButtonAction();
        editButton.addActionListener(buttonListener);
        JButton deleteButton = new JButton("delete");
        deleteButton.addActionListener(buttonListener);

        FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel pane1 = new JPanel(centerLayout);
        pane1.setLayout(new GridLayout(2, 0));
        pane1.add(idLabel);
        pane1.setBackground(Color.red);
        JPanel pane2 = new JPanel(centerLayout);
        pane2.setLayout(new GridLayout(2, 0));
        pane2.add(studentNameLabel);
        JPanel pane6 = new JPanel(centerLayout);
        pane6.setLayout(new GridLayout(2, 0));
        pane6.add(editButton);
        pane6.add(deleteButton);
        JPanel mainPane = new JPanel(centerLayout);
        mainPane.setLayout(new GridLayout(0, 1));
        mainPane.add(pane1);
        mainPane.add(pane2);
        mainPane.add(pane6);

        this.getContentPane().add(mainPane, BorderLayout.CENTER);
        this.setSize(500, 600);
    }

    class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public static void createDetailStudentInformation(Student student) {
        DetailStudentInformation detailStudentInformation = new DetailStudentInformation("Detail Student Information", student);
        detailStudentInformation.setVisible(true);
    }
}
