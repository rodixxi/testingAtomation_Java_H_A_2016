function VerificarCuil(cuil){
    var sCuil = new String(cuil);
    var sCab = sCuil.substr(0, 2);
    var sNum = sCuil.substr(2, 8);
    var sDv = sCuil.substr(10, 1);
    var DVE, msg;

    if (sCuil.length != 11)    {
        msg = " El CUIL debe ser de 11(once) digitos.";
        return msg;
    }

    if (sCab.length != 2)    {
        msg = " Verifique el CUIL Ingresado";
        return msg;
    }

    if (sNum.length != 8)    {
        msg = " Verifique el CUIL Ingresado";
        return msg;
    }

    if (sDv.length != 1)    {
        msg = " Verifique el CUIL Ingresado";
        return msg;
    }
    //- Compruebo cabezal valido
    var j = /20|23|24|27|30|33|34/;
    var valor = sCab.match(j);
    if (valor == null)
    {
        msg = " Verifique el CUIL Ingresado";
        return msg;
    }
    //- Cargo los pesos
    var pesos = new Array();
    pesos[0] = 5;
    pesos[1] = 4;
    pesos[2] = 3;
    pesos[3] = 2;
    pesos[4] = 7;
    pesos[5] = 6;
    pesos[6] = 5;
    pesos[7] = 4;
    pesos[8] = 3;
    pesos[9] = 2;
    //- Genero el array
    var s = sCab + sNum;
    var ss = s.split("");
    var total = 0;
    //- Multiplico y sumo
    for (i = 0; i <= 9; i++)
    {
        total = total + ss[i] * pesos[i];
    }
    //- Saco el mod
    var resto = total % 11;
    if ((resto == 0) || (resto == 1))
    {
        DVE = 0;
    }

    if (resto > 1)
    {
        DVE = 11 - resto;
    }

    if (sDv == DVE)
    {
        msg = "OK";
        return msg;
    }
    else
    {
        msg = "No es un CUIT/CUIL valido";
        return msg;
    }
}

function VerificarBeneficio(beneficio) {
    if (beneficio.length > 9 && beneficio.length < 12) {
        return "OK";
    }

    return "El número de beneficio no es válido";
}

function validarFormCuil(captcha) {
    if ($('select[name=tipodoc]').val() != '0025' && $('select[name=tipodoc]').val() != '0026' && $('select[name=tipodoc]').val() != '0029') {
        $('select[name=tipodoc]').addClass('error');
        return false;
    }
    if ($('#numDocCUIL').val().length < 6 || $('#numDocCUIL').val().length > 8) {
        $('#numDocCUIL').addClass('error');
        $('#numDocCUIL').parents('form').children('p.mensajeError').show().html("El número de documento es incorrecto.");
        return false;
    }
    if ($('#nombreCUIL').val().length < 1 || $('#nombreCUIL').val().length > 30) {
        $('#nombreCUIL').addClass('error');
        $('#nombreCUIL').parents('form').children('p.mensajeError').show().html("El nombre es incorrecto.");
        return false;
    }
    if ($('#apellidoCUIL').val().length < 1 || $('#apellidoCUIL').val().length > 30) {
        $('#apellidoCUIL').addClass('error');
        $('#apellidoCUIL').parents('form').children('p.mensajeError').show().html("El apellido es incorrecto.");
        return false;
    }
    if ($('#apellidoCasCUIL').val().length > 30) {
        $('#apellidoCasCUIL').addClass('error');
        $('#apellidoCasCUIL').parents('form').children('p.mensajeError').show().html("El apellido de casada es incorrecto.");
        return false;
    }
    var fecha = $('#fechaNacCUIL').val();
    if (fecha.substr(2, 1) != '/' || fecha.substr(5, 1) != '/') {
        $('#fechaNacCUIL').addClass('error');
        $('#fechaNacCUIL').parents('form').children('p.mensajeError').show().html("La fecha de nacimiento es incorrecta.");
        return false;
    }
    if (fecha.length != 10) {
        $('#fechaNacCUIL').addClass('error');
        $('#fechaNacCUIL').parents('form').children('p.mensajeError').show().html("La fecha de nacimiento es incorrecta.");
        return false;
    }

    if ($('input[name=sexoCUIL]:checked').val() != "F" && $('input[name=sexoCUIL]:checked').val() != "M") {
        $('input[name=sexoCUIL]').addClass('error');
        $('#sexoCUIL').parents('form').children('p.mensajeError').show().html("Debe seleccionar un sexo.");
        return false;
    }
    if (captcha.length != 6) {
        $('#captchaCUIL').addClass('error');
        return false;
    }

    return true;
}

