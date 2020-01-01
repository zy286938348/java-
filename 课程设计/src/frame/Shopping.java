package frame;

import dao.ShoppingDao;
import dao.daoImpl.ShoppingDaolmpl;
import entity.Menus;
import entity.User;
import service.MenuService;
import service.UserService;
import service.serviceImpl.MenuServiceImpl;
import service.serviceImpl.UserServiceImpl;
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

public class Shopping extends JFrame {
    MenuService menuService = new MenuServiceImpl();
    ShoppingDao shoppingDao = new ShoppingDaolmpl();
    UserService userService = new UserServiceImpl();

    LocalDateTime localDateTime = LocalDateTime.now();//获取时间

    private TableColumnModel tableColumnMode;

    private TableColumn tableColumn;

    private DefaultTableModel defaultTableModel = null;

    public Shopping(User user , String username1) {
        setTitle("欢迎    " +user.getName()+ "用户    登陆商家界面                                                                                           时间："+localDateTime.getHour()+":" +localDateTime.getMinute()+":"+localDateTime.getSecond());
        setSize(840,750);
        setLocation(200,30);
        setResizable(false);
        init(username1,user);
        setVisible(true);
     //   setDefaultCloseOperation(3);//设置点击后直接关闭程序而不是隐藏   // 商家界面不关闭
    }
    private void init(String username1,User user){
        List<Menus> menusList = menuService.selectMenuByuserName(username1);
        Object [][] Data = new Object[menusList.size()][4];
        Object [] DataTitle = {"ID","菜名","菜品图片","价格"};
        for (int i = 0; i < menusList.size(); i++) {
            for (int j = 0; j < DataTitle.length; j++) {
                switch (j) {
                    case 0:
                        Data[i][j] = menusList.get(i).getId();
                        break;
                    case 1:
                        Data[i][j] = menusList.get(i).getMenuName();
                        break;
                    case 2:
                        Data[i][j] = PictureUtil.getImage(menusList.get(i).getPath());
                        break;
                    case 3:
                        Data[i][j] = menusList.get(i).getPrice();
                        break;
                }
            }
        }
        defaultTableModel = new IconModel(Data,DataTitle){
            public boolean isCellEditable(int row, int column) {
                return false;//返回true表示能编辑，false表示不能编辑
            }
        };
        JTable j = new JTable(defaultTableModel);

        j.setRowHeight(100);
        j.getTableHeader().setFont(new Font("黑体",Font.PLAIN,30));
        j.setFont(new Font("黑体",Font.PLAIN,23));

        JScrollPane js = new JScrollPane(j);
        js.setBounds(90,70,650,500);

        /**
         * 设置注册时间列的列宽
         */
        tableColumnMode = j.getColumnModel();
        tableColumn =tableColumnMode.getColumn(2);
        tableColumn.setPreferredWidth(100);

        DefaultTableCellRenderer r = new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        j.getColumnModel().getColumn(0).setCellRenderer(r);
        j.getColumnModel().getColumn(3).setCellRenderer(r);
        j.setDefaultRenderer(Object.class,r);

        j.getTableHeader().setReorderingAllowed(false);
        setLayout(null);
        add(js);

        JLabel jLabel3 = new JLabel();
        jLabel3.setText(userService.getTextByUsername(username1));
        jLabel3.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        jLabel3.setForeground(Color.RED);
        jLabel3.setBounds(100,10,500,40);
        add(jLabel3);




        JButton jButton = new JButton("加入购物车");
        jButton.setFont(new Font("黑体",Font.PLAIN,25));
        jButton.setBounds(540,585,200,40);
        add(jButton);

        JButton jButton1 = new JButton("查看购物车");
        jButton1.setFont(new Font("黑体",Font.PLAIN,25));
        jButton1.setBounds(540,655,200,40);
        add(jButton1);

        JLabel jLabel = new JLabel("数量：");
        jLabel.setFont(new Font("黑体",Font.PLAIN,25));
        jLabel.setBounds(100,600,100,40);
        add(jLabel);

        JTextField jTextField = new JTextField();
        jTextField.setFont(new Font("黑体",Font.PLAIN,25));
        jTextField.setBounds(200,600,150,40);
        jTextField.setText("1");
        add(jTextField);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                entity.Shopping shopping = null;
                int row =j.getSelectedRow();
                if(row == -1){
                    System.out.println("未选择中");
                }else{
                    String username = user.getUsername();
                    String menuName = (String) j.getValueAt(row, 1);
                    String sUsername = username1;
                    System.out.println(sUsername);
                    double price = (double)j.getValueAt(row,3);
                    int num = Integer.parseInt(jTextField.getText());

                    double sum = price*num;
                    shopping = new entity.Shopping(username,sUsername,menuName,price,num,sum);
                    int flag = shoppingDao.insert(shopping);

                    if (flag == 0){
                        System.out.println("加入购物车失败");
                    }else{
                        System.out.println("加入购物车成功");
                    }
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ShoppingCar(user);
            }
        });
    }

}
