import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class DetailProfessorInformation extends MyFrame {
    public DetailProfessorInformation(String frameName, Professor professor) {
        super(frameName);
        JLabel idLabel = new JLabel("Professor ID: " + professor.getProfessorID());
        JLabel professorNameLabel = new JLabel("Professor Name: " + professor.getName());

        // TODO add JTable which shows the list of lectures the professor is taking.

        JButton editButton = new JButton("edit");
        ButtonAction buttonListener = new ButtonAction();
        editButton.addActionListener(buttonListener);
        JButton deleteButton = new JButton("delete");
        DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(professor);
        deleteButton.addActionListener(deleteButtonActionListener);

        FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel pane1 = new JPanel(centerLayout);
        pane1.setLayout(new GridLayout(2, 0));
        pane1.add(idLabel);
        pane1.setBackground(Color.red);
        JPanel pane2 = new JPanel(centerLayout);
        pane2.setLayout(new GridLayout(2, 0));
        pane2.add(professorNameLabel);
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

    class DeleteButtonAction implements ActionListener {
        private Professor professor; // 講義情報を保持するフィールド

        // コンストラクタで講義情報を受け取る
        public DeleteButtonAction(Professor professor) {
            this.professor = professor;
        }

        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "本当にこの教授を削除しますか？", "確認", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                DB.deleteFromTeacher(professor.getProfessorID());
                MyApp.initdata();
                dispose();
            }
        }
    }

    public static void createDetailProfessorInformation(Professor professor) {
        DetailProfessorInformation detailProfessorInformation = new DetailProfessorInformation(
                "Detail Professor Information", professor);
        detailProfessorInformation.setVisible(true);
    }
}
