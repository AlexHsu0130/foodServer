<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/animate.css">
<link rel="stylesheet" href="../css/appStyle.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
 <script src="../js/wow.min.js"></script>
<title>MemberInfo</title>

</head>
<body>
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> -->
	<!-- navbar -->
	<div id="mbbackground">
	<nav class="navbar navbar-inverse nav-full">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Link <span class="sr-only">(current)</span>
				</a></li>
				<li><a href="#">Job</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <i class="icon1"></i>分類
				</a></li>
				<li><a href="#"> <i class="icon1"></i>商城
				</a></li>
				<li><a href="#"> <i class="icon-user"></i> Sing In
				</a></li>
				<li><a href="#">LogIn</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- list -->
	
	<div class="fotoInfo">
 		
			<div class="fotoCase test wow bounceInDown ">
				<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">
            </div>
            <div class="separateLine wow fadeIn"> 
            </div>
        </div>
			 <!-- memberName -->
        <div class="name">
            <div class="nickName wow bounceInRight">
               <p>AlexHsu</p> 
            </div>
		</div>


		<div class="list-group wow bounceInLeft">

			<a href="#" class="list-group-item personalInfo"> 個人資料 </a> <a href="#"
				class="list-group-item"> 喜好設定</a> <a href="#"
				class="list-group-item"> 我的餐券</a> <a href="#"
				class="list-group-item"> 評論紀錄</a> <a href="#"
				class="list-group-item"> 收藏庫</a>
			
		</div>
	<div class="infoWall wow fadeInRight"></div>
	</div>
	<script>
	
	/* var  username = '<s:property value="%{user.username}"/>'; */
	
		// $('.personalInfo').click(function() {
		// 	/* alert('hi'); */
		// 	$.getJSON("/Food//MemberServlet", {
		// 		userAccount : 'taipeitec00801@gmail.com'
		// 	}, function(data) {
		// 		/* alert('hi');
		// 		alert(data); */
		// 		$('.infoWall').html('<p>'+ data.memberId +'</p><br>'+
		// 				'<p >'+ data.userPassword +'</p><br>'+
		// 				'<p>'+ data.birthday +'</p><br>'+
		// 				'<p>'+ data.nickName +'</p><br>'+
		// 				'<p>'+ data.gender +'</p><br>'+
		// 				'<p>'+ data.userRank +'</p><br>'+
		// 				'<p>'+ data.preference +'</p><br>'+'</div>');
		// 	});
		// });
		$('.personalInfo').click(function(){
				$.ajax({
					url : '/foodSrverSH/_02_login/login.do',
					type : 'POST',
					data : {
						userAccount : 'taipeitec00801@gmail.com'
					},
					dataType : 'json', //text,json,xml
					success : function(data) {
						$('.infoWall').html('<p>'+ data.memberId +'</p><br>'+
								'<p >'+ data.userPassword +'</p><br>'+
								'<p>'+ data.birthday +'</p><br>'+
								'<p>'+ data.nickName +'</p><br>'+
								'<p>'+ data.gender +'</p><br>'+
								'<p>'+ data.userRank +'</p><br>'+
								'<p>'+ data.preference +'</p><br>'+'</div>');
					}
					
				});
			});
	</script>
	 <script>
              new WOW().init();
              </script>



</body>
</html>