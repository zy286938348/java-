package frame;

import entity.Menus;
import entity.Shopping;
import entity.User;
import service.MenuService;
import service.ShoppingService;
import service.serviceImpl.MenuServiceImpl;
import service.serviceImpl.ShoppingServiceImpl;
import until.IconModel;
import until.PictureUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCar extends JFrame {
    ShoppingService shoppingService  = new ShoppingServiceImpl();

    private TableColumnModel tableColumnMode;

    private TableColumn tableColumn;
    public ShoppingCar(User user){
        setTitle("购物车界面");
        setSize(800,500);
        setLocation(500,200);
        init(user);
        setVisible(true);

    }

    private void init(User user) {
        List<Shopping> shoppingList = shoppingService.selectShoppingByuserName(user.getUsername());
        System.out.println(shoppingList.isEmpty());
        Object [] DataTitle = {"商家","菜名","单价","数量","总计"};
        Object [][] Data = new Object[shoppingList.size()][DataTitle.length];

        for (int i = 0; i < shoppingList.size(); i++) {
            for (int j = 0; j < DataTitle.length; j++) {
                switch (j) {
                    case 0:
                        Data[i][j] = shoppingList.get(i).getsUsername();
                        break;
                    case 1:
                        Data[i][j] = shoppingList.get(i).getMenuName();
                        break;
                    case 2:
                        Data[i][j] = shoppingList.get(i).getPrice();
                        break;
                    case 3:
                        Data[i][j] = shoppingList.get(i).getNum();
                        break;
                    case 4:
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
    }

    public static void main(String[] args) {
        new ShoppingCar(new User("q12345","456asd","商家"));
    }
}
