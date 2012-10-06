<%-- 
    Document   : login
    Created on : Oct 6, 2012, 3:49:29 PM
    Author     : giovani
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
   <h2>Login</h2>
   <form method="post" action="${appRoot}/login">
    <label><span>E-mail: </span><input name="email" type="text" size=50 /></label><br />
    <label><span>Senha: </span><input name="passwd" type="password" size=50 /></label><br />
    <label><span>Administrador: </span> <input type="checkbox" name="admin" value="ON" /></label><br />
    <input type="submit" value="Login" />
   </fom>