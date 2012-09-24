<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<h2>${requestScope.title}</h2>
<form>
    <label ><span>Quantidade de CER oferecida: 100.000 de CER</span></label><br/>
    <label ><span>Valor mínimo do Lance: R$ 1.000.000,00 <span></label><br />
    <label ><span>Data Inicial: </span><input type="text" value="12/12/2012" size=7 /></label><br/>
    <label ><span>Hora Inicial: </span><input type="time" size=5 value="00:00:00"  /></label><br/>
    <label ><span>Data de Término: </span><input type="text" value="12/12/2012" size=7/></label><br/>
    <label ><span>Hora Inicial: </span><input type="time" size=5 value="00:00:00" /></label><br/>
    <label ><span>Lances:<p></span><input type="text" size=100/></p></label><br/>
    <label ><span>Valor do Lance: </span><input type="time" /></label><br/>
    <input type="submit" value="Confirmar Lance"/>
</form>
