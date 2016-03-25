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
		<a href="${ctxPath}/admin/group/edit">Add New Group</a>
		<hr>
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
						<td>${group.groupName}</td>
						<td>${group.captainName}</td>
						<td>${group.bookName}</td>
						<td>${group.beginTime ? string("yyyy-MM-dd")}</td>
						[#if group.status== "0"]
							<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group/approve?id=${group.id}&status=2">通过申请</a></td>
						[/#if]
							<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group/edit?groupId=${group.id}">Edit</a></td>
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
		
	</body>
</html>
