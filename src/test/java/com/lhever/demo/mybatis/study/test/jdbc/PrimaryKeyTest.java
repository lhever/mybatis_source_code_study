package com.lhever.demo.mybatis.study.test.jdbc;

import org.apache.ibatis.executor.resultset.ResultSetWrapper;
import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.Arrays;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/20 14:53
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/20 14:53
 * @modify by reason:{方法名}:{原因}
 */
public class PrimaryKeyTest {

    // JDBC实现insert后，返回自增主键值的原理
    @Test
    public void testGetPrimaryKey() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/cosdb?" +
                "useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "postgres", "postgres");
        conn.setAutoCommit(false);
        PreparedStatement pstm = conn.prepareStatement("insert into book(name, price, create_time) values(?, ?, now())",
                Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, "name1");
        pstm.setFloat(2, 12.3f);
        pstm.addBatch();
        pstm.setString(1, "name2");
        pstm.setFloat(2, 14.5f);
        pstm.addBatch();
        pstm.executeBatch();
// 返回自增主键值
        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
            Object value = rs.getObject(1);
            Object object = rs.getObject(2);
            System.out.println(value);
            System.out.println(object);
        }
        conn.commit();
        rs.close();
        pstm.close();
        conn.close();


    }


    @Test
    public void testF() {

//        File f = new File("C:/Users/lihong10/Desktop/淳安分析/ddd/ddd");
//        File f = new File("C:/Users/lihong10/Desktop/数据分析/临安分析20200311/临时数据_修改成PG/临时数据_修改成PG");
        File f = new File("F:\\rent_manage_platform\\rent\\src\\main\\resources\\lib");

        File[] files = f.listFiles();
        StringBuilder builder = new StringBuilder();

        Arrays.stream(files).forEach(
                jar -> {
                    if (builder.length() == 0) {
                        builder.append("lib/" + jar.getName());
                    } else {
                        builder.append(" ").append("lib/" + jar.getName());
                    }


                }
        );


        System.out.println(builder.toString());

    }


    @Test
    public void testF1() {

        File f = new File("F:\\rent_manage_platform\\rent\\src\\main\\resources\\sql");

        File[] files = f.listFiles();
        StringBuilder builder = new StringBuilder();

        Arrays.stream(files).forEach(
                jar -> {
                    if (jar.getName().endsWith(".sql")) {
                        System.out.println(jar.getName());
                    }


                }
        );


        System.out.println(builder.toString());

    }






    /*
            -------------------------------
            CREATE OR REPLACE FUNCTION func_book2() RETURNS refcursor AS
            $body$
            DECLARE
            result refcursor;
            BEGIN
            open result for select * from book,sys_user; --你可以任意选择你想要返回的表和字段
            return result;
            END;
            $body$
            LANGUAGE 'plpgsql';
            ------------------------------------------
    */
    @Test
    public void testResultSet1() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/cosdb?" +
                        "useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "postgres", "postgres");
        conn.setAutoCommit(false);


        CallableStatement proc = conn.prepareCall("{ ? = call func_book2() }");
        proc.registerOutParameter(1, Types.OTHER);
        proc.execute();
        ResultSet rs = (ResultSet) proc.getObject(1);
        int i = 0;
        while (rs.next()) {
            System.out.println("next: " + i++);
            int anInt = rs.getInt(1);
            String str5 = rs.getString(2);
            String str6 = rs.getString(6);

            System.out.println("" + anInt + ":" +  str5  + ":"  +  str6);
        }


        boolean next = rs.next();
        if (next) {
            int i2 = rs.getInt(1);
            System.out.println(i2);
        }


        conn.close();


    }



/*
    CREATE OR REPLACE FUNCTION my_select(IN bookid int, OUT book_ refcursor, OUT user_ refcursor)
    RETURNS record AS
    $BODY$
    begin
    open book_ for select * from book where id = bookid;
    open user_ for select * from sys_user;
    end;
    $BODY$
    LANGUAGE 'plpgsql';
*/
    @Test
    public void testResultSet2() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/cosdb?" +
                        "useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "postgres", "postgres");
        conn.setAutoCommit(false);

        CallableStatement cs = conn.prepareCall("{call my_select(?,?,?)}");

        cs.setInt(1, 9);
        cs.registerOutParameter(2, Types.OTHER);
        cs.registerOutParameter(3, Types.OTHER);
        int i = cs.executeUpdate();

        getNextResultSet(cs);




        ResultSet book = (ResultSet) cs.getObject(2);
        ResultSet user = (ResultSet) cs.getObject(3);
        while (book != null && book.next()) {
            System.out.println("book: " + book.getInt(1));
        }
        while (user != null && user.next()) {
            System.out.println("user:" + user.getString(1));
        }

        book.close();
        user.close();
        conn.close();
    }




    // 该方法来自mybatis - 类：DefaultResultSetHandler.class

    private ResultSetWrapper getNextResultSet(Statement stmt) {
        // Making this method tolerant of bad JDBC drivers
        try {
            if (stmt.getConnection().getMetaData().supportsMultipleResultSets()) {
                // Crazy Standard JDBC way of determining if there are more results
                if (!(!stmt.getMoreResults() && stmt.getUpdateCount() == -1)) {
                    ResultSet rs = stmt.getResultSet();
                    if (rs == null) {
                        return getNextResultSet(stmt);
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            // Intentionally ignored.
        }
        return null;
    }















}


























