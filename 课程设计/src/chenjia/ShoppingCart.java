package chenjia;

import javax.swing.*;
import java.awt.*;

public class ShoppingCart extends JFrame{

    public void JTable() {
        //setBounds(350, 150, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[] columnNames = {"编号", "饭店", "图片", "菜名", "价格", "数量"};// 定义表格列名数组
        // 定义表格数据数组
        Object[][] tableValues = new String[2][6];
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues, columnNames);
        // 创建显示表格的滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,700,300);
        add(scrollPane);
        //scrollPane.setVisible(true);
        //add(scrollPane, BorderLayout.CENTER);
    }

    public ShoppingCart(){
        setTitle("购物车");
        setSize(700,500);
        setResizable(false);//不可调整大小
        setBounds(300,100,700,500);
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
        setVisible(true);

        //添加按钮
        JButton buy = new JButton("购买");
        buy.setBounds(50,400,65,30);
        add(buy);
        JButton delete = new JButton("删除");
        delete.setBounds(580,400,65,30);
        add(delete);
    }

    public static void main(String[] args) {
        ShoppingCart ss =new ShoppingCart();
        ss.JTable();
    }
}
