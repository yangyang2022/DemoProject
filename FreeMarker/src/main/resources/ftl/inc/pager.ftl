
<#macro pager totalPage curPage showPage url clz="">

    <#if (showPage >= totalPage) >
        <@showPager start=1 end=totalPage curPage=curPage url=url clz = clz />
    <#else >
    <#assign half=(showPage/2)>
    <#-- half 的值大于当前页 显示前面的所有值-->
    <#if (half>curPage)>
    <#-- 前面-->
        <@showPager start=1 end=curPage curPage=curPage url=url clz = clz />
        <#-- 后面-->
        <@showPager start=(curPage+1) end=(showPage) curPage=curPage url=url clz = clz />
    <#-- 当前的页面大于一半的值-->
    <#else >
        <#if (curPage < totalPage-half) >
            <@showPager start=(curPage-half) end=(curPage+half) curPage=curPage url=url clz = clz />
        <#else >
            <@showPager start=(curPage-(showPage-(totalPage-curPage))) end=totalPage curPage=curPage url=url clz = clz />
        </#if>
    </#if>

    </#if>

</#macro>

<#macro showPager start end curPage url clz="">
    <#list start..end as page>
        <#if curPage == page>
        [${curPage}]
        <#else >
        <a href="${url}" ${(clz=="")?string("","class='${clz}'")}>${page}</a>
        </#if>
    </#list>
</#macro>