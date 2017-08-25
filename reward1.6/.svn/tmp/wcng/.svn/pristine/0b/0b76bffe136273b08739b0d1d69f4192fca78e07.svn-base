<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script type="text/javascript">
        function pageRefush(t){
	         document.getElementById("searchBnt").click();
        }
        
        function toCusAdmin(orgCode){
        	window.localStorage.setItem("orgCode", orgCode);
        	window.open("${webpath }/rs/external/merchants/wap/loginAdmin?orgCode="+orgCode, "_blank");
        }
        
        $(function(){
            if("${param.noload}"==null||"${param.noload}"==""){
                pageRefush();
            }
        });
        
    </script>
</head>
<body>

<c:import url="../../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/inner/organize/toPagePost" method="get" name="listform">
	<div class="searchBar">
		<table style="width:100%">
			<tr>
				<td width="30%">商户名称：<input type="text" name="key" value="${key }"/></td>
				<td style="text-align: right;">
					<ul>
						<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchBnt">检索</button></div></div></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">			
			<li><a title="确实要批量审核这些记录吗?"  callback="pageRefush" target="selectedTodo" rel="id" postType="string" href="${webpath }/rs/inner/organize/updateList?status=2" class="delete"  ><span>批量审核</span></a></li>			
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl">商户号</th>
				<th width="120">商户名称</th>
				<th width="80" >创建日期</th>
				<th width="70">审核状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.page.result }">
	            <tr target="sid_user" rel="${vo.orgCode }"> 
	                  <td><input name="id" value="${vo.orgCode }" type="checkbox">${vo.orgCode }</td> 
	                  <td><a href="javascript:toCusAdmin('${vo.orgCode }')" >${vo.orgName }</a></td> 
	                  <td>${vo.formatTime}</td> 
	                  <td>${vo.status == "1"?"待审核":(vo.status == "2")?"已通过审核":"未通过审核"}</td> 
	                  <td>
	                    <a target="dialog"  width="600" height="500"  href="${webpath }/rs/inner/organize/getMerchantsInfo?orgCode=${vo.orgCode }&op=dis" >查看详情</a>
	                    <c:if test='${vo.status eq "3"}'>   
							<a title="请仔细核对信息，确定要审核通过吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/organize/updateList?orgCodes=${vo.orgCode }&status=2" >审核通过</a>				
						</c:if>		
	                    <c:if test='${vo.status eq "1"}'>   
							<a title="请仔细核对信息，确定要审核通过吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/organize/updateList?orgCodes=${vo.orgCode }&status=2" >审核通过</a>
							<a target="dialog"  width="600" height="500" href="${webpath }/rs/inner/organize/getMerchantsInfo?orgCode=${vo.orgCode }&op=edit">审核不通过</a>
						</c:if>
										

					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>