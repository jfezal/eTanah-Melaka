<%-- 
    Document   : carian_keputusan_mahkamah
    Created on : Apr 16, 2014, 8:48:41 PM
    Author     : MohammadHafifi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script language="javascript" type="text/javascript">
    function validateForm2(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila masukkan Id Permohonan yang dikehendaki");
            $('#idPermohonanCarian').focus();
            return false;
        }

        return true;
    }
    function addRecord(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?popupAddRecord&idPermohonan=${actionBean.idPermohonan}", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?popupViewRecord&idRujukan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?popupEditRecord&idRujukan='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function removeSingle(idRujukan)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?deleteSingle&idRujukan='+idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPagePantau1();
    }
    function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idRujukan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan + '&dokumenId=' + dokumenId;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function removeImej(idImej,idRujukan) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?deleteSelected&idImej='+idImej+'&idRujukan='+idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPage();
            },'html');
        }        
    }
    function refreshPageMahkamah(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_keputusan_mahkamah?refreshpage&idPermohonan='+id;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiKeputusanMahkamah">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Keputusan Mahkamah</legend>
            <p><label for="idPermohonan"><em>*</em>Id Permohonan:</label>
                <input type="text" name="idPermohonan" id="idPermohonanCarian" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchNoSerahan" value="Cari" class="btn" onclick="return validateForm2()"/>
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.permohonan ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keputusan Mahkamah</legend>
                <div class="content" align="center">
                    Id Permohonan: ${actionBean.idPermohonan}
                </div>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiMahkamah}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Rujukan"><a class="popup" onclick="viewRecord(${line.idRujukan})">${line.noRujukan}</a></display:column>
                        <display:column title="Mahkamah" property="namaSidang"></display:column>
                        <display:column title="Tarikh Sebutan/Perbicaraan" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Tarikh Keputusan" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhRujukan}"/></display:column>
                        <display:column title="Keputusan" property="catatan"></display:column>
                        <display:column title="Muat Naik">
                            <c:if test="${line.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${line.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.dokumen.kodDokumen.nama}"/>
                            </c:if>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}', '${line.dokumen.idDokumen}',
                                     '${actionBean.permohonan.idPermohonan}','KMD','${line.idRujukan}');return false;" height="15" width="15" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                            <c:if test="${line.dokumen.namaFizikal != null}">
                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="removeImej('${line.dokumen.idDokumen}','${line.idRujukan}');" height="15" width="15" alt="Hapus"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.dokumen.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>

                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idRujukan}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiMahkamah[line_rowNum-1].idRujukan}');" />
                            </div>
                        </display:column>

                    </display:table>
                    <br>
                    <s:hidden name="idPermohonan"/>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
