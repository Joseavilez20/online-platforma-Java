
<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<% if(session.getAttribute("user")!=null && session.getAttribute("user")!="" )
{
  Usuario  u= (Usuario) request.getSession().getAttribute("user");
  if(u.getRol().equals("admin"))
  {%>
      <jsp:include page="WEB-INF/admin/indexAdmin.jsp"/>
  <%}else  if(u.getRol().equals("estudiante")){%>
   <jsp:include page="WEB-INF/usuario/indexUsuario.jsp"/>
    <%}else{%>
        <jsp:include page="WEB-INF/main/main.jsp"/>
    <%}%>
<%}else{%>
<jsp:include page="WEB-INF/main/main.jsp"/>
<%}%>




