package frame;
import dao.daoImpl.OrderDaoImpl;
import entity.Menus;
import entity.Order;
import entity.User;
import service.MenuService;
import service.serviceImpl.MenuServiceImpl;
import until.IconModel;
import until.PictureUtil;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
public class Business {
//    UserService userService = new UserServiceImpl();
    MenuService menuService = new MenuServiceImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();

    TableColumnModel tableColumnMode;

    TableColumn tableColumn;

    DefaultTableModel defaultTableModel = null;
    public Business(User user,boolean b){
        JFrame jf = new JFrame();
        jf.setTitle("xx点餐平台  欢迎"+user.getName()+"商家进入        时间：");
        jf.setSize(1300,600);
        jf.setVisible(true);

        JPanel jp1 = new JPanel();
//        JPanel jp2 = new JPanel();
//        JPanel jp3 = new JPanel();



//        jf.setLayout(null);
//        init(jp1,user,jf);
//        init1(jp1,user);

        JButton button1 = new JButton("菜品管理");
        JButton button2 = new JButton("查看订单");
        JButton button3 = new JButton("查看报表");



        jf.add(button1).setBounds(0,0,130,100);
        jf.add(button2).setBounds(0,100,130,100);
        jf.add(button3).setBounds(0,200,130,100);



        button1.setFont(new Font("黑体",Font.PLAIN,20));
        button2.setFont(new Font("黑体",Font.PLAIN,20));
        button3.setFont(new Font("黑体",Font.PLAIN,20));





        jp1.setVisible(b);
//        jp2.setVisible(b);
//        jp3.setVisible(b);




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jp1.removeAll();
                jp1.revalidate();//对panel1面板中的组件重新布局并绘制
                jp1.repaint();
                init(jp1,user,jf);
                jp1.setVisible(true);
//                jf.remove(jp1);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jp1.removeAll();
                jp1.revalidate();//对panel1面板中的组件重新布局并绘制
                jp1.repaint();
                init1(jp1,user);
                jp1.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jp1.removeAll();
                jp1.revalidate();//对panel1面板中的组件重新布局并绘制
                jp1.repaint();
                init2(jp1,user);
                jp1.setVisible(true);
            }
        });


        jf.add(jp1);
//        jf.add(jp2);
//        jf.add(jp3);
    }

    private void init(JPanel jPanel,User user,JFrame jFrame){
        List<Menus> menusList = menuService.selectMenuByuserName(user.getUsername());
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
        js.setBounds(150,0,550,500);

        /**
         * 设置注册时间列的列宽
         */
        tableColumnMode = j.getColumnModel();
        tableColumn =tableColumnMode.getColumn(2);
        tableColumn.setPreferredWidth(100);
        /**
         * 设置内容居中
         */
//        j.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer r = new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        j.getColumnModel().getColumn(0).setCellRenderer(r);
        j.getColumnModel().getColumn(3).setCellRenderer(r);
        j.setDefaultRenderer(Object.class,r);

        /**
         * 设置了表头不可以被移动
         */
        j.getTableHeader().setReorderingAllowed(false);

        JButton button3 = new JButton("删除");
        jPanel.add(button3).setBounds(550,500,100,50);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row =j.getSelectedRow();
                if(row == -1){
                    System.out.println("未选择中");
                }else{
                    int id  = (Integer) j.getValueAt(row,0);
                    int flag = menuService.deleteUserByPrinaryKey(id);
                    jFrame.dispose();
                    new Business(user,true);
                    if (flag==1){
                        System.out.println("删除成功");
                    }else{
                        System.out.println("删除失败");
                    }
                }
            }
        });

        jPanel.setLayout(null);
        JLabel jLabel2 = new JLabel("菜的名字：");
        JLabel jLabel3 = new JLabel("菜品图片：");
        JLabel jLabel4 = new JLabel("菜的价格：");

        jLabel2.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        jLabel3.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        jLabel4.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,30));

        jLabel2.setBounds(720,50,250,50);
        jLabel3.setBounds(720,150,250,50);
        jLabel4.setBounds(720,250,250,50);

        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jLabel4);

        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();

        jTextField1.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        jTextField2.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        jTextField3.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));

        jTextField1.setBounds(900,50,210,50);
        jTextField2.setBounds(900,150,210,50);
        jTextField3.setBounds(900,250,210,50);

        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
