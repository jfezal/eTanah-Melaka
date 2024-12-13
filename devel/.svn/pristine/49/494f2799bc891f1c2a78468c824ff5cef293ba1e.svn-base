<%-- 
    Document   : edit_penyerah
    Created on : Apr 14, 2010, 4:33:13 PM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>-->

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>


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


    function simpan(id)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?kemaskiniPenyerah&idMohon=" + id;
        frm.action = url;
        frm.submit();   
        self.close();

    }
    function tutup(id)
    {
        
        self.close();

    }



</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.daftar.UtilitiBetulHakmilik" name="form1">

    <br/>




    <s:hidden name="idPenyerah" id="idPenyerah"/>
    <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value = "${actionBean.permohonan.idPermohonan}"/>
    <fieldset class="aras1">
        <legend>
            Kemaskini Penyerah

        </legend>


        <p>
            <label>Nama :</label>
            <s:text name="permohonan.penyerahNama" id="penyerahNama" size="42"/><em>*</em>
        </p>
        <p>
            <label>Alamat :</label>
            <s:text name="permohonan.penyerahAlamat1" id="penyerahAlamat1" size="30"/><em>*</em>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:text name="permohonan.penyerahAlamat2" id="penyerahAlamat2" size="30"/>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:text name="permohonan.penyerahAlamat3" id="penyerahAlamat3" size="30"/>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:text name="permohonan.penyerahAlamat4" id="penyerahAlamat4" size="30"/>
        </p>
        <p>
            <label>Poskod :</label>
            <s:text name="permohonan.penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
        </p>

        <p>

            &nbsp&nbsp&nbsp<center>  
            <%--<s:submit name="kemaskiniPenyerah" value="Kemaskini" class="btn" onclick="simpan('${actionBean.permohonan.idPermohonan}');"/>--%> 
            <s:submit name="kemaskiniPenyerah" value="Kemaskini" class="btn"/>
            <s:submit name="tutup" value="tutup" class="btn" onclick="self.close();"/></center></p>
</p>
</fieldset>

</s:form>
