/*js例子*/

/*
 * jQuery判断checkbox是否选中
 */
function checkboxIsSelect() {
	$("#id").is(":checked");
}

/**
 * 将表单序列化成字符串
 */
$("#form").serialize();

/*
 * $.ajax模版
 */
$.ajax({
	async:true,
	cache:true,
	contentType:"application/x-www-form-urlencoded",
	url:"http://",
	data:{
		"param":param
	},
	type:"POST",
	dataType:"String", /*预期服务器返回数据类型*/
	beforeSend:function(xhr){
		/**
		 * 发起请求先，设置头部
		 */
		xhr.setRequestHeader("X-Custom-Header1", "Bar");
	},
	success:function(result){
		/**
		 * 请求成功处理方法
		 */
	},
	error:function(event,xhr,options,exc){
		/**
		 * 错误处理
		 */
	},
	complete:function(event,xhr,options){
		/**
		 * 完成处理
		 */
	}
});
/**jsonp 类型下只能使用GET,不能用POST
 *  jsonp: "jsoncallback", 定义返回函数
 *  String callbackName = (String)request.getAttribute("jsoncallback");
 *  String renderStr = callbackName+"("+jsonStr+")"; 
 *  response.setContentType("text/plain;charset=UTF-8");
 */