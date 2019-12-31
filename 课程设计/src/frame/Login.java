package frame;

import entity.User;
import finals.Final;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import until.PictureUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    UserService userService = new UserServiceImpl();
    public Login(){
        setTitle("XX点餐平台-登录-注册");
        setSize(900,600);
        setLocation(300,200);
        setResizable(false);
        init();
        setVisible(true);
        setDefaultCloseOperation(3);//设置点击后直接关闭程序而不是隐藏
    }

    private void init(){
        JLabel l_1 = new JLabel("登录账号：");
        l_1.setFont(new Font("楷体",Font.LAYOUT_NO_LIMIT_CONTEXT,35));

        JTextField jt_1 = new JTextField(20);
        jt_1.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel l_3 = new JLabel("用户类型：");
        l_3.setFont(new Font("楷体",Font.PLAIN,35));

        JComboBox jco_type = new JComboBox(Final.NAME);
        jco_type.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,30));

        JLabel l_2 = new JLabel("登录密码：");
        l_2.setFont(new Font("楷体",Font.PLAIN,35));
        JPasswordField jt_2 = new JPasswordField(20);

        JButton btn_1 = new JButton("登录");
        btn_1.setFont(new Font("楷体",Font.PLAIN,35));
        /**
         * 登录事件
         */
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                checkLogin();
                String username = jt_1.getText();
                String password = String.valueOf(jt_2.getPassword());
                String type = String.valueOf(jco_type.getSelectedItem());
                //检查数据库中是否存在该用户的信息
                User user = userService.checkLogin(username, password,type);
                if (user != null){
                    System.out.println(user.toString());
                    if (user.getName()==null){
                        dispose();
                        System.out.println("请完善个人信息");
                    }else{
                        System.out.println("2");
                        if (user==null){
                            System.out.println("输入错误");
                            System.out.println();
                        }else if (user.getType().equals("管理员")){
                            new Admin(user);
                        }else if (user.getType().equals("商家")){
                            new Business(user,false);
                        }else if (user.getType().equals("客户")){
                            new UserFrame(user);
                        }
                        dispose();
                    }
                }else{
                    //创建一个 JOptionPane的实例以显示具有指定消息类型，选项和图标的消息。
                    JOptionPane.showMessageDialog(null, "输入的账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        /**
         * 注册
         */
        JButton btn_3 = new JButton("注册");
        btn_3.setFont(new Font("楷体",Font.PLAIN,35));

        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jt_1.getText();
                String password = String.valueOf(jt_2.getPassword());
                String type = String.valueOf(jco_type.getSelectedItem());

                Boolean flag = userService.checkRegister(username);
                User user = new User(username,password,type);
                if (flag){
                    JOptionPane.showMessageDialog(null, "注册失败，该用户id已经存在！请重新输入！！！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if (useridIsValid(username)){
                        JOptionPane.showMessageDialog(null, "账号输入格式有误！用户名必须是u开头,5位数字", "错误", JOptionPane.ERROR_MESSAGE);
                    }else if (passwordIsValid(password)){
                        JOptionPane.showMessageDialog(null, "密码格式错误！密码必须是6~12位字母数字构成！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        userService.insertUser(user);
                        dispose();
                        new Login();
                        JOptionPane.showMessageDialog(null, "注册成功，请登录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        setLayout(null);
        add(l_1).setBounds(150,110,200,50);
        add(jt_1).setBounds(350,110,300,50);
        add(l_3).setBounds(150,180,200,50);
        add(jco_type).setBounds(350,180,300,50);
        add(l_2).setBounds(150,250,200,50);
        add(jt_2).setBounds(350,250,300,50);
        add(btn_1).setBounds(100,400,200,70);
        add(btn_3).setBounds(600,400,200,70);
    }

    /**
     * 验证账号格式是否正确
     * @param username
     * @return
     */
    private boolean useridIsValid(String username) {
        boolean flag = false;
        char[] s = username.toCharArray();
        if (s[0]!='u'||s.length>6){
            flag = true;
        }else{
            for (int i = 1;i<s.length;i++){
                if ((int)s[i]>57||(int)s[i]<48){
                    flag = true;
                }
            }
        }
        return flag;
    }
    /**
     * 验证密码格式
     * @param passward
     * @return
     */
    private boolean passwordIsValid(String passward){
        char[] s = passward.toCharArray();
        boolean flag = false;
        if (s.length>12||s.length<6){
            flag = true;
        }else{
            for (int i = 0;i<s.length;i++){
                if ((int)s[i]>122||(int)s[i]<48){
                    flag = true;
                }
            }
        }
        return flag;
    }
}