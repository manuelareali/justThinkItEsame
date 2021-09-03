<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beanweb.CreaNecessitaBoundary"%>

<%
Class.forName("com.mysql.jdbc.Driver");
	if(request.getParameter("OK")!=null){
		if (CreaNecessitaBoundary.getInstance().creaAnnuncioPressed(request.getParameter("tipologia"),request.getParameter("urgenza"), request.getParameter("richiesta")) == true){
%>
	<jsp:forward page="bachecaCaritasNecessita.jsp"/>
<%			
	}
	}
	
	if(request.getParameter("indietro") != null){
%>
	<jsp:forward page="bachecaCaritasNecessita.jsp"/>
<%
	}
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>CREA NECESSITA'</title>
    <link rel="stylesheet" href ="../css/creaNecessitaCaritas.css">
    <link rel="icon" sizes="64x64" href="../img/favicon.png">
  </head>
  <body>
  <div class="container">
  <form  action = "creaNecessitaCaritas.jsp" name ="my" method = "POST">
  
  
  <div class = "content">
  <div class = "content text-center">
    <h1>Crea Necessit�</h1>
   </div>
  
</div>
<div class = "conteiner text-center my-5">
<div class = "row">
<div class = "col">
<h6>Tipologia</h6>
<select id = "tipologia" name="tipologia" >
		<option id = "Vestiti" value="Vestiti" selected="selected">Vestiti</option>
		<option id = "Cibo" value= "Cibo" selected= "selected">Cibo</option>
	</select>	
	</div>
<div class = "col">	 
<h6>Urgenza Necessit�</h6>	
		<select id = "urgenza" name="urgenza" >
		<option id = "Alta" value="Alta" selected="selected">Alta</option>
		<option id = "Normale" value="Normale" selected="selected">Normale</option>
		<option id = "Bassa" value="Bassa" selected="selected">Bassa</option>
	</select>
	</div>
	</div>
</div>
<div class = "container text-center my-5">
	<h4>Note</h4>
  <textarea id = "richiesta" name= "richiesta" placeholder="Scrivi qui le esigenze."/></textarea>
	</div>		
		
	

			
			
		<div id="popup5" class="overlay">
			<div class="popup">

				<div class="content">
					<h3 class="fw-bold">Sei sicuro di voler confermare?</h3>
					<p>I dati inseriti non potranno essere modificati.</p>
					<div class="content text-center">
						<button class="btn btn-outline-light"
								type="submit" name="OK" value="OK">OK</button> 
							<button class="btn btn-outline-light"
								type="submit" name="" value="">ANNULLA</button>
					</div>
				</div>

			</div>
		</div>
	
	  </form>
	  </div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

		<div class = "container text-center my-3">
  		<div class="row">
		<div class="col ">
			<a href="bachecaCaritasNecessita.jsp"><button class="btn btn-warning" type = "submit" name = "indietro" value = "indietro">Indietro</button></a>
		</div>
		<div class = "col"></div>
		<div class = "col"></div>
		<div class = "col"></div>
		<div class = "col"></div>
		
		<div class = "col">	
		<a class="button" href="#popup5"><button  class="btn btn-light" type="submit" name = "CREA ANNUNCIO" value = "CREA ANNUNCIO">Crea Annuncio</button></a>
		</div>
	</div>
	</div>


  
   
  </body>
</html>