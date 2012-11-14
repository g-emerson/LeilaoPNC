<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <h2>${requestScope.title}</h2>
    <form method="post" action="${appRoot}/leilao/lance">
     <p>Quantidade de CER oferecida: ${leilao.quantidadeCER} de CER</p>
     <p>Valor mínimo do Lance: R$ ${leilao.lanceMinimo}</p>
     <fmt:formatDate  value="${leilao.dataInicial}" pattern="dd/MM/yyyy" var="fmtData" />
     <p>Data Inicial: ${fmtData}</p>
     <fmt:formatDate  value="${leilao.horaInicial}" pattern="HH:mm" var="fmtTime" />
     <p>Hora Inicial: ${fmtTime}</p>
     <fmt:formatDate  value="${leilao.dataFinal}" pattern="dd/MM/yyyy" var="fmtData" />
     <p>Data de Término: ${fmtData}</p>
     <fmt:formatDate  value="${leilao.horaFinal}" pattern="HH:mm" var="fmtTime" />
     <p>Hora Inicial: ${fmtTime}</p>
     <c:choose>
         <c:when test="${leilao.maiorLance != null}">
     <p>Maior lance até o momento: ${leilao.maiorLance.valor}</p>
        </c:when>
        <c:otherwise>
     <p>Nenhum lance até o momento</p>
        </c:otherwise>
     </c:choose>
     <input type="hidden" name="leilao_id" value="${leilao.id}" />
     <label ><span>Valor do Lance: </span><input type="text" name="valorLance"/></label><br/>
     <input type="submit" value="Confirmar Lance"/>
    </form>