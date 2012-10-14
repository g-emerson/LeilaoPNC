<%-- 
    Document   : CadastrarEntidade
    Created on : Sep 23, 2012, 8:18:36 PM
    Author     : giovani
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <h2>${requestScope.title}</h2>
   <form method="post" action="${appRoot}/cadastrar">
    <label><span>Nome: </span><input name="nome" type="text"  size=50 /> </label><br />
    <label><span>CNPJ: </span><input name="cnpj" type="text" size=30 /></label><br />
    <label><span>Segmento de Mercado: </span>
     <select name="segmentoMercado">
      <c:forEach items="${listaSegmentoMercado}" var="segMe">
       <option value="${segMe.id}">${segMe.nome}</option>>
      </c:forEach>
     </select>
    </label><br />
    <label><span>Quantidade de CER: </span><input name="quantCER" type="text" /></label><br />
    <label><span>E-mail: </span><input name="email" type="text" size=50 /></label><br />
    <label><span>Senha: </span><input name="passwd" type="password" /></label><br />
    <label><span>Confirmar senha: </span><input name="passwdConf" type="password" /></label><br />
    <input type="submit" />
   </form>
