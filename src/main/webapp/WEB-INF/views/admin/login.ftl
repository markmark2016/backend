[#ftl]
[#include "/_inc/inc.ftl"]
<!DOCTYPE html>
<html lang="zh">
<head>
[#include "/_inc/meta.ftl"]
<script src="${ctxPath}/resources/assets/js/jquery-1.8.0.min.js" type="text/javascript" ></script>
<script type="text/javascript">
	function submitForm(){
		$('#postform').submit();
	}
</script>

<link rel="stylesheet" type="text/css" href="${ctxPath}/resources/assets/css/register.css"/>

</head>
<body>



<div class='signup_container'>

   <h1 class='signup_title'>用户登陆</h1>
    <img src='${ctxPath}/resources/assets/imgs/people.png' id='admin'/>
    <div id="signup_forms" class="signup_forms clearfix">
            <form id="postform" class="signup_form_form" id="signup_form" method="post" action="${ctxPath}/admin/login" data-secure-action="" data-secure-ajax-action="">
                    <div class="form_row first_row">
                        <label for="signup_email">请输入用户名</label>
                        <input type="text" name="username" placeholder="请输入用户名" id="signup_name" data-required="required">
                    </div>
                    <div class="form_row">
                        <label for="signup_password">请输入密码</label>
                        <input type="password" name="password" placeholder="请输入密码" id="signup_password" data-required="required">
                    </div>
           </form>
    </div>

    <div class="login-btn-set"><button onclick="submitForm()" class='login-btn'></button></div>
</div>

</body>

</html>
