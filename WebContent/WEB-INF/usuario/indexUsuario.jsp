   
    <!--INCLUIMOS EL FRAGMENTO DEL MODAL DE LOGIN-->
    <!--jsp:include page="../views/Login.jspf"/-->
    
<jsp:include page="header.jspf"/>   
<jsp:include page="menu.jspf"/> 

<c:if test="${not empty segmento}">
 <jsp:include page="${segmento}"/>   
</c:if>

<c:if test="${!not empty segmento}">
<c:choose>
  <c:when test="${not empty persona}">
    <jsp:include page="misCursos.jspf"/> 
  </c:when>
  
  <c:otherwise>
     <jsp:include page="formUsuario.jspf"/>
  </c:otherwise>
</c:choose>
</c:if>

 
