package com.yangyang.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static com.yangyang.TableUtils.TableUtils.*;

@Repository
public class BookShopDao implements IBookShopDao {
    private String sql = "";
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int findBookPriceByIsbn(String isbn) {
        sql = "select price from t_book where isbn = ? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }

    @Override
    public void updateBookStock(String isbn) {
        //检测库存是否足够
        int stock = getStockFromIsbn(isbn);
        if(stock <= 0 ){
            throw new BookException("库存数量不够!");
        }
        sql = "update t_book_stock set stock = stock-1 where isbn = ?";
        jdbcTemplate.update(sql,isbn);
    }

    @Override
    public void updateUserAccount(String username, int price) {
        //验证余额是否足够
        int balance = getAccountFromUser(username);
        if(balance < price){
            throw new BookException("用户余额不足!");
        }
        sql = "update t_account set balance = balance-? where username=? ";
        jdbcTemplate.update(sql,price,username);
    }

    public int getStockFromIsbn(String isbn){
        sql = "select stock from "+ T_BOOK_STOCK+" where isbn = ? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }

    public int getAccountFromUser(String username){
        sql = "select balance from "+T_ACCOUNT +" where username=? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,username);
    }
}
