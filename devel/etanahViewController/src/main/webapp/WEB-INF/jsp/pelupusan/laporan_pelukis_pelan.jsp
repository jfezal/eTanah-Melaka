<%-- 
    Document   : laporan_pelukis_pelan
    Created on : Oct 25, 2010, 9:42:29 AM
    Author     : afham
    Modified   : Murali
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){

        var kt = '${actionBean.permohonanLaporanPelan.kodTanah.kod}';
        if(kt == '99'){
            $('#lain2').show() ;
        }
        else {
            $('#lain2').hide();
        }
          
        $('#mT').hide();
        $('#aG').hide();
        $('#tB').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();
        
        // Kawasan Parlimen && Dun
        var mhk = '${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod}';
        var dun = '${actionBean.hakmilikPermohonan.kodDUN.kod}';
        // alert(mhk);
        // alert(dun);
        if(mhk == 'P134'){
            $('#kodDmT').val(dun);
            $('#mT').show();
            $('#aG').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        }else if(mhk == 'P135'){
            $('#kodDaG').val(dun);
            $('#aG').show();
            $('#mT').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        }else if(mhk == 'P136'){
            $('#kodDtB').val(dun);
            $('#tB').show();
            $('#aG').hide();
            $('#mT').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        }else if(mhk == 'P137'){
            $('#kodDbK').val(dun);
            $('#bK').show();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#kM').hide();
            $('#jS').hide();
        }else if(mhk == 'P138'){
            $('#kodDkM').val(dun);
            $('#kM').show();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#jS').hide();
        }else if(mhk == 'P139'){
            $('#kodDjS').val(dun);
            $('#jS').show();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
        }else{
            $('#jS').hide();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
        }

    });
    
    function removeTanahRizab(idTanahRizabPermohonan)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deleteTanahRizab&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.opener.refreshPageTanahRizab();
            },'html');
        }
    }

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
   
    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
		
        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        //var strStageID2 = "g_charting_permohonan";
        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgram2(strUserID, strNama_, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        var strStageID2 = "g_charting_semak";
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgram4(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
    <%--var stageId = "g_semak_pu";--%>
            var stageId = strIDStage;
            alert("nama:" + strNama);
            alert ("jawatan:" + strJawatan);
            alert ("stageid:" + stageId);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
        }

    <%-- function RunProgram4(strUserID, strNama_, strJawatan, strIDPermohonan, strStageID) {

         var strNama2 = ReplaceAll(strNama," ","_");
         var strJawatan2 = ReplaceAll(strJawatan," ","_");
         var strStageID2 = "g_semak_permohonan";
         var objShell = new ActiveXObject("WScript.Shell");
         var sysVar = objShell.Environment("System");
         //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
         //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


         objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
     }--%>

    <%--function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        var stageId = "g_charting_permohonan";
         // replace " " with "_"

         strNama = ReplaceAll(strNama," ","_");
         strJawatan = ReplaceAll(strJawatan," ","_");
         strStageID = ReplaceAll(strStageID," ","_");
         alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
        
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }--%>


        function removeTanahMilik(id)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deleteTanahMilik&id='
                    +id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

        function removePermohonanTerdahulu(idMohonManual)
        {
            if(confirm('Adakah anda pasti?')) {
    <%-- var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deletePermohonanTerdahulu&idPermohonan='
         +idPermohonan;--%>

                     var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deletePermohonanTerdahulu&idMohonManual='
                         +idMohonManual;
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     },'html');}
             }
             function removeLaporKawasan(idMohonlaporKws)
             {
                 if(confirm('Adakah anda pasti?')) {
                     var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?removeLaporKawasan&idMohonlaporKws='
                         +idMohonlaporKws;
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     },'html');}
             }

             function popupEditLaporKawasan(h){

                 var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?editLaporKawasan&idMohonlaporKws='+h;
                 window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
             }

             function tambahTanahRizab(){
                 var p = true ;
                 var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahRizabPopup&p='+p;
                 window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    <%--window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahRizabPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");--%>
                    }

                    function tambahTanahMilik(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahMilikPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }

                    function tambahPermohonanTerdahulu(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?permohonanTerdahuluPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }
                    function tambahKawasan(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?kawasanPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }
                    function refreshRizab(){
                        var status = '1';
                        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?refreshpage&status='+status;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }

                    function refreshSendiri(){
                        var url = '${pageContext.request.contextPath}/pelupusan/pelukis_pelan?refreshpage';
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }

    <%--  function refreshStatusTanah(){
          var url = '${pageContext.request.contextPath}/pelupusan/pelukis_pelan?refreshpage';
          $.get(url,
          function(data){
              $("#StatusTanahDiv").replaceWith($('#StatusTanahDiv', $(data)));
          },'html');
      }--%>

          function refreshDalamKawasan(){
              var url = '${pageContext.request.contextPath}/pelupusan/pelukis_pelan?refreshpage';
              $.get(url,
              function(data){
                  $("#DalamKawasanDiv").replaceWith($('#DalamKawasanDiv', $(data)));
              },'html');
          }

          function popupTanahRizab(h){
    <%--  alert(h);--%>

            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?showEditTanahRizab&idTanahRizabPermohonan='+h;

            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function popupPermohonanTerdahulu(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditPermohonanTerdahulu&idPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=700,height=800");
        }
        function popup(i){

            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?showEditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }
        function show(){
            //            alert($('#kodTanah').val());
            if($('#kodTanah').val() == '99'){
                $('#lain2').show() ;
            }
            else {
                $('#lain2').hide() ;
            }
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" /><%-- 
    Document   : laporan_pelukis_pelan
    Created on : Oct 25, 2010, 9:42:29 AM
    Author     : afham
    Modified   : Murali
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){

        var kt = '${actionBean.permohonanLaporanPelan.kodTanah.kod}';
        if(kt == '99'){
            $('#lain2').show() ;
        }
        else {
            $('#lain2').hide();
        }
    });


    
    function removeTanahRizab(idTanahRizabPermohonan)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deleteTanahRizab&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.opener.refreshPageTanahRizab();
            },'html');
        }
    }

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
   
    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
		
        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        //var strStageID2 = "g_charting_permohonan";
        var strStageID2 = strStageID;
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
    }
    function RunProgram3(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

        var strNama2 = ReplaceAll(strNama," ","_");
        var strJawatan2 = ReplaceAll(strJawatan," ","_");
        var strStageID2 = "g_charting_keputusan";
    <%--var strStageID2 = strStageID;--%>
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }
        function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama," ","_");
            var strJawatan2 = ReplaceAll(strJawatan," ","_");
            var strStageID2 = "g_charting_semak";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }

    <%--function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        var stageId = "g_charting_permohonan";
         // replace " " with "_"

         strNama = ReplaceAll(strNama," ","_");
         strJawatan = ReplaceAll(strJawatan," ","_");
         strStageID = ReplaceAll(strStageID," ","_");
         alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
        
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }--%>


        function removeTanahMilik(id)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deleteTanahMilik&id='
                    +id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

        function removePermohonanTerdahulu(idMohonManual)
        {
            if(confirm('Adakah anda pasti?')) {
    <%-- var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deletePermohonanTerdahulu&idPermohonan='
         +idPermohonan;--%>

                     var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deletePermohonanTerdahulu&idMohonManual='
                         +idMohonManual;
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     },'html');}
             }
             function removeLaporKawasan(idMohonlaporKws)
             {
                 if(confirm('Adakah anda pasti?')) {
                     var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?removeLaporKawasan&idMohonlaporKws='
                         +idMohonlaporKws;
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     },'html');}
             }
             
             function popupEditLaporKawasan(h){
                 var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?editLaporKawasan&idMohonlaporKws='+h;
                 window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
             }



             function tambahTanahRizab(){
                 var p = true ;
                 var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahRizabPopup&p='+p;
                 window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    <%--window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahRizabPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");--%>
                    }

                    function tambahTanahMilik(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?tanahMilikPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }

                    function tambahPermohonanTerdahulu(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?permohonanTerdahuluPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }
                    function tambahKawasan(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?kawasanPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }
                    function refreshRizab(){
                        var status = '1';
                        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?refreshpage&status='+status;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }

                    function refreshSendiri(){
                        var url = '${pageContext.request.contextPath}/pelupusan/pelukis_pelan?refreshpage';
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }
                    

                    function popupTanahRizab(h){
    <%--  alert(h);--%>

            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?showEditTanahRizab&idTanahRizabPermohonan='+h;

            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function popupPermohonanTerdahulu(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditPermohonanTerdahulu&idPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=700,height=800");
        }
        function popup(i){

            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?showEditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }
        function show(){
            //            alert($('#kodTanah').val());
            if($('#kodTanah').val() == '99'){
                $('#lain2').show() ;
            }
            else {
                $('#lain2').hide() ;
            }
        }
        function changeHideDun(value){
            // alert(value);
            $('#kodD').val("");
            if(value == 'P134'){
                $('#mT').show();
                $('#aG').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P135'){
                $('#aG').show();
                $('#mT').hide();
                $('#mT').attr("disabled", "disabled");
                $('#tB').hide();
                $('#tB').attr("disabled", "disabled");
                $('#bK').hide();
                $('#bK').attr("disabled", "disabled");
                $('#kM').hide();
                $('#kM').attr("disabled", "disabled");
                $('#jS').hide();
                $('#jS').attr("disabled", "disabled");
            }else if(value == 'P136'){
                $('#tB').show();
                $('#aG').hide();
                $('#mT').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P137'){
                $('#bK').show();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#kM').hide();
                $('#jS').hide();
            }else if(value == 'P138'){
                $('#kM').show();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
                $('#jS').hide();
            }else if(value == 'P139'){
                $('#jS').show();
                $('#kM').hide();
                $('#bK').hide();
                $('#tB').hide();
                $('#aG').hide();
                $('#mT').hide();
            }
        }
        function dun(value){
            $('#kodD').val(value);
        }

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
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

     
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelukisPelanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="kodD" id="kodD"/>
    <div class="subtitle displaytag" align="center">
        <c:if test="${actionBean.edit}">

            <br>
            <c:if test="${actionBean.stageId eq 'g_charting_permohonan'  || actionBean.stageId eq '04KmsknLapChrtg' || actionBean.stageId eq '03KmsknKptsChrtg'|| actionBean.stageId eq 'g_charting_keputusan'}">
                <fieldset class="aras1" id="locate">
                    <legend>
                        Charting
                    </legend>

                    <p align="center">
                        Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                    </p> <br><br>
                    <p>
                        <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                    </p>
                    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <c:if test="${actionBean.stageId eq 'agihan_tugas2'}">
                        <p>
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram4('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>--%>
                </fieldset> <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'g_charting_keputusan_ptd' || actionBean.stageId eq 'g_charting_keputusan_ptg'}">
                <fieldset class="aras1" id="locate">
                    <legend>
                        Charting
                    </legend>

                    <p align="center">
                        Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                    </p> <br><br>
                    <p>
                        <s:button name="chart" id="chart" value="Charting" class="btn" onclick="RunProgram3('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                    </p>
                    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <c:if test="${actionBean.stageId eq 'agihan_tugas2'}">
                        <p>
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram4('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>--%>
                </fieldset> <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semak_charting' || actionBean.stageId eq '03ArahLaporanTanah' || actionBean.stageId eq '10AghnTgs' || actionBean.stageId eq '04ArhnPenyediaanPU' || actionBean.stageId eq '10PmbtlnChrtg' || actionBean.stageId eq 'agihan_tugas2'}">
                <fieldset class="aras1" id="locate">
                    <legend>
                        Charting
                    </legend>

                    <p align="center">
                        Sila charting pada lot tanah yang dipohon sebelum menyediakan laporan pelan.
                    </p> <br><br>
                    <p>
                        <s:button name="chart" id="chart" value="Kemaskini Charting" class="longbtn" onclick="RunProgram2('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                    </p>
                    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <c:if test="${actionBean.stageId eq 'agihan_tugas2'}">
                        <p>
                            <s:button name="chart" id="chart" value="Semak Charting" class="longbtn" onclick="RunProgram4('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>--%>
                </fieldset> <br>
            </c:if>

        </c:if>






        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah Dipohon
            </legend>
            <c:set var ="statusHakmilik" value="T"/>
            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                           requestURI="/pelupusan?laporan_pelukis_pelan">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="hakmilik.noLot"/>
                        <display:column title="Lot/PT" property="hakmilik.lot.nama"/>
                        <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"/>
                        <display:column title="Seksyen">
                            <c:if test="${actionBean.edit}">
                                <c:if test="${line.hakmilik.seksyen ne null}">
                                    ${line.hakmilik.seksyen.nama}
                                    <s:hidden name="kodSeksyen.nama" value="${line.hakmilik.seksyen.kod}"/>
                                </c:if>
                                <c:if test="${line.hakmilik.seksyen eq null}">
                                    -
                                    <s:hidden name="kodSeksyen.nama" value="0"/>
                                </c:if>

                            </c:if>
                            <c:if test="${!actionBean.edit}">
                                <c:if test="${line.hakmilik.seksyen ne null}">
                                    ${line.hakmilik.seksyen.nama}
                                </c:if>
                                <c:if test="${line.hakmilik.seksyen eq null}">
                                    -
                                </c:if>
                            </c:if>

                        </display:column>
                        <display:column title="Daerah" property="hakmilik.bandarPekanMukim.daerah.nama"/>
                    </c:if>
                </c:if>
                <c:if test="${line.hakmilik eq null}">
                    -----
                    <c:if test="${line.permohonan.kodUrusan.kod ne 'PJLB'}">
                        <c:set var ="statusHakmilik" value="TW"/>
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="noLot" />
                        <display:column title="Lot/PT" property="lot.nama"/>
                        <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                        <display:column title="Seksyen">
                            <c:if test="${actionBean.edit}">
                                <s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select>
                            </c:if>
                            <c:if test="${!actionBean.edit}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen ne null}">
                                    ${actionBean.hakmilikPermohonan.kodSeksyen.nama}
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen eq null}">
                                    -
                                </c:if>
                            </c:if>
                        </display:column>
                        <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                    <c:set var ="statusHakmilik" value="TW"/>
                    <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                    <display:column title="ID Hakmilik">${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>
                    <display:column title="Lot/PT" property="lot.nama"/>
                    <display:column title="No.Lot/PT" property="noLot" />
                    <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                    <display:column title="Seksyen">
                        <c:if test="${actionBean.edit}">
                            <s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select>
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen ne null}">
                                ${actionBean.hakmilikPermohonan.kodSeksyen.nama}
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodSeksyen eq null}">
                                -
                            </c:if>
                        </c:if>
                    </display:column>
                    <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                </c:if>

                <%--<display:column title="Hapus">
                <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahMilik('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                </div>
                </display:column>--%>
            </display:table>

            <%--lll ${statusHakmilik}......  ${actionBean.hakmilik} ${actionBean.stageId} ${edit}--%>
            <c:if test="${!actionBean.edit}">
                <c:if test="${statusHakmilik eq 'TW'}">
                    <c:if test="${actionBean.stageId eq 'agihan_tugas2'}">
                        <p>
                            <s:button name="chart" id="chart" value="Kemaskini Charting" class="longbtn" onclick="RunProgram4('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>
            </c:if>

            <%--<s:button class="btn" value="Tambah" name="" id=""/>&nbsp;--%>
            <br>

        </fieldset>
    </div>
    <br/>
    <%--<br/>
    <div class="subtitle displaytag" align="center">
        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Milik
            </legend>
            <div class="content" align="center">

                <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                    <display:column title="Jenis Hakmilik">
                        <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Nombor Hakmilik">
                        <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Nombor Lot/PT" >
                        <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                    </display:column>

                    <display:column title="Luas">
                        <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                        <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                        <c:if test="${line.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.kategoriTanah.nama eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Cukai (RM)">
                        <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                        <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                    </display:column>
                </display:table>
            </div>--%>

    <%--  <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                     requestURI="/pelupusan?laporan_pelukis_pelan">
          <display:column title="No">${line_rowNum}</display:column>
          <display:column title="Jenis Milik" />
          <display:column title="Cawangan" />
          <display:column title="Daerah" />
          <display:column title="Bandar/Pekan/Mukim" />
          <display:column title="No. PT/Lot"/>
          <display:column title="No. H/M"/>
          <display:column title="No. Lesen LPS"/>
          <display:column title="Catatan"/>

                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahMilik('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                    </div>
                    </display:column>
                </display:table>
             <c:if test="${edit}">
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;
                </c:if>--%>
    <%-- <br>

        </fieldset>
    </div>--%>

    <%--<div class="subtitle displaytag" align="center">

        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Rizab
            </legend>

            <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/laporan_pelukis_pelan">
                <display:column title="No" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idTanahRizabPermohonan}"/>
                </display:column>
                <display:column property="noLitho" title="No Lembaran Piawai" />
                <display:column property="rizab.nama" title="Jenis Rizab" />
                <display:column property="noLot" title="No Lot" />
                <display:column property="lokasi" title="Kedudukan Tanah" />
                <display:column property="noWarta" title="No. Warta"/>
                <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                <c:if test="${edit}">
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 id='rem_${line_rowNum}'  onclick="popupTanahRizab('${line.idTanahRizabPermohonan}');" onmouseover="this.style.cursor='pointer';"/>
                        </p>
                    </display:column>
                    <display:column title="Hapus">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 id='rem_${line_rowNum}'    onclick="removeTanahRizab('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');" onmouseover="this.style.cursor='pointer';"/>
                        </p>
                    </display:column>
                </c:if>
            </display:table>
            <c:if test="${edit}">
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;
            </c:if>
            <br>

        </fieldset>
    </div>--%>

    <br/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Status Tanah 
            </legend>
            <p>

                <c:if test="${actionBean.edit}">
                    <label for="nolitho"><font color="red">*</font>No. Lembaran Piawai :</label>
                    <s:text name="noLitho" size="20" />
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <label for="nolitho">No. Lembaran Piawai :</label>
                    <c:if test="${actionBean.noLitho ne null}">
                        ${actionBean.noLitho}
                    </c:if>
                    <c:if test="${actionBean.noLitho eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
            <p>
                <c:if test="${actionBean.edit}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' && actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'PJBTR'}">
                        <label><font color="red">*</font>Luas</label>
                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <label><font color="red">*</font>Keluasan Terlibat :</label>
                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' or actionBean.permohonan.kodUrusan.kod eq 'PTBTC' or actionBean.permohonan.kodUrusan.kod eq 'PTBTS' or actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                        <p>
                            <label><font color="red">*</font>Luas Atas Tanah :</label>
                            ${actionBean.hakmilikPermohonan.hakmilik.luas}
                            <%--<s:text name="hakmilikPermohonan.hakmilik.luas" size="20" readonly="true"/>--%>
                            ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                            <%--<s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meter Persegi</s:option>
                                <s:option value="MP">Hektar</s:option>
                            </s:select>--%>
                        </p>
                    </c:if>

                    <p>
                        <label>Isipadu :</label>
                        <s:text name="hakmilikPermohonan.luasTerlibat" maxlength="15" readonly="true"/>
                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meter Padu</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label>Jarak Kedalaman :</label>
                        <s:text name="hakmilikPermohonan.kedalamanTanah" maxlength="15" readonly="true"/>
                        <s:text name="hakmilikPermohonan.kedalamanTanahUOM.nama" readonly="true"/>
                    </p>
                </c:if>
            </c:if>


            <c:if test="${!actionBean.edit}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PJBTR'}">
                    <label>Luas :</label> ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' or actionBean.permohonan.kodUrusan.kod eq 'PTBTC' or actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' or actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                        <p>
                            <label>Luas Atas Tanah :</label>
                            ${actionBean.hakmilikPermohonan.hakmilik.luas}
                            ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                        <p>
                            <label>Isipadu:</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat}
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </p>
                    </c:if>
                </c:if>
            </c:if>

            <p>
                <c:if test="${actionBean.edit}">
                    <br/>
                    <label><font color="red">*</font>Status Tanah :</label>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' && actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP'}">
                            <s:select name="kodP" style="width:150px" id="kodPemilikan">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod"/>
                            </s:select>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                        <s:select name="kodP" style="width:150px" id="kodPemilikan">
                            <%--<s:option value="0">Sila Pilih</s:option>--%>
                            <s:options-collection collection="${listUtil.senaraiKodPemilikanPTBT}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                        <s:select name="kodP" style="width:150px" id="kodPemilikan">
                            <%--<s:option value="0">Sila Pilih</s:option>--%>
                            <s:options-collection collection="${listUtil.senaraiKodPemilikanBMBT}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <s:select name="kodP" style="width:150px" id="kodPemilikan">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPemilikanDIS}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <s:select name="kodP" style="width:150px" id="kodPemilikan">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodPemilikanLPSP}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                </c:if>
                <c:if test="${!actionBean.edit}">

                    <label>Status Tanah :</label>
                    <c:if test="${actionBean.kodPString ne null}">
                        ${actionBean.kodPString}
                    </c:if>
                    <c:if test="${actionBean.kodPString eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
            <p>
                <c:if test="${actionBean.edit}">
                    <label><font color="red">*</font>Jenis Tanah :</label>
                    <s:select name="kodT" style="width:150px" value="${actionBean.permohonanLaporanPelan.kodTanah.kod}" id="kodTanah" onchange="show();">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                    </s:select>
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
                        ${actionBean.permohonanLaporanPelan.kodTanah.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonanLaporanPelan eq null or actionBean.permohonanLaporanPelan.kodTanah eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
            <p id="lain2">
                <c:if test="${actionBean.edit}">
                    <label><font color="red">*</font>Sila Nyatakan :</label>
                    <s:text name="ulasan"/>
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <label>Sila Nyatakan : </label>
                    <c:if test="${actionBean.ulasan ne null}">
                        ${actionBean.ulasan}
                    </c:if>
                    <c:if test="${actionBean.ulasan eq null}">
                        -
                    </c:if>
                </c:if>
            </p>

            <p>
                <label for="projekkerajaan">Jika Di tanda untuk projek kerajaan, Nyatakan :</label>
                <c:if test="${actionBean.edit}">
                    <s:text name="projekKerajaan" size="50" />
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.projekKerajaan ne null}">
                        ${actionBean.projekKerajaan}
                    </c:if>
                    <c:if test="${actionBean.projekKerajaan eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
            <c:if test="${actionBean.negeri eq '04'}">
                <p>
                    <label>Kawasan Parlimen :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="javaScript:changeHideDun(this.value)">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                    </c:if>
                </p>
                <p id="mT">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N01">Kuala Linggi</s:option>
                            <s:option value="N02">Tanjung Bidara</s:option>
                            <s:option value="N03">Ayer Limau</s:option>
                            <s:option value="N04">Lendu</s:option>
                            <s:option value="N05">Taboh Naning</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>

                <p id="aG">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N06">Rembia</s:option>
                            <s:option value="N07">Gadek</s:option>
                            <s:option value="N08">Machap</s:option>
                            <s:option value="N09">Durian Tunggal</s:option>
                            <s:option value="N10">Asahan</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>

                <p id="tB">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N11">Sungai Udang</s:option>
                            <s:option value="N12">Pantai Kundor</s:option>
                            <s:option value="N13">Paya Rumput</s:option>
                            <s:option value="N14">Kelebang</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>

                <p id="bK">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N15">Bachang</s:option>
                            <s:option value="N16">Ayer Keroh</s:option>
                            <s:option value="N17">Bukit Baru</s:option>
                            <s:option value="N18">Ayer Molek</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>

                <p id="kM">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N19">Kesidang</s:option>
                            <s:option value="N20">Kota Laksamana</s:option>
                            <s:option value="N21">Duyong</s:option>
                            <s:option value="N22">Bandar Hilir</s:option>
                            <s:option value="N23">Telok Mas</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>

                <p id="jS">
                    <label>DUN :</label>
                    <c:if test="${actionBean.edit}">
                        <s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="N24">Bemban</s:option>
                            <s:option value="N25">Rim</s:option>
                            <s:option value="N26">Serkam</s:option>
                            <s:option value="N27">Merlimau</s:option>
                            <s:option value="N28">Sungai Rambai</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                    </c:if>
                </p>
            </c:if>
            &nbsp;

        </fieldset>
    </div>
    <br>

    <div class="subtitle displaytag" align="center">

        <fieldset class="aras1" id="locate">
            <legend>
                Permohonan Terdahulu
            </legend>


            <%--  <display:table class="tablecloth" name="${actionBean.permohonanTerdahuluList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                             requestURI="${pageContext.request.contextPath}/maklumat_tambahan">--%>
            <%--<c:if test="${actionBean.permohonan.permohonanSebelum ne null}">--%>
            <%--<display:table class="tablecloth" name="${actionBean.permohonan.permohonanSebelum}" pagesize="20" cellpadding="0" cellspacing="0" id="line">--%>
            <display:table class="tablecloth" name="${actionBean.listpermohonanTanahTerdahulu}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                <s:hidden name="" class="${line1_rowNum -1}" value="${line1.permohonanManualSemasa.idMohonManual}"/>
                <display:column title="No">${line1_rowNum}</display:column>
                <display:column title="ID Permohanan/No Fail"  property="permohonanTerdahulu.idPermohonan"/>
                <display:column title="Urusan" property="permohonanTerdahulu.kodUrusan.nama"/>
                <display:column title="Status Keputusan" property="statusKeputusan" />
                <%-- <display:column title="Hapus">
                     <div align="center">
                         <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                              id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${actionBean.permohonan.permohonanSebelum.idPermohonan}');"/>
                     </div>
                 </display:column>--%>
                <c:if test="${actionBean.edit}">
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line1.permohonanManualSemasa.idMohonManual}')"/>
                        </div>
                    </display:column>
                </c:if>

            </display:table> <br/>
            <%--</c:if>--%>
            <%--<c:if test="${actionBean.permohonan.permohonanSebelum eq null}">--%>
            <c:if test="${actionBean.edit}">
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()"/>&nbsp;
            </c:if>
            <%--</c:if>--%>
            <br>

        </fieldset>
    </div>
    <br/>

    <br/>
    <div class="subtitle" align="center">

        <fieldset class="aras1">
            <legend> Dalam Kawasan</legend>
            <br>
            <p>Tanah Dipohon Berada Dalam Kawasan :
            <div id="DalamKawasanDiv">
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                    <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                    <display:column title="No">${line9_rowNum}</display:column>
                    <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                    <display:column title="Catatan">
                        <c:if test="${line9.catatan ne null}">
                            ${line9.catatan}
                        </c:if>
                        <c:if test="${line9.catatan eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Warta" property="noWarta"/>
                    <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="No Pelan Warta" property="noPelanWarta" />
                    <c:if test="${actionBean.edit}">
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line9_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeLaporKawasan('${line9.idMohonlaporKws}')"/>
                            </div>
                        </display:column>

                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line9_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupEditLaporKawasan('${line9.idMohonlaporKws}')"/>
                            </div>
                        </display:column>


                    </c:if>
                </display:table><br/>
            </div>
            <table>
                <tr>
                    <td align="right">
                        <c:if test="${actionBean.edit}">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahKawasan()"/>&nbsp;
                        </c:if>

                    </td>
                </tr>
            </table>




            <%-- <c:if test="${!actionBean.edit}">
                     <s:textarea name="catatanLain" rows="5" cols="50" />
             </c:if>--%>

        </fieldset>
    </div>


    <fieldset class="aras1" id="locate">
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
            <legend>
                Lain-lain Hal
            </legend>

            <p>
                <label for="catatan">Lain lain hal / Catatan :</label>
                <c:if test="${actionBean.edit}">
                    <s:textarea name="catatan" rows="5" cols="50" />
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.catatan ne null}">
                        ${actionBean.catatan}
                    </c:if>
                    <c:if test="${actionBean.catatan eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
            <legend>Catatan</legend>
            <p>
                <label for="catatan">Jika Lain-lain,Nyatakan :</label>
                <c:if test="${actionBean.edit}">
                    <s:textarea name="catatan" rows="5" cols="50" />
                </c:if>
                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.catatan ne null}">
                        ${actionBean.catatan}
                    </c:if>
                    <c:if test="${actionBean.catatan eq null}">
                        -
                    </c:if>
                </c:if>
            </p>
        </c:if>


        <br>

        <c:if test="${actionBean.edit}">
            <p align="center">
                <c:if test="${actionBean.edit}">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
        </c:if>
    </fieldset>



</s:form>