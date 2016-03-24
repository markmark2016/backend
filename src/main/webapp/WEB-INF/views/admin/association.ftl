[#ftl]
[#include "/_inc/inc.ftl"]
<!doctype html>
<html>
<head>
   	[#include "/_inc/meta.ftl"]
	[#include "/_inc/css.ftl"]
	[#include "/_inc/link.ftl"]
</head>
<body>
	[#include "head.ftl"]
	
	<div class="admin-content">
		<a href="${ctxPath}/admin/association/edit">新建社群</a>
		<hr>
		<table class="am-table">
			<thead>
				<tr>
					<td>社群名</td>
					<td>社群简介</td>
					<td>读书口号</td>
					<td>***</td>
				</tr>
			</thead>
			<tbody>
			[#if list??]
				[#list list as association]
					<tr>
						<td>${association.name}</td>
						<td>${association.associationDesc}</td>
						<td>${association.slogan}</td>
						<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/association/edit?associationId=${association.id}">Edit</a></td>
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