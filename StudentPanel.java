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
        studentPanel.add(buttonRegister, BorderLayout.NORTH);
        studentPanel.add(reloadButton, BorderLayout.EAST);
        studentPanel.add(scrollPane, BorderLayout.CENTER);

        return studentPanel;
    }

    public static class ReloadButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tableModel.setLectures(MyApp.students);
        }
    }

    public static class RegisterButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RegisterFormStudent.createRegisterFormStudent(() -> {
                tableModel.setLectures(MyApp.students);
            });
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
            // flush the table
            fireTableDataChanged();
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
            return col == 2;
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
