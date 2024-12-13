<%-- 
    Document   : edit_penyerah
    Created on : Apr 14, 2010, 4:33:13 PM
    Author     : Wazer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function tutup1(event, f) {
        doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'html',
            data: q,
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
                $.unblockUI();
                self.close();
            }
        });
    }


    function simpan1(id)
    {
        alert(id);

        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?simpanWakilKuasa&idMohon=" + id, "eTanah";
                frm.action = url;
        frm.submit();
        self.close();

    }
    function simpan2(id) {
        alert(id);
        window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?simpanWakilKuasa&idMohon=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=0,height=0");
    }



</script>
<s:messages/>
<s:errors/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.daftar.UtilitiBetulHakmilik" name="form1">

    <br/>

    <s:hidden name="idPenyerah" id="idPenyerah"/>
    <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value = "${actionBean.permohonan.idPermohonan}"/>
    <s:hidden name="wakilKuasa.idWakil" id="idWakil" value = "${actionBean.wakilKuasa.idWakil}"/>
    <fieldset class="aras1">
        <legend>Carian Kuasa Wakil</legend>
        <br>
        <c:if test="${actionBean.flag}">
        <p><label for="wKuasa">Id Wakil Kuasa:</label>
            <input type="text" name="wKuasa" id="wKuasa"  onkeyup="this.value = this.value.toUpperCase();"/>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:submit name="checkWakilKuasa" value="Cari" class="btn" />
            <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
        </p>
        </c:if>
        <c:if test="${actionBean.flag2}">
        <c:if test="${actionBean.wakilKuasa.idWakil ne null}">
            <br>
            <p><label for="keputusan">No Wakil Kuasa :</label>
                ${actionBean.wakilKuasa.idWakil}
            </p>
        </c:if>
        <c:if test="${actionBean.wakilKuasa.idWakil ne null}">
            <p>
                <label>&nbsp;</label>

                <s:submit name="simpanWakilKuasa" value="Simpan" class="btn" onclick="simpan1('${actionBean.wakilKuasa.idWakil},${actionBean.permohonan.idPermohonan}');"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </c:if>
        </c:if>
        <c:if test="${actionBean.flag3}">
            <br>
            <p>
                Kemasukan Surat 
                <font style="font-family: Tahoma; font-size: 13px; font-weight: bold;">
                ${actionBean.wakilKuasa.idWakil}</font>
                berjaya dimasukkan kepada permohonan 
                <font style="font-family: Tahoma; font-size: 13px; font-weight: bold;">
                ${actionBean.permohonan.idPermohonan}</font>
            </p>
        
        <c:if test="${actionBean.wakilKuasa.idWakil ne null}">
            <p> <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </c:if>
        </c:if>    
    </fieldset>

</s:form>


