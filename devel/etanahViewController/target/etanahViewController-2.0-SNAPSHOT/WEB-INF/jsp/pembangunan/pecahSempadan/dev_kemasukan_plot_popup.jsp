<%--
    Document   : dev_kemasukan_plot_popup
    Created on : Jun 30, 2010, 10:13:02 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#jumPlot').hide();
        $('#plotHakmiliktemp').hide();
        $('#plotTHakmiliktemp').hide();
        $('#plotKerajaantemp').hide();
        $('#jumluas').hide();
        $('#jumluasHakmilik').hide();
        $('#jumluasTHakmilik').hide();
        $('#jumluasKerajaan').hide();
        $('#jumluasKerajaanAmbil').hide();
        $('#luasHmcur').hide();
        $('#luasRzcur').hide();
        $('#luasKjcur').hide();
        $('#luaskerajaanAmbilR').hide();
        
    });
</script>
<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function calcLuasHakmilik(elmnt,content){
        // validations
        var jumluas = $('#jumluas').val();
        var luashakmilik = $('#luashakmilik').val();
        var jumhakmilik = $('#jumluasHakmilik').val();
        var jumthakmilik = $('#jumluasTHakmilik').val();
        var jumkerajaan = $('#jumluasKerajaan').val();
        var jumhakmilikcur = $('#luasHmcur').val();

        var jumtemp = parseFloat(Number(jumluas)) - ((parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan))));
        var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
        var base = parseFloat(Number(0));

        if(parseFloat(Number(jumhakmilikcur)) > base){
            //edit record
            var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumhakmilikcur)));
            luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
            if(parseFloat(Number(luashakmilik)) > parseFloat(Number(luastinggal))){
                alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                $('#luashakmilik').val('');
                $('#luashakmilik').focus();
                return true;
            }
    <%-- var baki = parseFloat(Number(luastinggal)) - parseFloat(Number(luashakmilik));
     if(baki > base){
         alert('Sila masukkan luas yang sebenar.');
         $('#luashakmilik').val('');
         $('#luashakmilik').focus();
         return true;
     }--%>
             }
             else{
                 // Add record
                 jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                 if(parseFloat(Number(luashakmilik)) > parseFloat(Number(jumtemp))){
                     alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                     $('#luashakmilik').val('');
                     $('#luashakmilik').focus();
                     return true;
                 }
             }
             return false;
         }

         function calcLuasTHakmilik(elmnt,content){
             // validations
             validateNumber(elmnt,content);

             var jumluas = $('#jumluas').val();
             var luasrizab = $('#luasrizab').val();
             var kodUOM = $('#kodUOM').val();  
             if (kodUOM !== '') {
                 if (kodUOM == 'M') {
                     luasrizab = luasrizab / 10000;
                     alert('covert luas : '+luasrizab);
                 }
             } else {
                 alert('Sila masukkan unit luas');
                 return false; 
             }
             var jumhakmilik = $('#jumluasHakmilik').val();
             var jumthakmilik = $('#jumluasTHakmilik').val();
             var jumkerajaan = $('#jumluasKerajaan').val();                            
             var jumrizabcur = $('#luasRzcur').val();
             var jumtemp = parseFloat(Number(jumluas)) - ((parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan))));
             var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
             var base = parseFloat(Number(0));
             if(parseFloat(Number(jumrizabcur)) > base){
                 //edit record
                 var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumrizabcur)));
                 luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
                 if(parseFloat(Number(luasrizab)) > parseFloat(Number(luastinggal))){
                     alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                     $('#luasrizab').val('');
                     $('#luasrizab').focus();
                     return true;
                 }
    <%--var baki = parseFloat(Number(luastinggal)) - parseFloat(Number(luasrizab));
    if(baki > base){
        alert('Sila masukkan luas yang sebenar.');
        $('#luasrizab').val('');
        $('#luasrizab').focus();
        return true;
    }--%>
            }
            else{
                // add record
                jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                if(parseFloat(Number(luasrizab)) > parseFloat(Number(jumtemp))){
                    alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                    $('#luasrizab').val('');
                    $('#luasrizab').focus();
                    return true;
                }
            }
        }

        function calcLuasKerajaan(elmnt,content){
            // validations
            validateNumber(elmnt,content);

            var jumluas = $('#jumluas').val();
            var luaskerajaan = $('#luaskerajaan').val();
            var jumhakmilik = $('#jumluasHakmilik').val();
            var jumthakmilik = $('#jumluasTHakmilik').val();
            var jumkerajaan = $('#jumluasKerajaan').val();
            var jumkerajaancur = $('#luasKjcur').val();
            var jumtemp = parseFloat(Number(jumluas)) - parseFloat(Number(jumhakmilik)) - parseFloat(Number(jumthakmilik)) - parseFloat(Number(jumkerajaan));
            var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
            var base = parseFloat(Number(0));
            if(parseFloat(Number(jumkerajaancur)) > base){
                // edit record
                var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumkerajaancur)));
                luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
                if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(luastinggal))){
                    alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                    $('#luaskerajaan').val('');
                    $('#luaskerajaan').focus();
                    return true;
                }
    <%-- var baki = parseFloat(Number(luastinggal)) - parseFloat(Number(luaskerajaan));
     if(baki > base){
         alert('Sila masukkan luas yang sebenar.');
         $('#luaskerajaan').val('');
         $('#luaskerajaan').focus();
         return true;
     }--%>
             }
             else{
                 // add record
                 jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                 if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(jumtemp))){
                     alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                     $('#luaskerajaan').val('');
                     $('#luaskerajaan').focus();
                     return true;
                 }
             }
         }

    <%--    function validateInput(input){
            var plot = $('#jumPlot').val();
            if (input > plot){
                alert('No Plot tidak wujud.');
                return true;
            }
        }--%>
