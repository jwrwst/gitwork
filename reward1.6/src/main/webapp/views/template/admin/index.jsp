<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>众赏系统管理平台</title>
<link rel="stylesheet" type="text/css" href="../../../assets/def/css/applet.css">
<link rel="shortcut icon" href="/assets/favicon.ico" />
<script src="/assets/plugin/dwz-plugin/dwz-base-load.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/assets/plugin/dwz-plugin/dwz.frag.xml", {
//		loginUrl:"/plsystem", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"/admin",// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/assets/plugin/dwz-plugin/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
<script type="text/javascript">
    $(function(){
           //var  username=getCookie("loginname");
           //$("#userformat").html("用户: "+username);
    });
    function settitle(title){
        $("#main_title").html(title);
        //此操作是为了在切换页面时，关闭长连接
        try{onCloseAllConnection();}catch(e){}
        
    }
</script>
<script type="text/javascript">
//路径地址
var serviceUrl="";
function logout(){
	window.location.href = serviceUrl+"/admin";
}
</script>
</head>

<body scroll="no">
       <div style="display: none;" id="menuhtml">
          <c:forEach var="item" items="${userAuthList}">
			     <script type="text/javascript">
			          if("${item.authType}"=="01"){
			              $(".accordion").append("<div class='accordionHeader'><h2><span>Folder</span>${item.authName}</h2></div>");
			              $(".accordion").append("<div class='accordionContent'><ul class='tree treeFolder' id='menu${item.authId}'></ul></div>");
			          }
			          
			          //循环所有树形结构
			          $(".tree").each(function(obj){
			               //当当前元素的父ID等于当前树形的根节点ID
			               if("menu${item.parentId}"==$(this).attr("id")){
			                    if("${item.authUrl}"==null||"${item.authUrl}"==""){
			                    	$("#"+$(this).attr("id")).append("<li id='menu${item.authId}'><a href='javascript:;'>${item.authName}</a></li>");
			                    }else{
			                       	$("#"+$(this).attr("id")).append("<li id='menu${item.authId}'><a href='${item.authUrl}' target='navTab' rel='main' onclick='settitle(\"${item.authName}\")'>${item.authName}</a></li>");
			                    }
			               //否则查询当前元素在哪个子节点上面
				           }else{
				                //循环当前树形下面的所有li元素
					            $("#"+$(this).attr("id")).find("li").each(function(t){
					                if("menu${item.parentId}"==$(this).attr("id")){
					                    //如果是操作按键不显示
					                    if("${item.authType}"!="03"){
						                    //当存在子元素，首先创建一个ul元素
						                    if($(this).children("ul").length==0){
						                       $(this).append("<ul id='menuul"+$(this).attr("id")+"'></ul>");
						                    }
						                    //添加子元素
						                    if("${item.authUrl}"==null||"${item.authUrl}"==""){
						                        $("#menuul"+$(this).attr("id")).append("<li id='menu${item.authId}'><a href='javascript:;'>${item.authName}</a></li>");
						                    }else{
						                        $("#menuul"+$(this).attr("id")).append("<li id='menu${item.authId}'><a href='${item.authUrl}' target='navTab' rel='main' onclick='settitle(\"${item.authName}\")'>${item.authName}</a></li>");
						                    }
					                    }
					                }
					            });
				          }
			          });
				 </script>
		</c:forEach>
    </div>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a style="height:50px; width:auto;float: left;color:#b9ccda;text-decoration: none;line-height: 50px;">
				  <img src="'../../../../../assets/def/images/logo.png" alt="" style="height:50px;float:left;margin-right:30px;"/>
				  <span style="display: inline-block;height:50px;line-height: 50px;font-size: 25px;">众赏后台管理系统</span>
				</a>
				<ul class="nav">
					<li><a href="javascript:void(0)" id="logoutId" onclick="logout();">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>
        
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2 id="userformat">菜单管理</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
				</div>
				<script type="text/javascript">
				    $(".accordion").append($("#menuhtml").html());
				</script>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;" id="main_title" style="padding-left:10px;padding-right:10px;">欢迎页面</a></li>
						</ul>
					</div>
				</div>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
					</div>
					
				</div>
			</div>
		</div>

	</div>

</body>
</html>