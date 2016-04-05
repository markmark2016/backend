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
			<h2>总用户数：${userlist?size}</h2>
			<form class="am-form form-horizontal" action="${ctxPath}/admin/users/search" method="POST" data-am-validator>
				<input type="text" class="form-control" name="nickName" placeholder="用户昵称" value="" >
				<button type="submit" class="am-btn am-btn-primary">查询</button>
			</form>
			<table class="am-table">
				<thead>
					<tr>
						<td>#</td>
						<td>User Name</td>
						<td>Head Image</td>
						<!-- <td>Subscribe Time</td> -->
						<td>Province</td>
						<td>City</td>
						<!-- <td>UnSubscribe Time</td> -->
					</tr>
				</thead>
				<tbody>
				[#list userlist as user]
					<tr>
						<td>${user.id?c?default("")}</td>
						<td>${user.nickname?default("")}</td>
						<td><img style="width:24px;height:auto;" src="${user.headImgUrl?default("")}"></td>
						<!--<td>2015-10-24 12:39:26</td> -->
						<td>${user.province?default("")}</td>
						<td>${user.city?default("")}</td>
						<!-- <td></td> -->
					</tr>
				[/#list]
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
