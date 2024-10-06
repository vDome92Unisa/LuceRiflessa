<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Home.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">	
<title>Home</title>
</head>
<body>

		<%@ include file="./WEB-INF/header.jsp" %>
		<%@ include file="./WEB-INF/menu.jsp" %>
	
		<div id="main" class="clear">
			
			<%for(int i = 0 ; i < categorie.size() ; i++){%>
				<div class="categoria categoria<%=i%>">
				<%switch(i){
					case 0 : %> <h2>Collane</h2><a href="Collane.jsp" id="blu">vedi tutta la categoria</a> <hr>
								<%break;
					case 1 : %> <h2>Bracciali</h2> <a href="Bracciali.jsp" id="blu">vedi tutta la categoria</a> <hr>
								<%break;
					case 2 : %> <h2>Anelli</h2><a href="Anelli.jsp" id="blu">vedi tutta la categoria</a>  <hr>
								<%break;
					case 3 : %> <h2>Pendenti</h2><a href="Pendenti.jsp" id="blu">vedi tutta la categoria</a>  <hr>
								<%break;
					case 4 : %> <h2>Orecchini</h2><a href="Orecchini.jsp" id="blu">vedi tutta la categoria</a>  <hr>
								<%break;
				
				}for(int j = 0; j< categorie.get(i).size(); j++){
					ProdottoBean bean = categorie.get(i).get(j);%>
					 <div class="item">
						<ul>
							<li><a href="dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
							<li><%=bean.getNome()%></li>
							<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
							<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Home.jsp"><button>Aggiungi al carrello</button></a></li>
		 				</ul>
					</div>
				<%}%></div><%}%> 
		</div>
	
		<%@ include file="./WEB-INF/footer.jsp" %>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script>
			$(document).ready(function(){
				$("img").hover(function(){
					$(this).css({"height": "135px", "width" :"135px"});
				}, function(){
						$(this).css({"height" : "130px", "width" : "130px"});
					});
				});
			
			$(document).ready(function(){
				$(".categoria0 .item").slice(6).hide();
				$(".categoria1 .item").slice(5).hide();
				$(".categoria2 .item").slice(6).hide();
				$(".categoria3 .item").slice(6).hide();
				$(".categoria4 .item").slice(6).hide();
				});

		</script>

</body>
</html>