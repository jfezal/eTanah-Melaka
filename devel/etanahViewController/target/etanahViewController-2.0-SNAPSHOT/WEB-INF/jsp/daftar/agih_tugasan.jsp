<%-- 
    Document   : agihTugasan
    Created on : 01 Oktober 2009, 12:34:43 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doAgih(e, f) {
        var i = $('#id_pguna').val();
        if (
                i == '') {
            alert('Sila pilih pengguna terlebih dahulu.');
            $('#id_pguna').focus();
            return false;
        }
        if (confirm('Adakah anda pasti? Sila semak dokumen terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }

    function doQueryTask(val) {

        $('#tugasan').html('');
        $('#loading-img').show();
        var nama = $('#id_pguna option:selected').text();

        var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + val;
        $.get(url,
                function(data) {
                    $('#loading-img').hide();
                    $('#tugasan').html(nama + ' mempunyai ' + data + ' tugasan.');
                }
        );
    }
</script>
<s:useActionBean beanclass="etanah.view.stripes.AgihTugasanActionBean" var="penggunaperanan"/>
<s:form beanclass="etanah.view.stripes.AgihTugasanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <%--   <fieldset class="aras1">
               <legend>
                   Maklumat Fail
               </legend>
               <p>
                   <label for="ID Berkaitan"> ID Perserahan : </label>
                   ${actionBean.mohon.idPermohonan}&nbsp;
               </p>
               <p><label for="Urusan"> Urusan : </label>
                   ${actionBean.mohon.kodUrusan.nama}&nbsp;
               </p>
           </fieldset>
           <br/>--%>
        <c:if test="${fn:length(actionBean.senaraiPermohonanBerangkai) > 0}" >
            <fieldset class="aras1">
                <legend>
                    Senarai Permohonan
                </legend>                

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanBerangkai}" cellpadding="0"
                                   cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="idPermohonan" title="ID Perserahan"/>
                        <display:column property="kodUrusan.nama" title="Urusan" />
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
    </div>
    <br/>
    <c:if test="${!empty penggunaperanan.listPp}" >

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Agihan Tugas
                </legend>
                <p><label>Hantar Kepada :</label>
                    <%-- <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                         <s:option value="">-- Sila Pilih --</s:option>
                         <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" title="${nama}"></s:options-collection>
                     </s:select> <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/> --%>

                    <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <c:forEach items="${penggunaperanan.listPp}" var="i" >
                            <s:option value="${i.idPengguna}">${i.nama}</s:option>
                        </c:forEach>
                    </s:select> <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>

                    <em id="tugasan"/>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>

                    <%--<c:if test="${actionBean.isBerhalang}">
                        <s:button name="agihPT" value="Agih" class="btn" disabled="true" onclick="doAgih(this.name, this.form);"/>
                    </c:if>
                    <c:if test="${!actionBean.isBerhalang}">--%>
                    <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
                    <%-- </c:if>--%>
                </p>
            </fieldset>

        </div>
    </c:if>
</s:form>