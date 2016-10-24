
<#macro select id datas value="" key="" text="" headkey="" headvalue="">
    <select id="${id}" name="${id}">
        <#if headkey!="">
            <option value="${headvalue}">${headvalue}</option>
        </#if>
        <#if datas?is_hash_ex>
            <#-- 如果是哈希 就通过key and value 来取值-->
            <#local ks =datas?keys />
            <#list ks as k>
                <#if value==k>
                    <option value="${k}" selected>${datas[k]}</option>
                <#else >
                    <option value="${k}">${datas[k]}</option>
                </#if>
            </#list>
            <#else >
            <#-- 这里datas 不是hash -->
                <#list datas as d>
                    <#if key != "">
                    <#-- 这是对象 -->
                        <#if value==d[key]?string>
                            <option value="${d[key]}" selected>${d[text]}</option>
                        <#else >
                            <option value="${d[key]}">${d[text]}</option>
                        </#if>
                    <#else >
                    <#-- 这是string -->
                        <#if value == d>
                            <option value="${d}" selected>${d}</option>
                        <#else >
                            <option value="${d}">${d}</option>
                        </#if>
                    </#if>
                </#list>
        </#if>
    </select>
</#macro>

<#macro xmlSelect cid id>
    <#local title = fieldDoc["fields/field[@id='${cid}']"].@name>
    ${title}:
    <select id="${id}" name="${id}">
        <option value="">${title}</option>
        <#list fieldDoc["fields/field[@id='${cid}']/data"] as d>
            <option value="${d.key}">${d.value}</option>
        </#list>
    </select>
</#macro>