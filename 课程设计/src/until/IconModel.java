package until;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class IconModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    public IconModel(Object[] cnames, int row) {
        super(cnames, row);
    }

    public IconModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    //important here
    public Class<?> getColumnClass(int col) {
        Vector<?> v = (Vector<?>) dataVector.elementAt(0);
        return v.elementAt(col).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        Class<?> columnClass = getColumnClass(col);
        return columnClass != ImageIcon.class;
    }
}
