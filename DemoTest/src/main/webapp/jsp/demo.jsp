<%@ page import="java.util.stream.IntStream" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: syy
  Date: 2016/6/13
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
    <h1>Hello yangyang</h1>
    <%--
        genaeral object:
        out -> printwriter
        request -> HttpServletRequest
        response -> HttpServletResponse
        session -> HttpSession
        application -> ServletContext
    --%>
<%
    String brline = "</br>";
    String str = "hello yangyang";
    out.println(str+brline);
    String username = request.getParameter("username");

%>
    <%!
        int add(int a,int b){
            return a+b;
        }
    %>
    <%=add(10,20)%>
<br/>
    <%=application.getRealPath("/")%>
    <%=request.getParameter("username")%>
<form>
    inputNumber: <input type="text" name="number">
    <input type="submit" value="submit">
    <%
        try {
            int number = Integer.parseInt(request.getParameter("number"));
            %>
        <table border="1">
            <%
                for(int i = 1;i<=number;i++){
                    %>
                <tr>
                    <%
                        for(int j = 1;j<=i;j++){
                            %>
                        <td><%=i%> * <%=j%> = <%=i*j%></td>
                    <%
                        }
                    %>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } catch (NumberFormatException e) {
            %>
        <h1>Please input a conrect number </h1>
    <%
        }
    %>
</form>
</body>
</html>
