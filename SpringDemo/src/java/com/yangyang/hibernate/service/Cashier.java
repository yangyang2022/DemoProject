package com.yangyang.hibernate.service;

import com.yangyang.tx.IBookShopService;
import com.yangyang.tx.ICashier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试 事物的传播行为(一个事务去调用另一个事务)
 * ( 如果第一本书可以购买但不够第二本书)
 *
 */
@Service
public class Cashier implements ICashier{
    @Resource
    private IBookShopService bookShopService;

    @Override
    public void checkOut(String username, List<String> isbns) {
        for(String isbn:isbns){
            bookShopService.buy(username,isbn);
        }
    }
}