function validarFormExpedientes(captcha) {
    if ($('input[name=expTipo]:checked').val() == "") {
        $('input[name=expTipo]').addClass('error');
        return false;
    }

    $('.numEXPED:visible input').each(function () {
        if ($(this).val() == '')
            return false;
    });

    if (captcha.length != 6) {
        $('#captchaEXPED').addClass('error');
        return false;
    }

    return true;
}

function getDondeCobro(tipo, valor, captcha) {
    $('div.loading').show();
    $.ajax({
        url: "/svc/dondecobro.php",
        dataType: "json",
        type: 'POST',
        data: "&" + tipo + "=" + valor + "&captcha=" + captcha,
        success: function (data) {
            $(".cobros form").hide();
            if(data.SLA == 'up' || data.SLA == 'down') {
                ga('send', {
                    hitType: 'event',
                    eventCategory: 'SLA',
                    eventAction: data.SLA,
                    eventLabel: 'DondeCobro'});
            }
            if (data.success) {
                $('.cobros .resultado').show();
                if (data.tipo == "beneficio") {
                    $('.cobros .resultados').show();
                    $('.cobros .beneficiario').hide();
                    $('#dcNombre').html(data.nombre);
                    $('#dcLugar').html(data.lugar);
                    $('#dcPeriodo').html("Período: " + data.periodo);
                    $('#dcFechas li:first span').html(data.desde);
                    $('#dcFechas li:last span').html(data.hasta);
                } else if (data.tipo == "listado") {
                    $('.cobros .beneficiario').show();
                    $('.cobros .resultados').hide();
                    $('.cobros .beneficiario ul').empty();
                    for (var i = 0; i < data.beneficios.length; i++) {
                        $('.cobros .beneficiario ul').append("<li><a href='javascript:void(0)' data-beneficio='" + data.beneficios[i].numero + "'>" + data.beneficios[i].numero + " / <span class='bold'>" + data.beneficios[i].sistema + data.beneficios[i].cuil + "</a></span></li>");
                    }
                    $('#imgCaptchaCOBROS2').attr('src','/svc/captcha.php'+ '?' + (new Date()).getTime());
                    $('.cobros .beneficiario ul li a').on('click', function () {
                        var beneficio = $(this).attr('data-beneficio');
                        getDondeCobro('beneficio', beneficio, $('#captchaCOBROS2').val());
                    });
                }
            } else {
                $('.cobros .error').show();
                $('#dcError').html(data.error);
            }
            //$('div.loading').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $(".cobros form").hide();
            $('.cobros .error').show();
            $('#dcError').html("No se pudieron obtener resultados.");
            $('div.loading').hide();
            ga('send', {
                hitType: 'event',
                eventCategory: 'SLA',
                eventAction: 'down',
                eventLabel: 'DondeCobro'});
        }
    });
}

function getCuil(tipo, numero, nombre, apellido, casada, nacimiento, sexo, captcha) {
    $('div.loading').show();
    var postData = "&tipodoc=" + tipo + "&numdoc=" + numero + "&nombre=" + nombre + "&apellido=" + apellido + "&apellidocasada=" + casada + "&fechnacimiento=" + nacimiento + "&sexo=" + sexo;
    
    ga('send', {
      hitType: 'event',
      eventCategory: '1click',
      eventAction: 'Consulta',
      eventLabel: 'CUIL'
    });
    $.ajax({
        url: "/svc/cuil.php",
        dataType: "json",
        type: 'POST',
        data: postData + "&captcha=" + captcha,
        success: function (data) {
/*            ga('send', {
                hitType: 'event',
                eventCategory: 'SLA',
                eventAction: 'up',
                eventLabel: 'CUIL'});*/
            $(".cuil form").hide();
            if (data.success) {
                $('#cuilResCuil').html(data.datos.cuil);
                $('#cuilResNombre').html(data.datos.nya);
                $('#cuilResDoc').html(data.datos.documento);
                $('#cuilResultados').show();
                $('#cuilURL').attr('href', "/svc/cuilPDF.php?cuil=" + data.datos.cuil + postData);
                $('.cuil .resultados').show();
            } else {
                $('.cuil .error').show();
                $('#cuilError').html(data.error);
            }
            $('div.loading').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $(".cuil form").hide();
            $('.cuil').show();
            $('#cuilError').html("No se pudieron obtener resultados.");
            $('div.loading').hide();
            ga('send', {
                hitType: 'event',
                eventCategory: 'SLA',
                eventAction: 'down',
                eventLabel: 'CUIL'});
        }
    });
}

