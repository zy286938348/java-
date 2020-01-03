package frame;

import dao.MenuDao;
import dao.UserDao;
import dao.daoImpl.MenuDaoImpl;
import dao.daoImpl.UserDaoImpl;
import entity.Menus;
import entity.User;
import until.IconModel;
import until.PictureUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class UserFrame extends JFrame {
    UserDao userDao = new UserDaoImpl();
    LocalDateTime localDateTime = LocalDateTime.now();//获取时间

    private JTable ta = null;
    private JScrollPane pan = null;
    private DefaultTableModel model = null;

    private TableColumnModel tableColumnMode;

    private TableColumn tableColumn;
    public UserFrame(User user) {

        setTitle("欢迎    " +user.getName()+ "用户    登陆用户界面                                                                                           时间："+localDateTime.getHour()+":" +localDateTime.getMinute()+":"+localDateTime.getSecond());
        setSize(840,600);
        setLocation(200,200);
        setResizable(false);
        init(user);
        setVisible(true);
        setDefaultCloseOperation(3);//设置点击后直接关闭程序而不是隐藏
    }
    private void init(User user){
        List<User> userList = userDao.selectMenuByType("商家");
        Object[] columnName = {"图片","名字"};
        Object[][] menu = new Object [userList.size()][columnName.length];
        for(int i=0; i<userList.size(); i++) {
            for(int j=0; j<5; j++) {
                switch(j) {
                    case 0: menu[i][j] = PictureUtil.getImage(userList.get(i).getPath());break;
                    case 1: menu[i][j] = userList.get(i).getUsername();break;
                }
            }
        }
        model = new IconModel(menu,columnName){
            public boolean isCellEditable(int row, int column) {
                return false;//返回true表示能编辑，false表示不能编辑
            }
        };
        ta = new JTable(model);
        ta.setRowMargin(5);
        ta.setRowHeight(80);
        pan = new JScrollPane();
        pan.getViewport().add(ta);
        pan.setBounds(70, 25, 700,450);

//        ta.setRowHeight(100);
        ta.getTableHeader().setFont(new Font("黑体",Font.PLAIN,30));
        ta.setFont(new Font("黑体",Font.PLAIN,23));

        /**
         * 设置注册时间列的列宽
         */
        tableColumnMode = ta.getColumnModel();
        tableColumn =tableColumnMode.getColumn(0);
        tableColumn.setPreferredWidth(40);
        /**
         * 设置内容居中
         */
        DefaultTableCellRenderer r = new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        ta.setDefaultRenderer(Object.class,r);

        /**
         * 设置了表头不可以被移动
         */
        ta.getTableHeader().setReorderingAllowed(false);

        JButton jButton = new JButton("进入商家");
        jButton.setFont(new Font("黑体",Font.PLAIN,25));
        jButton.setBounds(650,500,150,40);
        setLayout(null);
        add(pan);
        add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row =ta.getSelectedRow();
                if(row == -1){
                    System.out.println("未选择中");
                }else{
                    String username  = (String) ta.getValueAt(row,1);
                    new Shopping(user,username);
                }
            }
        });

        ImageIcon icon = new ImageIcon("C:\\Users\\16524\\Desktop\\user1.png");
        JButton jButton1 = new JButton(icon);
        jButton1.setFont(new Font("黑体",Font.PLAIN,25));
        jButton1.setBounds(780,10,40,40);
        add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UserMsg(user);
            }
        });
    }

    public static void main(String[] args) {
        new UserFrame(new User());
    }

}
