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
		<form class="am-form form-horizontal" action="${ctxPath}/admin/association/save" method="post" data-am-validator>
		<div class="am-form-group">
			<label class="control-label">小组名称</label>
			<div class="col-sm-10">
			[#if association??]
				<input type="hidden" name="id" value="${association.id}" >
			[/#if]
				<input type="text" class="form-control" name="name" placeholder="社群名称" value="[#if association??]${association.name}[/#if]" required>
			</div>
		</div>
		<div class="am-form-group">
			<label for="recruit_annoncement" class="control-label">口号</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="slogan" value="[#if association??]${association.slogan}[/#if]" placeholder="口号" required>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="col-sm-2 control-label">社群简介</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="6" name="associationDesc" placeholder="小组简介" required>[#if association??]${association.associationDesc}[/#if]</textarea>
			</div>
  		</div>
  		<button type="submit" class="am-btn am-btn-primary">保存</button>
	</form>
	</div>
</header>
</body>
</html>