$(document).ready(function(){

  $(".respuesta").click(function(){

    $(".some").show();
    $("#comentario").show();
    $("#aclaracion").show();
    $("#enviar").show();
    $("#cancelar").show();
    $(".disponible").show();
    $("#contador").show();
  });

  $("#cancelar").click(function(){
    $(".some").hide();
    $("#comentario").hide();
    $("#enviar").hide();
    $("#cancelar").hide();
    $("input:radio").attr("checked", false);
    $("#comentario").val('');
    $(".disponible").fadeOut();
    $("#contador").fadeOut();
    var diff = 300;
  });

  var pet = $('#frminformacion').attr('action');
  var met = $('#frminformacion').attr('method');
  
    
  $("#enviar").click(function(e){
      $("#status").fadeIn(2000);
  
  si = $("si").val();
  enParte = $("enParte").val();
  no = $("no").val();

  if(si != "" || enParte != "" || no != "" ){
    $.ajax({
      url:pet,
      type:met,
      data:$('#frminformacion').serialize(),
      success:function(result){
        $("#status").html('¡Gracias!');
      }
    });
        
  }else{
    $("#status").html("No deje campos vacíos");
    $("#status").fadeOut(5000);
  }

  $("input:radio").attr("checked", false);
  $("#comentario").val('');
  $(".some").fadeOut();
  $("#si").fadeOut();
  $("#enParte").fadeOut();
  $("#no").fadeOut();
  $(".si").fadeOut();
  $(".enParte").fadeOut();
  $(".no").fadeOut();
  $("#comentario").fadeOut();
  $(".disponible").fadeOut();
  $("#contador").fadeOut();
  $("#enviar").fadeOut();
  $("#cancelar").fadeOut();
  e.preventDefault();
  });
});