[#ftl]
[#include "/_inc/inc.ftl"]
<!DOCTYPE html>
<html lang="zh">
<head>
[#include "/_inc/meta.ftl"]
	[#include "/_inc/css.ftl"]
	[#include "/_inc/link.ftl"]
</head>
<body>
[#include "head.ftl"]

<div class="admin-content">
[#if association??]社群：${association.name ? default("")}-------------[/#if]
[#if category ??]类目：${category.categoryName ? default("")}[/#if]
    <input type="hidden" id="associationId" name="associationId"
           value="[#if association??]${association.id ? default("")}[/#if]"/>
    <input type="hidden" id="categoryId" name="categoryId"
           value="[#if category ??]${category.id ? default("")}[/#if]"/>
    <table class="am-table">
        <thead>
        <tr>
            <td>Group Name</td>
            <td>Book Name</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        [#if groupList??]
            [#list groupList as group]
            <tr>
                <td>[#if group.groupName??]${group.groupName?default('')}[/#if]</td>
                <td>[#if group.bookName??]${group.bookName?default('')}[/#if]</td>
                <td><a class="am-btn am-btn-primary"
                       href="${ctxPath}/admin/remark/group?groupId=${group.id}">查看书评</a></td>
            </tr>
            [/#list]
        [/#if]
        </tbody>
    </table>

</div>
</body>
</html>
