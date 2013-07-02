<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link href="${BaseURL}css/style.css?id=xxx" media="screen" rel="stylesheet" type="text/css">

<script src="${BaseURL}dwzres/javascripts/jquery-1.4.4.js" type="text/javascript"></script>
<script src="${BaseURL}js/jquery.browser.min.js" type="text/javascript"></script>
<script src="${BaseURL}js/jquery.draw.js" type="text/javascript"></script>
<script src="${BaseURL}js/image_mapper.js" type="text/javascript"></script>
<script src="${BaseURL}js/application.js" type="text/javascript"></script>

<div id="user_image">
	<img id="my_image" src="${ImageBaseURL}/${savePath}" />
	<div id="myCanvas"></div>
</div>
