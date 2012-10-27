<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%><!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8"/> 
  <title>${requestScope.title}</title>
  <meta name="viewport" content="width=device-width,user-scalable=false" />
  <link rel="stylesheet" type="text/css" href="${appRoot}/${css}" />
  <script src="${appRoot}/js/myscript.js"></script>
 </head>
 <body>
  <header><jsp:include page="${requestScope.header}" />
  </header>		
  <nav id="breadcrumb"><jsp:include page="${requestScope.breadcrumb}" />
  </nav>		
  <section id="contexto" >
  <nav id="nav_contexto">
   <h2>Menu</h2><jsp:include page="${requestScope.menuContexto}" />
  </nav>
  <article id="sc_contexto"><jsp:include page="${requestScope.main}" />
  </article>
 </section>
 <footer><jsp:include page="${footer}" />
 </footer>
</body>
</html>
