<%-- 
    Document   : CadastrarEntidade
    Created on : Sep 23, 2012, 8:18:36 PM
    Author     : giovani
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h2>${requestScope.title}</h2>
   <form>
    <label><span>Nome: </span><input type="text"  size=50 /> </label><br />
    <label><span>CNPJ: </span><input type="text" size=30 /></label><br />
    <label><span>E-mail: </span><input type="text" size=50 /></label><br />
    <label><span>Quantidade de CER: </span><input type="text" /></label><br />
    <input type="submit" />
   </form>