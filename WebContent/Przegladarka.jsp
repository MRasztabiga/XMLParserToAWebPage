<!DOCTYPE HTML>
<html lang="pl">
<head>
	<meta charset="ISO 8859-2"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>	
	<title>Strona na PPB</title>
	<script src ="http://code.jquery.com/jquery-latest.min.js" ></script>
	<script>
	/*===================== DO WCZYTANIA NAZW I OPISOW DO DIVÓW PO RAZ PIERWSZY ---> returnElements(0,10)===========================*/
	$(document).ready(function(){
		$.ajax({
			type:'GET',
			cache: false,
			async: true,
			url:'Servlet',
			success: function(response) {   
		      var size = Object.keys(response).length;
		       $.each(response, function(index , item) {
		    	 if(index < size/2){
		    	   var nazwa = "#guzik" + (index+1).toString(); 
		    		$(nazwa).text(item);
		       }
		    	 if(index>= size/2){
		    		 var nazwa ="#myDIV" + (index-9).toString();
		    		 $(nazwa).text(item);
		    	 }
		     });
		    },
		    error: function() {
		        alert('Not okay ^_^');
			}
		});
});
	/* WYSWIETLANIE NAZW W DIVACH PO PRZEŁADOWANIU STRONY/ PRZESYŁANIE ŻADANIA POST I ZWRACANIE OBIEKTU JSON Z LISTA OPISOW I NAZW 
	=============================================================================================================================================================================*/
	$(document).ready(function(){
		$('#clickme, #nextPage, #previousPage').click(function(){ //po kliknięciu ktoregos z buttonow o tych ID wykonuje sie funkcja
		//alert((this).id);
			var clickedID = this.id; // zmienna przechowujaca id kliknietego przycisku
			var pageValue = null; // zmienna przechowujaca obecny numer strony
			
			if(clickedID == "clickme"){ // jesli klikniety przycisk o id=clicmke to wyszukiwanie strony odbywa sie przez wpisaniu liczby  do pola tekstowego
				if($('#fulltext').val() != null){ // jesli pole tekstowe nie jest puste 
					pageValue = $('#fulltext').val(); // numer strony (string) do przekazania w żadaniu POST do serwera 
				}
			}
			if(clickedID == "nextPage"){ // jesli klikniety przycisk o id=nextPage to zaladowana zostaje kolejna strona z 10 elementami
			
				if($('#fulltext').val() != null){ 
					pageValue = parseInt($('#fulltext').val()) + 1; // obecna wartosc strony odczytana z pola tekstowego powiekszona o 1
					//alert(pageValue);
					pageValue = pageValue.toString(); // nr strony przekonwertowany do stringa
					$('#fulltext').val(pageValue); // nr strony (string) do przekazania w zadanu POST
				}
			}
			if(clickedID == "previousPage"){ // jesli klikniety przycisk o id=previousPage to zaladowana zostaje poprzednia w kolejnosci strona z 10 elementami
				if($('#fulltext').val() !== null && parseInt($('#fulltext').val()) > 0 ){
				pageValue = parseInt($('#fulltext').val()) - 1; 
				pageValue = pageValue.toString();
				$('#fulltext').val(pageValue);
				}
			}
		 	var numer = 1; // zmienna przesylana w zadaniu POST, identyfikator strony wysylajecej zadanie
			$.ajax({
				type:'POST',
				data: {name: pageValue, number: numer}, // przesylane w zadaniu dane
				cache: false,
				async: true, 
				url:'Servlet', // nazwa klasy do ktorej przychodzi zadanie
				success: function(response) {   // jesli uda sie wyslac zadanie, wykonuje sie funkcja zwracajaca odpowiedz z obiektem JSON
			      
			       $.each(response, function(index , item) { // iteracja kolejnych elementów listy zwroconej przez metode "doPost" z klasy Servlet
			    	
			       if(index >= 10){ // pierwsze 10 elementwo w liscie to opisy 
			    	   var nazwa = "#myDIV" + (index-9).toString(); // zmienna przechowujaca obecna nazwę (ID) diva do ktorego beda umieszczane dane
			    	   
			    	   $(nazwa).empty(); // kazdy z divow jest czysczony przed wyswietleniem kolejnej porcji opisow
			    	   $(nazwa).text(item); // // kazdy z divow jest 
			       }
			       if(index < 10){ // nazwy znajduja sie na ostatnich 10 elementach listy
			    	   var nazwa = "#guzik" + (index+1).toString(); 

			    	    $(nazwa).empty();
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
	/*================================================================================================================================================================
		********************************************************
		**********************************************************
	*/
    </script>

	<meta name="description" content="Robimy strone internetowa na PPB"/>
	<meta name="keywords" content="PPB,XML"/>

	<link rel="stylesheet" href="style1.css" type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Lato|Josefin+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	    <style>
	   
		#myDIV1{
	    	white-space: pre-wrap;
	    }
	    #myDIV2{
	    	white-space: pre-wrap;
	    }
	    #myDIV3{
	    	white-space: pre-wrap;
	    }
	    #myDIV4{
	    	white-space: pre-wrap;
	    }
	    #myDIV5{
	    	white-space: pre-wrap;
	    }
	    #myDIV6{
	    	white-space: pre-wrap;
	    }
	    #myDIV7{
	    	white-space: pre-wrap;
	    }
	    #myDIV8{
	    	white-space: pre-wrap;
	    }
	    #myDIV9{
	    	white-space: pre-wrap;
	    }
	    #myDIV10{
	    	white-space: pre-wrap;
	    }
	    
	    
      /* ------------------------CZĘŚĆ-GÓRNA-MENU------------------------ */

       wygląd wszystkich elementów - a - znajdujących się w elemencie - ol 
      ol a {

		text-decoration: none;
		display:block;
        color:#FFF;
        padding:0 5px;
      }
	    ol p {
        display:block;
        text-decoration: none;
        color:#FFF;
        padding:0 5px;
      }

      /* ------------------------CZĘŚĆ-ROZWIJANA-MENU------------------------ */

      /* wygląd elementu - li - w części rozwijanej */
      ol > li > ul > li {
        position:relative;
        background-color:#303030;
			text-decoration: none;
	display:block;
      }

      /* wygląd elementu - a - w części rozwijanej */
      ol > li > ul > li > a {
        border-top:1px solid #303030;
		text-decoration: none;
		display:block;
      }
	  
      /* wygląd elementu - li - w części rozwijanej, po najechaniu kursorem myszki na dany element - li */
      ol > li > ul > li:hover {
        background-color:#DDD; /**/
			text-decoration: none;
	display:block;
      }
    </style>
   
