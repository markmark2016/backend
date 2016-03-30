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
	<style type="text/css">
		.brand_table {
		    border-collapse: collapse;
		    border-spacing: 0;
		    margin-bottom: 20px;
		    max-width: 100%;
		    text-align: center;
		    width: 100%;
		}
		.brand_table tr > td {
		    border: 1px solid #ddd;
		    line-height: 35px;
		}
		.brand_table .table_bold {
		    font-weight: bold;
		}
	</style>
</head>
	<body>
		[#include "head.ftl"]
	<div class="admin-content">
		<!--<a href="${ctxPath}/admin/book/edit">Add New Book</a>-->
		<a class="am-btn am-btn-success" onclick="messageModel(' ')">新建书籍</a>
		<br/>
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
						<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/book/delete?bookId=${book.id?default('')}">删除</a></td>
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
		       		   <table id="xsph" class="brand_table" align="center">
		       		   		<tr>
		                    	<td id="title1" style='width:30%'></td>
		                    	<td id="author1" style='width:30%'></td>
		                    	<td id="summary1" style='width:30%'></td>
		                    	<td id="image1" style='width:30%'><img src=""/></td>
		                    	<td style='width:30%'><input type="radio" name="bookradio" value="1"></td>
		                    </tr>
		                    <tr>
		                    	<td id="title2" style='width:30%'></td>
		                    	<td id="author2" style='width:30%'></td>
		                    	<td id="summary2" style='width:30%'></td>
		                    	<td id="image2" style='width:30%'><img src=""/></td>
		                    	<td style='width:30%'><input type="radio" name="bookradio" value="2"></td>
		                    </tr>
		                    <tr>
		                    	<td id="title3" style='width:30%'></td>
		                    	<td id="author3" style='width:30%'></td>
		                    	<td id="summary3" style='width:30%'></td>
		                    	<td id="image3" style='width:30%'><img src=""/></td>
		                    	<td style='width:30%'><input type="radio" name="bookradio" value="3"></td>
		                    </tr>
		                    <tr>
		                    	<td id="title4" style='width:30%'></td>
		                    	<td id="author4" style='width:30%'></td>
		                    	<td id="summary4" style='width:30%'></td>
		                    	<td id="image4" style='width:30%'><img src=""/></td>
		                    	<td style='width:30%'><input type="radio" name="bookradio" value="4"></td>
		                    </tr>
		                    <tr>
		                    	<td id="title5" style='width:30%'></td>
		                    	<td id="author5" style='width:30%'></td>
		                    	<td id="summary5" style='width:30%'></td>
		                    	<td id="image5" style='width:30%'><img src=""/></td>
		                    	<td style='width:30%'><input type="radio" name="bookradio" value="5"></td>
		                    </tr>
		       		   </table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal">关闭
		            </button>
		            <button id="search" onclick="searchCheck()" type="button" class="btn btn-success">
		          		     查找
		            </button>
		            <button id="craeteBook" onclick="addCheck()" type="button" class="btn btn-primary">
		          		    提交
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
			function search(searchname){
				var ctxPath = '${ctxPath}';
				$.post(ctxPath + "/admin/book/searchadouban", {
					"searchname" : searchname
				}, function(data) {
					if (data.success) {
						var books = data.booklist;
						for(var i=0;i<books.length;i++){
							$("#title"+ (i+1)).html(books[i].title);
							$("#author"+ (i+1)).html(books[i].author);
							$("#summary"+ (i+1)).html(books[i].summary);
							var src = books[i].image;
							$("#image"+ (i+1)).children('img').attr({ src: src });
						}
					} else {
						alert('更新失败');
					}
				});
			}
			function addCheck(){
				var searchname = $.trim($("#searchname").val());
				if(searchname == ''){
					alert('请先搜索');
				}
				var id = $('input:radio[name=bookradio]:checked').val();
				if(id == undefined){
					alert('请选择一本书');
				}
				var title = $.trim($("#title"+id).text());
				var author = $.trim($("#author"+id).text());
				var summary = $.trim($("#summary"+id).text());
				var image = $.trim($("#image"+id).children('img').attr("src"));
				addPost(title,author,summary,image);
			}
			function addPost(title,author,summary,image){	
				var ctxPath = '${ctxPath}';
				$.post(ctxPath + "/admin/book/save", {
					"title" : title,
					"author" : author,
					"summary" : summary,
					"image" : image
				}, function(data) {
					if (data.success) {
						var bookId = data.bookId;
						alert('新添加的书籍Id：'+bookId);
					} else {
						alert('更新失败');
					}
				});
			}
			function messageModel(message) {
				$('#messageModel').modal();
			};
		</script>
</html>
