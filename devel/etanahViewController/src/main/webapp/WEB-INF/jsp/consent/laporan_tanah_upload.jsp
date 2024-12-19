<%-- 
    Document   : laporan_tanah_upload
    Created on : May 31, 2013, 3:39:40 PM
    Author     : muhammad.amin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MUATNAIK GAMBAR</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    //    function refreshlptn(){
    //        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang??refreshpage';
    //        $.get(url,
    //        function(data){
    //            $('#page_div').html(data);
    //            self.opener.refreshlptn();
    //            self.close();
    //        },'html');
    //    }
    function openFrame(type){        
        var idHakmilik = $('#idHakmilik').val();
        NoPrompt();
        window.open("${pageContext.request.contextPath}/consent/laporan_tanah_ladang?openFrame&idHakmilik="+idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function refreshpage2(type,pandanganImej,idLapor){
        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang?refreshpage&type='+type+'&pandanganImej='+pandanganImej+'&idLapor='+idLapor;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            
    }
             
    function deleteImage(idImej, idDokumen, type,f) {
        doBlockUI();
        NoPrompt();
        var imageType = $('#pandanganImej').val();
        var idLapor = $('#idLapor').val();
        
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/consent/laporan_tanah_ladang?deleteImage&idImej='+idImej+'&idDokumen='+idDokumen+'&type='+type+'&imageType='+imageType+'&idLapor='+idLapor,q,
            function(data){
                $('#page_div').html(data);

            }, 'html');

            self.refreshpage2('imgPopup',imageType,idLapor);
        }
    }
        
    function blockUI(){
        NoPrompt();
        doBlockUI ();
    }
        
    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }
</script>

<s:errors/>
<s:messages/>
<body>
        <script>
            // Allow the user to be warned by default.
            var allowPrompt = true; 
            window.onbeforeunload = WarnUser;
            function WarnUser()
            {   
                if(allowPrompt)
                    refreshpage();
                if(allowPrompt)
                {
                    event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
                }
                else
                {
                    // Reset the flag to its default value.
                    allowPrompt = true;
                }
            }
            function NoPrompt()
            {
                allowPrompt = false;
            }
    
        </script>
    <div id="error"/>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:form beanclass="etanah.view.stripes.consent.LaporanTanahLadangActionBean">
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <s:hidden name="pandanganImej" id="pandanganImej" value="H"/>
        <%--<s:hidden name="pandanganImej" id="pandanganImej" value="${actionBean.pandanganImej}"/>--%>
        <fieldset class="aras1">         
            <legend>Senarai Gambar</legend>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikImejLaporanListEdit}" pagesize="10" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/consent/laporan_tanah_ladang">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Gambar">
                        <img id="img${line.dokumen.idDokumen}" alt="${line.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${line.dokumen.idDokumen}" height="100" width="100" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${line.dokumen.idDokumen}" data-plussize="400,300" onclick="popImej('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor='pointer';"/>
                    </display:column>
                    <display:column title="Catatan">
                        ${line.catatan}
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <a onclick="deleteImage('${line.idImej}','${line.dokumen.idDokumen}','imgPopup',this.form);" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                        </div> 
                    </display:column>
                </display:table>
            </div>
        </fieldset>
        <br/> 
        <fieldset class="aras1">
            <s:hidden name="pandanganImej" value="${pandanganImej}"/>
            <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}"/>
            <legend>Muat Naik</legend>
            <p> <p>
                <label><font color="red">*</font>Catatan :</label><s:textarea name="catatan" id="catatan" />
                &nbsp;<p></p>
            <label>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <br/>
            <p>
                <label>&nbsp;</label>&nbsp;            
                <s:submit name="simpanImejLaporan" value="Simpan" class="btn" onclick="blockUI();"/>
                <s:button name="close" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
            </p>
        </fieldset>

    </s:form>
</body>
