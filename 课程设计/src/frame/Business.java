package frame;
import entity.Menus;
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

    TableColumnModel tableColumnMode;

    TableColumn tableColumn;

    DefaultTableModel defaultTableModel = null;
    public Business(User user,boolean b){
        JFrame jf = new JFrame();
        jf.setTitle("xx点餐平台  欢迎"+user.getName()+"商家进入        时间：");
        jf.setSize(1150,600);
        jf.setVisible(true);

        JPanel jp1 = new JPanel(null);




        init(jp1,user,jf);

        JButton button1 = new JButton("菜品管理");




        jf.add(button1).setBounds(0,0,130,100);





        button1.setFont(new Font("黑体",Font.PLAIN,20));






        jp1.setVisible(b);




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jp1.setVisible(true);
            }
        });


        jf.add(jp1);
    }

    private void init(JPanel jPanel,User user,JFrame jf){
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
                    jf.dispose();
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
                jf.dispose();
                new Business(user,true);
            }
        });
        jPanel.add(js);
    }


    public static void main(String[] args) {
        new Business(new User(),false);
    }
}
