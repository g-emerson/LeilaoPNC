<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%--
	Template para o site do leilão.

	Este arquivo define de onde carregar os compenetes gerais do layout e carrega
	o template de layout utilizado.
--%>

<%
	request.setAttribute("css", "mystyle.css");
	request.setAttribute("header", "header.jsp");
	request.setAttribute("breadcrumb", "breadcrumb.jsp");
	request.setAttribute("menuPrincipal", "menuPrincipal.jsp");
	pageContext.setAttribute("footer", "footer.jsp");
%>

<%@ include file="template-layout.jsp" %>