</head>
<body>
<script type="text/javascript" src = "js/jquery-1.6.2.js"></script>
<script type ="text/javascript">

</script>


	<div id="container">
	
		<div class="rectangle">
			<center>
				<div id="logo">
				<a href="index5.html" class="logolink">Przegladarka CPE</a>
				</div>
			</center>
		</div>
		
		<ol>
	<li><a href="#nazwa1"> <div id ="nazwa1">  <p id="guzik1" onclick="myFunction('myDIV1')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV1">
							
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
	<li><a href="#nazwa2"> <div id ="nazwa2"> <p id="guzik2" onclick="myFunction('myDIV2')"></p> </div>
			 <ul>
					<li><a href="#">
							<div id="myDIV2">
									<p id="dlugi_napis2"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
		<li><a href="#nazwa3"><div id = "nazwa3"><p id="guzik3" onclick="myFunction('myDIV3')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV3">
									<p id="dlugi_napis3"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
	<li><a href="#nazwa4"><div id="nazwa4"><p id="guzik4" onclick="myFunction('myDIV4')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV4">
									<p id="dlugi_napis4"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
		<li><a href="#nazwa5"><div id="nazwa5"><p id="guzik5" onclick="myFunction('myDIV5')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV5">
									<p id="dlugi_napis5"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
	<li><a href="#nazwa6"><div id="nazwa6"><p id="guzik6" onclick="myFunction('myDIV6')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV6">
									<p id="dlugi_napis6"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
		<li><a href="#nazwa7"><div id="nazwa7"><p id="guzik7" onclick="myFunction('myDIV7')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV7">
									<p id="dlugi_napis7"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
	<li><a href="#nazwa8"><div id="nazwa8"><p id="guzik8" onclick="myFunction('myDIV8')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV8">
									<p id="dlugi_napis8"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>

     		<li><a href="#nazwa9"><div id ="nazwa9"><p id="guzik9" onclick="myFunction('myDIV9')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV9">
									<p id="dlugi_napis9"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>
	
	<li><a href="#nazwa10"><div id="nazwa10"><p id="guzik10" onclick="myFunction('myDIV10')"></p></div>
			 <ul>
					<li><a href="#">
							<div id="myDIV10">
									<p id="dlugi_napis10"></p>
							</div></a>
					</li>
			 </ul>
			 </a>
	</li>

 
  </ol>
		
		Nr Strony: <input type ="text" id="fulltext">
		
		<input id="clickme" type="button" value ="Click"/>
		<input id="nextPage" type ="button" value="Next"/>
		<input id="previousPage" type="button" value="Prev"/>
		<span id ="result1"></span>
		
		<div class="rectangle">
			<center>
				&copy; Jakub Kucharski, Radosław Kiraga, Krystian Kisicki, Michał Rasztabiga, Mateusz Zaborski
			</center>
		</div>

	</div>


<script>
	//var dlugi_opis = "";
	
	var i ;
	var dlugosc = 10;
	
for(i=0; i < dlugosc; i++){
//document.getElementById('guzik'+(i+1)).innerHTML = nazwa[i];
document.getElementById('myDIV'+(i+1)).style.display ="none";
//document.getElementById('dlugi_napis'+(i+1)).innerHTML = dlugi_opis[i];
}

function myFunction(idDIV) {
	var x = document.getElementById(idDIV);
    if (x.style.display == 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}


</script>
</body>
</html>