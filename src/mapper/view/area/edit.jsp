<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}area/${pojo.id}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
                <div class="unit">
					<label>所属映射：</label>
					<select name="area.mapper.id" class="required combox">
						<option value="">请选择</option>
						<c:forEach var="item" items="${mappers}">
							<option value="${item.id}" <c:if test="${item.id == pojo.mapper.id}">selected="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="unit">
					<label>颜色标识：</label>
					<select name="area.color" class="required">
						<option value="">请选择</option>
						<c:forEach var="item" items="${colors}">
							<option value="${item}" style="color:${item}; font-weight:bold;"
								<c:if test="${item == pojo.color}">selected="selected"</c:if>>${item}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="unit">
					<label>热点名称：</label>
					<input class="required" name="area.name" value="${pojo.name}" />
				</div>
				
				<div class="unit">
                    <label>热点坐标</label>
                    <textarea name="area.coords" rows="3" cols="50">${pojo.coords}</textarea>
                </div>
				
				<div class="unit">
                    <label>映射描述</label>
                    <textarea name="area.description" rows="3" cols="50">${pojo.description}</textarea>
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