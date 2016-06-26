package com.yangyang.dao;

import com.yangyang.model.Category;
import com.yangyang.model.Pager;
import com.yangyang.model.Product;
import com.yangyang.model.ShopException;

import java.util.HashMap;
import java.util.Map;

public class ProductDao extends BaseDao<Product> implements IProductDao{

    private ICategoryDao categoryDao;

    @ShopDI
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void add(int cid,Product product) {
        Category c = categoryDao.load(cid);
        if(c == null ) {
            throw new ShopException("要添加的产品种类不存在!");
        }
        product.setCategory(c);
        super.add(product);
    }

    @Override
    public void update(Product product) {
        super.update(product);
    }

    @Override
    public void delete(int id) {
        //TODO 如果用户购买了该商品,就不能删除,该商品存在于订单时
        //也不能删除,如果可以删除的画,先删除该商品的图片
    }

    @Override
    public Product load(int id) {
        return super.load(Product.class,id);
    }

    @Override
    public Pager<Product> find(int cid, String productName) {
        Map<String,Object> params = new HashMap<>();
        if(cid >=0 ){
            params.put("cid",cid);
        }
        if(productName != null || !"".equals(productName.trim())){
            params.put("name","%"+productName+"%");
        }
        return super.find(Product.class,params);
    }

    @Override
    public void addStock(int id, int num) {
        Product p = this.load(id);
        p.setStock(p.getStock()+num);
        this.update(p);
    }

    @Override
    public void decreseStock(int id, int num) {
        Product p = this.load(id);
        p.setStock(p.getStock()-num);
        this.update(p);
    }
}
