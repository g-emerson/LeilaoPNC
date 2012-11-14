<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <h2>${requestScope.title}</h2>
   <c:if test="${usuario.admin}">
   <h3>Entidades com aprovação pendente</h3>
   <form>
    <table>
     <thead>
      <tr><td>ID</td><td>Nome</td><td>e-mail</td><td>CNPJ</td><td>Qte. CER</td><td>Validação</td></tr>
     </thead>
     <c:forEach items="${listaPendentes}" var="ent">
      <tr>
       <td>${ent.id}</td>
       <td>${ent.nome}</td>
       <td>${ent.email}</td>
       <td>${ent.cnpj}</td>
       <td>${ent.quantidadeCER}</td>
       <td id="validacao_${ent.id}">
        ${ent.status.texto}
          &nbsp;<input type="button" value="Validar" onclick='validarEntidade("${appRoot}",${ent.id},true)' />
          &nbsp;<input type="button" value="Reprovar" onclick='validarEntidade("${appRoot}",${ent.id},false)' />
       </td>
      </tr>
     </c:forEach>
    </table>
   </form>
   </c:if>
   <h3>Ranking das entidades</h3>
   <table>
    <thead>
     <tr><td>ID</td><td>Nome</td><td>CNPJ</td><td>Qte. CER</td></tr>
    </thead>
    <c:forEach items="${rankingEntidades}" var="ent">
    <tr>
     <td>${ent.id}</td>
     <td>${ent.nome}</td>
     <td>${ent.cnpj}</td>
     <td>${ent.quantidadeCER}</td>
    </tr>
    </c:forEach>
   </table>
