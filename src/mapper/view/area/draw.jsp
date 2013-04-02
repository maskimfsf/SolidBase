<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- saved from url=(0028)http://www.image-mapper.com/ -->
<html class="win chrome chrome2 webkit webkit5">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" type="image/png" href="${BaseURL}images/favicon.png">
		<title>Image Mapper</title>
		<link href="${BaseURL}css/style.css?id=xxx" media="screen" rel="stylesheet" type="text/css">
		<link href="${BaseURL}css/layout-default-latest.css" type="text/css" rel="stylesheet"  />
		<script src="${BaseURL}dwzres/javascripts/jquery-1.4.4.js" type="text/javascript"></script>
		<script src="${BaseURL}js/linkedSelect.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="${BaseURL}js/jquery-ui-latest.js"></script>
		<script src="${BaseURL}js/jquery.layout-latest.js" type="text/javascript"></script>
		<script src="${BaseURL}js/debug.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('body').layout({
					east__size: .35
				});
			});
		</script>
		<style>
		body{
			font-size:12px;
		}
		div.unit {
			display: block;
			margin: 0;
			padding: 5px 0;
			position: relative;
			clear: both;
		}
		label {
			float: left;
			width: 80px;
			padding: 0 5px;
			line-height: 21px;
			font-size: 12px;
		}
		textarea, .input {
			padding: 2px;
			margin: 0;
			line-height: 15px;
			font-size: 12px;
			border-style: solid;
			border-width: 1px;
			border-color: #a2bac0 #b8d0d6 #b8d0d6 #a2bac0;
			background-color: #FFF;
		}
		textarea {
			width:65%;
		}
		</style>		
	</head>
	<body>
		<form action="${BaseURL}area/${area.id}/" method="POST" onsubmit="return validateCallback(this)">
			<input type="hidden" name="_method" value="put" />
			<div class="ui-layout-center">
				<iframe name="area_image_iframe" width="99%" height="99%" src="${BaseURL}area/image?savePath=${area.mapper.image.savePath}"></iframe>
				<!--div id="user_image">
					<img id="my_image" src="${ImageBaseURL}/${area.mapper.image.savePath}" />
					<div id="myCanvas"></div>
				</div-->
			</div>
			<div class="ui-layout-east">
				<div class="unit">
					<label>线条颜色：</label>
					<span id="area_color" value="${area.color}" style="padding:3px 10px; background:${area.color}">${area.color}</span>
				</div>
				
				<div class="unit">
					<label>所属映射：</label>
					<input class="input" disabled="disabled" size="40" value="${area.mapper.name}" />
				</div>
				
				<div class="unit">
					<label>映射描述：</label>
					<textarea disabled="disabled" name="area.description" rows="5">${area.mapper.description}</textarea>
				</div>
				
				<div class="unit">
					<label>热点名称：</label>
					<input class="input" name="area.name" size="40" value="${area.name}" />
				</div>
                
				<div class="unit">
                    <label>热点描述</label>
                    <textarea name="area.description" rows="5">${area.description}</textarea>
                </div>
                
                <div class="unit">
                    <label>热点坐标</label>
                    <textarea id="area_coords" class="output-code-textarea" name="area.coords" rows="10">${area.coords}</textarea>
                </div>
                
                <div class="unit" style="text-align:center; padding:20px; font-size:14px;">
					<a href="#" id="undo" class="button">撤销</a> |
					<a href="#" id="redo">重做</a> |
					<a href="#" id="reload_coords">渲染</a> |
					<input type="submit" style="border:none; background:#fff; color:#368af1; font-size:14px; cursor:pointer;" vlaue="保存" />
					<script>
						function validateCallback(form){
							$form = $(form);
							$.post(
								$form.attr("action"),
								$form.serializeArray(),
								function(data){
									//{"statusCode":"200","message":"update ok","navTabId":"xssytxrd","forwardUrl":"","callbackType":"","title":""}
									if (200 == data.statusCode){
										alert(data.message);
									}else{
										alert(data.message);
									}
								},
								"json"
							);
							return false;
						}
						$("#save_area").click(function(){
							
						});
					</script>
				</div>
			</div>
		</form>
	</body>
</html>