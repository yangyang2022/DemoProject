import com.yangyang.model.BaseDao;
import com.yangyang.model.ICategoryDao;
import com.yangyang.model.IProductDao;
import com.yangyang.model.ShopDI;
import com.yangyang.model.Category;
import com.yangyang.model.Product;
import org.junit.Test;

public class TestProductDao extends BaseDao<Product>{
    private IProductDao productDao;
    private ICategoryDao categoryDao;

    @ShopDI
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @ShopDI
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }
    @Test
    public void testProduct() {
        Category c1= categoryDao.load(6);//plane
        Category c2 = categoryDao.load(15);//fruit
        Product p = new Product("波音747飞机",12345.23,"这是一个飞机","我是一个图片",0,100,c1);
        Product p2 = new Product("苹果",3.5,"我是苹果","我是一个图片",0,10,c2);
        productDao.add(c1.getId(),p);
        productDao.add(c2.getId(),p2);
    }
}
