<%-- 
    Document   : pembatalan_perserahan
    Created on : Jun 10, 2010, 12:48:38 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSearch(f,e){       
        var v = $('#idPerserahan').val();
        if(v == ''){
            alert('Sila masukkan  ID Perserahan/ID Permohonan.');
            $('#idPerserahan').focus();
            return;
        }
        f.action = f.action + '?' + e;        
        f.submit();
    }
    function clearForm(f) {
        $('#idPerserahan').val('');
    }

    function doPrintViaApplet (docId) {
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>

<div class="subtitile">    

    <s:errors/>
    <s:messages/>
    <s:form beanclass="etanah.view.daftar.utiliti.PembatalanPerserahanAction">
        <fieldset class="aras1">
            <legend>Pembatalan Permohonan</legend>
            <p>
                <label><em>*</em>ID Perserahan/ID Permohonan :</label>
                <s:text name="idPerserahan" id="idPerserahan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>           
            <p>
                <label>&nbsp;</label>
                <s:button name="search" value="Cari"  onclick="doSearch(this.form, this.name);" class="btn"/>
                <s:button name="reset" value="Isi Semula" onclick="clearForm(this.form);" class="btn"/>
            </p>
            <br/>
        </fieldset>
        <c:if test="${actionBean.isExist}">
            <br/>
            <fieldset class="aras1">
                <s:hidden name="taskId"/>
                <s:hidden name="perserahan.idPermohonan"/>
                <legend>Keputusan Carian</legend>
                <p>
                    <label>ID Perserahan :</label>${actionBean.perserahan.idPermohonan}&nbsp;
                </p>
                <p>
                    <label>Kod Urusan :</label>${actionBean.perserahan.kodUrusan.kod}&nbsp;
                </p>
                <p>
                    <label>Urusan :</label>${actionBean.perserahan.kodUrusan.nama}&nbsp;
                </p>
                <p>
                    <label>Sebab Batal :</label>
                    <s:textarea name="sebab" cols="80" rows="10"/>
                    &nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="save" value="Simpan" class="btn"/>
                </p>

            </fieldset>
        </c:if>
        <br>
        <c:if test="${actionBean.cetak}">
            <br/>
            <fieldset class="aras1">
                <s:hidden name="perserahan.idPermohonan"/>
                <legend>Cetak Surat Pembatalan</legend>
                <p>
                    <label>ID Perserahan :</label>${actionBean.perserahan.idPermohonan}&nbsp;
                </p>
                <p>
                    <label>Kod Urusan :</label>${actionBean.perserahan.kodUrusan.kod}&nbsp;
                </p>
                <p>
                    <label>Urusan :</label>${actionBean.perserahan.kodUrusan.nama}&nbsp;
                </p>
                <br>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.d1}"
                                   cellpadding="0" cellspacing="0" id="line1">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Papar">
                            ${line1.tajuk}
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                                 onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor='pointer';"/>
                        </display:column>
                    <%--    <display:column title="Cetak">
                            <s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line1.idDokumen}');" class="btn"/>
                        </display:column>--%>
                    </display:table>
                </div>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>