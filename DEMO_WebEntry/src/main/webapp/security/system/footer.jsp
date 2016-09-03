<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<a id="alertMsg" href="#myModal" role="button" data-toggle="modal" ></a>
<div class="modal small hide fade" id="myModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header" style="padding: 13px 15px;">
					<button style="margin-top:-10px;" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">提示标题</h3>
				</div>
				<div class="modal-body">
					<p class="error-text">
						<i id="myModalIcon" class="icon-warning-sign modal-icon"></i><span id="myModalContent">提示内容</span>
					</p>
				</div>
				<div class="modal-footer">
					<button id="myModalBtn_1" style="padding: 1px 11px;font-size: 12px;" class="btn btn-danger" data-dismiss="modal">确定</button>
					<button id="myModalBtn_2" class="btn_two" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
</div>

<div id="cmyModal" style="display: none;" tabindex="-1">
				<div class="modal-header" style="padding: 7px 15px;">
					<button style="margin-top:-5px;" type="button" class="close" onclick="msgBox_hide()">×</button>
					<h6 id="cmyModalLabel">提示标题</h6>
				</div>
				<div class="modal-body" style="padding:30px 15px;">
						<i id="cmyModalIcon" class="icon-ok-sign modal-icon" style="font-size: 3.5em"></i>
						<span id="cmyModalContent" style="margin: 0px 0px 0px 10px;position: relative;top: -8px;">提示内容</span>
				</div>
				<div class="modal-footer" style="padding:5px 10px;">
					<button class="btn_two" onclick="msgBox_hide()">确定</button>
				</div>
</div>

<footer>
	<hr style="margin:0px">

	<!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
	<!-- <p class="pull-right">
		A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free
			Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a>
	</p> -->

	<%--<p>
		&copy; 2013 <a href="http://www.hillsun.net" target="_blank">晓山信息产业股份有限公司</a>版权所有
	</p>--%>
</footer>