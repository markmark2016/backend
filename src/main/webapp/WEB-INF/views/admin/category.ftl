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
		<input type="hidden" name="associationId" value="${associationId}" id="associationId"/>
		<div class="admin-content">
		<a class="am-btn am-btn-success" onclick="messageModel(' ')">新建类别</a>
		<hr>
		<table class="am-table">
			<thead>
				<tr>
					<td>类别名</td>
					<td>***</td>
					<td>***</td>
					<td>***</td>
				</tr>
			</thead>
			<tbody>
			[#if categorylist??]
				[#list categorylist as category]
					<tr>
						<td id="category_${category.id}">${category.categoryName}</td>
						<td><a class="am-btn am-btn-primary" onclick="messageModel('${category.id}')">Edit</a></td>
						<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/group?associationId=${associationId}&categoryId=${category.id}">小组</a></td>
						<td><a class="am-btn am-btn-primary" href="${ctxPath}/admin/category/delete?categoryId=${category.id}&associationId=${associationId}">删除</a></td>
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
		         		      新建类别
		            </h4>
		         </div>
		         <div class="modal-body">
		       		   名称：<input class="form-control" type="text" name="newname" id="newname" value="" />
		       		   <input type="hidden" id="categoryId" name="categoryId" val="" />
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal">关闭
		            </button>
		            <button id="createCategory" onclick="checkAdd()" type="button" class="btn btn-primary">
		          		     提交更改
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	
	
	<script type="text/javascript">
	
		function checkAdd(){	
			var categoryName = $.trim($("#newname").val());
			var associationId = $.trim($("#associationId").val());
			var categoryId = $.trim($("#categoryId").val());
			if(categoryName == ''){
				alert('请输入类别名字');
			}else{
				addPost(associationId,categoryName,categoryId);
			}
		}
		
		function addPost(associationId,categoryName,categoryId){	
			var ctxPath = '${ctxPath}';
			$.post(ctxPath + "/admin/category/save", {
				"associationId" : associationId,
				"categoryId" : categoryId,
				"categoryName" : categoryName
			}, function(data) {
				if (data.success) {
					window.location.href = '${ctxPath}'
							+ "/admin/category?associationId=${associationId}";
				} else {
					alert('更新失败');
				}
			});
		}
		
	
		function messageModel(message) {
			var categorynameid = '#category_' + message;
			var categoryname = $(categorynameid).text()
			$('#newname').val(categoryname);
			$('#categoryId').val(message);
			$('#messageModel').modal();
		};
		
	</script>
	</body>
</html>
