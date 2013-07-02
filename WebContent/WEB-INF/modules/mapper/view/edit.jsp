<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}mapper/${pojo.id}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
                <div class="unit">
					<label>关联映射的图片：</label>
					<select name="mapper.image.id" class="required combox">
						<option value="">请选择</option>
						<c:forEach var="item" items="${images}">
							<option value="${item.id}" <c:if test="${item.id == pojo.image.id}">selected="selected"</c:if>>${item.displayName}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="unit">
					<label>映射名称：</label>
					<input class="required" name="mapper.name" value="${pojo.name}" />
				</div>
				
				<div class="unit">
					<label>映射宽度：</label>
					<input class="required" value="${pojo.width}" disabled="disabled" />
				</div>
				
				<div class="unit">
					<label>映射高度：</label>
					<input class="required" value="${pojo.height}" disabled="disabled" />
				</div>
				
				<div class="unit">
                    <label>映射描述</label>
                    <textarea name="mapper.description" rows="3" cols="50">${pojo.description}</textarea>
                </div>
            </div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
				</ul>
			</div>
       </form>
	</div>
</div>