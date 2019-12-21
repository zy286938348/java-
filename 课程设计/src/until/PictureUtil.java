package until;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 通过传入路径获取到图片
 */
public class PictureUtil {
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = PictureUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
