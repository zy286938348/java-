package frame;

import dao.ShoppingDao;
import entity.Menus;
import entity.Order;
import entity.Shopping;
import entity.User;
import service.MenuService;
import service.OrderService;
import service.ShoppingService;
import service.serviceImpl.MenuServiceImpl;
import service.serviceImpl.OrderServiceImpl;
import service.serviceImpl.ShoppingServiceImpl;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCar extends JFrame {
    ShoppingService shoppingService  = new ShoppingServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    private TableColumnModel tableColumnMode;

    private TableColumn tableColumn;
    public ShoppingCar(User user){
        setTitle("购物车界面");
        setSize(800,500);
        setLocation(500,200);
        setResizable(false);
        init(user);
        setVisible(true);

    }

    private void init(User user) {
        List<Shopping> shoppingList = shoppingService.selectShoppingByuserName(user.getUsername());
        System.out.println(shoppingList.isEmpty());
        Object [] DataTitle = {"ID","商家","菜名","单价","数量","总计"};
        Object [][] Data = new Object[shoppingList.size()][DataTitle.length];

        for (int i = 0; i < shoppingList.size(); i++) {
            for (int j = 0; j < DataTitle.length; j++) {
                switch (j) {
                    case 0:
                        Data[i][j] = shoppingList.get(i).getId();
                        break;
                    case 1:
                        Data[i][j] = shoppingList.get(i).getsUsername();
                        break;
                    case 2:
                        Data[i][j] = shoppingList.get(i).getMenuName();
                        break;
                    case 3:
                        Data[i][j] = shoppingList.get(i).getPrice();
                        break;
                    case 4:
                        Data[i][j] = shoppingList.get(i).getNum();
                        break;
                    case 5:
                        Data[i][j] = shoppingList.get(i).getSum();
                        break;
                }
            }
        }
        JTable j = new JTable(Data,DataTitle){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        j.setRowHeight(40);
        j.getTableHeader().setFont(new Font("黑体",Font.PLAIN,30));
        j.setFont(new Font("黑体",Font.PLAIN,23));

        JScrollPane js = new JScrollPane(j);
        js.setBounds(20,20,750,380);


        DefaultTableCellRenderer r = new  DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        j.setDefaultRenderer(Object.class,r);
        j.getTableHeader().setReorderingAllowed(false);
        setLayout(null);
        add(js);


        double  cal = 0;
        for(int c=0;c < shoppingList.size() ; c++){

            cal = cal + shoppingList.get(c).getSum();
        }

        JLabel jLabel = new JLabel("总价：" +  cal);
        jLabel.setFont(new Font("黑体",Font.PLAIN,25));
        jLabel.setBounds(330,410,200,40);
        add(jLabel);


//
        JButton jButton = new JButton("删除");
        jButton.setFont(new Font("黑体",Font.PLAIN,25));
        jButton.setBounds(25,410,100,40);
        add(jButton);
//
        JButton jButton1 = new JButton("购买");
        jButton1.setFont(new Font("黑体",Font.PLAIN,25));
        jButton1.setBounds(665,410,100,40);
        add(jButton1);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row =j.getSelectedRow();
                if(row == -1){
                    System.out.println("未选择中");
                }else {
                    int id  = (Integer) j.getValueAt(row,0);
                    int flag = shoppingService.deleteById(id);
                    if (flag==1){
                        System.out.println("删除成功");
                        dispose();
                        new ShoppingCar(user);
                    }else{
                        System.out.println("删除失败");
                    }
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0 ;i <shoppingList.size();i++){
                    Order order = new Order(user.getUsername(),shoppingList.get(i).getsUsername(),shoppingList.get(i).getMenuName(), shoppingList.get(i).getPrice(),(int)shoppingList.get(i).getNum());
                    int flag = orderService.insertOrder(order);;
                    if (flag!=0){
                        System.out.println("加入成功");
                    }
                }
                int  flag = shoppingService.deleteAll(user.getUsername());
                if (flag==0){
                    System.out.println("清空失败");
                }else{
                    System.out.println("清空成功");
                }
                dispose();
                new ShoppingCar(user);
            }
        });
    }

    public static void main(String[] args) {
        new ShoppingCar(new User("q12345","456asd","商家"));
    }
}
