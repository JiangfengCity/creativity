var xhr;
//通用方法获取Ajax对象
var getXmlReq = function(){
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else {
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
};

/**
 * 解析JSON，兼容不支持JSON对象的浏览器
 * @param str
 * @returns
 */
function parseJson(str){
	var msg ;
	try{
		msg = JSON.parse(str);
	}catch(e){
		msg = eval("("+str+")");
	}
	return msg;
}

/**
 * 用于处理含有iframe框架的页面出现内嵌超时页面问题
 */
function checkTimeoutForIframe(){
	getXmlReq();
    xhr.onreadystatechange = function(){
		if (xhr.readyState == 4) {
			if(xhr.responseText == "success"){
			}else{
				location.href = login_url;//需要在父页面定义js变量login_url
			}
		}
	};
    xhr.open("GET",assembleUrlAndMill(check_url), false);//需要在父页面定义js变量check_url
    xhr.send(null);
}

/**
 * 下面两函数用于取消浏览器自动缓存相同url请求的功能
 * @param url
 */
function gotoDest(url){
	location.href = assembleUrlAndMill(url);
}
function assembleUrlAndMill(url){
	var new_url = url+(url.indexOf("?") == -1?"?":"&")+new Date().getTime();
	return new_url;
}

/**
 * 下面两函数可用于服务器返回消息正确和错误提示的处理
 * @param $target
 * @param msg
 */
function showTipError($target,msg){
	$target.css("color","#ee8100");
	$target.html(msg);
	setTimeout(function() {
		$target.html("");
	}, 3000);
}
function showTipRight($target,msg){
	$target.css("color","#007c02");
	$target.html(msg);
	setTimeout(function() {
		$target.html("");
	}, 3000);
}

/**
 * 处理iframe页面下导航栏被选中时的醒目切换，不能算是通用函数
 * @param url
 * @param target
 */
function changeIframeUrl(url,target){
	checkTimeoutForIframe();
	$(".element_lists li").removeClass('selected');//注意此处的css的class的定义
	$(target).addClass('selected');
	$("#main").attr("src",assembleUrlAndMill(url));
}