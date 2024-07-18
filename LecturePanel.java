import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;

public class LecturePanel extends JPanel {
    static JTable table;
    public static LectureTableModel tableModel;

    // Class method
    // This method will be called from Home.java later
    public static LecturePanel createLecturePanel() {
        tableModel = new LectureTableModel(MyApp.lectures);
        table = new JTable(tableModel);

        // Add a button to the last column
        table.getColumn("Details").setCellRenderer(new ButtonRenderer());
        table.getColumn("Details").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Add a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 800));

        // Add a button to register a new lecture
        JButton buttonRegister = new JButton("register a new lecture");
        RegisterButtonAction registerButtonActionListener = new RegisterButtonAction();
        buttonRegister.addActionListener(registerButtonActionListener);

        JButton reloadButton = new JButton("Reload");
        ReloadButtonAction reloadButtonActionLister = new ReloadButtonAction();
        reloadButton.addActionListener(reloadButtonActionLister);

        // Create a panel
        LecturePanel lecturePanel = new LecturePanel();
        lecturePanel.add(reloadButton, BorderLayout.EAST);
        lecturePanel.add(buttonRegister, BorderLayout.NORTH);
        lecturePanel.add(scrollPane, BorderLayout.CENTER);

        return lecturePanel;
    }

    public static class ReloadButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Update the table
            tableModel.setLectures(MyApp.lectures);
        }
    }

    // ActionListener for the button to register a new lecture
    static class RegisterButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RegisterFormLecture.createRegisterFormLecture();
        }
    }

    static class LectureTableModel extends AbstractTableModel {
        private ArrayList<Lecture> lectures;
        private String[] columnNames = { "ID", "Title", "Room", "Day and Time", "Professor", "Details" };

        // Constructor
        public LectureTableModel(ArrayList<Lecture> lectures) {
            this.lectures = lectures;
        }

        public int getRowCount() {
            return lectures.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public void setLectures(ArrayList<Lecture> lectures) {
            this.lectures = lectures;
            fireTableDataChanged(); // Notify the model change and redraw the table
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Lecture lecture = lectures.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return lecture.getLectureID();
                case 1:
                    return lecture.getLectureName();
                case 2:
                    return lecture.getClassRoom();
                case 3:
                    return lecture.getDayOfWeek() + " " + lecture.getPeriod();
                case 4:
                    return lecture.getProfessorInCharge();
                default:
                    return "Details";
            }
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public boolean isCellEditable(int row, int col) {
            return col == 5;
        }
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        // Constructor
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

        // Constructor
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
                // Show the detail information of the lecture
                int row = table.getSelectedRow();
                Lecture lecture = ((LectureTableModel) table.getModel()).lectures.get(row);
                DetailLectureInformation.createDetailLectureInformation(lecture);
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
