<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<style>
    #panel{
        width: 300px;
        border: 1px solid #000;
    }
    #pheader{
        padding: 10px 0px;
        background: #00f;
        color: #fff;
        border-bottom: 1px solid #000;
    }
</style>
<div id="panel">
    <div id="pheader"><decorator:title /></div>
    <div id="pcontent"><decorator:body /></div>
</div>