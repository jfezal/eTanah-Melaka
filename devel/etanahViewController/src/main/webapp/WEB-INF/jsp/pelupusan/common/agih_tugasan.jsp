<%-- 
    Document   : agih_tugasan
    Created on : Oct 27, 2010, 12:15:05 PM
    Author     : sitifariza.hanim
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
        if(i == ''){
            alert('Sila pilih pengguna terlebih dahulu.');
            $('#id_pguna').focus();
            return false;
        }
        if(confirm('Adakah anda pasti? Sila semak dokumen terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.stripes.pelupusan.AgihanTugasDisActionBean" var="penggunaperanan"/>
<s:form beanclass="etanah.view.stripes.pelupusan.AgihanTugasDisActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label for="ID Berkaitan"> ID Perserahan : </label>
                ${actionBean.mohon.idPermohonan}&nbsp;
            </p>
            <p><label for="Urusan"> Urusan : </label>
                ${actionBean.mohon.kodUrusan.nama}&nbsp;
            </p>
             <br/>
        </fieldset>

    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Agihan Tugas
            </legend>
            <p><label>Hantar Kepada :</label>
                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="jawatan" />
                </s:select>
            </p>
            <br/>
            <!--<p>
                <label>Nota :</label>
                <s:textarea name="nota" rows="3" cols="45"/>
            </p>
            --><p>
                <label>&nbsp;</label>
                <%--<s:submit name="agihPT" value="Agih" class="btn"
                          onclick="return confirm('Adakah Anda pasti? Sila Semak Dokumen jika masih belum semak.')"/>--%>
                <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
            </p>
        </fieldset>

    </div>



</s:form>