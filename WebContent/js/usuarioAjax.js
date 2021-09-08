 $(document).ready(function() {

         
/*=============================================
LOGEARSE
=============================================*/
$('#btnSubmit2').click(function(e) {
             
            e.preventDefault();
		var email = $("#inputEmail").val();
                var pass = $("#inputPassword").val();
                
                if( !(/\w+([-+.']\w+)*@\w+([-.]\w+)/.test(email)) ) {
                    
                   
                    swal({
                          title: "información",
                          text: "correo invalido",
                          icon: "error",
                        })
                }else {
                    
          
                
                $.ajax({
                    url:'ServletUsuario_login',
                   method: "POST",
                    data: {
                        email : email,
                        password : pass,
                        action : 'login'
                    },
                    
                    
                    success:function(respuesta){
                       
                        //alert(respuesta);
                         var n = respuesta.localeCompare("invalido");
                 /*if(n==0 || respuesta == null  ||  respuesta=="")
                 {
                      swal({
                          title: "información",
                          text: "upps! revice su email y password!",
                          icon: "error",
                        })
                 }else{
                     
                 }*/
                        window.location="index.jsp";
                
                       
                    },error:function(respuesta){
                        
                        alert("an error ocured:",respuesta);
                    }
                    
                            //alert(res);
                             
                             
                       //window.location="formPerfil.jsp?idUser="+res;
                   
                  });
              }
                          
		});
               
/*=============================================
REGISTRARSE EL USUARIO  
=============================================*/         
       $('#btnSubmit3').click(function(e) {
             
            e.preventDefault();
		var email = $("#InputEmail").val();
                var pass = $("#InputPassword1").val();
                var pass2 = $("#InputPassword2").val()
                var n = pass.localeCompare(pass2);
                  if( !(/\w+([-+.']\w+)*@\w+([-.]\w+)/.test(email)) ) {
                    
                   $('#msj2').html("error al introducir el email");
                    
                }else if(n!=0){
                   $('#msj2').html("error contraseñas incompatibles"); 
                }
                else{
                
                
                $.ajax({
                    url:'ServletUsuario_login',
                   method: "POST",
                    data: {
                        email : email,
                        password : pass,
                        password2 : pass2,
                        action : 'registrar'
                    },
                    
                    
                    success:function(respuesta){
                     swal({
                          title: "información",
                          text: respuesta,
                          icon: "success",
                        }).then((value)=>{
                           // window.location="index.jsp";
                           $('#exampleModal2').modal('hide');
                            $('#exampleModal').modal('show');
                            
                                

                        })
                       
                    },error:function(respuesta){
                        
                        alert("an error ocured:",respuesta);
                    }
                    
                            //alert(res);
                             
                             
                       //window.location="formPerfil.jsp?idUser="+res;
                   
                  });
              }
                          
		});
                
          
/*=============================================
INTRODUCIR DATOS A TABLA PERSONA
=============================================*/ 
         $('#btnGuardarr').click(function() {
                        var idUserVar = $('#iduser').val();
			var nombreVar = $('#nombre').val();
			var apellidoVar = $('#apellido').val();
			var acercaVar = $('#acerca').val();
                        var emailVar = $('#email').val();
                        
                        var action ="insertar";
                         /*if(nombreVar==""){
                        $('#msj').html("error");
                        
                         }*/
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletPersona', {
                                iduser : idUserVar,
				nombre : nombreVar,
				apellido: apellidoVar,
				acerca :acercaVar,
                                email : emailVar,
                                action : action
			}, function(responseText) {
                             swal({
                          title: "información",
                          text: "Actualizado con exito",
                          icon: "success",
                        }).then((value)=>{
                           // window.location="index.jsp";
                            window.location="index.jsp";
                            
                                

                        })
                           
				//$('#tabla').html(responseText);
			});
		});
   /*=============================================
AGREGAR DESDE ADMIN
=============================================*/             
                
        $('.btnAgregarUsuario').click(function() {
                       // var idUserVar = $('#nuevoiduser').val();
			var nombreVar = $('#nuevoNombre').val();
			var apellidoVar = $('#nuevoApellido').val();
			var acercaVar = $('#nuevoAcerca').val();
                        var emailVar = $('#nuevoEmail').val();
                        var passVar = $('#nuevoPassword').val();
                        var rolVar = $('#nuevoPerfil').val();
                 
                        var action ="agregarUsuario";
                         /*if(nombreVar==""){
                        $('#msj').html("error");
                        
                         }*/
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletPersona', {
                                //iduser : idUserVar,
				nombre : nombreVar,
				apellido: apellidoVar,
				acerca :acercaVar,
                                email : emailVar,
                                password:passVar,
                                rol:rolVar,
                                action : action
			}, function(responseText) {
                            window.location="index.jsp";
				//$('#tabla').html(responseText);
			});
		});
  /*=============================================
ACTUALIZAR USUARIO DESDE PERFIL USUARIO
=============================================*/        
                
        $('#btnActualizarr').click(function() {
                        var idUserVar = $('#iduser').val();
			var nombreVar = $('#nombre').val();
			var apellidoVar = $('#apellido').val();
			var acercaVar = $('#acerca').val();
                        var emailVar = $('#email').val();
                        
                        var action ="actualizar";
                         /*if(nombreVar==""){
                        $('#msj').html("error");
                        
                         }*/
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletPersona', {
                                iduser : idUserVar,
				nombre : nombreVar,
				apellido: apellidoVar,
				acerca :acercaVar,
                                email : emailVar,
                                action : action
			}, function(responseText) {
                             swal("guardado exitosamente!", "", "success");
				//$('#tabla').html(responseText);
			});
		});
    /*=============================================
ACTUALIZAR PASS DESDE PERFIL USUARIO
=============================================*/        
                
        $('.btnActualizarPass').click(function() {
                        var idUserVar = $('#iduser').val();
			var passactualVar = $('#passactual').val();
                        var passnuevaVar = $('#passnueva').val();
                          var passrepeat = $('#passrepeat').val();
                            var action ="actualizarPass";
                        var n = passnuevaVar.localeCompare(passrepeat);
                        if(n!=0){
                            $('#msj').html("error contraseñas no iguales");
                        }else{
                      
                         
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                        
			$.post('ServletUsuario_login', {
                                iduser : idUserVar,
				passactual : passactualVar ,
				passnueva: passnuevaVar,
				
                                
                                action : action
			}, function(responseText) {
                            if(responseText.localeCompare("errorpass")==0)
                            {
                               $('#msj').html("vuela a digitar su contraseña actual"); 
                            }else{
                                swal({
                                         type: "success",
                                         title:responseText,
                                         showConfirmButton: true,
                                         confirmButtonText: "Cerrar",
                                         closeOnConfirm: false
                                         }).then((result) => {
                                        if (result.value) {

                                        location.reload();

                                        }
                                        })
                            }//$('#tabla').html(responseText);
			});
                    }
		});
        
        /*=============================================
ACTUALIZAR USUARIO DESDE ADMIN
=============================================*/        
                
        $('.btnActualizarUsuario').click(function() {
                        var idUserVar = $('#editarIdUser').val();
			var nombreVar = $('#editarNombre').val();
			var apellidoVar = $('#editarApellido').val();
			var acercaVar = $('#editarAcerca').val();
                        var emailVar = $('#editarEmail').val();
                        
                        var passwordVar= $('#editarPassword').val();
                        var mipasswordVar= $('#myPassword').val();
                        alert(mipasswordVar);
                      
                        var rolVar =$('#editarPerfil').val();
                        
                        var action ="actualizarUsuario";
                         /*if(nombreVar==""){
                        $('#msj').html("error");
                        
                         }*/
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletPersona', {
                                iduser : idUserVar,
				nombre : nombreVar,
				apellido: apellidoVar,
				acerca :acercaVar,
                                email : emailVar,
                                password :passwordVar,
                                miPassword :mipasswordVar,
                                rol : rolVar,
                                action : action
			}, function(responseText) {
                            
//                           
                             swal({
                                         type: "success",
                                         title: "El usuario ha sido modificado correctamente",
                                         showConfirmButton: true,
                                         confirmButtonText: "Cerrar",
                                         closeOnConfirm: false
                                         }).then((result) => {
                                        if (result.value) {

                                        location.reload();

                                        }
                                        })
			});
		});
        
                
