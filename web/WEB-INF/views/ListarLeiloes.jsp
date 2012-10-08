<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <h2>${requestScope.title}</h2>
   <h3>Leilões com confirmação pendente [ou da entidade]</h3>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Estado</td></tr>
    </thead>
    <c:forEach items="${leiloesPropostos}" var="leilao">
     <tr>
      <td><a href="${appRoot}/leilao/editar?leilao_id=${leilao.id}">${leilao.nomeLeilao}</a></td>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td>${leilao.estado}</td>
     </tr>
    </c:forEach>
   </table>
   <h3>Leilões aprovados</h3>
   <table>
    <thead>
     <tr><td>Nome</td><td>Entidade</td><td>CER</td><td>Lance Mínimo</td><td>Data inicial</td><td>Data final</td><td>Estado</td></tr>
    </thead>
    <c:forEach items="${leiloesAprovados}" var="leilao">
     <tr>
      <td><a href="${appRoot}/leilao/editar?leilao_id=${leilao.id}">${leilao.nomeLeilao}</a></td>
      <td>${leilao.entidade.nome}</td>
      <td>${leilao.quantidadeCER}</td>
      <td>${leilao.lanceMinimo}</td>
      <td><fmt:formatDate value="${leilao.dataInicial}" pattern="dd/MM/yyyy" /></td>
      <td><fmt:formatDate value="${leilao.dataFinal}" pattern="dd/MM/yyyy" /></td>
      <td>${leilao.estado}</td>
     </tr>
    </c:forEach>
   </table>
