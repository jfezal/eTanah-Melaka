<%-- 
    Document   : maklumat_agensi_kerajaan
    Created on : Sep 21, 2012, 12:03:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<script type="text/javascript">
    function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
    }
    function kemaskini(i){
        var d = $('.x'+i).val();
        
        var idMohon = document.getElementById("idPermohonan").value;
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan?showEditPemohon&idPihak="+d+"&idPermohonan="+idMohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600, scrollbars=yes");
    }
    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            <%--alert("hi");--%>
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan?deletePemohon&idPemohon='+val;
            $.get(url,function(data){$('#page_div').html(data);},'html');
        }
    }
    function refreshMaklumatPemohon(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan?refreshMaklumatPemohon';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatAgensiKerajaanActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>
            <s:hidden name="idPermohonan" id="idPermohonan"/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_agensi_kerajaan">

                    <display:column title="Bil">
                        ${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Kod Agensi" />
                    <display:column title="Alamat" >${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                        ${line.pihak.alamat4}</display:column>
                    <display:column property="pihak.poskod" title="Poskod" />
                    <display:column property="pihak.negeri.nama" title="Negeri" />
                    <c:if test="${edit eq true}">
                        <display:column title="Kemaskini">
                            <a href="#" onclick="kemaskini('${line_rowNum -1},${idPermohonan}');return false;">Kemaskini</a>
                        </display:column>    
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                            </div>
                        </display:column>
                        </c:if>
                </display:table>
                        <c:if test="${fn:length(actionBean.listPemohon) != 1}" >
                <s:button class="btn" value="Tambah" name="tambah" id="tambah" onclick="addPemohon();"/>
                        </c:if>

            </div>
        </fieldset>
    </div>
</s:form>
