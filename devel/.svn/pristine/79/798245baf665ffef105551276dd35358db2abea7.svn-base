<%-- 
    Document   : edit_jabatan_teknikal
    Created on : Oct 12, 2011, 3:02:35 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" 
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    $(document).ready( function(){
        maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var mandatori = document.getElementById('mandatori2').value;
        //            alert(mandatori);
        if(mandatori == 'Y'){
            //                alert(document.getElementById('mandatori2').value);
            document.getElementById('mandatori').checked = 'true';
        }else{
            //                alert('asdasdasd');
            //                alert(document.getElementById('mandatori2').value);
        }
    });
        
    function search(index, kodAsal, idRujukan){
    <%--  alert(kodAsal) ;--%>
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?kodAgensiPopup2&index='+index+'&kodAsal='+kodAsal + '&idRujukan=' + idRujukan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }
        function searchDokumen(index, kodAsal, idRujukan){
    <%--  alert(kodAsal) ;--%>
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?kodAgensiPopup3&index='+index+'&kodAsal='+kodAsal + '&idRujukan=' + idRujukan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }
        function confirmRefresh() {
            setTimeout("location.reload(true);",1500);
        }
            
        function removePermohonanRujSalinan(idRujukan,idSalinan){
            //                alert(idRujukan + " and row = " + idSalinan) ;
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?deleteRujukanSalinan&idRujukan='+idRujukan+'&idSalinan='+idSalinan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod dihapuskan") ;
                    self.confirmRefresh() ;
                },'html');
            }
        }
            
        function removePermohonanRujDokumen(idRujukan,idDokumen){
            //                alert(idRujukan + " and row = " + idSalinan) ;
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/sedia_jabatan?deleteRujukanDokumen&idRujukan='+idRujukan+'&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod dihapuskan") ;
                    self.confirmRefresh() ;
                },'html');
            }
        }
            
        function setValue(){
            if(document.edit.mandatori[0].checked){
                
                document.edit.mandatori[0].value = "Y" ;
            }
            else {
                document.edit.mandatori[0].value = "T" ;
            }
        }
        function setNewDate() {
            alert($('#date1').val());
            var test = new Date() ;
            //                alert(test.getDay()) ;
        }
            
        function letClose(){
            //                alert("Hi");
            self.close() ;
            self.opener.refreshAgensi() ;
        }
            
</script>

<s:errors/>
<s:messages/>
<s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean" name="edit">

    <fieldset class="aras1">

        <div class="content" align="center">
            <s:hidden name="kod" id="kod"/>
            <s:hidden name="idRujukan" id="idRujukan"/>
            <s:hidden name="mandatori2" id="mandatori2" value="${actionBean.mandatori}"/>
            <table>

                <tr><td>Nama Jabatan </td>
                    <td> : </td>
                    <td>${actionBean.pprl.agensi.nama}</td></tr>
                <tr><td>Catatan <br/><br/><br/><br/><br/></td>
                    <td> : <br/><br/><br/><br/><br/></td>
                    <td><s:textarea name="catatan"  cols="50" rows="5"/></td></tr>
                    <%--<c:if test="${line.tarikhDisampai eq null}">--%>
                <tr><td>Tarikh Hantar </td>
                    <td> : </td>
                    <td><s:text name="tarikhHantar" class="datepicker" id="date1" formatPattern="dd/MM/yyyy" size="15" onchange="javascript:setNewDate()"/></td></tr>

                <tr><td>Tempoh hari </td>
                    <td> : </td>
                    <td><s:text name="jangkaMasa_hari" id="hari"/></td></tr>
                <tr><td>Tarikh Jangka Terima </td>
                    <td> : </td>
                    <td><s:text name="tarikhJangkaTerima" formatPattern="dd/MM/yyyy" size="15" readonly="true"/></td></tr>
                <tr><td>Mandatori </td>
                    <td> : </td>
                    <td><s:checkbox name="mandatori" id="mandatori" onchange="setValue();"/></td></tr>

            </table>
            <s:submit name="kemaskini" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            <s:button name="close" id="kembali" value="Tutup" class="btn" onclick="javascript:letClose();"/>
        </div>

    </fieldset>
    <fieldset class="aras1">
        <div class="content" align="center">
            <legend>Senarai Salinan Kepada</legend>
            <br/>
            <%--   <c:set value="0" var="i"/>--%>

            <display:table class="tablecloth" name="${actionBean.prs}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pelupusan/sedia_jabatan">
                <display:column title="Bil" >${line_rowNum}
                    <s:hidden name="" class="${line_rowNum -1}" value="${line.permohonanRujukanLuar.idRujukan}"/>
                </display:column>
                <display:column title="Nama Jabatan">${line.kodAgensi.nama}</display:column>

                <display:column title ="Hapus">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="removePermohonanRujSalinan(${actionBean.kod},${line.idMohonRujLuarSalinan})"  onmouseover="this.style.cursor='pointer';"/>
                </display:column>

                <%--<s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />--%>
            </display:table>
            <s:button class="btn" value="Tambah" name="add" onclick="javascript:search(${actionBean.pprl.idRujukan},document.getElementById('kod').value,document.getElementById('idRujukan').value)" />
        </div>
    </fieldset>
    <fieldset class="aras1">
        <div class="content" align="center">
            <legend>Senarai Dokumen Disertakan</legend>
            <br/>
            <%--   <c:set value="0" var="i"/>--%>

            <display:table class="tablecloth" name="${actionBean.prsd}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pelupusan/sedia_jabatan">
                <display:column title="Bil" >${line_rowNum}
                    <s:hidden name="" class="${line_rowNum -1}" value="${line.idMohonRujLuarDok}"/>
                </display:column>
                <display:column title="Kod Dokumen">${line.dokumen.kodDokumen.kod}</display:column>
                <display:column title="Nama Dokumen">${line.dokumen.tajuk}</display:column>

                <display:column title ="Hapus">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="removePermohonanRujDokumen(${actionBean.kod},${line.idMohonRujLuarDok})"  onmouseover="this.style.cursor='pointer';"/>
                </display:column>

                <%--<s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />--%>
            </display:table>
            <s:button class="btn" value="Tambah" name="add" onclick="javascript:searchDokumen(${actionBean.pprl.idRujukan},document.getElementById('kod').value,document.getElementById('idRujukan').value)" />
        </div>
    </fieldset>
</s:form>


