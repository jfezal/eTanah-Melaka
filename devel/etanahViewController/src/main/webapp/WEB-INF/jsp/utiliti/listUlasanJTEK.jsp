<%-- 
    Document   : listUlasanJTEK
    Created on : Nov 8, 2013, 10:41:36 AM
    Author     : nurashidah
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function tambahDokumen(frm, idMohon, kodAgensi) {
        var url = '${pageContext.request.contextPath}/utility/tambah_dok_JTEK?dokumenSerta&idMohon=' + idMohon + '&kodAgensi=' + kodAgensi;
        frm.action = url;
        frm.submit();
    }

</script>

<s:form beanclass="etanah.view.utility.JTEKUtilityActionBean" name="utilityJTEKForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Aduan
            </legend>
            <p>
                <font size="2" color="Red">Perhatian :</font> Sila masukkan ID Mohon atau Kod Agensi untuk membuat carian.
            </p>
            <p> <label for="idMohon"><font color="red">*</font>ID Mohon :</label>
                    <s:text name="idMohon" id="idMohon"></s:text>
                </p>
                <p><label for="status"><font color="red">*</font>Kod Agensi :</label>
                    <s:select name="kodAgensi" id="kodAgensi">
                        <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiKodAgensi}" var="line">
                        <s:option value="${line.kod}">${line.nama}</s:option>
                    </c:forEach>
                </s:select>
                <s:submit name="searchUlasan" id="searchUlasan" value="Cari" class="btn"/>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>
                Senarai Ulasan dari Jabatan Teknikal
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.ulasanJTEK}" id="line" class="tablecloth" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/utility/ulasan_JTEK">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="permohonan.idPermohonan" title="ID Permohonan" style="vertical-align:baseline"/>
                    <display:column property="kodAgensi.kod" title="Kod" style="vertical-align:baseline"/>
                    <display:column property="kodAgensi.nama" title="Agensi" style="vertical-align:baseline"/>                                   
                    <display:column title="Tambah Dokumen">

                        <div align="center">
                            <img alt='Klik Untuk Tindakan' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="tambahDokumen(document.forms.utilityJTEKForm, '${line.permohonan.idPermohonan}', '${line.kodAgensi.kod}')"/>
                        </div>
                    </display:column>                                  
                </display:table>
            </div>
        </fieldset>

    </div>
</s:form>