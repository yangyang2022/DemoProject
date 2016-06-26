package com.yangyang.dao;

import com.yangyang.model.Category;

import java.util.List;

public interface ICategoryDao {
    void add(Category category);
    void delete(int id);
    void update(Category category);
    Category load(int id);
    List<Category> list(String name);
    List<Category> list();
}
