import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;

public class ProfessorPanel extends JPanel {
    static JTable table;
    public static ProfessorTableModel tableModel;

    public static ProfessorPanel createProfessorPanel() {

        tableModel = new ProfessorTableModel(MyApp.professors);
        table = new JTable(tableModel);

        // Add a button to the last column
        table.getColumn("Details").setCellRenderer(new ButtonRenderer());
        table.getColumn("Details").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 800));

        JButton buttonRegister = new JButton("register a new professor");
        RegisterButtonAction registerButtonActionListener = new RegisterButtonAction();
        buttonRegister.addActionListener(registerButtonActionListener);

        JButton reloadButton = new JButton("Reload");
        ReloadButtonAction reloadButtonActionLister = new ReloadButtonAction();
        reloadButton.addActionListener(reloadButtonActionLister);

        ProfessorPanel professorPanel = new ProfessorPanel();
        professorPanel.add(buttonRegister, BorderLayout.NORTH);
        professorPanel.add(reloadButton, BorderLayout.EAST);
        professorPanel.add(scrollPane, BorderLayout.CENTER);

        return professorPanel;
    }

    public static class ReloadButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Update the table
            tableModel.setProfessors(MyApp.professors);
        }
    }

    public static class RegisterButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RegisterFormProfessor.createRegisterFormProfessor(() -> {
                tableModel.setProfessors(MyApp.professors);
            });
        }
    }

    static class ProfessorTableModel extends AbstractTableModel {
        private ArrayList<Professor> professors;
        private String[] columnNames = { "ID", "Name", "Details" };

        public ProfessorTableModel(ArrayList<Professor> professors) {
            this.professors = professors;
        }

        public int getRowCount() {
            return professors.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public void setProfessors(ArrayList<Professor> professors) {
            this.professors = professors;
            fireTableDataChanged(); // Notify model change and redraw
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Professor professor = professors.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return professor.getProfessorID();
                case 1:
                    return professor.getName();
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
                // Show dialog with professor details
                int row = table.getSelectedRow();
                Professor professor = ((ProfessorTableModel) table.getModel()).professors.get(row);
                DetailProfessorInformation.createDetailProfessorInformation(professor);
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
