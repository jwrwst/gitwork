<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    //成功页面刷新
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         //addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }


</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/organize/update" name="addform"  class="pageForm required-validate"  onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
		
			<div class="divider">divider</div>
			<div class="unit">
				<label>注册人：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.name}"/>
			</div>
			<div class="unit">
				<label>联系方式：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.mobile}"/>
			</div>
			
			
			<div class="divider">divider</div>
			<div class="unit">
				<label>品牌名称：</label>
				<input type="hidden" name="status" value="3">
				<input type="hidden" name="orgCode" value="${bsMerchantsInfo.orgCode }">
				<input type="text" readonly="readonly" value="${bsMerchantsInfo.orgName }"/>
			</div>
			<div class="unit">
				<label>企业名称：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.merName }"/>
			</div>
			<div class="unit">
				<label>手机号：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.phone }"/>
			</div>
			<div class="unit">
				<label>地址：</label>
				<input readonly="readonly" value="${ bsMerchantsInfo.address}"/>
			</div>
			<div class="unit">
				<label>商户类型：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.typeName } ${ registerMerchantsEntity.codeTypeName }"/>
			</div>
			<div class="unit">
				<label>邮箱：</label>
				<input readonly="readonly" value="${ bsMerchantsInfo.email}"/>
			</div>
			
			<div class="unit">
				<label>企业资质：</label>
				<img src="${bsMerchantsInfo.photo} " style="width:200px;">
				
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>店铺名称：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.storeName}"/>
			</div>
			<div class="unit">
				<label>店铺地址：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.storeAddress}"/>
			</div>
			<div class="unit">
				<label>店铺联系方式：</label>
				<input readonly="readonly" value="${ registerMerchantsEntity.storeMobile}"/>
			</div>
			<div class="divider">divider</div>
			
			<div class="unit">
				<label>审核备注：</label>
				<textarea rows="5" style="width:90%;text-align:left;" name="remark">${bsMerchantsInfo.remark }</textarea>
				
			</div>
			
			<div class="divider">divider</div>
			
		</div>
		<div class="formBar">
			<ul>
			   <c:if test='${param.op eq "edit"}'> 
				 <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				 <li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			  </c:if>
			  <c:if test='${param.op eq "dis"}'> 
			  	 <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			  </c:if>
				
				
			</ul>
		</div>
	</form>
</div>