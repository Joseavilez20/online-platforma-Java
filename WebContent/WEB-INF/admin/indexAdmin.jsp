<%@page import="entidades.Usuario"%>
<!DOCTYPE html>

<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
  
  
  <link rel="stylesheet" href="css/fontawesome-all.css" >
  

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  <style>
	
	video {
    width: 100%;
    height: auto;
}

</style>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">

   <% if(session.getAttribute("user")!=null)
{
  Usuario  u= (Usuario) request.getSession().getAttribute("user");
  if(u.getRol().equals("admin"))
  {%>
  <jsp:include page="header.jspf" />
  <!-- Left side column. contains the logo and sidebar -->
  <jsp:include page="menu.jspf" />
  <!-- Content Wrapper. Contains page content -->
  <%!String pagina="";
  String param="";%>
 <% pagina=(String) request.getAttribute("path");
 
 %>

 <%if (pagina!=null && pagina!=""){ %>
 
  <jsp:include page="<%=pagina %>"/> 

 
 <%}else{%>
   <jsp:include page="dashboard.jspf" />
   <%} %>

 
 
  <!-- Main Footer -->
 <jsp:include page="footer.jspf" />

  <!-- Control Sidebar -->
 <jsp:include page="ItemsAdmin.jspf" />
  <!-- /.control-sidebar -->
  
   
<%}else{
 response.sendRedirect("index.jsp");
%>
   
<%}%>
   
<%}else{response.sendRedirect("index.jsp");%>

<%}%>


  <!-- Main Header -->
 
 
  
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<script src="js/cursoAjax.js"></script>
<script src="js/usuarioAjax.js"></script>
<script src="js/fileAjax.js"></script>
<script src="js/precargarVideo.js"></script>
<script  src="js/sweetalert2.all.js"></script>
 <script src="js/temaAjax.js" ></script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>
  

