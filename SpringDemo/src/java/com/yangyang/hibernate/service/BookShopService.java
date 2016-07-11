package com.yangyang.hibernate.service;

import com.yangyang.hibernate.dao.BookShopDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * spring hibernate 事务的流程:
 *  1: 在方法之前获取session
 *  2: 把session和当前线程绑定,这样就能在sessionFactory中获取session
 *  否则会报异常
 *  3: 若方法正常结束,没有异常 则:提交事务,解除绑定,关闭session
 *      若方法出现异常 则: 回滚事务,解除绑定,关闭session
 */
@Service
public class BookShopService implements IBookShopService{

    @Resource
    private BookShopDao bookShopDao;

    @Override
    public void buy(String username, String isbn) {

        //1: 获取书的单价
        int price = bookShopDao.findBookPriceByIsbn(isbn);
        //2: 更新书的库存
        bookShopDao.updateBookStock(isbn);
        //3: 更新用户余额
        bookShopDao.updateUserAccount(username,price);
    }
}
