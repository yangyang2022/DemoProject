package com.yangyang.hibernate.dao;

import com.yangyang.hibernate.model.BookException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class BookShopDao implements IBookShopDao{
    String hql = "";
    @Resource
    private SessionFactory sessionFactory;

    //获取跟当前线程绑定的session
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int findBookPriceByIsbn(String isbn) {
        hql = "select b.price from Book b where b.isbn = :isbn ";
        return (int) getSession().createQuery(hql).setParameter("isbn",isbn).uniqueResult();
    }

    @Override
    public void updateBookStock(String isbn) {
        //验证stock是否足够
        int stock = getStockFromIsbn(isbn);
        if(stock <= 0 ){
            throw new BookException("书库存不够!");
        }
        hql = "update Book b set b.stock = b.stock-1 where b.isbn = :isbn";
        getSession().createQuery(hql).setParameter("isbn",isbn).executeUpdate();
    }

    @Override
    public void updateUserAccount(String username, int price) {
        //验证 balance 是否合法
        int balance = getBalanceFromUsername(username);
        if(balance - price < 0 ){
            throw new BookException("余额不足!");
        }
        hql = "update Account a set a.balance = a.balance-:price where a.username = :username ";
        getSession().createQuery(hql).setParameter("price",price).setParameter("username",username).executeUpdate();
    }
    public int getStockFromIsbn(String isbn){
        hql = "select b.stock from Book b where b.isbn = :isbn";
        return (int) getSession().createQuery(hql).setParameter("isbn",isbn).uniqueResult();
    }
    public int getBalanceFromUsername(String username){
        hql = "select a.balance from Account a where a.username = :username";
        return (int) getSession().createQuery(hql).setParameter("username",username).uniqueResult();
    }
}
