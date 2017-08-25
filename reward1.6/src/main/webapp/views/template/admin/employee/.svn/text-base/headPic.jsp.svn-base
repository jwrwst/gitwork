<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com"%>
<script type="text/javascript">
var headPic = '${vo.headPic}';
</script>
		<link rel="stylesheet" href="../../../assets/def/css/cms.css">
<!-- <div class="pageContent">	
	<div class="pageFormContent" layoutH="58"> -->
		<input type="hidden" name="empId" id="empId" value="${vo.id }" />
		<div class="topfixed">
			头像：<input type="text" id="address" class="selfinput" style="height:30px;text-align:center;" readonly>
		   <input type="submit" value="上传" id="uploadphoto">		
		</div>
		<div class="centerpic">
			<div class="pic" id="nobigpic">
				<div class="selfbackicon nopicture" style="top:65%"></div>
			</div>
		</div>
		<div class="bombtn selfsubbtn">       
	            <input type="submit" id="selfbutn" value="提交" >		
		</div>
<!-- 		</div>
</div> -->
<script type="text/javascript">
	function pageRefushChild(t) {
		navTabAjaxDone(t);
		addform.reset();
		$.pdialog.closeCurrent();
		window.parent.document.getElementById("searchBnt").click();
	}
</script>




<div style="display: none;" id="fileList">
	<form action="" name="uploadForm" id="uploadForm" method="post"
		enctype="multipart/form-data"></form>
	<input id="imageInput" style="display: none;" type="file"
		name="myPhoto" onchange="loadImageFile();"
		accept="image/gif, image/jpeg">
</div>
<!-- <script type="text/javascript" src="../../../assets/plugin/dwz-plugin/js/jquery-1.7.2.js"></script> -->
<script src="http://image.haidilao.com/picservice/wap/cupload.js"></script>
<!-- <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> -->
<!-- <script type="text/javascript" src="../../../../assets/def/js/photograph/printManage.js?v=1"></script> -->
<!-- <script type="text/javascript" src="../../../../assets/def/js/photograph/cameraprint.js?v=10"></script> -->
<script type="text/javascript" src="../../../assets/def/js/photograph/print-photo.js?v=10"></script>
