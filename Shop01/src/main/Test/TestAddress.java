import com.yangyang.Utils.DBUtils;
import com.yangyang.dao.IAddressDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Address;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class TestAddress {
    private IAddressDao addressDao;

    @ShopDI
    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Test
    public void testLinkDB() {
        Connection con = DBUtils.getConnetion();
        if(con == null) System.out.println("fail");
        else System.out.println("ok");
    }
    @Test
    public void testAddressAdd() {
        //User user = new User("admin","123123","超级管理员",1);
        Address address = new Address("云南省昆明市学府路146号,老沈收","114","234000");
        addressDao.add(address,2);
    }

    @Test
    public void testUpdate() {
        Address ad = addressDao.load(2);
        ad.setPostcode("xxx");
        addressDao.update(ad);
    }
    @Test
    public void testDelete() {
        addressDao.delete(2);
    }

    @Test
    public void testAddressLoad() {
        System.out.println(addressDao.load(1));
    }
    @Test
    public void testAddressList() {
        List<Address> list = addressDao.list(2);
        list.forEach(System.out::println);
    }
}
