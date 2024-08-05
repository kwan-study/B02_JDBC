package com.ohgiraffers.common;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(
                    new FileReader(
                            "src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            /* 설명. DML 실행 시 자동 커밋이 아닌 수동 커밋을 하겠다는 의미(수동 커밋/롤백) */
            con.setAutoCommit(false);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /* 설명. connection을 닫는 개념은 별도로 분리하고 실제로 닫는 시점을 나중에 우리가 추후 service계층에서 진행 할 예정 */

        return con;
    }

    public static void close(Connection con) {
        try {
            if(con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement st) {
        try {
            if(st != null && !st.isClosed()) st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs) {
        try {
            if(rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if(con != null && !con.isClosed()) con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void rollback(Connection con) {
        try {
            if(con != null && !con.isClosed()) con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}