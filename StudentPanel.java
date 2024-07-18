import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;

public class StudentPanel extends JPanel {
    static JTable table;
    public static StudentTableModel tableModel;

    public static StudentPanel createStudentPanel() {

        tableModel = new StudentTableModel(MyApp.students);
        table = new JTable(tableModel);

        // Add a button to the last column
        table.getColumn("Details").setCellRenderer(new ButtonRenderer());
        table.getColumn("Details").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 800));

        JButton buttonRegister = new JButton("register a new student");
        RegisterButtonAction registerButtonActionListener = new RegisterButtonAction();
        buttonRegister.addActionListener(registerButtonActionListener);

        JButton reloadButton = new JButton("Reload");
        ReloadButtonAction reloadButtonActionLister = new ReloadButtonAction();
        reloadButton.addActionListener(reloadButtonActionLister);

        StudentPanel studentPanel = new StudentPanel();
        studentPanel.add(reloadButton, BorderLayout.EAST);
        studentPanel.add(buttonRegister, BorderLayout.NORTH);
        studentPanel.add(scrollPane, BorderLayout.CENTER);

        return studentPanel;
    }

    public static class ReloadButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Update the table
            tableModel.setLectures(MyApp.students);
        }
    }

    public static class RegisterButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RegisterFormStudent.createRegisterFormStudent();
        }
    }

    static class StudentTableModel extends AbstractTableModel {
        private ArrayList<Student> students;
        private String[] columnNames = { "ID", "Name", "Details" };

        public StudentTableModel(ArrayList<Student> students) {
            this.students = students;
        }

        public int getRowCount() {
            return students.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public void setLectures(ArrayList<Student> students) {
            this.students = students;
            fireTableDataChanged(); // モデルの変更を通知してテーブルを再描画
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Student student = students.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return student.getStudentID();
                case 1:
                    return student.getName();
                default:
                    return "Details";
            }
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public boolean isCellEditable(int row, int col) {
            return col == 2; // Only the last column is editable
        }
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Show dialog with student details
                int row = table.getSelectedRow();
                Student student = ((StudentTableModel) table.getModel()).students.get(row);
                DetailStudentInformation.createDetailStudentInformation(student);
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}

/*
 * // Here, define the UI of Student List
 * public class StudentPanel extends JPanel{
 * static JList list;
 * 
 * // public static LecturePanel createLecturePanel() {
 * // LecturePanel lecturePanel = new LecturePanel();
 * 
 * // SwingUtilities.invokeLater(new Runnable() {
 * // public void run() {
 * 
 * // }
 * // });
 * // return lecturePanel;
 * // }
 * 
 * public static StudentPanel createStudentPanel() {
 * 
 * DefaultListModel model = new DefaultListModel();
 * // temporary data
 * for (int i = 0; i < 100; i++) {
 * model.addElement("Student " + (i+1));
 * }
 * 
 * list = new JList(model);
 * list.setCellRenderer(new ButtonRenderer());
 * list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 * list.addMouseListener(new MouseAdapter() {
 * public void mouseClicked(MouseEvent e) {
 * int index = list.locationToIndex(e.getPoint());
 * ButtonRenderer renderer = (ButtonRenderer) list.getCellRenderer();
 * Rectangle bounds = list.getCellBounds(index, index);
 * Point point = e.getPoint();
 * point.translate(-bounds.x, -bounds.y);
 * if (renderer.button.getBounds().contains(point)) {
 * renderer.button.doClick();
 * }
 * }
 * });
 * 
 * JScrollPane scrollPane = new JScrollPane(list);
 * scrollPane.createVerticalScrollBar();
 * scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.
 * VERTICAL_SCROLLBAR_ALWAYS);
 * scrollPane.setPreferredSize(new Dimension(800, 800)); // Set your desired
 * size here
 * scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
 * 
 * JButton buttonRegister = new JButton("Add new Student");
 * RegisterButtonAction registerButtonActionListener = new
 * RegisterButtonAction();
 * buttonRegister.addActionListener(registerButtonActionListener);
 * 
 * StudentPanel studentPanel = new StudentPanel();
 * //studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
 * studentPanel.add(buttonRegister, BorderLayout.CENTER);
 * studentPanel.add(scrollPane, BorderLayout.CENTER);
 * 
 * return studentPanel;
 * }
 * 
 * public static class RegisterButtonAction implements ActionListener {
 * public void actionPerformed(ActionEvent e) {
 * RegisterFormStudent.createRegisterFormStudent();
 * }
 * }
 * 
 * public static class ButtonRenderer extends JPanel implements ListCellRenderer
 * {
 * JLabel label;
 * JButton button;
 * 
 * public ButtonRenderer() {
 * setLayout(new BorderLayout());
 * label = new JLabel();
 * button = new JButton("Detail");
 * DetailButtonAction detailButtonActionListener = new DetailButtonAction();
 * button.addActionListener(detailButtonActionListener);
 * button.setPreferredSize(new Dimension(100, 20));
 * add(label, BorderLayout.CENTER);
 * add(button, BorderLayout.EAST);
 * }
 * 
 * // implements the abstract method
 * public Component getListCellRendererComponent(JList list, Object value, int
 * index, boolean isSelected, boolean cellHasFocus) {
 * label.setText((String) value);
 * return this;
 * }
 * 
 * class DetailButtonAction implements ActionListener {
 * public void actionPerformed(ActionEvent e) {
 * JOptionPane.showMessageDialog(list, "None selected!", "Error",
 * JOptionPane.INFORMATION_MESSAGE);
 * }
 * }
 * }
 * }
 */