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
						<td>15</td>
						<td>${user.nickname}</td>
						<td><img style="width:24px;height:auto;" src="${user.headImgUrl}"></td>
						<!--<td>2015-10-24 12:39:26</td> -->
						<td>${user.province}</td>
						<td>${user.city}</td>
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
