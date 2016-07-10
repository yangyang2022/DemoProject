package com.yangyang.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BookShopService implements IBookShopService{

    @Resource
    private BookShopDao bookShopDao;

    // 1: propagation 事务传播行为,默认取值 REQUIRED(调用方法的事物)
    // --> 对应这里 两本书都没有买成功
    // 2: REQUIRES_NEW 新开的独立的事务
    //--> 对应这里 买成功一本书 (其他事物挂起,新事物开启)
    // 3:  使用isolation 指定 事务的隔离级别 常用取值: READ_COMMITTED
    // 4: 默认情况下 对所有运行时异常对事务回滚 ,也可以指定异常回滚 rollbackFor 一般不用
    // 5:  readOnly = true 指定事务为只读 (这里是要修改数据库,所以为false)
    // 6: timeout 防止事务对运行时间占用过长,超时强制回滚
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = BookException.class,
            readOnly = false,
            timeout = 1
    )
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
