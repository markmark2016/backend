[#ftl]
[#include "/_inc/inc.ftl"]
<!doctype html>
<html>
<head>

   	[#include "/_inc/meta.ftl"]
	[#include "/_inc/css.ftl"]
	[#include "/_inc/link.ftl"]
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	[#include "head.ftl"]
	<div class="admin-content">
		<form class="am-form form-horizontal" action="${ctxPath}/admin/group/save" method="post" data-am-validator>
		
		[#if group??]
			<input type="hidden" name="id" value="${group.id}" >
		[/#if]
		<input type="hidden" name="associationId" value="[#if associationId??]${associationId}[/#if]" >
		<input type="hidden" name="categoryIdFk" value="[#if categoryId??]${categoryId}[/#if]" >
		<div class="am-form-group">
			<label class="control-label">小组名称</label>
			<div class="col-sm-10">
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
			<label for="recruit_annoncement" class="col-sm-2 control-label">结束日期日期(*活动一旦开始则无法修改)</label>
			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
			 <input type="text" class="am-form-field" placeholder="结束日期" name="endTime" value="[#if group??]${group.endTime?string("yyyy-MM-dd")}[/#if]" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span></button>
			  </span>
			</div>
  		</div>
  		<div class="am-form-group">
			<label for="recruit_annoncement" class="col-sm-2 control-label">最晚加入日期(*活动一旦开始则无法修改)</label>
			<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
			 <input type="text" class="am-form-field" placeholder="最晚加入日期" name="latestTime" value="[#if group??][#if group.latestTime??]${group.latestTime?string("yyyy-MM-dd")}[/#if][/#if]" readonly>
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
			<label for="book_id" class="col-sm-2 control-label">图书</label><br>
			<input type="text" class="form-control" placeholder="图书" name="bookIdFk" required value="2" requried>
			<a href="#" class="am-btn am-btn-success" onclick="messageModel(' ')">选择图书</a>
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
	         		     输入书籍名称
	            </h4>
	         </div>
	         <div class="modal-body">
	       		   <input class="form-control" type="text" name="searchname" id="searchname" value="" />
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
			$('#messageModel').modal();
		};
</script>
</html>