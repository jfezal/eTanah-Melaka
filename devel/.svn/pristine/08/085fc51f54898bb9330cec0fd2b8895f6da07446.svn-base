<%--
    Document   : batal_permohonan
    Created on : September 26, 2012, 11:50:38 AM
    Author     : muhammad.amin
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
    
    $(document).ready(function(){
        $('form').submit(function() {
            doBlockUI();
        });
    });

    <%--    function doSearch(f,e){
            var v = $('#idPerserahan').val();
            if(v == ''){
                alert('Sila masukkan ID Permohonan.');
                $('#idPerserahan').focus();
                return;
            }
            f.action = f.action + '?' + e;
            f.submit();
        }--%>
    
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

            function doBlockUI (){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            }
</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div class="subtitile">
    <s:errors/>
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.consent.BatalPermohonanActionBean">
        <fieldset class="aras1">
            <legend>Pembatalan Permohonan</legend>
            <p>
                <label><font color="red">*</font>ID Permohonan :</label>
                <s:text name="idPerserahan" id="idPerserahan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <%-- <s:submit name="search" value="Cari"  onclick="doSearch(this.form, this.name);" class="btn"/>--%>
                <s:submit name="search" value="Cari" class="btn"/>
                <s:submit name="reset" value="Isi Semula" onclick="clearForm(this.form);" class="btn"/>
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
                    <label>ID Permohonan :</label>${actionBean.perserahan.idPermohonan}&nbsp;
                </p>
                <p>
                    <label>Kod Urusan :</label>${actionBean.perserahan.kodUrusan.kod}&nbsp;
                </p>
                <p>
                    <label>Urusan :</label>${actionBean.perserahan.kodUrusan.nama}&nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Sebab Batal :</label>
                    <s:textarea name="sebab" cols="80" rows="10"/>
                    &nbsp;
                </p>
                <p> <label><font color="red">*</font> Ditandatangan Oleh :</label>
                    <s:select name="mohonTandatanganDokumen.diTandatangan" id="namapguna">
                         <s:option label="Sila Pilih" value=""/>                          
                         <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                            <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select> &nbsp;
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
                    <label>ID Permohonan :</label>${actionBean.perserahan.idPermohonan}&nbsp;
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
                    </display:table>
                </div>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>