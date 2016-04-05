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
		<a href="#" ><h1 onclick="addNewGroup()">Add New Group</h1></a>
		<hr>
		[#if association??]社群：${association.name ? default("")}-------------[/#if]
		[#if category ??]类目：${category.categoryName ? default("")}[/#if]
		<input type="hidden" id="associationId" name="associationId" value="[#if association??]${association.id ? default("")}[/#if]" /> 
		<input type="hidden" id="categoryId" name="categoryId" value="[#if category ??]${category.id ? default("")}[/#if]" /> 
		<table class="am-table">
			<thead>
				<tr>
					<td>Group Name</td>
					<td>Group Leader</td>
					<td>Book Name</td>
					<td>Start Date</td>
					[#if apply??]
					<td>***</td>
					[/#if]
					<td>***</td>
				</tr>
			</thead>
			<tbody>
			[#if groupList??]
				[#list groupList as group]
					<tr>
						<td>[#if group.groupName??]${group.groupName?default('')}[/#if]</td>
						<td>[#if group.captainName??]${group.captainName?default('')}[/#if]</td>
						<td>[#if group.bookName??]${group.bookName?default('')}[/#if]</td>
						<td>[#if group.beginTime??]${group.beginTime ? string("yyyy-MM-dd")}[/#if]</td>
						[#if group.status== "0"]
							<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group/approve?id=${group.id}&status=2">通过申请</a></td>
						[/#if]
							<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group/edit?groupId=${group.id}">Edit</a></td>
							<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group/delete?groupId=${group.id}">删除</a></td>
					</tr>
				[/#list]
			[/#if]
			</tbody>
		</table>
		<!--
		<ul data-am-widget="pagination" class="am-pagination am-pagination-select am-no-layout">
			<li class="am-pagination-prev ">
				<a href="#" class="">上一页</a>
			</li>
	        <li class="am-pagination-select">
	          <select>
	              <option value="#" class="">2 / 3</option>
	          </select>
	        </li>
		    <li class="am-pagination-next ">
	    		<a href="#" class="">下一页</a>
		  	</li>
		</ul>
		-->
	</div>
		<script type="text/javascript">
			function addNewGroup(){
				var associationId = $.trim($("#associationId").val());
				var categoryId = $.trim($("#categoryId").val());
				if(associationId == ''){
					window.location.href = '${ctxPath}'
						+ "/admin/group/edit";
				}else{
					window.location.href = '${ctxPath}'
						+ "/admin/group/edit?associationId=" + associationId + "&categoryId=" + categoryId;
				}
			}
		</script>
	</body>
</html>
