<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
response.setCharacterEncoding("UTF-8");
request.setAttribute("title", "Leilão PNC");
request.setAttribute("menuContexto", "menuGeral.jsp");
request.setAttribute("breadcrumb", "menuEntidades.jsp");
request.setAttribute("main", "Login.jsp");
%>
<jsp:include page="WEB-INF/views/leilao-template.jsp" />
