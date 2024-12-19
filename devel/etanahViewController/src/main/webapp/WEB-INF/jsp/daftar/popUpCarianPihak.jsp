<%--
    Document   : popUpCarianPihak
    Created on : Jul 19, 2010, 7:24:45 PM
    Author     : 5rule, fix by Aizuddin
--%>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kemasukan Berkaitan Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <style type="text/css">
            .replaceP{
                width:100%;
                color:#003194;
                font-weight: bold;
            }

            .L
            {

                color:#003194;
                font-weight: bold;
            }

            .T{
                width:40%
            }


        </style>

        <script type="text/javascript">

            $(document).ready( function(){

            <%--set focus--%>
                    $('input').focus(function() {
                        $(this).addClass("focus");
                    });

                    $('input').blur(function() {
                        $(this).removeClass("focus");
                    });

                    $('select').focus(function() {
                        $(this).addClass("focus");
                    });

                    $('select').blur(function() {
                        $(this).removeClass("focus");
                    });
                });

                function simpanPenerima(f)
                {
                    alert("${actionBean.hakmilikPihakBerkepentingan.hakmilik.idHakmilik}");
                    var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePopup&idPihakPemberi=${actionBean.hakmilikPihakBerkepentingan.idHakmilikPihakBerkepentingan}&hakmilikWakil=${actionBean.hakmilikPihakBerkepentingan.hakmilik.idHakmilik}';


                    var q = $(f).formSerialize();
                    $.post(url,q,
                    function(data){
                        $('#page_div',opener.document).html(data);

                    },'html');
                    setTimeout("window.self.close()",1000);
                }

                function cariPemberi(noKP,kodKP,namaPemberi,jPihak){
            <%--alert(namaPemberi);--%>
            <%--alert(kodKP);--%>
                    params  = 'width='+screen.width;
                    params += ', height='+screen.height;
                    params += ', top=0, left=0'
                    params += ', fullscreen=yes';
                    params += ', directories=no';
                    params += ', location=no';
                    params += ', menubar=no';
                    params += ', resizable=no';
                    params += ', scrollbars=yes';
                    params += ', status=no';
                    params += ', toolbar=no';
                    var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?cariPemberi&noKP='+noKP+'&kodKP='+kodKP+'&namaPemberi='+namaPemberi+'&jPihak='+jPihak;
            <%-- $.get(url,
             function(data){
                 $('#page_div').html(data);
             });--%>
                     window.open(url, "PopUp", params);
                 }
                 function popup(url, jVal)
                 {
            <%--alert(jVal);--%>

                    params  = 'width='+screen.width;
                    params += ', height='+screen.height;
                    params += ', top=0, left=0'
                    params += ', fullscreen=yes';
                    params += ', directories=no';
                    params += ', location=no';
                    params += ', menubar=no';
                    params += ', resizable=no';
                    params += ', scrollbars=yes';
                    params += ', status=no';
                    params += ', toolbar=no';
       
                    if(jVal == "pemberi"){
                        alert("Maklumat pemberi berjaya dimasuk!");
               
                        $.post(url,
                        function(data){
                            $('#page_div',opener.document).html(data);

                        },'html');
                        setTimeout("window.self.close()",1000);
           
                    }
                    
                    if(jVal == "penerima"){
                        var kodSerah = $('#kodSerah').val();
                        if (kodSerah == 'SW') {
                            alert("Maklumat pemberi berjaya dimasuk!");
                            newwin=window.open(url,'PopUp',params);
                        } else {
                            alert("Maklumat pemberi berjaya dimasuk!");
                            $.post(url,
                            function(data){
                                $('#page_div',opener.document).html(data);
                            },'html');
                            setTimeout("window.self.close()",1000);
                            reload();
                        }                
                    } 
                }

                function pickPut(s){
                    jPihakValue = $('input:radio[name=jPihak]:checked').val();
                    opener.document.getElementById('namaPemberi').value = s;




                }


        </script>
    </head>
    <body>
        <%--       <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean"> --%>
        <s:form beanclass="etanah.view.daftar.SuratKuasaWakilActionBean">
            <s:hidden name="kodSerah" id="kodSerah" value="${actionBean.permohonan.kodUrusan.kodPerserahan.kod}"/>

            <s:messages/>
            <s:errors/>

            <s:hidden name="jPihak" value="${actionBean.jPihak}"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Kemasukan Pemberi
                    </legend>
                    <div class="subtitle">
                        <div class="content" align="center">
                            <table class="tablecloth" width="30%">
                                <tr><th colspan="2">Ruang Carian</th></tr>
                                <tr><td class="T"><p class="L">Nama</p></td><td><s:text name="namaPemberi" id="namaPemberi" style="width:100%" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                                <tr><td colspan="2"><p class="L" align="center">Atau</p></td></tr>
                                <tr><td><p class="L">Jenis Pengenalan:</p></td><td> <s:select name="kodKP" id="" value="kod" >
                                            <s:option value="null">Pilih ...</s:option>
                                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                                        </s:select></td></tr>
                                <tr><td><p class="L"> No Pengenalan:</p></td><td> <s:text name="noKP" id="noKP" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                            </table>
                        </div>
                        <br/>
                        <div align="center">
                            <s:button name="cari" value="Cari" class="btn" onclick="cariPemberi(noKP.value,kodKP.value, namaPemberi.value, jPihak.value)"/>
                            <s:button class="btn" onclick="javascript:window.close();" name="tutup" value="Tutup"/>
                        </div>
                    </div>
                    <br/>



                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/daftar/suratkuasawakil?cariPemberi" id="line">
                            <display:column title="Pilih">
                                <s:radio name="chkbox" id="chkbox${line_rowNum}" value="${line.idPihak}" class="checkbox" onclick="popup('${pageContext.request.contextPath}/daftar/suratkuasawakil/popUpKemasukanPenerimaWakil?idPihakPemberi=${line.idPihak}&jPihak=${actionBean.jPihak}&jenisPengenalanPemberi=${line.jenisPengenalan.kod}&noPengenalanPemberi=${line.noPengenalan}', jPihak.value);" />
                            </display:column>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>--%>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noPengenalan" title="No Pengenalan"/>
                            <display:column property="jenisPengenalan.kod" title="Jenis Pengenalan"/>
                            <%--<display:column property="jenis.nama" title="Jenis Pihak Berkepentingan"/>--%>

                        </display:table>
                    </div>
                </fieldset>
            </div>
        </s:form>
    </body>
</html>
