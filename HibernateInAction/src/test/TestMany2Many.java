import com.yangyang.model.Category;
import com.yangyang.model.Item;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestMany2Many {

    private EntityManager manager;
    private EntityTransaction transcation;
    @Before
    public void testinit() {
        manager = HibernateUtil.getEntityManager();
        transcation = manager.getTransaction();
        transcation.begin();
    }

    @After
    public void testDestroy() {
        transcation.commit();
        manager.close();
    }

    @Test
    public void testAdd() {
        Item item1 = new Item();
        item1.setItemName("item1--name");
        Item item2 = new Item();
        item2.setItemName("item2 --name");

        Category cat = new Category();
        cat.setCategoryName("cat1 -- name");
        Category cat2 = new Category();
        cat2.setCategoryName("cat2 --name");

        item1.getCategories().add(cat);
        item1.getCategories().add(cat2);
        item2.getCategories().add(cat);
        item2.getCategories().add(cat2);

        cat.getItems().add(item1);
        cat.getItems().add(item2);
        cat2.getItems().add(item1);
        cat2.getItems().add(item2);

        HibernateUtil.save(manager,cat,cat2,item1,item2);
    }

    @Test
    public void testFind() {

        //item 维护了关系,默认使用了lazy
        //Item item = manager.find(Item.class, 1);
        //System.out.println(item.getItemName()+" --> "+item.getCategories().size());

        // 和 维护关系一端find 一样,同样使用lazy
        Category cat = manager.find(Category.class,1);
        System.out.println(cat.getCategoryName()+" --> "+cat.getItems().size());
    }
}
