<%-- 
    Document   : lawatan_tapak
    Created on : Jan 18, 2010, 2:43:10 PM
    Author     : massita
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function validateForm(){

        if($('#draf').val()=="")
        {
            alert("Sila Isi Ringkasan Draf Kertas Siasatan");
            return false;
        }

        return true;
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/pengambilan/lawatanTapakHLL?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function test(f) {
        $(f).clearForm();
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
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.LawatanTapakHLLActionBean" id="form">
        <s:messages />
        <s:errors/>
        <fieldset class="aras1">
            <legend align="justify">Maklumat Lawatan Tapak</legend>
            <div class="content" align="left">
            <table border="0" cellspacing="5" width="1%">
                <c:if test="${edit}">
                    <tr>
                        <td valign="top"><label>Buat lawatan tapak :</label></td>
                        <td><s:textarea name="maksud" rows="3" cols="50" id="maksud"/></td>
                    </tr>
                    <tr>
                        <td valign="top"><label>Keperluan tanah :</label></td>
                        <td><s:textarea name="maksud" rows="3" cols="50" id="maksud"/></td>
                    </tr>

                    <tr>
                        <td valign="top"><label>Kegunaan Tanah :</label></td>
                        <td><s:textarea name="maksud" rows="3" cols="50" id="maksud"/></td>
                    </tr>

                    <tr>
                        <td><label>Imej :</label></td>
                         <td><c:if test="${actionBean.permohonanKertas.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${actionBean.permohonanKertas.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${actionBean.permohonanKertas.kodDokumen.nama}"/>
                        (${actionBean.permohonanKertas.dokumen.tajuk})
                        </c:if>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}', '${actionBean.permohonanKertas.dokumen.idDokumen}',
                                     '${actionBean.permohonan.idPermohonan}','KS','${actionBean.permohonanKertas.idKertas}');return false;" height="15" width="15" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                        <c:if test="${actionBean.permohonanKertas.dokumen.namaFizikal != null}">
                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="removeImej('${actionBean.permohonanKertas.dokumen.idDokumen}','${actionBean.permohonanKertas.idKertas}');" height="15" width="15" alt="Hapus"
                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${actionBean.permohonanKertas.dokumen.kodDokumen.nama}"/>
                        </c:if></td>
                    </tr>
                </table>
            </div>
        </fieldset><br />
        <div align="center">
            <tr>
                <td>
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    <s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/>
                </td>
            </tr>
        </div>
      </c:if>
        <c:if test="${view}">
            <tr>
                        <td valign="top"><label>Buat lawatan tapak :</label></td>
                        <td>${actionBean.permohonanKertas}</td>
                    </tr>
                    <tr>
                        <td valign="top"><label>Keperluan tanah :</label></td>
                        <td>${actionBean.permohonanKertas}</td>
                    </tr>

                    <tr>
                        <td valign="top"><label>Kegunaan Tanah :</label></td>
                        <td>${actionBean.permohonanKertas}</td>
                    </tr>

                    <tr>
                        <td><label>Imej :</label></td>
                        <td>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KS'}">
                                     <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                    (${actionBean.permohonanKertas.dokumen.tajuk})
                                </c:if>
                            </c:forEach>
                           </td>
                    </tr>
        </c:if>
    </div>
</s:form>