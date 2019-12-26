package frame;

import entity.User;
import service.UserService;
import service.serviceImpl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Admin extends JFrame {
    UserService userService = new UserServiceImpl();

    TableColumnModel tableColumnModel ,tableColumnModel2;

    TableColumn tableColumn ,tableColumn2;

    JTable jTable , jTable2;
    JScrollPane jScrollPane, jScrollPane2;

    public Admin(User u) {
        setTitle("欢迎"+u.getName()+"管理员");
        setSize(1730,900);
        setLocation(50,50);
        lable();
        init1(u);
        init2(u);
        //设置窗口不可被拉伸
        setResizable(false);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    private void lable(){
        setLayout(null);
        JLabel jLabel1 = new JLabel("商家信息：");
        JLabel jLabel2 = new JLabel("客户信息：");
        JLabel jLabel3 = new JLabel("用户名：");
        JLabel jLabel4 = new JLabel("密码：");

        jLabel1.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,40));
        jLabel2.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,40));
        jLabel3.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        jLabel4.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));

        jLabel1.setBounds(30,50,200,50);
        jLabel2.setBounds(850,50,200,50);
        jLabel3.setBounds(250,750,200,50);
        jLabel4.setBounds(750,750,300,50);

        add(jLabel1);
        add(jLabel2);
        add(jLabel3);
        add(jLabel4);

        JTextField username = new JTextField();
        JTextField password = new JTextField();

        username.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        password.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));

        username.setBounds(400,750,250,50);
        password.setBounds(870,750,250,50);

        add(username);
        add(password);


        JButton jButton = new JButton("查询");

        jButton.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));

        jButton.setBounds(1200,750,150,50);

        add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  str = username.getText();
                String pwd = userService.getPasswordByUsername(str);
                if (pwd!=null){
                    password.setText(pwd);
                }else{
                    username.setText("");
                    JOptionPane.showMessageDialog(null, "未找到查询的信息，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    private void init1(User u) {
        setLayout(null);
        List<User> userList = userService.selectUserByType("商家");
        Object[] columnName = {"ID", "用户名", "姓名", "性别", "年龄", "注册时间"};
        Object[][] user = new Object[userList.size()][6];
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        user[i][j] = userList.get(i).getId();
                        break;
                    case 1:
                        user[i][j] = userList.get(i).getUsername();
                        break;
                    case 2:
                        user[i][j] = userList.get(i).getName();
                        break;
                    case 3:
                        user[i][j] = userList.get(i).getSex();
                        break;
                    case 4:
                        user[i][j] = userList.get(i).getAge();
                        break;
                    case 5:
                        user[i][j] = userList.get(i).getData();
                        break;
                }
            }
        }

        /**
         * 设置表格不可编辑
         */
        jTable = new JTable(user,columnName){
            public boolean isCellEditable(int row, int column) {
                return false;//返回true表示能编辑，false表示不能编辑
            }
        };

        /**
         * 设置内容居中
         */
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer r   =   new   DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,   r);
        /**
         * 设置了表头不可以被移动
         */
        jTable.getTableHeader().setReorderingAllowed(false);
        /**
         *
         */
        jTable.setRowHeight(30);
        jTable.getTableHeader().setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        jTable.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        /**
         * 设置注册时间列的列宽
         */
        tableColumnModel = jTable.getColumnModel();
        tableColumn =tableColumnModel.getColumn(5);
        tableColumn.setPreferredWidth(300);

        jScrollPane = new JScrollPane();
        jScrollPane.getViewport().add(jTable);
        jScrollPane.setBounds(40,130,800,500);
        add(jScrollPane);

        JButton jButton = new JButton("删除");
        jButton.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        jButton.setBounds(690,650,150,50);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =jTable.getSelectedRow();
                if (row==-1){
                    System.out.println("未获取到已选择的行");
                }else{
                    String  username =String.valueOf(jTable.getValueAt(row,1));
                    int flag = userService.deleteUserByUserName(username);
                    if (flag == 1){
                        dispose();
                        new Admin(u);
                        JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }else if (flag == 0){
                        JOptionPane.showMessageDialog(null, "删除失败,未找到指定用户", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        add(jButton);
    }

    private void init2(User u) {
        setLayout(null);
        List<User> userList = userService.selectUserByType("客户");
        Object[] columnName = {"ID", "用户名", "姓名", "性别", "年龄", "注册时间"};
        Object[][] user = new Object[userList.size()][6];
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        user[i][j] = userList.get(i).getId();
                        break;
                    case 1:
                        user[i][j] = userList.get(i).getUsername();
                        break;
                    case 2:
                        user[i][j] = userList.get(i).getName();
                        break;
                    case 3:
                        user[i][j] = userList.get(i).getSex();
                        break;
                    case 4:
                        user[i][j] = userList.get(i).getAge();
                        break;
                    case 5:
                        user[i][j] = userList.get(i).getData();
                        break;
                }
            }
        }

        /**
         * 设置表格不可编辑
         */
        jTable2 = new JTable(user,columnName){
            public boolean isCellEditable(int row, int column) {
                return false;//返回true表示能编辑，false表示不能编辑
            }
        };

        /**
         * 设置内容居中
         */
        jTable2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer r   =   new   DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable2.setDefaultRenderer(Object.class,   r);
        /**
         * 设置了表头不可以被移动
         */
        jTable2.getTableHeader().setReorderingAllowed(false);
        /**
         *
         */
        jTable2.setRowHeight(30);
        jTable2.getTableHeader().setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        jTable2.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        /**
         * 设置注册时间列的列宽
         */
        tableColumnModel2 = jTable2.getColumnModel();
        tableColumn2 =tableColumnModel2.getColumn(5);
        tableColumn2.setPreferredWidth(300);

        jScrollPane2 = new JScrollPane();
        jScrollPane2.getViewport().add(jTable2);
        jScrollPane2.setBounds(860,130,800,500);
        add(jScrollPane2);

        JButton jButton2 = new JButton("删除");
        jButton2.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        jButton2.setBounds(1510,650,150,50);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =jTable2.getSelectedRow();
                if (row==-1){
                    System.out.println("未获取到选择的行");
                }else{
                    String  username =String.valueOf(jTable2.getValueAt(row,1));
                    int flag = userService.deleteUserByUserName(username);
                    if (flag == 1){
                        dispose();
                        new Admin(u);
                        JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }else if (flag == 0){
                        JOptionPane.showMessageDialog(null, "删除失败,未找到指定商家", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        add(jButton2);
    }
}
