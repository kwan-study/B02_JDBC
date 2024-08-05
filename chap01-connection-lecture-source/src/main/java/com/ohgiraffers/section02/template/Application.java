package com.ohgiraffers.section02.template;

import java.sql.Connection;

import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;
import static com.ohgiraffers.section02.template.JDBCTemplate.close;

/* 설명. import static은 메소드명까지 작성하며 static 메소드를 '클래스명'을 생략하고 호출할 수 있다. */
public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        System.out.println("con = " + con);

        /* 설명. 하나의 트랜잭션 단위에 대한 비즈니스 로직 실행 */

        close(con);
    }
}
