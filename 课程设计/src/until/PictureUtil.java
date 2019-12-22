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
