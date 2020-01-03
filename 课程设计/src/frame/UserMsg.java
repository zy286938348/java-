package frame;

import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UserMsg extends JFrame {
    UserDao userDAO = new UserDaoImpl();
    public UserMsg(User user){
        setTitle("用户信息");
        setSize(500,700);
        setLocation(788,185);
        init(user);
        //设置窗口不可被拉伸
        setResizable(false);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        //设置点击关闭时仅关闭当前的界面
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void init(User user){
        JLabel l_1 = new JLabel("登录账号："+user.getUsername());
        l_1.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JLabel realName = new JLabel("真实姓名：");
        realName.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        JTextField jt_realName = new JTextField(user.getName(),20);
        jt_realName.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel sex = new JLabel("用户性别：");
        sex.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        JRadioButton jc_sex1,jc_sex2;
        //获取用户的性别
        boolean nowSexMan = false;
        boolean nowSexWoman = false;
        if (user.getSex()==null){
            nowSexMan = false;
            nowSexWoman = false;
        }else if (user.getSex().equals("男")){
            nowSexMan = true;
        }else if (user.getSex().equals("女")){
            nowSexWoman = true;
        }
        ButtonGroup  bg;
        bg=new ButtonGroup();
        jc_sex1= new JRadioButton("男",nowSexMan);
        jc_sex1.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        jc_sex1.addItemListener(new ItemListener() { // 给单选按钮添加一个点击监听器
            public void itemStateChanged(ItemEvent e) { // 单选按钮被选中
                user.setSex("男");
            }
        });
        jc_sex2 = new JRadioButton("女",nowSexWoman);
        jc_sex2.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        jc_sex2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                user.setSex("女");
            }
        });

        JLabel changeAge = new JLabel("年龄：");
        changeAge.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        JTextField  jChangeAge= new JTextField(20);
        jChangeAge.setFont(new Font("楷体",Font.PLAIN,30));
        jChangeAge.setText(user.getAge()+"");

        JLabel changePassword = new JLabel("修改密码：");
        changePassword.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        JTextField  jChangePassword= new JTextField(20);
        jChangePassword.setFont(new Font("楷体",Font.PLAIN,30));

        JButton jButton1 ,jButton2;
        jButton1 = new JButton("确定");
        jButton1.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(jt_realName.getText());
                if (!jChangePassword.getText().equals("")){
                    user.setPassword(jChangePassword.getText());
                }
                user.setAge(Integer.parseInt(jChangeAge.getText()));
                userDAO.update(user);
                dispose();
            }
        });

        jButton2 = new JButton("取消");
        jButton2.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLayout(null);

        l_1.setBounds(30,60,400,40);
        add(l_1);

        realName.setBounds(30,140,150,40);
        add(realName);
        jt_realName.setBounds(150,140,250,40);
        add(jt_realName);

        sex.setBounds(30,220,150,40);//用户性别
        add(sex);

        jc_sex1.setBounds(180,220,80,40);
        bg.add(jc_sex1);
        add(jc_sex1);
        jc_sex2.setBounds(280,220,80,40);
        bg.add(jc_sex2);
        add(jc_sex2);

        changeAge.setBounds(30,310,150,40);
        add(changeAge);
        jChangeAge.setBounds(150,310,250,40);
        add(jChangeAge);

        changePassword.setBounds(30,400,150,40);
        add(changePassword);
        jChangePassword.setBounds(150,400,250,40);
        add(jChangePassword);

        jButton1.setBounds(60,530,150,60);
        add(jButton1);
        jButton2.setBounds(260,530,150,60);
        add(jButton2);
    }

    public static void main(String[] args) {
        new UserMsg(new User());
    }
}
