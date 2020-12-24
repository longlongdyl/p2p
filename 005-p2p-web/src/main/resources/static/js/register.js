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
var loginPasswordFlag = false;
var phoneFlag = false;
var messageCode = false;
$(function () {

	$('#phone').blur(function () {
		var phone=$('#phone').val();
		if (phone==null || phone == ""){
			showError("phone","请输入电话号码");
			phoneFlag = false;
		}else if (!/^1[3|4|5|7|8][0-9]{9}$/.test(phone)){
			showError("phone","电话号码不合法");
			phoneFlag = false;
		}else {

			$.ajax({
				url:'/userRegister',
				data:{phone:$('#phone').val()},
				type:'post',
				success : function (data) {
					if (data.msg.indexOf('成功') >=0) {
						showSuccess("phone");
						phoneFlag = true;
					}else {
						showError("phone",data.msg);
						$("#loginPasswordOk").hide();
						loginPasswordFlag = false;
					}
				}
			});
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
		if (loginPasswordFlag==true && phoneFlag==true && messageCode==true){
			$.ajax({
				url:'/userRegister',
				data:{phone:$('#phone').val(),
					loginPassword:$('#loginPassword').val()},
				type:'post',
				success : function (data) {
					if (data.msg.indexOf('成功') >0){
						alert(data.msg);
						setTimeout(function(){
							location.href="/index";
							}, 500);
					}else {
						$('#phone').val('');
						$('#loginPassword').val('');
						showError("phone",data.msg);
						$("#loginPasswordOk").hide();
						loginPasswordFlag = false;
					}
				}

			})
		}
	})

	});


function sendMessage() {
	if (loginPasswordFlag==true && phoneFlag==true ) {
		var code = $("#code");
		code.attr("disabled", "true");
		setTimeout(function () {
			code.css("opacity", "0.8");
		}, 1000)
		var time = 10;
		var set = setInterval(function () {
			code.html(--time + "秒后重新获取");
		}, 1000);
		setTimeout(function () {
			code.attr("disabled", false).html("重新获取验证码");
			clearInterval(set);
		}, 10000);
	}
	var randomCode = ('000000' + Math.floor(Math.random() * 999999)).slice(-6);

	$.ajax({
		url:'/registerCode',
		data:{'randomCode':randomCode,'phone':$('#phone').val()}
	})
}


function mesblur() {
	if (loginPasswordFlag==true && phoneFlag==true ) {
		$.ajax({
			url: '/registerCode',
			type: 'post',
			data: {'code': $("#messageCode").val(), 'phone': $('#phone').val()},
			success: function (data) {
				if (data == "1") {
					showSuccess('messageCode');
					messageCode = true
				} else if (data == "2") {
					showError('messageCode', '请先获取验证码')
				} else {
					showError('messageCode', '验证码错误')
				}
			}
		})
	}
}
