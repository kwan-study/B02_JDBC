package com.ohgiraffers.section03.delete;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuService {

    public void removeMenu(int menuCode) {
        Connection con = getConnection();

        MenuRepository repository = new MenuRepository();
        int result = repository.deleteMenu(con, menuCode);

        if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}