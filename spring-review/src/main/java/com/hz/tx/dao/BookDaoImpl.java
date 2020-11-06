package com.hz.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther zehua
 * @Date 2020/11/6 19:31
 */
@Repository
public class BookDaoImpl {
    @Autowired
    private JdbcTemplate template;

    public int getPrice(int id) {
        String sql = "select price from book where id=?";
        Integer integer = template.queryForObject(sql, int.class, id);
        return integer;
    }

    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void updateAccountByName(String username) {
        String sql = "update account set balance = balance-100 where username=?";
        template.update(sql, username);

        //int i = 1 / 0;
    }

    //@Transactional( propagation = Propagation.REQUIRED)
    public void updatebStockById(int id) {
        String sql = "update book_stock set stock = stock-1 where id=?";
        template.update(sql, id);
        // int i = 1 / 0;
    }

    public void update(String username, int id) {
        this.updateAccountByName(username);
        this.updatebStockById(id);
    }

}
