package com.ohgiraffers.section01.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        System.out.println("con = " + con);
        PreparedStatement pstmt = null;

        /* 설명.
         *  DML(insert, update, delete) 작업 시에는 반환 결과가 int가 된다.
         *  그리고 조회와 달리 executeQuery()가 아닌 executeUpdate()를 사용하게 된다.
         * */
        int result = 0;

        String query = "INSERT INTO TBL_MENU (MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS)"
                + " VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "봉골레청국장");
            pstmt.setInt(2, 5000);
            pstmt.setInt(3, 4);
            pstmt.setString(4, "Y");

            /* 설명.
             *  혹시라도 기존의 테이블에서 auto_increment 값이 내부적으로 증가되어 있을 때 이를 다시 줄이고자 할 때는
             *  잘못 들어간 데이터를 지우고 auto_increment를 초기화 한 후 insert를 다시 해야 한다.
             *  ex)
             *  delete from tbl_menu where menu_code = 28;
             *  alter table tbl_menu auto_increment = 24;   -- auto_increment가 다시 24번부터 생성 되도록 초기화
             * */

            result = pstmt.executeUpdate();

            if(result > 0) {
                System.out.println("insert 결과: " + result + ", 수동 커밋하기");
                con.commit();
            } else {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);

            /* 설명. connection 객체를 닫는 순간 자동 커밋 된다. */
            close(con);
        }
    }
}