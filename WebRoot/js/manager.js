/**
 * 通用删除操作的入口函数
 * @param url
 * @param target
 */
function doDelete(url,target){
	if(confirm("确认删除用户？")){
		asycDelete(url,target);
	}
}

/**
 * 处理禁用/启用功能的通用执行函数，参数target=this
 * @param url
 * @param target
 */
function doActivateOrFobidden(url,target){
	getXmlReq();
    xhr.onreadystatechange = function(){
		if (xhr.readyState == 4) {
			if(xhr.responseText){
				try{
					var msg = parseJson(xhr.responseText);
					if(msg.status == 'fail'){
						alert(msg.msg+",操作失败！");
					}else if(msg.status == 'success'){
						target.innerHTML = msg.msg;
						$(target).html(msg.msg);
						$prev = $(target).parent().prev();
						$prev.html(msg.extend);
						$prev.toggleClass("c");
					}
				}catch(e){
					alert("交互过程有异常！");
				}
			}
		}
	};
    xhr.open("GET",assembleUrlAndMill(url), false);
    xhr.send(null);
}

/**
 * 处理删除功能的通用执行函数，参数target=this
 * @param url
 * @param target
 */
function asycDelete(url,target){
	getXmlReq();
    xhr.onreadystatechange = function(){
		if (xhr.readyState == 4) {
			if(!xhr.responseText){
			}else{
				try{
					var msg = parseJson(xhr.responseText);
					if(msg.status == 'fail'){
						alert(msg.msg+",删除操作失败！");
					}else if(msg.status == 'success'){
						$(target).parent().parent().remove();							
						//console.log(currentUsers);
						$("#current").html(parseInt($("#current").html())-1);
					}
				}catch(e){
					alert("交互过程有异常！");
				}
			}
		}
	};
    xhr.open("GET",assembleUrlAndMill(url), false);
    xhr.send(null);
}