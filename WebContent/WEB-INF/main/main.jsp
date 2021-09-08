
<%
    String segmento = (String)(request.getAttribute("segmento")); %>
    
    <!--INCLUIMOS EL FRAGMENTO DEL MODAL DE LOGIN  Y REGISTRO-->
    <jsp:include page="../views/Login.jspf"/>
    <jsp:include page="../views/registro.jspf"/>
    
<% if(segmento!="" && segmento!=null){%>
<!--jsp:include page="../layout/head.jspf"/-->

<jsp:include page="../views/${segmento}">
    <jsp:param name="cursos" value="${cursos}" />
</jsp:include>




<%} else {%>

<jsp:include page="../views/header.jspf"/>
  <jsp:include page="../views/contenido.jspf"/>
  <%}%>
  
 
