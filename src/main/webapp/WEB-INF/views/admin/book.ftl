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
	</body>
	<div class="admin-content">
		<a href="${ctxPath}/admin/book/edit">Add New Book</a>
		<form class="am-form form-horizontal" action="${ctxPath}/admin/book/search" method="POST" data-am-validator>
			<input type="text" class="form-control" name="bookName" placeholder="书籍名称" value="" >
			<button type="submit" class="am-btn am-btn-primary">查询</button>
		</form>
		<hr>
		<table class="am-table">
			<thead>
				<tr>
					<td>#ID</td>
					<td>Book Name</td>
					<td>Author</td>
					<td>Image</td>
					<td>Summary</td>
					<td>***</td>
				</tr>
			</thead>
			<tbody>
				[#if bookList??]
				[#list bookList as book]
					<tr>
						<td>${book.id?default('')}</td>
						<td>${book.title?default('')}</td>
						<td>${book.author?default('')}</td>
						<td>
							<img style="width:50px;height:auto;" src="${book.image?default('')}">
						</td>
						<td>${book.summary?default('')}</td>
						<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/book/edit?bookId=${book.id?default('')}">Edit</a></td>
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
</html>
