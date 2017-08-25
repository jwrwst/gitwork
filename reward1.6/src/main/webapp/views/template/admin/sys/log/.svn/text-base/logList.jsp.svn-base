<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <c:import url="../../../js/auth.jsp"></c:import>
    <script type="text/javascript">
        function pageRefush(t){
	         document.getElementById("searchBnt").click();
        }
        function onsub(){
            if($("#startDate").val()!=""&&$("#endDate").val()!=""&&$("#startDate").val()>$("#endDate").val()){
                   alert("开始时间不能大于结束时间,请重新选择。");
                   return ;
            }
             $("#listform").submit();
        }
        $(function(){
          $("#key").val("${key}");
        })
    </script>
</head>
<body>

<c:import url="../../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/pl/logmanager/page" method="post" name="listform" id="listform">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>操作类型：</label>
				<select class="combox" name="key" id="key">
					<option value="">--全部--</option>
					<option value="login">登陆</option>
					<option value="save">保存</option>
					<option value="delete">删除</option>
					<option value="update">修改</option>
				</select>
			</li>
			<li>
				<label>开始时间：</label>
				<input type="text" name="startDate" class="date" readonly="true" value="${startDate }"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
			    <p>
				<label>结束时间：</label>
				<input type="text" name="endDate" class="date" readonly="true" value="${endDate }"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				</p>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="onsub()" id="searchBnt">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
   <div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?"  callback="pageRefush" target="selectedTodo" rel="id" postType="string" href="${webpath }/rs/pl/logmanager/delete" class="delete" auth="userLogDelete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl"></th>
				<th width="120">IP地址</th>
				<th >操作员</th>
				<th >操作方式</th>
				<th width="80" orderField="operatorTime">创建日期</th>
				<th width="70">备注</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.data }">
	            <tr target="sid_user" rel="${vo.log_id }"> 
	                  <td><input name="id" value="${vo.log_id }" type="checkbox">${vo.log_id }</td> 
	                  <td>${vo.ipAddr }</td> 
	                  <td>${vo.operatorUserName }</td> 
	                  <td>${vo.operatorType }</td> 
	                  <td>${vo.operatorTime }</td> 
	                  <td>${vo.message }</td> 
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>