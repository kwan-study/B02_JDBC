package com.ohgiraffers.section02.update;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuService {
    public void modifyMenu(Menu modifyMenu) {
        Connection con = getConnection();

        MenuRepository repository = new MenuRepository();
        int result = repository.updateMenu(con, modifyMenu);

        if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }
}