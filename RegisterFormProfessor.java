import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Here, define UI of Register form of new professors
public class RegisterFormProfessor extends MyFrame {
    JTextField title;
    JTextField professorName;
    JTextField professor_id;

    class ButtonAction implements ActionListener {
        private JTextField professor_id;
        private JTextField professorName;

        public ButtonAction(JTextField professor_id, JTextField professorName) {
            this.professor_id = professor_id;
            this.professorName = professorName;
        }

        public void actionPerformed(ActionEvent e) {
            DB.insertIntoTeacher(professor_id.getText(), professorName.getText());
            MyApp.initData();
            dispose();
        }
    }

    // Constructor
    public RegisterFormProfessor(String frameName) {
        super(frameName);
        JLabel titleLabel = new JLabel("add a new professor");
        JLabel professorNameLabel = new JLabel("name");
        JLabel professor_idLabel = new JLabel("Professor ID");

        professorName = new JTextField();
        professor_id = new JTextField();

        JButton addButton = new JButton("add");
        ButtonAction buttonListener = new ButtonAction(professor_id, professorName);
        addButton.addActionListener(buttonListener);

        JPanel pane1 = new JPanel();
        pane1.add(titleLabel);
        JPanel pane2 = new JPanel();
        pane2.setLayout(new GridLayout(2, 0));
        pane2.add(professorNameLabel);
        pane2.add(professorName);
        JPanel pane3 = new JPanel();
        pane3.setLayout(new GridLayout(2, 0));
        pane3.add(professor_idLabel);
        pane3.add(professor_id);

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new GridLayout(0, 1));
        mainPane.add(pane1);
        mainPane.add(pane2);
        mainPane.add(pane3);
        mainPane.add(addButton);

        this.getContentPane().add(mainPane, BorderLayout.CENTER);
        this.setSize(500, 600);
    }

    public static void createRegisterFormProfessor() {
        RegisterFormProfessor registerFormProfessor = new RegisterFormProfessor("add a new professor");
        registerFormProfessor.setVisible(true);
    }
}
