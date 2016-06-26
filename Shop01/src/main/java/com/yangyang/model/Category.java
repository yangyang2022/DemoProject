package com.yangyang.model;

import com.yangyang.Utils.ValidateForm;
import com.yangyang.Utils.ValidateType;

public class Category {
    private int id;

    @ValidateForm(type = ValidateType.NotNull,errorMsg = "类别名称不能为空!")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
