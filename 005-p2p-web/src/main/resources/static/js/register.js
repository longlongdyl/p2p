//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}



//注册协议确认
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});
});

$(function () {
	var loginPasswordFlag = false;
	var phoneFlag = false;
	$('#phone').blur(function () {
		var phone=$('#phone').val();
		if (phone==null || phone == ""){
			showError("phone","请输入电话号码");
			phoneFlag = false;
		}else if (!/^1[3|4|5|7|8][0-9]{9}$/.test(phone)){
			showError("phone","电话号码不合法");
			phoneFlag = false;
		}else {
			showSuccess("phone");
			phoneFlag = true;
		}

	});
	$('#loginPassword').blur(function () {
		var loginPassword=$('#loginPassword').val();
		if (loginPassword==null || loginPassword == ""){
			showError("loginPassword","请输入密码");
			loginPasswordFlag = false;
		}else if (!/^(?=^[\d_a-zA-Z]{6,20}$)(?=(.*\d)+)(?=(.*[a-zA-Z])+).*$/.test(loginPassword)){
			showError("loginPassword","密码不合法");
			loginPasswordFlag = false;
		}else {
			showSuccess("loginPassword");
			loginPasswordFlag = true;
		}
	});


	$('#btnRegist').click(function () {
		if (loginPasswordFlag==true && phoneFlag==true){
			$.ajax({
				url:'/userRegister',
				data:{phone:$('#phone').val(),
					loginPassword:$('#loginPassword').val()},
				type:'post',
				success : function (data) {
					if (data.indexOf('成功') >0){
						alert(data);
						setTimeout(function(){
							location.href="/login";
							}, 500);
					}else {
						$('#phone').val('');
						$('#loginPassword').val('');
						showError("phone",data);
						$("#loginPasswordOk").hide();
						loginPasswordFlag = false;
					}
				}

			})
		}
	})
});