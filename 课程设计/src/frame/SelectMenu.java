package frame;

import dao.MenuDao;
import dao.daoImpl.MenuDaoImpl;
import entity.Menus;
import entity.User;
import until.IconModel;
import until.PictureUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SelectMenu extends JFrame {
    MenuDao menuDao = new MenuDaoImpl();

    private JTable ta = null;
    private JScrollPane pan = null;
    private DefaultTableModel model = null;

    public SelectMenu(User user) {
        setSize(1000,750);
        setLocation(500,140);
        menus(user);
        //设置窗口不可被拉伸
        setResizable(false);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        setVisible(true);
    }
    private void menus(User user){
        List<Menus> menusList = menuDao.selectMenuByuserName(user.getUsername());
        Object[] columnName = {"id","图片","菜名","单价"};
        Object[][] menu = new Object [menusList.size()][4];
        for(int i=0; i<menusList.size(); i++) {
            for(int j=0; j<5; j++) {
                switch(j) {
                    case 0: menu[i][j] = menusList.get(i).getId();break;
                    case 1: menu[i][j] = PictureUtil.getImage(menusList.get(i).getPath());break;
                    case 2: menu[i][j] = menusList.get(i).getMenuName();break;
                    case 3: menu[i][j] = menusList.get(i).getPrice();break;
                }
            }
        }
        model = new IconModel(menu,columnName);
        ta = new JTable(model);
        ta.setRowMargin(5);
        ta.setRowHeight(50);
        pan = new JScrollPane();
        pan.getViewport().add(ta);
        pan.setBounds(25, 160, 950,450);
        setLayout(null);
        add(pan);
    }

    public static void main(String[] args) {
        new SelectMenu(new User());
    }
}
