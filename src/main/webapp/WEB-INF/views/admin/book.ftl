[#ftl]
[#include "/_inc/inc.ftl"]
<!DOCTYPE html>
<html lang="zh">
<head>

</head>
	<body>
		[#include "head.ftl"]
		[#if userlist ??]
			[#list userlist as user]${user.id}[/#list]
		[/#if]
		[#if grouplist ??]
			[#list grouplist as group]${group.id}[/#list]
		[/#if]
	</body>
</html>
