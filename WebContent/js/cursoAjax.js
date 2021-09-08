

 $(document).ready(function() {
        
         
/*=============================================
AGREGAR CURSO
=============================================*/
$('.btnAgregarCurso').click(function() {
			var nombreVar = $('#nuevoNombre').val();
			var descripcionVar = $('#nuevaDescripcion').val();
			var subtituloVar = $('#nuevoSubtitulo').val();
                        var instructorVar = $('#nuevoInstructor').val();
                        var nivelVar = $('#nuevoNivel').val();
                        var action ="insertar";
                        if(nombreVar.length<=0 || instructorVar.length<=0 || nivelVar.length<=0){
                            $('.msj').html("existen campos vacios que son requeridos");
                        }
                       
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletCurso', {
				nombre : nombreVar,
				descripcion: descripcionVar,
				subtitulo : subtituloVar,
                                instructor : instructorVar,
                                nivel : nivelVar,
                                Action : action
			}, function(responseText) {
                          
                          swal({
                                         type: "success",
                                         title: "Guardado correctamente",
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
ELIMINAR CURSO
=============================================*/
  
    
        $('.btnEliminarCurso').click(function(e) {
             e.preventDefault();
             var idCurso = $(this).attr("idCurso");
              
        swal({
        title: 'Esta seguro de borrar el curso?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, borrar curso!'
      }).then((result) => {
        if (result.value) {
           var action ="borrar";
           //var fila=$(this).parent().parent();
          // var idCurso = $(this).parent().parent().find('#idCurso').text();
           var data ={idcurso:idCurso,Action:action};
           $.post('ServletCurso',data,function(res) {
                location.reload();
               // fila.remove();
           });

           
        }
            
		//var idCursoVar = $(this).attr("idCurso");
           }) 
        })
                   
/*=============================================
ACTUALIZAR CURSO
=============================================*/
                
       $('.btnActualizar').click(function(e) {
             e.preventDefault();
            // var Option= confirm("desea actualizar el producto");
            // var action ="actualizar";
		
               // if (Option){
                       var idCursoVar = $('#idCurso').val();
                        var nombreVar = $('#editarNombre').val();
                        var descripcionVar = $('#editarDescripcion').val();
			var subtituloVar = $('#editarSubtitulo').val();
                     var instructorVar = $('#editarInstructor').val();
                        var nivelVar = $('#editarNivel').val();
                        //var y = document.getElementById("mySelect2");
                        // var index = y.selectedIndex;
                        //var instructorVar =x.options[index].text;
                        
                        
                        var action ="actualizar";
                        
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ServletCurso', {
                                idcurso :  idCursoVar,
				nombre : nombreVar,
				descripcion: descripcionVar,
				subtitulo : subtituloVar,
                                instructor : instructorVar,
                                nivel : nivelVar,
                                Action : action
			}, function(responseText) {
                                //swal("Actualizacion exitosa!", "", "success");
                                  swal({
                                         type: "success",
                                         title: "El curso ha sido cambiado correctamente",
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
EDITAR CURSO
=============================================*/

                $(".btnEditarCurso").click(function(){

                    var idCurso = $(this).attr("idCurso");
                    var action ="editarCurso";
                   
                    var datos = {
                        Action:action,
                        idCurso:idCurso   
                    };

              $.ajax({
                  type: "POST",
                  url:"ServletCurso",
                 
                  data:datos,
                  success:function(respuesta){
                      //parsear a JSON
                      var respuesta = JSON.parse(respuesta);
   
                       $("#idCurso").val(respuesta["idCurso"]);
                           $("#editarNombre").val(respuesta["nombre"]);
                           $("#editarSubtitulo").val(respuesta["subTitulo"]);
                           $("#editarInstructor").val(respuesta["instructor"]);
                           $("#valor").val(respuesta["nivel"]);
                           $("#valor").html(respuesta["nivel"]);
                          
                           $("#editarDescripcion").val(respuesta["descripcion"]);
                      
                      }

                    })

            })


/*=============================================
INSCRIBIRSE EN UN CURSO
=============================================*/
  
    
        $('.btnInscribirse').click(function(e) {
             e.preventDefault();
             var idCurso = $(this).attr("idcurso");
             var idUser = $(this).attr("iduser");
             if(idUser!=null){
                 
             
             var action ="inscribirseCurso";
 
               var datos = {
                        Action:action,
                        idCurso:idCurso ,
                        idUser:idUser
                    };

              $.ajax({
                  type: "POST",
                  url:"ServletCurso",
                 
                  data:datos,
                  success:function(respuesta){
                        var progreso = 0;
                var idIterval = setInterval(function(){
                  // Aumento en 10 el progeso
                  progreso +=10;
                  $("#btnInscribir").html('<i class="fas fa-spinner fa-spin"></i>');
                  $('.videosa').prop("disabled", false);
                  //Si llegó a 40 elimino el interval
                  if(progreso == 30){  
                      $("#btnInscribir").html(respuesta);
                    clearInterval(idIterval);
                  }
                },1000);
                     
                     
                      
                      }

                    })
                }else{
                    
                    window.location="index.jsp";
                }
        })
        

        
         $('.btnmsj').click(function(e) {
             e.preventDefault();
            
              
        swal({
        title: 'Usted debe rellenar primero sus datos ?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'llenar datos!'
      }).then((result) => {
        if (result.value) {
           
                window.location="perfil";
          

           
        }
            
		//var idCursoVar = $(this).attr("idCurso");
           }) 
        })
                   
/*=============================================
ELIMINAR PERSONA DE CURSO
=============================================*/
  
    
        $('.btnEliminaUsuarioCurso').click(function(e) {
             e.preventDefault();
             var idCurso = $(this).attr("idcurso");
             var idUser  =$(this).attr("idusuario");
              
        swal({
        title: 'Esta seguro de borrar el usuario de dicho curso?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, borrar usuario!'
      }).then((result) => {
        if (result.value) {
           var action ="borrarUsuarioDeCurso";
           //var fila=$(this).parent().parent();
          // var idCurso = $(this).parent().parent().find('#idCurso').text();
           var data ={idcurso:idCurso,
               iduser:idUser,
               Action:action
           };
           $.post('ServletCurso',data,function(res) {
                location.reload();
               // fila.remove();
           });

           
        }
            
		//var idCursoVar = $(this).attr("idCurso");
           }) 
        })
        
        
          });   
           
 