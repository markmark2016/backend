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
		<form class="am-form form-horizontal" action="${ctxPath}/admin/book/save" method="post" data-am-validator>
			[#if book??]
				<input type="hidden" name="id" value="${book.id?default('')}"></input>
			[/#if]
			<div class="am-form-group">
				<label class="control-label">图书名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="title" value="[#if book??]${book.title?default('')}[/#if]" required>
				</div>
			</div>
	  		<div class="am-form-group">
				<label for="recruit_annoncement" class="col-sm-2 control-label">作者</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="author" value="[#if book??]${book.author?default('')}[/#if]"  required>
				</div>
	  		</div>
	  		<div class="am-form-group">
				<label for="recruit_annoncement" class="col-sm-2 control-label">图书图像URL</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="image"  value="[#if book??]${book.image?default('')}[/#if]" required>
				</div>
	  		</div>
	  		<div class="am-form-group am-form-icon">
				<label for="recruit_annoncement" class="col-sm-2 control-label">图书简介</label><br>
				<textarea class="form-control" rows="6" name="summary">[#if book??]${book.summary?default('')}[/#if]</textarea>
	  		</div>
	  		<button type="submit" class="am-btn am-btn-primary">保存</button>
		</form>
	</div>
	</body>
</html>
