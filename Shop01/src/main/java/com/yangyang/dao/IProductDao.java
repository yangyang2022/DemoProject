package com.yangyang.dao;

import com.yangyang.model.Pager;
import com.yangyang.model.Product;

public interface IProductDao {

    void add(int cid,Product product);
    void update(Product product);
    void delete(int id);
    Product load(int id);

    //根据商品 类别 和 名称 搜索
    Pager<Product> find(int cid, String name);

    //商品id ,商品num
    void addStock(int id,int num);
    void decreseStock(int id,int num);
}
