 $(document).ready(function() {
     
     
       $('.btnAgregarTema').click(function(e) {
             
            e.preventDefault();
		var nuevoTema = $("#nuevoTema").val();
                var idCurso  =$("#idCurso").val();
                if(nuevoTema.length<=0){
                   $('#msj6').html("por favor digite un nombre al tema"); 
                }
                
                
                else{
                
               
                $.ajax({
                    url:'ServletTema',
                   method: "POST",
                    data: {
                        nombre : nuevoTema,
                        idCurso :idCurso,
                        action : 'registrarT'
                    },
                    
                    
                    success:function(respuesta){
                     swal({
                          title: "información",
                          text: respuesta,
                          icon: "success",
                        }).then((value)=>{
                          
                            
                                $('#modalAgregarTema').modal('hide');
                                $("#elegirtema").empty();
                                LlenarCombo();

                        })
                       
                    },error:function(respuesta){
                        
                        alert("an error ocured:",respuesta);
                    }
                    
                        
                             
                             
                       
                   
                 });
             }
                          
		});
                
       function LlenarCombo(){
       $.post('ServletTema',{
           action:'rellenarSelect'
       }, 
       function (data){
          var c = JSON.parse(data);
          $('#elegirtema').append('<option value="">Seleccionar tema</option>');
          $.each(c, function(i, item){
                $('#elegirtema').append('<option value="'+item.idTema+'">'+item.nombre+'</option>');
          });
       });
    }
     
 });