<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <h2>${requestScope.title}</h2>
   <c:if test="${leiloesArrematados != null}">
   <h3>Leilões arrematados</h3>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Data inicial</td><td>Data final</td></tr>
    </thead>
    <c:forEach items="${leiloesArrematados}" var="leilao">
     <tr>
      <td><a href="#">${leilao.nomeLeilao}</a></td>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td><fmt:formatDate value="${leilao.dataInicial}" pattern="dd/MM/yyyy" /></td>
      <td><fmt:formatDate value="${leilao.dataFinal}" pattern="dd/MM/yyyy" /></td>
     </tr>
    </c:forEach>
   </c:if>
   </table>
   <c:if test="${leiloesPropostos != null}">
   <c:choose>
   <c:when test="${usuario.admin}">
   <h3>Leilões com confirmação pendente</h3>
   </c:when>
   <c:otherwise>
   <h3>Leilões da entidade</h3>
   </c:otherwise>
   </c:choose>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Estado</td></tr>
    </thead>
    <c:forEach items="${leiloesPropostos}" var="leilao">
     <tr>
      <c:choose>
      <c:when test="${usuario.admin}">
      <td><a href="${appRoot}/leilao/editar?leilao_id=${leilao.id}">${leilao.nomeLeilao}</a></td>
      </c:when>
      <c:otherwise>
      <td>${leilao.nomeLeilao}</td>
      </c:otherwise>
      </c:choose>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td>${leilao.estado.desc}</td>
     </tr>
    </c:forEach>
   </table>
   </c:if>
   <c:if test="${leiloesAprovados != null}">
   <c:choose>
   <c:when test="${usuario.admin}">
   <h3>Leilões aprovados</h3>
   </c:when>
   <c:otherwise>
   <h3>Leilões disponíveis</h3>
   </c:otherwise>
   </c:choose>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Data inicial</td><td>Data final</td><td>Estado</td></tr>
    </thead>
    <c:forEach items="${leiloesAprovados}" var="leilao">
     <tr>
      <c:choose>
      <c:when test="${usuario.admin}">
      <td><a href="${appRoot}/leilao/editar?leilao_id=${leilao.id}">${leilao.nomeLeilao}</a></td>
      </c:when>
      <c:otherwise>
      <td><a href="${appRoot}/leilao/lance?leilao_id=${leilao.id}">${leilao.nomeLeilao}</a></td>
      </c:otherwise>
      </c:choose>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td><fmt:formatDate value="${leilao.dataInicial}" pattern="dd/MM/yyyy" /></td>
      <td><fmt:formatDate value="${leilao.dataFinal}" pattern="dd/MM/yyyy" /></td>
      <td>${leilao.estado.desc}</td>
     </tr>
    </c:forEach>
   </table>
   </c:if>
   <c:if test="${leiloesFinalizados != null}">
   <h3>Leilões Finalizados</h3>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Data inicial</td><td>Data final</td><td>Estado</td></tr>
    </thead>
    <c:forEach items="${leiloesFinalizados}" var="leilao">
     <tr>
      <td>${leilao.nomeLeilao}</td>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td><fmt:formatDate value="${leilao.dataInicial}" pattern="dd/MM/yyyy" /></td>
      <td><fmt:formatDate value="${leilao.dataFinal}" pattern="dd/MM/yyyy" /></td>
      <td>${leilao.estado.desc}</td>
     </tr>
    </c:forEach>
   </table>
   </c:if>
