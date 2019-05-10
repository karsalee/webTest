package com.jxau.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class c3p0 {
    private static final String connectionURL="jdbc:mysql://localhost:3306/jdbc" ;
    private static final String username = "root";
    private static final String password = "lls19981115.";

    private static ComboPooledDataSource ds ;

    static {
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl(connectionURL);
            ds.setUser(username);
            ds.setPassword(password);

            ds.setInitialPoolSize(5);
            ds.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, Statement stmt, Connection con) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(con);
    }
    public static void close(Statement stmt1,Statement stmt2,Connection con) {
        closeStatement(stmt1);
        closeStatement(stmt2);
        closeConnection(con);
    }
    public static void close(Statement stmt1,Connection con) {
        closeStatement(stmt1);
        closeConnection(con);
    }


    private static void closeResultSet(ResultSet rs ) {
        try {
            if(rs!=null)rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void closeStatement(Statement stmt) {
        try {
            if(stmt!=null)
                stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void closeConnection(Connection con) {
        try {
            if(con!=null)con.close();//这里会把链接归还给连接池，并不是真正的断开链接
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
