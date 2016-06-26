import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

@WebServlet(value = "/demo",initParams = {@WebInitParam(name = "username",value = "yangyang")})
public class Demo extends HttpServlet {
    private String brline = "</br>";

    @Override
    public void init(ServletConfig config) throws ServletException {
        String username = config.getInitParameter("username");
        System.out.println("username: "+username);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // post
        response.setCharacterEncoding("utf-8"); //get
        PrintWriter out = response.getWriter();
        IntStream.rangeClosed(1,10).forEach(out::println);
    }
}
