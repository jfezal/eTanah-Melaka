<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="etanah.model.Pengguna"%>
<html>
    <head><title>Carian Hakmilik</title>
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
        
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;

                $.get(url,q,
                function(data){
                    if(data == '0'){
                        alert("Tiada Hakmilik Dijumpai.Sila pastikan data - data hakmilik adalah tepat");
                    }else if(data == '1'){
                        alert("Sila Pilih Kod Bandar Pekan Mukim");
                    }else if(data == '2'){
                        alert("Sila Pilih Daerah");
                    }else if(data == '3'){
                        alert("Sila Pilih Jenis Lot");
                    }else if(data == '4'){
                        alert("Sila Masukkan No Lot / No PT");
                    }
                    else{
                        //$('#page_div',opener.document).html(data);
opener.location.reload();                        self.close();
//                        copyToTextBox(data);
                    }
           
                },'html');
            }
           
            $(document).ready( function(){
           $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

                    });
        </script>
    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.share.common.PenyampaianBorangAActionBean" id="form">
    <s:hidden name="idBorangPerPermohonanA"/>
    <s:hidden name="idPermohonan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penampalan</legend>
       
     <p>
        <label>Tempat Tampal</label>
        <s:text name="tempatTampalA"/>
    </p>
    <p>
        <label>Tarikh Tampal</label>
        <s:text name="tarikhTampalA" class="datepicker" formatPattern="dd/MM/yyyy"/>
    </p>
    <p>
        <label>Nama </label>
        <s:text name="penghantarNotisA"/>
    </p>
    <p>
        <label>Catatan </label>
        <s:text name="catatanA"/>
    </p>
    <p><label>&nbsp; </label>
            <s:button class="btn" value="Simpan" name="simpanBorangA"  onclick="save(this.name,this.form);" />
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>
    <br>
     </fieldset>
    </div>
</s:form>
</body>
</html>