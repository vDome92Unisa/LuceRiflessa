<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <title>Aggiungi prodotto</title>
</head>
<body>

    <%@ include file="/WEB-INF/header.jsp" %>
    <%@ include file="/WEB-INF/menu.jsp" %>


    <div id="main" class="clear">

        <h2>AGGIUNGI PRODOTTO</h2>

        <form action="../catalogo" method="post" id="myform">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="page" value="admin/GestioneCatalogo.jsp"><br><br>
            <div class="tableRow">
                <p>Nome:</p>
                <p><input type="text" name="nome" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Descrizione:</p>
                <p><input type="text" name="descrizione" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Iva:</p>
                <p><input type="text" name="iva" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Prezzo:</p>
                <p><input type="text" name="prezzo" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Data:</p>
                <p><input type="text" name="dataUscita" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Quantità:</p>
                <p><input type="number" name="quantità" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Immagine:</p>
                <p><input type="text" name="img" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Categoria:</p>
                <p><input type="text" name="categoria" value="" required></p>
            </div>
            <div class="tableRow">
                <p>Materiale:</p>
                <p><input type="text" name="materiale" value="" required></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p><input type="submit" value="aggiungi"></p>
            </div>
        </form>

    </div>

    <%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>
