<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>
<body>
    <%@ include file="/WEB-INF/header.jsp" %>
    <%@ include file="./WEB-INF/menu.jsp" %>

    <div id="main" class="clear">
        <h2>Login</h2>

        <form action="Login" method="post" id="myform">
            <%if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("error") ){ %>
                <div class="tableRow">
                    <p></p>
                    <p class="error">Username o password errati!</p>
                </div>

            <%}else if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("checkout")){ %>
                <input type="hidden" name="checkout"/><br><br>

            <%}%>

            <div class="tableRow">
                <p>Username:</p>
                <p><input type="text" name="un" required placeholder="inserisci username"/></p>
            </div>
            <div class="tableRow">
                <p>Password:</p>
                <p><input type="password" name="pw" required placeholder="inserisci password"/></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p><input type="submit" value="login"> &nbsp;&nbsp;&nbsp; <a href="<%= request.getContextPath() %>/Registrazione.jsp">non sei registrato?</a></p>
            </div>
        </form>
    </div>

    <%@ include file="./WEB-INF/footer.jsp" %>

</body>
</html>
