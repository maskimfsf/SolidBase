<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
	<div class="pageContent">
        <form method="post" action="${BaseURL}${editPage.action}/" class="pageForm required-validate" onsubmit="return validateCallback(this, ${openType}AjaxDone)">
            <div class="pageFormContent" layoutH="58">
				<input type="hidden" name="_method" value="put" />
				<input type="hidden" name="notice.id" value="${editPage.pojo.id}" />
yy				
				<span style="clear: both; display: block; height: 10px;" />
				<label>标题：</label>
				<input class="required " name="notice.title" type="text" value="${editPage.pojo.title}" />

				<span style="clear: both; display: block; height: 10px;" />
				<label>用户：</label>
				<input class="required " name="notice.user.id" type="text" value="${editPage.pojo.user.id}" />
				
				<span style="clear: both; display: block; height: 10px;" />
				<label>发布内容：</label>
				<textarea class="required " name="notice.content" type="text" >${editPage.pojo.content}</textarea>
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