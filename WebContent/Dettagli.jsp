<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettagli</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%@ include file="/WEB-INF/header.jsp" %>
	<%@ include file="./WEB-INF/menu.jsp" %>
	
	<div id="main" class="clear">
	
	<%
		ProdottoBean product = (ProdottoBean) request.getSession().getAttribute("product");
	
		if (product != null) {
	%>
		<h2><%=product.getNome() %></h2>
			<div id="image"><img src="<%=product.getImmagine()%>" height="270" width="250">
			</div>
			<div id="listDettagli">
				<ul>	
					<li><span class="dettagli"><%=product.getNome()%></span></li>
					<li><span class="dettagli">Materiale</span>: <%=product.getMateriale()%></li>
					<li><span class="dettagli">Categoria</span>: <%=product.getCategoria()%></li>
					<li><span class="dettagli">Prezzo</span>: &euro;<%=product.getPrezzo()%></li>
					<li><span class="dettagli">Disponibile dal</span>: <%=product.getDataUscita()%></li>
					<% if(product.isInVendita()) {%>
						<li><span class="dettagli">DisponibilitÓ Immediata</span></li>
					<%}else{ %>
						<li><span class="dettagli">Non disponibile</span></li>
					<%}%>
					<li><a href="carrello?action=addC&id=<%=product.getIdProdotto()%>&page=Dettagli.jsp"><button>Aggiungi al carrello</button></a></li>
				</ul>
			</div>
	<%}%>

	</div>
			<%@ include file="./WEB-INF/footer.jsp" %>
	
</body>
</html>