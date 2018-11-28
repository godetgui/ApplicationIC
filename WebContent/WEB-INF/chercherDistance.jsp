<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chercher Distance</title>
</head>
<body>



	<form id="form" class="login-form" method="POST" action="ChercherDistance">
		<div class="form-group">
			<label>Ville d'arrivée</label>
			<input type="text" class="form-control" name="villeDepart" id="login"
				placeholder="Ville de départ">
		</div>
		<div class="form-group">
			<label>Ville de départ</label>
			<input type="text" class="form-control" name="villeArrivee" id="login"
				placeholder="Ville d'arrivée">
		</div>
		<button type="submit" class="btn btn-primary">Calculer la distance</button>
	</form>
	<br/>
	<br/>
	<br/>
	<div result>
		<label>Distance</label>
		<%=request.getAttribute("distance")%> km
	</div>

</body>
</html>