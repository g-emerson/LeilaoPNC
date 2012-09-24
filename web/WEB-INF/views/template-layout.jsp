<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8"/> 
  <title>${requestScope.title}</title>
  <link rel="stylesheet" type="text/css" href="${appRoot}/${css}" />
 </head>
 <body>
  <header>
   <jsp:include page="${requestScope.header}" />
  </header>
  <nav id="nav_prin">
   <h2>Menu Principal</h2>
   <jsp:include page="${requestScope.menuPrincipal}" />
  </nav>		
  <nav id="breadcrumb">
   <h2>Breadcrumb</h2>
    <jsp:include page="${requestScope.breadcrumb}" />
  </nav>		
  <section id="contexto" >
  <nav id="nav_contexto">
   <h2>Menu</h2>
   <jsp:include page="${requestScope.menuContexto}" />
  </nav>
  <article id="sc_contexto">
   <jsp:include page="${requestScope.main}" />
  </article>
 </section>
 <footer>
  <jsp:include page="${footer}" />
 </footer>
</body>
</html>