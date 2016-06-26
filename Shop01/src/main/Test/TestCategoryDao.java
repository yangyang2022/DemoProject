import com.yangyang.dao.ICategoryDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Category;
import org.junit.Test;

import java.util.List;

public class TestCategoryDao extends BaseTest {
    private ICategoryDao categoryDao;

    @ShopDI
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Test
    public void testAdd() {
        Category category1 = new Category();
        category1.setName("春季服装");
        Category category2 = new Category();
        category2.setName("夏季服装");
        Category category3 = new Category();
        category3.setName("求季服装");
        Category category4 = new Category();
        category4.setName("冬季服装");

        Category car = new Category();
        car.setName("汽车");
        Category plane = new Category();
        plane.setName("飞机");

        categoryDao.add(category1);
        categoryDao.add(category2);
        categoryDao.add(category3);
        categoryDao.add(category4);
        categoryDao.add(car);
        categoryDao.add(plane);
    }

    @Test
    public void testLoad() {
        Category cat = categoryDao.load(1);
        System.out.println(cat.getName());
    }

    @Test
    public void testDelete() {
        categoryDao.delete(1);
    }

    @Test
    public void testUpdate() {
        Category cat = categoryDao.load(5);
        cat.setName("军用汽车");
        categoryDao.update(cat);
    }

    @Test
    public void testList() {
        List<Category> list = categoryDao.list();
        System.out.println("size: "+list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        List<Category> list = categoryDao.list("季");
        System.out.println("size: "+list.size());
        list.forEach(System.out::println);
    }
}
