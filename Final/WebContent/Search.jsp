<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MUSIC22</title>
<style type="text/css">
.button{
	position:absolute;
	width:45px;
	height:25px;
	font-size:15px;
	left:50%;
	top:50%;
}
.border-style {
	border: 2px solid #B9948C;
    border-radius: 8px;
}

.music{
	width: 67px;
	height: 27px;
	font-family: 'Buenard';
	font-style: normal;
	font-weight: 400;
	font-size: 21px;
	line-height: 27px;
	
	color: #B9948C;
}
#padding{
	padding: 0px 0px 0px 15px;
}
.note{
	position:absolute;
	width:50px;
	height:50px;
	animation-timing-function: ease-in-out;
	animation-direction: alternate;
	animation-name:note;
	animation-duration:5s;
	animation-iteration-count:infinite;
	
}
@keyframes note{
	0%{
		
		left:640px;		
		top:0px;
		
	}
	25%{
		left:700px;
		top:60px;
		
	}
	50%{
		left:760px;
		top:0px;
	}
	75%{
		left:700px;
		top:-60px;
	}
	100%{
		left:640px;
		top:0px;
	}
}

</style>

</head>
<body style='background-color:#F7EFEB;
'>
<form action='${requestUri}' method='get'>
<div>
<p class="music" style = 'position:absolute;left:48%;top:85%'>Music</p>
<p class="music" style = 'position:absolute;left:46.53%;top:90%'>GROUP22</p>
</div>
<div>
<input type='text' class="border-style" id="padding"  
style='font-size:120%;position:absolute;left:50%;top:65%;
margin-top:-47px;margin-left:-400px;width:800px;height:60px' name='keyword' placeholder='請輸入音樂關鍵字'
onfocus="placeholder= '' " onblur="placeholder='請輸入音樂關鍵字'" />
</div>
<div>
<input type='image' src="images/magnifier.png" 
style='position:absolute;width:37px;height:37px;left:50%;top:68%;margin-top:-55px;
margin-left:368px '/>
</div>
<div>
<a href ='http://localhost:8080/Final_Project/TestProject'><img src="images/Beat Google.png" style='position:absolute;width:500px;height:250px;left:45%;top:50%;margin-top:-265px;
margin-left:-175px '></a>
</div>
</form>
</body>
</html>