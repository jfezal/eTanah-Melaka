<%-- 
    Document   : salinan_surat_edit
    Created on : May 20, 2015, 4:06:38 PM
    Author     : k.Hazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Salinan-salinan Kepada</title>

<script type="text/javascript">
    function addSalinan(){
        var kodDokumen = $('#kodDokumen').val();
        var idPermohonan = $('#idPermohonan').val() ;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/salinan_surat?showFormTambahSalinan&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editSalinan(idSalinan)
    {
        var kodDokumen = $('#kodDokumen').val();
        var idPermohonan = $('#idPermohonan').val() ;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/salinan_surat?showFormEditSalinan&idSalinan="+idSalinan+"&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function deleteSalinan(idSalinan){   
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {          
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/salinan_surat?deleteRow&idKandungan='+idSalinan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
//                self.opener.refreshSK();
            }, 'html');
        }
    }
    
    function refreshSK(){
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/salinan_surat?rehydrate&idPermohonan='+id ;
        $.get(url,
        function(data){
            $("#skDiv").replaceWith($('#skDiv', $(data)));
        },'html');
    }
</script>

<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>
<s:form beanclass="etanah.view.stripes.pembangunan.SalinanKepadaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" id="idPermohonan"/>
        <s:hidden name="kodDokumen" id="kodDokumen"/>
        <fieldset class="aras1">
            <div id ="edit">
                <legend>Maklumat Salinan Kepada</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Bil</th><th>Nama</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Catatan</th><th>Tindakan</th>
                        </tr>
                        <c:if test="${!empty actionBean.listSalinanKepada}">
                            <c:set var="i" value="1" />
                            <tr>
                            <div id ="skDiv">
                                <c:forEach items="${actionBean.listSalinanKepada}" var="line">
                                    <td>
                                        ${i}
                                    </td>
                                    <td>
                                        ${line.nama}
                                    </td>

                                    <td>
                                        ${line.alamat1}
                                        <c:if test="${line.alamat2 ne null}">, </c:if>
                                        ${line.alamat2}
                                        <c:if test="${line.alamat3 ne null}">, </c:if>
                                        ${line.alamat3}
                                        <c:if test="${line.alamat4 ne null}">, </c:if>
                                        ${line.alamat4}
                                    </td>
                                    <td>
                                        ${line.poskod}
                                    </td>
                                    <td>
                                        <c:if test="${line.negeri.kod eq '01'}">Johor</c:if>
                                        <c:if test="${line.negeri.kod eq '02'}">Kedah</c:if>
                                        <c:if test="${line.negeri.kod eq '03'}">Kelantan</c:if>
                                        <c:if test="${line.negeri.kod eq '04'}">Melaka</c:if>
                                        <c:if test="${line.negeri.kod eq '05'}">Negeri Sembilan</c:if>
                                        <c:if test="${line.negeri.kod eq '06'}">Pahang</c:if>
                                        <c:if test="${line.negeri.kod eq '07'}">Pulau Pinang</c:if>
                                        <c:if test="${line.negeri.kod eq '08'}">Perak</c:if>
                                        <c:if test="${line.negeri.kod eq '09'}">Perlis</c:if>
                                        <c:if test="${line.negeri.kod eq '10'}">Selangor</c:if>
                                        <c:if test="${line.negeri.kod eq '11'}">Terengganu</c:if>
                                        <c:if test="${line.negeri.kod eq '12'}">Sabah</c:if>
                                        <c:if test="${line.negeri.kod eq '13'}">Sarawak</c:if>
                                        <c:if test="${line.negeri.kod eq '14'}">WP Kuala Lumpur</c:if>
                                        <c:if test="${line.negeri.kod eq '15'}">WP Labuan</c:if>
                                        <c:if test="${line.negeri.kod eq '16'}">Luar Negara</c:if>
                                        <c:if test="${line.negeri.kod eq '17'}">WP Putrajaya</c:if>
                                        <c:if test="${line.negeri.kod eq '99'}">Lain-lain</c:if>
                                    </td>
                                    <td>
                                        ${line.catatan}
                                    </td>
                                    <td>
                                        <a onclick="deleteSalinan(${line.idSalinanKepada})" onmouseover="this.style.cursor='pointer';" title="Hapus Salinan"><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editSalinan(${line.idSalinanKepada})" onmouseover="this.style.cursor='pointer';" title="Kemaskini Salinan"><img alt="Kemaskini Salinan"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a> <br/>
                                    </td>
                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </div>
                        </c:if>
                    </table>
                </div>
                <br/>
            </div>
        </fieldset>
    </div>
    <fieldset class="aras1">
        <table  width="100%" border="0">
            <tr>
                <td align="center">
                    <s:button name="Tambah" id="save" value="Tambah S.K" class="longbtn" onclick="addSalinan()"/>
                    <%--<s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>--%>
                </td>
            </tr>
        </table>   
    </fieldset>     
</s:form>

