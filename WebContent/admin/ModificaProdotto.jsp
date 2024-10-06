<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.ProdottoBean, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>Modifica prodotto</title>
</head>
<body>

	<%@ include file="/WEB-INF/header.jsp" %>
	<%@ include file="/WEB-INF/menu.jsp" %>
	
	
	<div id="main" class="clear">
	
		<h2>MODIFICA PRODOTTO</h2>
		
	<% int id = Integer.parseInt(request.getParameter("prodotto"));
		ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getSession().getAttribute("products");
		ProdottoBean bean = null;
	for(ProdottoBean prodotto : prodotti){
		if(prodotto.getIdProdotto() == id){
			bean = prodotto;} 
			}%>
	
	<form action="../catalogo" method="post" id="myform">
		<input type="hidden" name="action" value="modifica">
		<input type="hidden" name="page" value="admin/GestioneCatalogo.jsp">
		<div class="tableRow">
			<p>ID:</p>
			<p><input type="text" name="id" value="<%=bean.getIdProdotto() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Nome:</p>
			<p><input type="text" name="nome" value="<%=bean.getNome() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Descrizione:</p>
			<p><input type="text" name="descrizione" value="<%=bean.getDescrizione() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Iva:</p>
			<p><input type="text" name="iva" value="<%=bean.getIva() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Prezzo:</p>
			<p><input type="text" name="prezzo" value="<%=bean.getPrezzo() %>" required></p>
		</div>		
		<div class="tableRow">
			<p>Data:</p>
			<p><input type="text" name="dataUscita" value="<%=bean.getDataUscita() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Quantit�:</p>
			<p><input type="number" name="quantit�" value="<%=bean.getQuantit�() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Immagine:</p>
			<p><input type="text" name="img" value="<%=bean.getImmagine() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Categoria:</p>
			<p><input type="text" name="categoria" value="<%=bean.getCategoria() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Materiale:</p>
			<p><input type="text" name="materiale" value="<%=bean.getMateriale() %>" required></p>
		</div>
		<div class="tableRow">	
			<p>Descrizione dettagliata:</p>
			<p><input type="text" name="descDett" value="<%=bean.getDescrizioneDettagliata()%>"></p>
		</div>
		<div class="tableRow">
			<p></p>
			<p><input type="submit" value="modifica"></p>
		</div>
	</form>
	
		</div>
	

	<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>