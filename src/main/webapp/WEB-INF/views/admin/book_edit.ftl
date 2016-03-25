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
		<form class="am-form form-horizontal" action="#" method="post" data-am-validator>
			<div class="am-form-group">
				<label class="control-label">图书名称</label>
				<div class="col-sm-10">
					<input type="hidden" name="book_id" value="12">
					<input type="text" class="form-control" name="title" value="营销的本质" required>
				</div>
			</div>
	  		<div class="am-form-group">
				<label for="recruit_annoncement" class="col-sm-2 control-label">作者</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="author" value="包政"  required>
				</div>
	  		</div>
	  		<div class="am-form-group">
				<label for="recruit_annoncement" class="col-sm-2 control-label">图书图像URL</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="image"  value="http://ec4.images-amazon.com/images/I/41W3OhqtirL._SX309_BO1,204,203,200_.jpg" required>
				</div>
	  		</div>
	  		<div class="am-form-group am-form-icon">
				<label for="recruit_annoncement" class="col-sm-2 control-label">图书简介</label><br>
				<textarea class="form-control" rows="6" name="summary">十年磨一剑，颠覆科特勒营销思想。从大量销售方式，到深度分销方式，未来属于社区商务方式……</textarea>
	  		</div>
	  		<button type="submit" class="am-btn am-btn-primary">保存</button>
		</form>
	</div>
	</body>
</html>
