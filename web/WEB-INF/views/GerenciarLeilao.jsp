<%-- 
    Document   : GerenciarLeilao
    Created on : Sep 23, 2012, 10:39:27 PM
    Author     : giovani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>${requestScope.title}</h2>
<form>
    <label ><span>(ID)Nome do Leilão: </span><input type="text" /></label><br/>
    <label ><span>Quantidade de CER oferecida: </span><input type="text" /></label><br/>
    <label ><span>Valor mínimo do lance: <span><input type="int" /></label><br />
    <label ><span>Data Inicial: </span><input type="date" /></label><br/>
    <label ><span>Hora Inicial: </span><input type="time" /></label><br/>
    <label ><span>Data de Término: </span><input type="date" /></label><br/>
    <label ><span>Hora de Término: </span><input type="time" /></label><br/>
    <input type="submit" />
</form>