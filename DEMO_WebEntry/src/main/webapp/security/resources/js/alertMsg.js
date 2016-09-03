//错误信息
var wrongmsg = function(msg) {
	$("#cmyModalLabel").text(" ");
	$("#cmyModalIcon").attr('class','icon-remove-sign modal-icon');
	$("#cmyModalContent").text(msg);
	msgBox_show();
};
//提示信息
var infomsg = function(msg) {
	$("#cmyModalLabel").text(" ");
	$("#cmyModalIcon").attr('class','icon-ok-sign modal-icon');
	$("#cmyModalContent").text(msg);
	msgBox_show();
};
//警告信息
var warnmsg = function(msg,fn) {
	$("#myModalLabel").text(" ");
	$("#myModalIcon").attr('class','icon-warning-sign modal-icon');
	$("#myModalContent").text(msg);
	$("#myModalBtn_1").attr('onclick',fn).show();
	$("#myModalBtn_2").text("取消");
	document.getElementById("alertMsg").click();
};
var msgBox_show=function(){
	$("#cmyModal").show();
};
var msgBox_hide=function(){
	$("#cmyModal").hide();
};