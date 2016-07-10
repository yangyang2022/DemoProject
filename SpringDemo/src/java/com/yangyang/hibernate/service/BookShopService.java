package com.yangyang.hibernate.service;

import com.yangyang.hibernate.dao.BookShopDao;
import com.yangyang.tx.IBookShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
