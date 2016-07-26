<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/quoteTag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${resources}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${resources}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
	<script src="${resources}/js/jQuery-latest.js"></script>
	<script src="${resources}/js/common.js"></script>
	<script src="${resources}/plugin/jQuery-validation/jquery.validate.js"></script>
	<script src="${resources}/plugin/jQuery-validation/additional-methods.js"></script>
	<script src="${resources}/plugin/jQuery-validation/localization/messages_zh.js"></script>
	<title>实训平台-登录</title>
	<script type="text/javascript">
		$().ready(function () {

			$("#command").validate({
				rules: {
					username: {
						required: true
					},
					password: {
						required: true
					}
				},
				errorPlacement: function (error, element) {
					var obj = element.parent().next("div");
					$(obj).css("display", "inline-block");
					error.appendTo(obj);
				},
				success: function (label) {
					label.parent().css("display", "none");
				},
				invalidHandler: function (event, validator) {
					$.each(validator.errorList, function (index, result) {
						var obj = result.element;
						$(obj).parent().next("div").show();
					});
				},
				submitHandler: function (form) {
					/*var obj = $("#verificationCodePrompt");
					if (publicUtil.isEmpty($("verificationCode").val())) {
						obj.html("请拖动滑块完成验证");
						obj.show();
						return false;
					}*/
					form.submit();
				}
			});
		});

	</script>
</head>
<body style="padding: 10%">
<c:if test="${not empty errorMsg }">
	${errorMsg}
</c:if>
<div class="segment login-cont">
	<h3 class="login-header"><i class="fa fa-user"></i>用户登录</h3>
	<div class="login-form">
		<form:form id="command" action="login.html" method="post">
			<div class="input-cont">
				<i class="fa fa-user"></i>
				<input id="fm-login-username" class="login-input" type="text" name="username" placeholder="昵称"/>
			</div>
			<div class="prompt red above" style="display: none"></div>
			<div class="input-cont">
				<i class="fa fa-key"></i>
				<input id="fm-login-password" class="login-input" type="password" name="password" placeholder="密码"/>
			</div>
			<div class="prompt red above" style="display: none"></div>
			<div>
				<jsp:include page="common/verificationCode.jsp"/>
			</div>
			<input id="verificationCode" name="verificationCode" type="hidden"/>
			<div id="verificationCodePrompt" class="prompt red above verificationCode" style="display: none;"></div>
			<input class="btn btn-primary btn-shadow login-submit-btn" type="submit" value="登录"/>
		</form:form>
	</div>
</div>
</body>
</html>