[#ftl]
[#include "/_inc/inc.ftl"]
<!DOCTYPE html>
<html lang="zh">
<head>
	[#include "/_inc/meta.ftl"]
	[#include "/_inc/css.ftl"]
	[#include "/_inc/link.ftl"]
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
	<body>
		[#include "head.ftl"]
	<div class="admin-content">
		<a href="${ctxPath}/admin/book/edit">Add New Book</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="am-btn am-btn-success" onclick="messageModel(' ')">新建书籍</a>
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
	
	
	<!-- 模态框（Modal） -->
		<div class="modal fade" id="messageModel" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		         		      新建书籍
		            </h4>
		         </div>
		         <div class="modal-body">
		       		   名称：<input class="form-control" type="text" name="searchname" id="searchname" value="" />
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal">关闭
		            </button>
		            <button id="createCategory" onclick="searchCheck()" type="button" class="btn btn-primary">
		          		     提交更改
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</body>
		<script type="text/javascript">
			function searchCheck(){
				var searchname = $.trim($("#searchname").val());
				if(searchname == ''){
					alert('输入书名');
				}else{
					search(searchname);
				}
			}
			function search(searchanme){
				var ctxPath = '${ctxPath}';
				$.post(ctxPath + "/admin/book/searchadouban", {
					"searchname" : searchname
				});
			}
			function messageModel(message) {
				$('#messageModel').modal();
			};
		</script>
</html>
