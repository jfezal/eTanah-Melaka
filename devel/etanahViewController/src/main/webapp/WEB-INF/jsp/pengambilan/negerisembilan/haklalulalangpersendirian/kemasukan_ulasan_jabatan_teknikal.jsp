<%-- 
    Document   : kemasukan_ulasan_jabatan_teknikal
    Created on : Okt 22, 2010, 6:10:08 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>


<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/pengambilan/ulasan_jabatan_teknikalPHLL?refreshpageUlasanJabatanTeknikal';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function saveUlasan(event, f){
        if(validate()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshUlasanJabatanTeknikalPage();
                self.close();
            },'html');
        }
    }
    function validate(){
        if($("#jabatan").val() == ""){
            alert('Sila pilih " Jabatan Teknikal " terlebih dahulu.');
            $("#jabatan").focus();
            return true;
        }
        if($("#ulasan").val() == ""){
            alert('Sila masukkan " Ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
        }
    }

    function muatNaikForm1(folderId, dokumenId, idPermohonan, dokumenKod, idKertas) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idKertas=' + idKertas + '&dokumenId=' + dokumenId;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeImej(idImej,idKertas) {
        var url = '${pageContext.request.contextPath}/pengambilan/lawatanTapakHLL?deleteSelected&idImej='+idImej+'&idKertas='+idKertas;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            refreshPage();
        },'html');
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.pengambilan.UlasanJabatanTeknikalPHLLActionBean">
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Kemasukan Ulasan Jabatan Teknikal</legend>
                <table>
                        <tr><td><label>Jabatan :</label></td>
                                    <td><s:select name="jabatanTeknikal" style="width:290px" id="jabatan">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                    <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                    <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                    <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                    <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                    <%--<s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>--%>
                                    </s:select></td>
                         </tr>
                         
                         <tr><td><label>Tarikh Ulasan :</label></td>
                             <td><s:text name="tarikh" size="40" id="datepicker" class="datepicker"/></td>
                         </tr>
                         
                         <tr><td><label>Ulasan :</label></td>
                             <td><s:textarea name="ulasan" rows="3" cols="40" id="ulasan"/></td>
                         </tr>

                        <tr>
                        <td><label>Fail Dokumen :</label></td>
                        <td><c:if test="${actionBean.permohonanKertas.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${actionBean.permohonanKertas.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${actionBean.permohonanKertas.kodDokumen.nama}"/>
                                <font size="2%">(${actionBean.permohonanKertas.dokumen.tajuk})</font>
                            </c:if>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}', '${actionBean.permohonanKertas.dokumen.idDokumen}',
                                     '${actionBean.permohonan.idPermohonan}','KS','${actionBean.permohonanKertas.idKertas}');return false;" height="15" width="15" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>

                            <c:if test="${actionBean.permohonanKertas.dokumen.namaFizikal != null}">
                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="removeImej('${actionBean.permohonanKertas.dokumen.idDokumen}','${actionBean.permohonanKertas.idKertas}');" height="15" width="15" alt="Hapus"
                                onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${actionBean.permohonanKertas.dokumen.kodDokumen.nama}"/>
                             </c:if>
                        </td>
                    </tr>
                </table>
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="saveUlasan(this.name, this.form);"/>
                            <label>&nbsp;</label>
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </p>
            </fieldset>
        </div>
    </s:form>
