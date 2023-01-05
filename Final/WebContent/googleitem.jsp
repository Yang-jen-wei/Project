<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MUSIC22</title>
<style type="text/css">
#padding{
	padding: 0px 0px 15px 15px;
}
a {
	color: #0B173B;
	font-size: 30px;
	text-decoration: none;
}
a:hover{
text-decoration:underline;
}
.border-style {
	border: 2px solid #B9948C;
    border-radius: 8px;
}
/* .hr{
	color: #B9948C;
} */
</style>
</head>
<body>
<body style='background-color: #F7EFEB'>
<form action='${requestUri}' method='get'>

	<div style='position: absolute;margin-top:190px;margin-left:100px; color: #B9948C;font-family:"Noto Sans TC"'>
		<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
			String s=orderList[i][1];
			s=s.substring(7);
		%>
		
		<a href='https:/<%=s%>' style='color: #B9948C;font-family:"Noto Sans TC";margin-left:125px; '><%=orderList[i][0]%> </a> 
		<br>
		<br>
		<br>
	
		<%
}
%>
	</div>
	<div>
		<img src="images/Beat Google.png"
			style='position: absolute; width: 150px; height: 100px; left: 50%; top: 48%; margin-top: -280px; margin-left: -590px'>
	</div>
	<div>
		<input type='text' class="border-style" id="padding"  
		style='font-size:120%;position:absolute;left:50%;top:48%;
		margin-top:-250px;margin-left:-400px;width:800px;height:25px' name='keyword' placeholder='請輸入音樂關鍵字'
		onfocus="placeholder= '' " onblur="placeholder='請輸入音樂關鍵字'" />
	</div>
	<div>
		<input type='image' src="images/magnifier.png" 
		style='position:absolute;width:37px;height:37px;left:50%;top:48%%;margin-top:97px;
		margin-left:368px '/>
	</div>
	<div>
		<img src="images/Vector.png" style='position: absolute;  left: 50%;top:10%; margin-top: 0px; margin-left: 350px'>
	</div>
</form>
</body>
</html>