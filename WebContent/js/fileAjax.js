$(document).ready(function () {

    $("#btnSubmit5").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        // Get form
        //var form = $('#form1')[0]; //option1
        var form = document.getElementById("form1");//option2
  
		// Create an FormData object 
       var data = new FormData(form);//option1 
        /*var data = new FormData();
        $.each($('#files')[0].files, function(k, value)
            {
                console.log(value);
                data.append(k, value);
            });
		*/
        /*jQuery.each(jQuery('#file')[0].files, function(i, file) {
    data.append('file-'+i, file);
        });*/
       
        

		// disabled the submit button
       // $("#btnSubmit").prop("disabled", true);
         var paperElement = document.getElementById("files");

  if (!paperElement.value) {
    console.log("No files selected.")
    return;
  }
 
  

         $.ajax({
            type: "POST",
            //enctype: 'multipart/form-data',
            url: "ServletImg",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
           /* timeout: 600000,*/
            success: function (data) {
              
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
               console.log("SUCCESS : ", data);
                //$("#btnSubmit").prop("disabled", false);

            },
            error: function (e) {
                 
                //$("#result").text(e.responseText);
                console.log("ERRORr : ", e);
                //$("#btnSubmit").prop("disabled", false);

            }
        });

    });
    
    
     $('.btnEliminarContenidoCurso').click(function(e) {
             e.preventDefault();
             var idContenido  =$(this).attr("idContenido");
             var idCurso = $(this).attr("idcurso");
             var idTema  =$(this).attr("idTema");
             var ruta  =$(this).attr("ruta");
              
        swal({
        title: 'Esta seguro de borrar el contenido de dicho tema?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, borrar contenido!'
        
      }).then((result) => {
        if (result.value) {
            
           var action ="borrarContenidoDeCurso";

           var data ={idcurso:idCurso,
               idcontenido:idContenido,
               idtema:idTema,
               ruta:ruta,
               action:action
           };
           $.post('ServletContenido',data,function(res) {
                location.reload();
               
           });

           
        }
            
		
           }) 
        })
        
         $('.btnEliminarTemaCurso').click(function(e) {
             e.preventDefault();
              
             var idCurso = $(this).attr("idcurso");
             var idTema  =$(this).attr("idTema");
              
        swal({
        title: 'Esta seguro de borrar el tema y todo su contenido en el?',
        text: "Si no lo esta puede cancelar la accion!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, borrar tema!'
        
      }).then((result) => {
        if (result.value) {
            
           var action ="borrarTemaDeCurso";

           var data ={
               idcurso:idCurso, 
               idtema:idTema,
               
               action:action
           };
           $.post('ServletTema',data,function(res) {
                location.reload();
               
           });

           
        }
            
		
           }) 
        })
        
        

});