function getCodem(cuil, captcha) {
    $('div.loading').show();
    var postData = "&cuil=" + cuil;
    ga('send', {
      hitType: 'event',
      eventCategory: '1click',
      eventAction: 'Consulta',
      eventLabel: 'CODEM'
    });
    $.ajax({
        url: "/svc/codem.php",
        dataType: "json",
        type: 'POST',
        data: postData + "&captcha=" + captcha,
        success: function (data) {
            $(".exped form").hide();
            if (data.success) {
                $('#codemResultados').show();
                $('#codemResCuil').html(data.datos.cuil);
                $('#codemURL').attr('href', "/svc/codemHTML.php?cuil=" + data.datos.cuil);
                $('.codem .resultados').show();
            } else {
                $('.exped .error').show();
                $('#cuilError').html(data.error);
            }
            $('div.loading').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $(".exped form").hide();
            $('.exped .error').show();
            $('#cuilError').html("No se pudieron obtener resultados.");
            $('div.loading').hide();
        }
    });
}

function llenarExpediente(expediente) {
    $('#listaExpedientes').hide();
    var contenido = "<p><strong>Expediente:</strong> " + expediente.expediente + "</p>";
    contenido += "<p><strong>Carátula:</strong> " + expediente.caratula + "</p>";
    contenido += "<p><strong>Documento:</strong> " + expediente.documento + "</p>";
    contenido += "<p><strong>Tipo:</strong> " + expediente.tipo + "</p>";
    contenido += "<p><strong>Fecha:</strong> " + expediente.fecha + "</p>";
    contenido += "<p><strong>Estado:</strong> " + expediente.estado + "</p>";
    $('#detalleExpediente').empty().html(contenido).show();
}

function getExpediente(captcha) {
    $('div.loading').show();
    var data = {
        captcha: captcha,
        tipo: 'expediente'
    };
    data['expediente'] = $('#exp1').val() + "-" + $('#exp2').val() + "-" + $('#exp3').val() + "-" + $('#exp4').val() + "-" + $('#exp5').val() + "-" + $('#exp6').val();

    ga('send', {
      hitType: 'event',
      eventCategory: '1click',
      eventAction: 'Consulta',
      eventLabel: 'Expedientes'
    });
    
    $.ajax({
        url: "/svc/expedientes.php",
        dataType: "json",
        type: 'POST',
        data: data,
        success: function (data) {
/*            ga('send', {
                hitType: 'event',
                eventCategory: 'SLA',
                eventAction: 'up',
                eventLabel: 'Expedientes'});*/
            $(".exped form").hide();
            if (data.success) {
                $('.exped .resultado').show();
                if (data.expedientes.length > 1) {
                    $('#listaExpedientes').show();
                    $('#listaExpedientes ul').empty();
                    for (var i = 0; i < data.expedientes.length; i++) {
                        $('#listaExpedientes ul').append("<li><a href='javascript:void(0)' data-expediente='" + i + "'>" + data.expedientes[i].documento + " / <span class='bold'>" + data.expedientes[i].expediente + "</a></span></li>");
                    }
                    expedientes = data.expedientes;
                } else if (data.expedientes.length == 1) {
                    llenarExpediente(data.expedientes[0]);
                } else if (data.expedientes.length == 0) {
                    $('#detalleExpediente').empty().html("No hay resultados para esos datos").show();
                } else {
                    $('.exped .resultado').hide();
                    $('.exped .error').show();
                    $('#expError').html("No se pudieron obtener resultados.");
                }
            } else {
                $('.exped .error').show();
                $('#expError').html(data.error);
            }
            $('div.loading').hide();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ga('send', {
                hitType: 'event',
                eventCategory: 'SLA',
                eventAction: 'down',
                eventLabel: 'Expedientes'});
            $(".exped form").hide();
            $('.exped .error').show();
            $('#expError').html("No se pudieron obtener resultados.");
            $('div.loading').hide();
        }
    });
}

