package com.ohgiraffers.section01.insert;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

/* 설명.
 *  Service 계층은 Connection 객체 생성 및 소멸(close) 및 비즈니스 로직, 트랜잭션(commit, rollback) 처리
 * */
public class MenuService {
    public void registMenu(Menu registMenu) {
        Connection con = getConnection();

        MenuRepository repository = new MenuRepository();
        int result = repository.insertMenu(con, registMenu);

        /* 설명. JDBCTemplate에 commit, rollback 메소드도 추가 */
        if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void modifyMenu(com.ohgiraffers.section02.update.Menu modifyMenu) {
    }
}







