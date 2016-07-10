package com.yangyang.hibernate.dao;

public interface IBookShopDao {

    //根据ISBN获取书的单价
    int findBookPriceByIsbn(String isbn);

    //更新isbn对的书 的库存 减一
    void updateBookStock(String isbn);

    //更新账户余额: 使username的balance - price
    void updateUserAccount(String username, int price);

    //假设一次只买一本书
}