$(document).ready(function(){
    $('.niceIt').NiceIt();
    $(".videoPop").click(function() {
        $('.popup.video iframe').attr('src', '//www.youtube.com/embed/'+$(this).attr('data-codigo')+"?autoplay=1");
        $(".modal, .popup.video").fadeToggle("slow", "linear");
    });
    $('.modal, .cerrar').click(function(){
        $('.popup.video iframe').attr('src', '');
        $('.modal, .popup.video').hide();
    });
    
    /* 1click */
    $('.tramitesUnClic .trigger').click(function(){
        var close = false;
        if($(this).hasClass('ban')){
           close = true;
        }

        $('.tramitesUnClic .trigger').removeClass('ban');
        $('.tramitesUnClic .trigger').parent().removeClass('bgTurq selected');
        $('.subTramite:visible').slideUp();
        if(!close){
            var clase = $(this).attr('data-tramite');
            var label = $(this).attr('data-tramite-eventlabel');
            $('.'+clase+" form").show();
            $('.'+clase+" form p.mensajeError").hide();
            $('.'+clase+" div.resultado").hide();
            $('.'+clase+" div.error").hide();
            $('.'+clase+" img").attr('src','/svc/captcha.php'+ '?' + (new Date()).getTime());
            $(this).addClass('ban');
            $(this).parent().addClass('bgTurq selected');
            $('.'+clase).slideDown();
        }
        
    });


   
    $('.cuil input[type=button]').click(function(){
        var globo = $('.cuil');
        var captcha = $('#captchaCUIL').val();
        $('input',globo).removeClass('error');
        var valido = validarFormCuil(captcha);
        if(valido){
            getCuil($('select[name=tipodoc]').val(), $('#numDocCUIL').val(), $('#nombreCUIL').val(), $('#apellidoCUIL').val(), $('#apellidoCasCUIL').val(), $('#fechaNacCUIL').val(), $('input[name=sexoCUIL]:checked').val(), captcha);
        }
    });
    
    $('.cobros input[type=button]').click(function(){
        var captcha = $('#captchaCOBROS').val();
        var valor = $('#ingreseNumCOBROS').val();
        var valid;
        if($('input[name=consultaCOBROS]:checked').val() == "cuil"){
            valid = VerificarCuil(valor);
        } else {
            valid = VerificarBeneficio(valor);
        }
        if(valid == "OK"){
            $('#ingreseNumCOBROS').removeClass('error');
            $('#dcInputError').html('').hide();
            if(captcha.length == 6){
                ga('send', {
                    hitType: 'event',
                    eventCategory: '1click',
                    eventAction: 'Consulta',
                    eventLabel: 'DondeCobro'
                  });
                getDondeCobro($('input[name=consultaCOBROS]:checked').val(), valor, captcha);
            } else
                $('#captchaCOBROS').addClass('error');
        } else {
            $('#ingreseNumCOBROS').addClass('error');
            $('#dcInputError').html("<strong>Error</strong>: "+valid).show();
        }
    });
    
    $('.exped input[type=button]').click(function(){
        var captcha = $('#captchaEXPED').val();
        $('.exped input').removeClass('error');
        var valido = validarFormExpedientes(captcha);
        if(valido){
            getExpediente(captcha);
        } 
    });
    
    $('.cert input[type=button]').click(function(){
        
    });
    
    $('.subTramite input').keydown(function(){
        $(this).removeClass('error');
    });
    
    $('.volver').click(function(){
        $(this).parents('.resultados').hide();
        var div = $(this).parents('.subTramite');
        $("form", div).show();
        $("form p.mensajeError", div).hide();
        $("div.resultado", div).hide();
        $("div.error", div).hide();
        $("img", div).attr('src','/svc/captcha.php'+ '?' + (new Date()).getTime());
    });
    
    $('.subTramite a.cerrar').click(function(){
        $('.tramitesUnClic .trigger').removeClass('ban');
        $('.tramitesUnClic .trigger').parent().removeClass('bgTurq selected');
        $('.subTramite:visible').slideUp();
    });
    
    /* fuente */
    $('#reducirFont').click(function(){
        var actual = parseInt($('.contenido').css('font-size'));
        if(actual > 10)
            $('.contenido').css('font-size', parseInt(actual-1) + 'px');
    });
    
    $('#aumentarFont').click(function(){
        var actual = parseInt($('.contenido').css('font-size'));
        if(actual < 20)
            $('.contenido').css('font-size', parseInt(actual+1) + 'px');
    });
    
    /* responsive */
    $(".mm").click(function() {
        $(".navTop").fadeToggle("slow", "linear");
    });
    var visible1click = 0;
    $('.tramitesUnClic .nextPrevResp .sig').click(function(){
        $('.tramitesUnClic ul.main li').hide();
        if(visible1click == $('.tramitesUnClic ul.main li').length - 1)
            visible1click = 0;
        else
            visible1click++;
        $('.tramitesUnClic ul.main li:eq('+visible1click+')').show();
    });
    $('.tramitesUnClic .nextPrevResp .ant').click(function(){
        $('.tramitesUnClic ul.main li').hide();
        if(visible1click == 0)
            visible1click = $('.tramitesUnClic ul.main li').length - 1;
        else
            visible1click--;
        $('.tramitesUnClic ul.main li:eq('+visible1click+')').show();
    });
    if(screen.width <= 480){
        $('li.presta > a').click(function(){
            $('nav > ul > li > a').hide();
            $(this).siblings('ul').show();
        });
        $('li.inicio > a').click(function(){
            var ul = $(this).parent('li').parent('ul').hide();
            $('nav > ul > li > a').show();
        });
    }
    if(screen.width > 480){
        $('li.presta > a').click(function(){
            if($(this).siblings('ul').is(":visible")){
                $(this).siblings('ul').hide();
            }else{
                $(this).siblings('ul').show();
            }
        });
    }
});
