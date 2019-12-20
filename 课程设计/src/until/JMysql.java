package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JMysql {
    /**
     * 数据库工具类
     */
        public static Connection getConnection(){
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderSystem?useSSL=false",
                        "root",
                        "admin");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

        public static void closeConnection(Connection connection){
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static PreparedStatement getPreparedStatement(Connection connection , String sql , Object...objects){
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                for (int i = 0; i < objects.length; i++) {
                    pstmt.setObject(i+1, objects[i]);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return pstmt;
        }
    }
