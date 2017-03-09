<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pl">
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>	
	
	<title>Wyszukiwarka</title>
	
	<meta name="description" content="Robimy strone internetowa na PPB"/>
	<meta name="keywords" content="PPB,XML"/>

	<link rel="stylesheet" href="style2.css" type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Lato|Josefin+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	
</head>
<script src ="http://code.jquery.com/jquery-latest.min.js" ></script>
	<script>
	var container = document.getElementById("test");
	
	$(document).ready(function(){
		$('#clickme').click(function(){
			var param1 = $('#fulltext').val();
		 	var numer = 2;
			$.ajax({
				type:'POST',
				data: {query: param1, number: numer},
				cache: false,
				async: true,
				url:'Servlet',
			success: function(response) {   
					var nazwa;
					var numer;
					var i = 0;	
					var sizeOfResponseArray = Object.keys(response).length; 
					//alert(sizeOfResponseArray);
					
					$("#test").empty();
					
					for(i = 0 ; i < sizeOfResponseArray/2; i++ ){
						//$("#test").append('<div id = "div'+ i +'" ></div>');
						numer = i.toString();
//$("#test").append('<div id = "nazwa'+ numer +'"><p id ="guzik' +  numer +'"onclick="myFunction("#myDIV'+numer+'")">/</p></div> <li><a href="#"> <div id= "myDIV'+numer+'"></div></a></li>');
$("#test").append('<div id = "nazwa'+ numer +'"><p id ="guzik' +  numer +'" onclick=myFunction("myDIV'+numer+'")>/</p></div><ol> <a href="#"> <div id= "myDIV'+numer+'"></div></a><ol>');
					}		 
				
						$.each(response, function(index, item) {
							
							if(index < sizeOfResponseArray/2){
							nazwa = "#guzik" + index.toString(); 
							$(nazwa).text(item);
							}
							if(index >= sizeOfResponseArray/2){
							nazwa ="#myDIV" + (index-(sizeOfResponseArray/2)).toString();
							$(nazwa).text(item);
							}
							
						});
			},
			error: function() {
			alert('Not okay ^_^');
			}
			});
		});
	});
	
	function myFunction(idDIV) {
		var x = document.getElementById(idDIV);
	    if (x.style.display == 'none') {
	        x.style.display = 'block';
	    } else {
	        x.style.display = 'none';
	    }
	}

	</script>
<body>
<script>
var i = 0;
for(i=0; i < sizeOfResponseArray/2; i++){
	document.getElementById('myDIV'+(i)).style.display ="none";
	}
</script>

	<div id="container">
	
		<div class="rectangle">
			<center>
				<div id="logo">
				<a href="index5.html" class="logolink">Przegladarka CPE</a>
				</div>
			</center>
		</div>
		
		<div class="wyszukiwarka">
									
							<div id="naz_szukaj">		
									WYSZUKAJ
							</div>						
				
		
						<center>
								<input type ="text" id="fulltext">
								<input id="clickme" type="button" value ="Szukaj"/>
						</center>		
		
		</div>
<div id="test"></div>
		<div class="rectangle">
			<center>
				&copy; Jakub Kucharski, Radoslaw Kiraga, Krystian Kisicki, Michal Rasztabiga, Mateusz Zaborski
			</center>
		</div>
	</div>

</body>
</html>