<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码</title>
    <script type="text/javascript">
        function reloadCode() {
            var time = new Date().getTime();
            document.getElementById("safeCode").src="<%=request.getContextPath()%>/safeImage?time="+time;
        }
    </script>
</head>
<body>
    <form action="checkSafeCode" method="post">
        验证码: <input type="text" name="checkcode" />
        <img alt="验证码" id="safeCode" src="<%=request.getContextPath()%>/safeImage" />
        <a href="javascript:reloadCode()">看不清楚</a><br/>
        <input type="submit" value="submit">
    </form>
</body>
</html>
