<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

<title>Checkout</title>
</head>
<body>

		<%@ include file="/WEB-INF/header.jsp" %>
		<%@ include file="./WEB-INF/menu.jsp" %>
		
		<% IndirizzoSpedizioneBean spedizione = (IndirizzoSpedizioneBean) request.getSession().getAttribute("spedizione");
			MetodoPagamentoBean pagamento = (MetodoPagamentoBean) request.getSession().getAttribute("pagamento");%>
	
		<div id="main" class="clear">

	<h2>Checkout</h2>
	<form action="Checkout" method="post" id="myform">
    <div class="tableRow">
        <p class="heading">Dati spedizione:</p>
        <p></p>
    </div>
    <div class="tableRow">
        <p>Nome:</p>
        <p><input type="text" name="nome" value="<%= spedizione != null ? spedizione.getNome() : "" %>" required/></p>
    </div>    
    <div class="tableRow">
        <p>Cognome:</p>
        <p><input type="text" name="cognome" value="<%= spedizione != null ? spedizione.getCognome() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>telefono:</p>
        <p><input type="text" name="tel" value="<%= spedizione != null ? spedizione.getTelefono() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>Indirizzo:</p>
        <p><input type="text" name="ind" value="<%= spedizione != null ? spedizione.getIndirizzo() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>Cap:</p>
        <p><input type="text" name="cap" value="<%= spedizione != null ? spedizione.getCap() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>Provincia:</p>
        <p><input type="text" name="prov" value="<%= spedizione != null ? spedizione.getProvincia() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>Città:</p>
        <p><input type="text" name="città" value="<%= spedizione != null ? spedizione.getCittà() : "" %>" required/></p>
    </div>

    <div class="tableRow">
        <p class="heading">Dati pagamento:</p>
        <p></p>
    </div>
    <div class="tableRow">
        <p>Titolare carta:</p>
        <p><input type="text" name="tit" value="<%= pagamento != null ? pagamento.getTitolare() : "" %>" required/></p>
    </div>    
    <div class="tableRow">
        <p>Numero:</p>
        <p><input type="text" name="numC" value="<%= pagamento != null ? pagamento.getNumero() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p>Scadenza:</p>
        <p><input type="date" name="scad" value="<%= pagamento != null ? pagamento.getScadenza() : "" %>" required/></p>
    </div>
    <div class="tableRow">
        <p></p>
        <p><input type="submit" value="checkout"></p>
    </div>
</form>

	</div>
	
	<%@ include file="./WEB-INF/footer.jsp" %>
	
</body>
</html>