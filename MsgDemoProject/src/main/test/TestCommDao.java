import com.yangyang.model.CommentDao;
import com.yangyang.model.ICommentDao;
import com.yangyang.mode.Comment;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TestCommDao {
    private ICommentDao commentDao ;
    private Date date;
    @Before
    public void testSetup() {
        commentDao = new CommentDao();
        date = new Date();
    }

    @Test
    public void testAdd() {
        // content date user_id msg_id
        Comment com = new Comment("hello yangyang",date);
        commentDao.add(com,100,4);
    }

    @Test
    public void testLoad() {
        System.out.println(commentDao.load(131));
    }

    @Test
    public void testComList() {
        commentDao.list(16).getDatas().forEach(System.out::println);
    }

}
