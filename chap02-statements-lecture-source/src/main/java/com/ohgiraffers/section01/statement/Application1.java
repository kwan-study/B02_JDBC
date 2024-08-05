package com.ohgiraffers.section01.statement;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /* 설명. 트랜잭션 처리를 위한 DBMS 연동용 Connection 객체 생성 */
        Connection con = getConnection();

        System.out.println("con = " + con);

        /* 설명. 해당 Connection을 통해 트랜잭션 처리(비즈니스 로직 처리, CRUD 등) */
        Statement stmt = null;      // 쿼리를 운반하고 결과를 반환
        ResultSet rset = null;      // 조회를 할 예정(DML 작업이면 ResultSet 대신 int로 처리)

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE");

            while(rset.next()) {

                /* 설명. while문 안의 rset은 한 행을 의미 */
                System.out.print(rset.getString("EMP_NAME") + " ");
                System.out.print(rset.getInt("SALARY") + "\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            /* 설명. 생성과 달리 역순으로 각 스트림을 닫는다. */
            close(rset);
            close(stmt);
            close(con);
        }


    }
}