//







        JButton button2 = new JButton("添加");
        jPanel.add(button2).setBounds(1000,500,100,50);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String str1 = jTextField1.getText();
                String str2 = jTextField2.getText();
                String str3 = jTextField3.getText();
                String str4 = user.getUsername();
                Menus menu = new Menus(str4,str2,str1,Integer.valueOf(str3));
                menuService.insertMenu(menu);
                jFrame.dispose();
                new Business(user,true);
            }
        });
        jPanel.add(js);
    }
    private void init1(JPanel jPanel,User user){
        List<Order> orderList = orderDao.getMenusByUsername(user.getUsername());
        Object [][] Data = new Object[orderList.size()][7];
        Object [] DataTitle = {"ID","用户名","菜名","价格","数量","总价","下单时间"};
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < DataTitle.length; j++) {
                switch (j) {
                    case 0:
                        Data[i][j] = orderList.get(i).getId();
                        break;
                    case 1:
                        Data[i][j] = orderList.get(i).getUsername();
                        break;
                    case 2:
                        Data[i][j] = orderList.get(i).getMenuName();
                        break;
                    case 3:
                        Data[i][j] = orderList.get(i).getPrice();
                        break;
                    case 4:
                        Data[i][j] = orderList.get(i).getNum();
                        break;
                    case 5:
                        Data[i][j] = orderList.get(i).getSum();
                        break;
                    case 6:
                        Data[i][j] = orderList.get(i).getDatatime();
                        break;
                }
            }
        }
        JTable jj = new JTable(Data,DataTitle);
        jj.setRowHeight(150);
        jj.getTableHeader().setFont(new Font("黑体",Font.PLAIN,30));
        jj.setFont(new Font("黑体",Font.PLAIN,23));

        JScrollPane jss = new JScrollPane(jj);
        jss.setBounds(150,0,900,500);

        /**
         * 设置注册时间列的列宽
         */
        tableColumnMode = jj.getColumnModel();
        tableColumn =tableColumnMode.getColumn(6);
        tableColumn.setPreferredWidth(230);
        /**
         * 设置内容居中
         */
//        j.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer r = new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        jj.getColumnModel().getColumn(0).setCellRenderer(r);
        jj.getColumnModel().getColumn(6).setCellRenderer(r);
        jj.setDefaultRenderer(Object.class,r);

        /**
         * 设置了表头不可以被移动
         */
        jj.getTableHeader().setReorderingAllowed(false);
        jPanel.add(jss);
    }
    private void init2(JPanel jPanel,User user){
        List<Order> orderList = orderDao.getMenusByUsername(user.getUsername());
        Object [][] Data = new Object[orderList.size()][4];
        Object [] DataTitle = {"用户名","菜名","总价","下单时间"};
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < DataTitle.length; j++) {
                switch (j) {
                    case 0:
                        Data[i][j] = orderList.get(i).getUsername();
                        break;
                    case 1:
                        Data[i][j] = orderList.get(i).getMenuName();
                        break;
                    case 2:
                        Data[i][j] = orderList.get(i).getSum();
                        break;
                    case 3:
                        Data[i][j] = orderList.get(i).getDatatime();
                        break;
                }
            }
        }
        JTable jj = new JTable(Data,DataTitle);
        jj.setRowHeight(70);
        jj.getTableHeader().setFont(new Font("黑体",Font.PLAIN,30));
        jj.setFont(new Font("黑体",Font.PLAIN,23));

        JScrollPane jss = new JScrollPane(jj);
        jss.setBounds(150,0,600,400);

        /**
         * 设置注册时间列的列宽
         */
        tableColumnMode = jj.getColumnModel();
        tableColumn =tableColumnMode.getColumn(3);
        tableColumn.setPreferredWidth(250);
        JLabel jLabel = new JLabel("今日总收入");
        jLabel.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,20));
        jLabel.setBounds(760,450,200,50);
        double price1 = 0;
        double[]priceArray = new double[orderList.size()];
        for (int i = 0; i < orderList.size(); i++) {
                        priceArray[i]= orderList.get(i).getSum();
                        price1 = price1 +priceArray[i];
        }
        JLabel jLabel1 = new JLabel(price1 + "");
        jLabel1.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,20));
        jLabel1.setBounds(900,450,100,50);
        jPanel.add(jss);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);


    }

//
//    public static void main(String[] args) {
//        new Business(new User(),false);
//    }
}
