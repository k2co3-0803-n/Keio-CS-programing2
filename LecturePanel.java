import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;

public class LecturePanel extends JPanel {
    static JTable table;
    public static LectureTableModel tableModel;

    // class method
    // Later, this method will be called from Home.java
    public static LecturePanel createLecturePanel() {
        // Create a list of lectures. This ArrayList has the actuak data of lectures.

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

        // public void setLectures(ArrayList<Lecture> lectures) {
        // this.lectures = lectures;
        // fireTableDataChanged(); // モデルの変更を通知してテーブルを再描画
        // }
        public void setLectures(ArrayList<Lecture> lectures) {
            this.lectures = lectures;
            fireTableDataChanged(); // モデルの変更を通知してテーブルを再描画
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
            return col == 5; // Only the last column is editable. But this is for the UI of pressing the
                             // button.
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
                // show the detail information of the lecture
                int row = table.getSelectedRow();
                // TODO probably this is not a good way to get the data of the selected row.
                // This data cannot contain the students who take this selected class.
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

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// // Here, define the UI of Lecture List
// public class LecturePanel extends JPanel{
// static JList list;

// // class method
// public static LecturePanel createLecturePanel() {
// DefaultListModel model = new DefaultListModel();

// // temporary data
// for (int i = 0; i < 100; i++) {
// model.addElement("Lecure " + (i+1));
// }

// list = new JList(model);
// list.setCellRenderer(new ButtonRenderer());
// list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
// list.addMouseListener(new MouseAdapter() {
// public void mouseClicked(MouseEvent e) {
// int index = list.locationToIndex(e.getPoint());
// ButtonRenderer renderer = (ButtonRenderer) list.getCellRenderer();
// Rectangle bounds = list.getCellBounds(index, index);
// Point point = e.getPoint();
// point.translate(-bounds.x, -bounds.y);
// if (renderer.button.getBounds().contains(point)) {
// renderer.button.doClick();
// }
// }
// });

// JScrollPane scrollPane = new JScrollPane(list);
// scrollPane.createVerticalScrollBar();
// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
// scrollPane.setPreferredSize(new Dimension(800, 800)); // Set your desired
// size here
// scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

// JButton buttonRegister = new JButton("新しい講義を追加");
// RegisterButtonAction registerButtonActionListener = new
// RegisterButtonAction();
// buttonRegister.addActionListener(registerButtonActionListener);

// LecturePanel lecturePanel = new LecturePanel();
// //lecturePanel.setLayout(new BoxLayout(lecturePanel, BoxLayout.Y_AXIS));
// lecturePanel.add(buttonRegister, BorderLayout.CENTER);
// lecturePanel.add(scrollPane, BorderLayout.CENTER);

// return lecturePanel;
// }

// public static class RegisterButtonAction implements ActionListener {
// public void actionPerformed(ActionEvent e) {
// RegisterFormLecture.createRegisterFormLecture();
// }
// }

// public static class ButtonRenderer extends JPanel implements ListCellRenderer
// {
// JLabel label;
// JButton button;

// public ButtonRenderer() {
// setLayout(new BorderLayout());
// label = new JLabel();
// button = new JButton("詳細");
// DetailButtonAction detailButtonActionListener = new DetailButtonAction();
// button.addActionListener(detailButtonActionListener);
// button.setPreferredSize(new Dimension(100, 20));
// add(label, BorderLayout.CENTER);
// add(button, BorderLayout.EAST);
// }

// // implements the abstract method(must)
// public Component getListCellRendererComponent(JList list, Object value, int
// index, boolean isSelected, boolean cellHasFocus) {
// label.setText(value.toString());
// return this;
// }

// class DetailButtonAction implements ActionListener {
// public void actionPerformed(ActionEvent e) {
// JOptionPane.showMessageDialog(list, "None selected!", "Error",
// JOptionPane.INFORMATION_MESSAGE);
// }
// }
// }
// }
