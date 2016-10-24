<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加私人信件</title>
    <script src="//cdn.ckeditor.com/4.5.10/standard/ckeditor.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>

    <script type="text/javascript">
        $(function () {
            var ab = document.getElementById("addAtts");
            var ac = document.getElementById("attachContainer");
            ab.onclick = function () {
                var node = "<span><br/><input type='file' name='atts' />" +
                        "<input type='button' value='移除' onclick='remove(this)'/> </span>";
                ac.innerHTML = ac.innerHTML + node;
            }
        });
        function remove(obj) {
            var ac = document.getElementById("attachContainer");
            ac.removeChild(obj.parentNode);
        }
    </script>
</head>
<body>
<s:fielderror />
    <form action="message_add" method="post" enctype="multipart/form-data">
        <jsp:include page="nav.jsp" />
        <table class="ct" width="900" align="center" border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td>信件标题</td>
                <td>
                    <input type="text" name="title" size="40">
                </td>
            </tr>
            <tr>
                <td>发送到邮箱</td>
                <td>
                    <input type="checkbox" name="isEmail">
                </td>
            </tr>
            <tr>
                <td colspan="2">选择用户</td>
            </tr>
            <tr>
                <s:if test="#us.size() <= 0"><td colspan="2">没有可发送的用户 </td></s:if>
                <s:else>
                    <td colspan="2"><s:checkboxlist theme="simple" list="#us" listKey="id" listValue="username" name="sus" /> </td>
                </s:else>
            </tr>
            <tr>
                <td colspan="2">添加附件</td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" name="" value="添加附件" id="addAtts"/>
                    <div id="attachContainer">
                        <input type="file" name="atts" />
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    内容
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <textarea cols="125" rows="20" name="content">${content}</textarea>
                    <script>CKEDITOR.replace( 'content' );</script>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <s:if test="#us.size() >0">
                        <input type="submit" value="发送" />
                    </s:if>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