</script>
<script type="text/javascript">
    function savePlotHakmilik(event, f){
    <%--alert("savePlot");--%>
            if(!validatePlotHakmilik()){
    <%--alert("save");--%>
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageMaklumatPecahSempadan();
                    self.close();
                },'html');
            }
        }
        
        //        rizab
        function savePlotTHakmilik(event, f){          
            if(!validatePlotTHakmilik()){    
        
                var jumluas = $('#jumluas').val();
                var luasrizab = $('#luasrizab').val();
               
                var jumhakmilik = $('#jumluasHakmilik').val();
                var jumthakmilik = $('#jumluasTHakmilik').val();
                var jumkerajaan = $('#jumluasKerajaan').val(); 
                //var jumluasKerajaanAmbil = $('#jumluasKerajaanAmbil').val();  
                         
                var jumrizabcur = $('#luasRzcur').val();

                var kodUOM = $('#kodUOM').val();  
                if (kodUOM !== '') {
                                   
                <c:if test="${actionBean.hpToget0.hakmilik.kodUnitLuas.kod eq 'H'}">
                    if (kodUOM == 'M') {
                        luasrizab = luasrizab / 10000;
                    }
                </c:if>
                    
                <c:if test="${actionBean.hpToget0.hakmilik.kodUnitLuas.kod eq 'M'}">
                    if (kodUOM == 'H') {
                        luasrizab = luasrizab * 10000;
                    }
                </c:if>
                    
                } else {
                    alert('Sila masukkan unit luas');
                    return false; 
                }

                var jumtemp = parseFloat(Number(jumluas)) - parseFloat(Number(jumhakmilik)) - parseFloat(Number(jumthakmilik)) -  parseFloat(Number(jumkerajaan));
                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
                var base = parseFloat(Number(0));
                
                if(parseFloat(Number(jumrizabcur)) > base){
                    //edit record
                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumrizabcur)));
                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
                    if(parseFloat(Number(luasrizab)) > parseFloat(Number(luastinggal))){
                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                        $('#luasrizab').val('');
                        $('#luasrizab').focus();
                        return true;
                    }
                }
                else{
                    // add record
                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                    if(parseFloat(Number(luasrizab)) > parseFloat(Number(jumtemp))){
                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                        $('#luasrizab').val('');
                        $('#luasrizab').focus();
                        return true;
                    }
                }
            
    <%--alert("save");--%>
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageMaklumatPecahSempadan();
                    $.unblockUI();
                    self.close();
                },'html');
            }
        }
        
        
        //kerajaan
        function savePlotTanahKerajaan(event, f){
    <%--alert("savePlot");--%>
            if(!validatePlotTanahKerajaan()){
                
                var jumluas = $('#jumluas').val();
                var luaskerajaan = $('#luaskerajaan').val();

                var jumhakmilik = $('#jumluasHakmilik').val();
                var jumthakmilik = $('#jumluasTHakmilik').val();
                var jumkerajaan = $('#jumluasKerajaan').val();
                //var jumluasKerajaanAmbil = $('#jumluasKerajaanAmbil').val();

                var jumkerajaancur = $('#luasKjcur').val();

                var kodUOM = $('#kodUOM').val();  
                if (kodUOM !== '') {              
                
                <c:if test="${actionBean.hpToget0.hakmilik.kodUnitLuas.kod eq 'H'}">
                    if (kodUOM == 'M') {
                        luaskerajaan = luaskerajaan / 10000;
                    }
                </c:if>
                    
                <c:if test="${actionBean.hpToget0.hakmilik.kodUnitLuas.kod eq 'M'}">
                    if (kodUOM == 'H') {
                        luaskerajaan = luaskerajaan * 10000;
                    }
                </c:if>
                    
                } else {
                    alert('Sila masukkan unit luas');
                    return false; 
                }

                var jumtemp = parseFloat(Number(jumluas)) - parseFloat(Number(jumhakmilik)) - parseFloat(Number(jumthakmilik)) - parseFloat(Number(jumkerajaan));
                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
                var base = parseFloat(Number(0));

                if(parseFloat(Number(jumkerajaancur)) > base){
                    // edit record
                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumkerajaancur)));
                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
                    if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(luastinggal))){
                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                        $('#luaskerajaan').val('');
                        $('#luaskerajaan').focus();
                        return true;
                    }
                }
                else{
                    // add record
                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                    if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(jumtemp))){
                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                        $('#luaskerajaan').val('');
                        $('#luaskerajaan').focus();
                        return true;
                    }
                }
    <%--alert("save");--%>
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);            
                    self.opener.refreshPageMaklumatPecahSempadan();            
                    $.unblockUI();
                    self.close();
                },'html');
            }
        }
        
        //kerajaanAmbil
        function savePlotTanahAmbilKerajaan(event, f){
            if(!validatePlotTAmbilKerajaan()){

                //                var jumluas = $('#jumluas').val();
                //                var luaskerajaanAmbil = $('#luaskerajaanAmbil').val();  
                //            
                //                var jumhakmilik = $('#jumluasHakmilik').val();
                //                var jumthakmilik = $('#jumluasTHakmilik').val();
                //                var jumkerajaan = $('#jumluasKerajaan').val();        
                //                var jumluasKerajaanAmbil = $('#jumluasKerajaanAmbil').val();
                //       
                //                var jumluaskerajaanAmbilR = $('#luaskerajaanAmbilR').val();
                //
                //                var kodUOM = $('#kodUOM').val();  
                //                if (kodUOM !== '') {
                //                    if (kodUOM == 'M') {
                //                        luaskerajaanAmbil = luaskerajaanAmbil / 1000;
                //                    }
                //                } else {
                //                    alert('Sila masukkan unit luas');
                //                    return false; 
                //                }
                //
                //                var jumtemp = parseFloat(Number(jumluas)) - ((parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)) + parseFloat(Number(jumluasKerajaanAmbil))));
                //                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)) + parseFloat(Number(jumluasKerajaanAmbil)));
                //                var base = parseFloat(Number(0));
                //
                //                if(parseFloat(Number(jumluaskerajaanAmbilR)) > base){
                //                    // edit record
                //                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumluaskerajaanAmbilR)));
                //                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
                //                    if(parseFloat(Number(luaskerajaanAmbil)) > parseFloat(Number(luastinggal))){
                //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                //                        $('#luaskerajaanAmbil').val('');
                //                        $('#luaskerajaanAmbil').focus();
                //                        return true;
                //                    }
                //                }
                //                else{
                //                    // add record
                //                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
                //                    if(parseFloat(Number(luaskerajaanAmbil)) > parseFloat(Number(jumtemp))){
                //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
                //                        $('#luaskerajaanAmbil').val('');
                //                        $('#luaskerajaanAmbil').focus();
                //                        return true;
                //                    }
                //                }
            
    <%--alert("save");--%>
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);            
                    self.opener.refreshPageMaklumatPecahSempadan();            
                    $.unblockUI();
                    self.close();
                },'html');
            }
        }
        
        //COOOOOOMMMMMMENNNNNNNNTTTTTTTTTT
        //
        //        function savePlotTHakmilik(event, f){          
        //            if(!validatePlotTHakmilik()){            
        //                var jumluas = $('#jumluas').val();
        //                var luasrizab = $('#luasrizab').val();
        //                var kodUOM = $('#kodUOM').val();  
        //                if (kodUOM !== '') {
        //                    if (kodUOM == 'M') {
        //                        luasrizab = luasrizab / 1000;
        //                        //alert('covert luas : '+luasrizab);
        //                    }
        //                } else {
        //                    alert('Sila masukkan unit luas');
        //                    return false; 
        //                }
        //                var jumhakmilik = $('#jumluasHakmilik').val();
        //                var jumthakmilik = $('#jumluasTHakmilik').val();
        //                var jumkerajaan = $('#jumluasKerajaan').val();                            
        //                var jumrizabcur = $('#luasRzcur').val();
        //                var jumtemp = parseFloat(Number(jumluas)) - ((parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan))));
        //                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
        //                var base = parseFloat(Number(0));
        //                if(parseFloat(Number(jumrizabcur)) > base){
        //                    //edit record
        //                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumrizabcur)));
        //                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luasrizab)) > parseFloat(Number(luastinggal))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luasrizab').val('');
        //                        $('#luasrizab').focus();
        //                        return true;
        //                    }
        //                }
        //                else{
        //                    // add record
        //                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luasrizab)) > parseFloat(Number(jumtemp))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luasrizab').val('');
        //                        $('#luasrizab').focus();
        //                        return true;
        //                    }
        //                }
        //            
        //    <%--alert("save");--%>
        //                $.blockUI({
        //                    message: $('#displayBox'),
        //                    css: {
        //                        top:  ($(window).height() - 50) /2 + 'px',
        //                        left: ($(window).width() - 50) /2 + 'px',
        //                        width: '50px'
        //                    }
        //                });
        //            
        //                var q = $(f).formSerialize();
        //                var url = f.action + '?' + event;
        //                $.post(url,q,
        //                function(data){
        //                    $('#page_div',opener.document).html(data);
        //                    self.opener.refreshPageMaklumatPecahSempadan();
        //                    $.unblockUI();
        //                    self.close();
        //                },'html');
        //            }
        //        }
        //
        //        function savePlotTanahKerajaan(event, f){
        //    <%--alert("savePlot");--%>
        //            if(!validatePlotTanahKerajaan()){
        //                
        //                var jumluas = $('#jumluas').val();
        //                var luaskerajaan = $('#luaskerajaan').val();
        //                var jumhakmilik = $('#jumluasHakmilik').val();
        //                var jumthakmilik = $('#jumluasTHakmilik').val();
        //                var jumkerajaan = $('#jumluasKerajaan').val();
        //                var jumkerajaancur = $('#luasKjcur').val();
        //                var kodUOM = $('#kodUOM').val();  
        //                if (kodUOM !== '') {
        //                    if (kodUOM == 'M') {
        //                        luaskerajaan = luaskerajaan / 1000;
        //                    }
        //                } else {
        //                    alert('Sila masukkan unit luas');
        //                    return false; 
        //                }
        //                var jumtemp = parseFloat(Number(jumluas)) - parseFloat(Number(jumhakmilik)) - parseFloat(Number(jumthakmilik)) - parseFloat(Number(jumkerajaan));
        //                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
        //                var base = parseFloat(Number(0));
        //                if(parseFloat(Number(jumkerajaancur)) > base){
        //                    // edit record
        //                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumkerajaancur)));
        //                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(luastinggal))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luaskerajaan').val('');
        //                        $('#luaskerajaan').focus();
        //                        return true;
        //                    }
        //                }
        //                else{
        //                    // add record
        //                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luaskerajaan)) > parseFloat(Number(jumtemp))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luaskerajaan').val('');
        //                        $('#luaskerajaan').focus();
        //                        return true;
        //                    }
        //                }
        //    <%--alert("save");--%>
        //                $.blockUI({
        //                    message: $('#displayBox'),
        //                    css: {
        //                        top:  ($(window).height() - 50) /2 + 'px',
        //                        left: ($(window).width() - 50) /2 + 'px',
        //                        width: '50px'
        //                    }
        //                });
        //            
        //                var q = $(f).formSerialize();
        //                var url = f.action + '?' + event;
        //                $.post(url,q,
        //                function(data){
        //                    $('#page_div',opener.document).html(data);            
        //                    self.opener.refreshPageMaklumatPecahSempadan();            
        //                    $.unblockUI();
        //                    self.close();
        //                },'html');
        //            }
        //        }
        //        
        //        function savePlotTanahAmbilKerajaan(event, f){
        //    <%--alert("savePlot");--%>
        //            if(!validatePlotTAmbilKerajaan()){               
        //                var jumluas = $('#jumluas').val();
        //                var luaskerajaanAmbil = $('#luaskerajaanAmbil').val();              
        //                var jumhakmilik = $('#jumluasHakmilik').val();
        //                var jumthakmilik = $('#jumluasTHakmilik').val();
        //                var jumkerajaan = $('#jumluasKerajaan').val();               
        //                var jumluaskerajaanAmbilR = $('#luaskerajaanAmbilR').val();
        //                var kodUOM = $('#kodUOM').val();  
        //                if (kodUOM !== '') {
        //                    if (kodUOM == 'M') {
        //                        luaskerajaanAmbil = luaskerajaanAmbil / 1000;
        //                    }
        //                } else {
        //                    alert('Sila masukkan unit luas');
        //                    return false; 
        //                }
        //                var jumtemp = parseFloat(Number(jumluas)) - parseFloat(Number(jumhakmilik)) - parseFloat(Number(jumthakmilik)) - parseFloat(Number(jumkerajaan));
        //                var sumsemua = (parseFloat(Number(jumhakmilik)) + parseFloat(Number(jumthakmilik)) + parseFloat(Number(jumkerajaan)));
        //                var base = parseFloat(Number(0));
        //                if(parseFloat(Number(jumluaskerajaanAmbilR)) > base){
        //                    // edit record
        //                    var luastinggal = parseFloat(Number(jumluas)) - (parseFloat(Number(sumsemua)) - parseFloat(Number(jumluaskerajaanAmbilR)));
        //                    luastinggal=(Math.round(luastinggal*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luaskerajaanAmbil)) > parseFloat(Number(luastinggal))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luaskerajaanAmbil').val('');
        //                        $('#luaskerajaanAmbil').focus();
        //                        return true;
        //                    }
        //                }
        //                else{
        //                    // add record
        //                    jumtemp=(Math.round(jumtemp*10000)/10000).toFixed(4);
        //                    if(parseFloat(Number(luaskerajaanAmbil)) > parseFloat(Number(jumtemp))){
        //                        alert('Luas yang dimasukkan melebihi Jumlah Luas Keseluruhan yang disediakan.');
        //                        $('#luaskerajaanAmbil').val('');
        //                        $('#luaskerajaanAmbil').focus();
        //                        return true;
        //                    }
        //                }
        //            
        //    <%--alert("save");--%>
        //                $.blockUI({
        //                    message: $('#displayBox'),
        //                    css: {
        //                        top:  ($(window).height() - 50) /2 + 'px',
        //                        left: ($(window).width() - 50) /2 + 'px',
        //                        width: '50px'
        //                    }
        //                });
        //            
        //                var q = $(f).formSerialize();
        //                var url = f.action + '?' + event;
        //                $.post(url,q,
        //                function(data){
        //                    $('#page_div',opener.document).html(data);            
        //                    self.opener.refreshPageMaklumatPecahSempadan();            
        //                    $.unblockUI();
        //                    self.close();
        //                },'html');
        //            }
        //        }

        function validatePlotHakmilik(){
            var idHakmilik = $('#idHakmilik').val();
            var noPlot = $('#noPlot').val();
            var luashakmilik = $('#luashakmilik').val();
            if (idHakmilik == '0' || idHakmilik == "" || idHakmilik == '00' ){
                alert('Sila Pilih ID Hakmilik.');
                $('#idHakmilik').val('');
                $('#idHakmilik').focus();
                return true;
            }
            if (noPlot == '0' || noPlot == "" || noPlot == '00' ){
                alert('Sila masukkan No Plot.');
                $('#noPlot').val('');
                $('#noPlot').focus();
                return true;
            }
            if (luashakmilik == '0' || luashakmilik == "" || luashakmilik == '00' ){
                alert('Sila masukkan Luas Tanah.');
                $('#luashakmilik').val('');
                $('#luashakmilik').focus();
                return true;
            }
            return false;
        }

        function validatePlotTAmbilKerajaan(){
            var noPlot = $('#noPlot').val();
            var luaskerajaanAmbil = $('#luaskerajaanAmbil').val();
            var kodUOM = $('#kodUOM').val();
            var kegunaanTanah = $('#kegunaanTanah').val();
            
            if (kegunaanTanah == ""){
                alert('Sila masukkan Kegunaan Tanah.');
                $('#kegunaanTanah').val('');
                $('#kegunaanTanah').focus();
                return true;
            }         
            if (noPlot == '0' || noPlot == "" || noPlot == '00' ){
                alert('Sila masukkan No Plot.');
                $('#noPlot').val('');
                $('#noPlot').focus();
                return true;
            }
            if (luaskerajaanAmbil == '0' || luaskerajaanAmbil == "" || luaskerajaanAmbil == '00' ){
                alert('Sila masukkan Luas Tanah Yang Diambil Kerajaan.');
                $('#luaskerajaanAmbil').val('');
                $('#luaskerajaanAmbil').focus();
                return true;
            }
            if (kodUOM == "" ){
                alert('Sila masukkan Unit Luas');
                $('#kodUOM').val('');
                $('#kodUOM').focus();
                return true;
            }
            return false;
        }
        
        function validatePlotTHakmilik(){
            var noPlot = $('#noPlot').val();
            var luasrizab = $('#luasrizab').val();
            var kodUOM = $('#kodUOM').val();
            var kegunaanTanah = $('#kegunaanTanah').val();
            
            if (kegunaanTanah == ""){
                alert('Sila masukkan Kegunaan Tanah.');
                $('#kegunaanTanah').val('');
                $('#kegunaanTanah').focus();
                return true;
            }
            
            if (noPlot == '0' || noPlot == "" || noPlot == '00' ){
                alert('Sila masukkan No Plot.');
                $('#noPlot').val('');
                $('#noPlot').focus();
                return true;
            }
            if (luasrizab == '0' || luasrizab == "" || luasrizab == '00' ){
                alert('Sila masukkan Luas Tanah.');
                $('#luasrizab').val('');
                $('#luasrizab').focus();
                return true;
            }
            if (kodUOM == "" ){
                alert('Sila masukkan Unit Luas');
                $('#kodUOM').val('');
                $('#kodUOM').focus();
                return true;
            }
            return false;
        }

        function validatePlotTanahKerajaan(){
            var noPlot = $('#noPlot').val();
            var kegunaanTanah = $('#kegunaanTanah').val();
            var luaskerajaan = $('#luaskerajaan').val();
            var kodUOM = $('#kodUOM').val();
            
            if (kegunaanTanah == '0' || kegunaanTanah == "" || kegunaanTanah == '00' ){
                alert('Sila masukkan Kegunaan Tanah.');
                $('#kegunaanTanah').val('');
                $('#kegunaanTanah').focus();
                return true;
            }
            if (luaskerajaan == '0' || luaskerajaan == "" || luaskerajaan == '00' ){
                alert('Sila masukkan Luas Tanah.');
                $('#luaskerajaan').val('');
                $('#luaskerajaan').focus();
                return true;
            }
            if (kodUOM == "" ){
                alert('Sila masukkan Unit Luas');
                $('#kodUOM').val('');
                $('#kodUOM').focus();
                return true;
            }
            return false;
        }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPecahSempadanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <%--<s:select name="plotArray" id="listplot"/>--%>

            <c:if test="${actionBean.tajuk eq 'KHK'}">
                <legend>Kemasukan Maklumat Plot Yang Dikeluarkan Hakmilik</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'EKHK'}">
                <legend>Kemaskini Maklumat Plot Yang Dikeluarkan Hakmilik</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'TKHK'}">
                <legend>Kemasukan Maklumat Plot Yang Dirizabkan Kepada Agensi Kerajaan</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'ETKHK'}">
                <legend>Kemaskini Maklumat Plot Yang Dirizabkan Kepada Agensi Kerajaan</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'AK'}">
                <legend>Kemasukan Maklumat Plot Yang Diserah Kepada Kerajaan</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'AMK' || actionBean.tajuk eq 'EAMK'}">
                <legend>Kemasukan Maklumat Plot Yang Diambil Dari Kerajaan</legend>
            </c:if>
            <c:if test="${actionBean.tajuk eq 'EAK'}">
                <legend>Kemaskini Maklumat Plot Yang Diserah Kepada Kerajaan</legend>
            </c:if>
            <br>
            <%--Tanah Hakmilik--%>
            <c:if test="${actionBean.tajuk eq 'KHK'|| actionBean.tajuk eq 'EKHK'}">
                <s:hidden name="idPlot" value="${actionBean.mpp.idPlot}"/>

                <p><label>ID Hakmilik: </label>
                    <%--<s:text name="idHakmilik" id="idHakmilik" size="20"/>--%>
                    <s:select name="idHakmilik" id="idHakmilik"  style="width:160px;" >
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.hpList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik"/>
                    </s:select>
                </p>
                <p><label>No. Plot:</label>
                    <s:text name="noPlot" id="noPlot" size="8"/> 
                </p>
                <p><label>Bil. Unit:</label>
                    <s:text name="bilUnit" id="bilUnit" size="8" onkeyup="validateNumber(this,this.value);"/> 
                </p>
                <p><label>Luas Tanah:</label>
                    <s:text name="luashakmilik" value ="0.0000" id="luashakmilik" size="8" formatPattern="0.0000" onkeyup="calcLuasHakmilik(this,this.value);"/>
                    <c:if test = "${actionBean.tajuk eq 'EKHK'}">
                        <s:select name="kodUOM" id="kodUOM" style="" value="${actionBean.mpp.kodUnitLuas.kod}">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                    <c:if test = "${actionBean.tajuk ne 'EKHK'}">
                        <s:select name="kodUOM" id="kodUOM" style="">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                </p>
                <s:text name="luashakmilik" id="luasHmcur" value="0.0000" formatPattern="0.0000"/>
            </c:if>


            <%--Tanah Rizab--%>
            <c:if test="${actionBean.tajuk eq 'TKHK' || actionBean.tajuk eq 'ETKHK'}">
                <s:hidden name="idPlot" value="${actionBean.mpp.idPlot}"/>

                <p><label>No. Plot:</label>
                    <s:text name="noPlot" id="noPlot" size="8"/>
                </p>
                <p><label>Bil. Unit:</label>
                    <s:text name="bilUnit" id="bilUnit" size="8" onkeyup="validateNumber(this,this.value);"/> 
                </p>
                <p><label>Kegunaan Tanah:</label>
                    <s:text name="gunatanahrizab" id="kegunaanTanah"  size="100"/>
                </p>
                <p><label>Luas Tanah:</label>
                    <s:text name="luasrizab" id="luasrizab"  value="0.0000" size="8" formatPattern="0.0000"/>
                    <c:if test = "${actionBean.tajuk ne 'ETKHK'}">
                        <s:select name="kodUOM" id="kodUOM" style=""> <%--onkeyup="calcLuasTHakmilik(this,this.value);" --%>
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                    <c:if test = "${actionBean.tajuk eq 'ETKHK'}">
                        <s:select name="kodUOM" id="kodUOM" style="" value="${actionBean.mpp.kodUnitLuas.kod}">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                </p>
                <s:text name="luasrizab" id="luasRzcur" value="0.0000"  formatPattern="0.0000"/>
            </c:if>


            <%--Tanah Kerajaan--%>
            <c:if test="${actionBean.tajuk eq 'AK' || actionBean.tajuk eq 'EAK'}">
                <s:hidden name="idPlot" value="${actionBean.mpp.idPlot}"/>

                <p><label>No. Plot:</label>
                    <s:text name="noPlot" id="noPlot" size="8"/>
                </p>
                <p><label>Bil. Unit:</label>
                    <s:text name="bilUnit" id="bilUnit" size="8" onkeyup="validateNumber(this,this.value);"/> 
                </p>
                <p><label>Kegunaan Tanah:</label>
                    <s:text name="gunatanahkerajaan" id="kegunaanTanah"  size="100"/>
                </p>
                <p><label>Luas Tanah:</label>
                    <s:text name="luaskerajaan" id="luaskerajaan" value="0.0000" size="8" formatPattern="0.0000"/> 
                    <c:if test = "${actionBean.tajuk eq 'EAK'}">
                        <s:select name="kodUOM" id="kodUOM" style="" value="${actionBean.mpp.kodUnitLuas.kod}">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                    <c:if test = "${actionBean.tajuk ne 'EAK'}">
                        <s:select name="kodUOM" id="kodUOM" style="">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                </p>
                <s:text name="luaskerajaan" id="luasKjcur" value="0.0000"  formatPattern="0.0000"/>

                <c:if test ="${actionBean.kodNegeri eq '04'}">
                    <c:if test ="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                        <p><label>Perlu No. PT</label>
                            <s:select name="perluNoPT" id="perluNoPT">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:option value="Y">Ya</s:option>
                                <s:option value="T">Tidak</s:option>
                            </s:select>
                        </p>
                        <p><label>Penjenisan</label>
                            <s:select name="kodKategoriTanah" id="kodKategoriTanah">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                    </c:if>                                         
                </c:if>
            </c:if>

            <c:if test="${actionBean.tajuk eq 'AMK' || actionBean.tajuk eq 'EAMK'}">
                <s:hidden name="idPlot" value="${actionBean.mpp.idPlot}"/>

                <p><label>No. Plot:</label>
                    <s:text name="noPlot" id="noPlot" size="8"/>
                </p>
                <p><label>Bil. Unit:</label>
                    <s:text name="bilUnit" id="bilUnit" size="8" onkeyup="validateNumber(this,this.value);"/> 
                </p>
                <p><label>Kegunaan Tanah:</label>
                    <s:text name="gunatanahkerajaan" id="kegunaanTanah"  size="100"/>
                </p>
                <p><label>Luas Tanah:</label>
                    <s:text name="luaskerajaanAmbil" id="luaskerajaanAmbil" value="0.0000" size="8" formatPattern="0.0000"/> 
                    <c:if test = "${actionBean.tajuk eq 'EAMK'}">
                        <s:select name="kodUOM" id="kodUOM" style="" value="${actionBean.mpp.kodUnitLuas.kod}">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                    <c:if test = "${actionBean.tajuk ne 'EAMK'}">
                        <s:select name="kodUOM" id="kodUOM" style="">
                            <s:option label="--Sila Pilih--"  value="" />             
                            <s:option label="HEKTAR"  value="H" />  
                            <s:option label="METER PERSEGI"  value="M" />                                   
                        </s:select>
                    </c:if>
                </p>
                <s:text name="luaskerajaanAmbil" id="luaskerajaanAmbilR" value="0.0000" formatPattern="0.0000"/>
            </c:if>



            <p>
                <label>&nbsp;</label>
                <c:if test="${actionBean.tajuk eq 'KHK'}">
                    <s:button name="simpanPlotHakmilik" id="simpan" value="Simpan" class="btn" onclick="savePlotHakmilik(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'EKHK'}">
                    <s:button name="simpanKemaskiniHakmilik" id="simpan" value="Simpan" class="btn" onclick="savePlotHakmilik(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'TKHK'}">
                    <s:button name="simpanPlotRizab" id="simpan" value="Simpan" class="btn" onclick="savePlotTHakmilik(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'ETKHK'}">
                    <s:button name="simpanKemaskiniRizab" id="simpan" value="Simpan" class="btn" onclick="savePlotTHakmilik(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'AK'}">
                    <s:button name="simpanPlotKerajaan" id="simpan" value="Simpan" class="btn" onclick="savePlotTanahKerajaan(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'EAK'}">
                    <s:button name="simpanKemaskiniKerajaan" id="simpan" value="Simpan" class="btn" onclick="savePlotTanahKerajaan(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'AMK'}">
                    <s:button name="simpanPlotAmbilKerajaan" id="simpan" value="Simpan" class="btn" onclick="savePlotTanahAmbilKerajaan(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.tajuk eq 'EAMK'}">
                    <s:button name="simpanKemaskiniPlotAmbilKerajaan" id="simpan" value="Simpan" class="btn" onclick="savePlotTanahAmbilKerajaan(this.name, this.form);"/>
                </c:if>

                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
            <s:text name="jumlahPlot" id="jumPlot"/>
            <s:text name="bilplotHakmiliktemp" id="plotHakmiliktemp"/>
            <s:text name="bilplotTHakmiliktemp" id="plotTHakmiliktemp"/>
            <s:text name="bilplotKerajaantemp" id="plotKerajaantemp"/>

            <s:text name="luas" id="jumluas" formatPattern="0.0000"/>
            <s:text name="jumluasHakmilik" id="jumluasHakmilik" formatPattern="0.0000"/>
            <s:text name="jumluasTHakmilik" id="jumluasTHakmilik" formatPattern="0.0000"/>
            <s:text name="jumluasKerajaan" id="jumluasKerajaan" formatPattern="0.0000"/>
            <s:text name="jumluasKerajaanAmbil" id="jumluasKerajaanAmbil" formatPattern="0.0000"/>
        </fieldset>
    </div>

</s:form>


