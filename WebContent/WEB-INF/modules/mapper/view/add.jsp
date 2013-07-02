<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}mapper/" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<div class="unit">
					<label>关联映射的图片：</label>
					<select name="mapper.image.id" class="required combox">
						<option value="">请选择</option>
						<c:forEach var="item" items="${images}">
							<option value="${item.id}">${item.displayName}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="unit">
					<label>映射名称：</label>
					<input class="required" name="mapper.name" />
				</div>
				
				<div class="unit">
                    <label>映射描述</label>
                    <textarea name="mapper.description" rows="3" cols="50"></textarea>
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