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
		<form id="foo" class="am-form form-horizontal" action="${ctxPath}/admin/association/save" method="post" enctype="multipart/form-data" data-am-validator>
		<div class="am-form-group">
			<label class="control-label">小组名称</label>
			<div class="col-sm-10">
			[#if association??]
				<input id="id" type="hidden" name="id" value="${association.id}" >
			[/#if]
				<input type="text" id="name" class="form-control" name="name" placeholder="社群名称" value="[#if association??]${association.name}[/#if]" required>
			</div>
		</div>
		<div class="am-form-group">
			<label for="recruit_annoncement" class="control-label">口号</label>
			<div class="col-sm-10">
				<input type="text" id="slogan" class="form-control" name="slogan" value="[#if association??]${association.slogan}[/#if]" placeholder="口号" required>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="col-sm-2 control-label">社群简介</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="associationDesc" rows="6" name="associationDesc" placeholder="小组简介" required>[#if association??]${association.associationDesc}[/#if]</textarea>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="picture" class="col-sm-2 control-label">社群图片</label>
			<div class="col-sm-10">
				 <input type="file" id="pictureUrl" name="picture" alt="图片"/>
				 [#if pic??]
				 	<img src="${pic}"/>
				 [/#if]
			</div>
  		</div>
  		<button type="button" class="am-btn am-btn-primary" onClick="checkAddForm()">保存</button>
	</form>
	</div>
</header>
<script type="text/javascript">
	function checkAddForm(){
		var ctxPath = '${ctxPath}';
		var id = $.trim($("#id").val())
		var name = $.trim($("#name").val());
		var slogan = $.trim($("#slogan").val());
		var associationDesc = $.trim($("#associationDesc").val());
		var pictureUrl = $.trim($("#pictureUrl").val());
		if(pictureUrl != ""){
			$.ajaxFileUpload({
			    url: ctxPath + '/admin/upload',
			    secureuri: false,
			    fileElementId: ["pictureUrl"],
			    dataType: 'JSON',
			    success: function(data) {
					var jsonData = data.replace("<pre>", "").replace(
								"</pre>", "");
					var obj = jQuery.parseJSON(jsonData);
			        if (obj.status) {
						addPost(id,name, slogan, associationDesc,obj.pictureUrl);
			        } else {
			           alert(上传失败);
			        }
			    }
			});
		}else{
			$("#foo").submit();
		}
	};
	
	function addPost(id,name, slogan, associationDesc,pictureUrl){
		var ctxPath = '${ctxPath}';
		$.post(ctxPath + "/admin/association/save", {
			"id" : id,
			"name" : name,
			"slogan" : slogan,
			"associationDesc" : associationDesc,
			"pictureUrl" : pictureUrl
		}, function(data) {
			if (data.success) {
				window.location.href = '${ctxPath}'
						+ "/admin/association";
			} else {
			}
		});
		window.location.href = '${ctxPath}'
						+ "/admin/association";
	};
	
</script>
</body>
</html>