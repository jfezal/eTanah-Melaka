<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function resetValue() {
        alert("hoho");
        $('#idMohon').val('');
    }
</script>
        
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.lelong.UtilitiCetakSemulaActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="dokumen">

        <fieldset class="aras1">
            <legend>Cetak Semula</legend>

            <p>
                <label><em>*</em>ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan" size="40" maxlength="20" id="idMohon" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" class="btn" onclick=""/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
            </p>

            <br/>
            <br/>
            <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                <display:column title="Bil" sortable="true">${row_rowNum}</display:column>
                <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                <display:column title="Catatan" property="catatan" />
                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                <display:column title="Papar">
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar ${row.dokumen.kodDokumen.nama}"/>
                    </c:if>
                </display:column>
            </display:table>
            <br/>
        </fieldset>
    </div>
</s:form>


