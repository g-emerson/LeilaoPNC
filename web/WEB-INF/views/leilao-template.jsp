<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%><%--
	Template para o site do leilão.

	Este arquivo define de onde carregar os compenetes gerais do layout e carrega
	o template de layout utilizado.
--%><%
	request.setAttribute("appRoot", application.getContextPath());
	request.setAttribute("css", "css/mystyle.css");
	request.setAttribute("header", "/WEB-INF/static/header.jsp");
	request.setAttribute("breadcrumb", "/WEB-INF/static/breadcrumb.jsp");
	pageContext.setAttribute("footer", "/WEB-INF/static/footer.jsp");
%><%@ include file="/WEB-INF/static/template-layout.jsp" %>