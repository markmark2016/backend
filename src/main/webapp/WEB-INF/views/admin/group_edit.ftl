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
		<form class="am-form form-horizontal" action="${ctxPath}/admin/group/save" method="post" data-am-validator>
		<div class="am-form-group">
			<label class="control-label">小组名称</label>
			<div class="col-sm-10">
			[#if group??]
				<input type="hidden" name="id" value="${group.id}" >
			[/#if]
				<input type="text" class="form-control" name="groupName" placeholder="小组名称" value="[#if group??]${group.groupName}[/#if]" required>
			</div>
		</div>
		<div class="am-form-group">
			<label for="recruit_annoncement" class="control-label">口号</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="readSlogan" value="[#if group??]${group.readSlogan}[/#if]" placeholder="口号" required>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="col-sm-2 control-label">开始日期(*活动一旦开始则无法修改)</label>
			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
			 <input type="text" class="am-form-field" placeholder="开始日期" name="beginTime" value="[#if group??]${group.beginTime?string("yyyy-MM-dd")}[/#if]" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span></button>
			  </span>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="col-sm-2 control-label">小组简介</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="6" name="groupDesc" placeholder="小组简介" required>[#if group??]${group.groupDesc}[/#if]</textarea>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="control-label">小组管理员Email(登陆用)</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="captainEmail" value="[#if group??]${group.captainEmail}[/#if]" placeholder="Email" required>
			</div>
  		</div>
  		<!--
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="control-label">小组管理员密码(登陆用, 留空则不修改，如果填写则覆盖原密码)</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="group_leader_password" placeholder="Password">
			</div>
  		</div>
  		-->
  		<div class="am-form-group am-form-icon">
			<label for="book_id" class="col-sm-2 control-label">图书ID</label><br>
			<input type="text" class="form-control" placeholder="图书id" name="bookIdFk" required value="2" requried>
   		</div>
   		<div class="am-form-group am-form-icon">
			<label for="creator_id" class="col-sm-2 control-label">用户ID</label><br>
			<input type="text" class="form-control" placeholder="用户id" name="userIdFk" required value="[#if group??]${group.userIdFk}[/#if]" required>
   		</div>
   		<div class="am-form-group am-form-icon">
			<label for="wechat_qrcode" class="col-sm-2 control-label">加入小组后显示的二维码</label><br>
			<input type="text" class="form-control" placeholder="请填写二维码的对应文本，程序会自动生成二维码图案" name="wechatQrcode" value="[#if group??]${group.wechatQrcode}[/#if]" required>
   		</div>
  		<div class="am-form-group">
			<label for="mode" class="col-sm-2 control-label">新增成员方式</label>
			<div class="col-sm-10">
				<input type="radio" class="am-checkbox-inline" name="groupMode" value="1" ?>&nbsp;<span>无限制加入</span>&nbsp;
				<input type="radio" class="am-checkbox-inline" name="groupMode" value="0" ?>&nbsp;<span>活动开始后不能再加入</span>&nbsp;
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="listVisiable" class="col-sm-2 control-label">列表是否可见</label>
			<div class="col-sm-10">
				<input type="radio" class="am-checkbox-inline" name="listVisiable" value="1" ?>&nbsp;<span>可见</span>&nbsp;<br>
				<input type="radio" class="am-checkbox-inline" name="listVisiable" value="0" ?>&nbsp;<span>不可见</span>&nbsp;<br>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="remarkVisiable" class="col-sm-2 control-label">书评是否可见</label>
			<div class="col-sm-10">
				<input type="radio" class="am-checkbox-inline" name="remarkVisiable" value="1" ?>&nbsp;<span>可见</span>&nbsp;<br>
				<input type="radio" class="am-checkbox-inline" name="remarkVisiable" value="0" ?>&nbsp;<span>不可见</span>&nbsp;<br>
			</div>
  		</div>
  		<button type="submit" class="am-btn am-btn-primary">保存</button>
	</form>
	</div>
</body>
<script type="text/javascript">
	[#if group??]]
		$(function(){
			var groupMode = "${group.groupMode ? default("")}";
			var remarkVisiable = "${group.remarkVisiable ? default("")}";
			var listVisiable = "${group.listVisiable ? default("")}";
			
			$('input:radio[name=groupMode]').each(function(){
		    	if ($(this).val() == groupMode) { 
					$(this).attr("checked", true); 
				}    
		    });
	    	$('input:radio[name=remarkVisiable]').each(function(){
	    	if ($(this).val() == remarkVisiable) { 
				$(this).attr("checked", true); 
			}    
	 	    });
	    	$('input:radio[name=listVisiable]').each(function(){
	    	if ($(this).val() == listVisiable) { 
				$(this).attr("checked", true); 
			}    
	   		});
		    
		});
	[/#if]]
		
</script>
</html>