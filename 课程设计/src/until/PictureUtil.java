package until;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * 通过传入路径获取到图片
 */
public class PictureUtil {
    public static ImageIcon getImage(String path) {
        return new ImageIcon(PictureUtil.class.getResource(path));
    }
}
class IconModel extends DefaultTableModel {
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