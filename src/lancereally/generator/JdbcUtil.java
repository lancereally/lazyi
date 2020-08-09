package lancereally.generator;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    // 数据库驱动
    private static String Driver = "";
    // 数据库地址
    private static String url = "";
    // 数据库用户名
    private static String userName = "";
    // 数据库密码
    private static String password = "";
    static {
        try {
            // 通过相对路径加载文件
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            // 2.通过类加载器的方法获得一个输入流
            InputStream in = classLoader.getResourceAsStream("../config/jdbc-interface.properties");
            // 3.创建一个properties对象
            Properties p = new Properties();
            // 4.加载输入流
            p.load(in);
            // 5.获取相关参数的值
            // 用getProperty方法通过关键字获取信息
            Driver = p.getProperty("Driver");
            url = p.getProperty("url");
            userName = p.getProperty("userName");
            password = p.getProperty("password");


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //get set
    public static String getDriver() {
        return Driver;
    }

    public static void setDriver(String driver) {
        Driver = driver;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        JdbcUtil.url = url;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        JdbcUtil.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        JdbcUtil.password = password;
    }
    // 获取数据库连接
    public static Connection getConn() {
        Connection conn = null;
        try {
            // 加载驱动
            Class.forName(Driver);
            // 获取数据库连接
            conn = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭数据库资源
    public static void closeAll(ResultSet rs, Statement stat, Connection conn) {
        /* 分别按顺序关闭数据库的结果集资源，Statement 对象资源以及Connection 连接数据库对象 */
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}