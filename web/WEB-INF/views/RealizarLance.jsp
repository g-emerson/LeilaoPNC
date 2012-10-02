<%@ page pageEncoding="UTF-8" %>
   <h2>${requestScope.title}</h2>
    <form>
     Quantidade de CER oferecida: 100.000 de CER<br/>
     Valor mínimo do Lance: R$ 1.000.000,00 <br />
     <label ><span>Data Inicial: </span><input type="text" value="12/12/2012" size=7 /></label><br/>
     <label ><span>Hora Inicial: </span><input type="text" size=5 value="00:00"  /></label><br/>
     <label ><span>Data de Término: </span><input type="text" value="12/12/2012" size=7 /></label><br/>
     <label ><span>Hora Inicial: </span><input type="text" size=5 value="00:00" /></label><br/>
     <label ><span>Lances:</span><input type="text" size=5 /></label><br/>
     <label ><span>Valor do Lance: </span><input type="text" /></label><br/>
     <input type="submit" value="Confirmar Lance"/>
    </form>