/*=============================================
ELIMINAR USUARIO
=============================================*/
  
    
        $('.btnEliminaUsuario').click(function(e) {
             e.preventDefault();
             var idUser = $(this).attr("idUsuario");
              
        swal({
        title: 'Esta seguro de borrar el usuario?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, borrar usuario!'
      }).then((result) => {
        if (result.value) {
           var action ="borrarUsuario";
           //var fila=$(this).parent().parent();
          // var idCurso = $(this).parent().parent().find('#idCurso').text();
           var data ={
               idUser:idUser,
               action:action
           };
           $.post('ServletUsuario_login',data,function(res) {
                location.reload();
               // fila.remove();
           });

           
        }
            
		//var idCursoVar = $(this).attr("idCurso");
           }) 
        })
        
 /*=============================================
EDITAR USUARIO
=============================================*/

                $(".btnEditarUsuario").click(function(){

                    var idUsuario= $(this).attr("idUsuario");
                    var action ="editarUsuario";
                   
                    var datos = {
                        action:action,
                        idUser:idUsuario   
                    };

              $.ajax({
                  type: "POST",
                  url:"ServletPersona",
                 
                  data:datos,
                  success:function(respuesta){
                      //parsear a JSON
                      var respuesta = JSON.parse(respuesta);
                       
                       
                           $("#editarNombre").val(respuesta[0]);
                           $("#editarApellido").val(respuesta[1]);
                           $("#editarAcerca").val(respuesta[2]);
                          $("#valorPerfil").val(respuesta[3]);
                           $("#valorPerfil").html(respuesta[3]);
                           $("#editarEmail").val(respuesta[4]);
                           //$("#editarPassword").val(respuesta[5]);
                           $("#myPassword").val(respuesta[5]);
                          
                           $("#editarIdUser").val(respuesta[6]);

                      }

                    })

            })

 });