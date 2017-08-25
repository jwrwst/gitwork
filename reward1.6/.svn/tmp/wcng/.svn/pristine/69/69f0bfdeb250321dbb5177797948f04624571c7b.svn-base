<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${restfulResult.page.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
		<span>条，共${restfulResult.page.totalCount}条</span>
	</div>
	
	<div class="pagination" targetType="navTab" totalCount="${restfulResult.page.totalCount}" numPerPage="${restfulResult.page.pageSize}" pageNumShown="10" currentPage="${restfulResult.page.pageNo}"></div>
</div>