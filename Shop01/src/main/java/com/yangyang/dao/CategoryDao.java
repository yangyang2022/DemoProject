package com.yangyang.dao;

import com.yangyang.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDao extends BaseDao<Category> implements ICategoryDao{

    @Override
    public void add(Category category) {
        super.add(category);
    }

    @Override
    public void delete(int id) {
        //TODO 需要判断该类别是否还有商品存在,如果有不能删除
        super.delete(Category.class,id);
    }

    @Override
    public void update(Category category) {
        super.update(category);
    }

    @Override
    public Category load(int id) {
        return super.load(Category.class,id);
    }

    @Override
    public List<Category> list(String name) {
        Map<String,Object> params = new HashMap<>();
        if(name != null && !"".equals(name.trim()))
            params.put("name","%"+name+"%");

        return super.list(Category.class,params);
    }

    @Override
    public List<Category> list() {
        return list(null);
    }
}
