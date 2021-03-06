<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <h2>${requestScope.title}</h2>
   <p>Quantidade de CERs disponíveis para proposta: ${qteCERsDisponiveis}</p>
   <c:choose>
       <c:when test="${entidadeValidada}">
   <form method="post" action="${appRoot}/propostaleilao">
    <label ><span>Nome do Leilão: </span><input type="text" name="nome" /></label><br/>
    <label ><span>Quantidade de CER oferecida: </span><input type="text" name="quantCER" /></label><br />
    <label ><span>Valor mínimo do lance: </span><input type="text" name="lanceMinimo" /></label><br />
    <input type="submit" value="Confirmar" />
   </form>
       </c:when>
       <c:otherwise>
   <p class="error">
    Entidade não pode propor leilões pois ainda não teve seus CERs validados pelo Administrador.
   </p>
       </c:otherwise>
   </c:choose>