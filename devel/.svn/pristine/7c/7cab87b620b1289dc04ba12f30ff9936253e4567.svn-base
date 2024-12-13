<%-- 
    Document   : create_idKumpulan
    Created on : Sep 2, 2010, 1:27:04 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.hasil.CreateIdKumpulanActionBean" id="create_idKump">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kumpulan Akaun</legend>
            <%--<p>
                <label><em>*</em>ID Kumpulan :</label>
                <s:text name="kumpulanAkaun.idKumpulan" id="idKump" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>--%>
            
            <p>
                <label><em>*</em>Daerah :</label>
                <s:select name="daerah" id="caw" onchange="t(this.value)" style="width:200px" disabled="true">
                    <s:option label="Pilih Daerah..." value="" />
                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"/>
                </s:select>
            </p>

            <p>
                <label><em>*</em>Nama Kumpulan :</label>
                <s:textarea name="kumpulanAkaun.catatan" id="notes" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="save" value="Simpan" class="btn" onclick="return validateField(this.form);"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('create_idKump');"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
</s:form>
<script type="text/javascript">
    function validateField(f){
        var id = document.getElementById("idKump");
        var cw = document.getElementById("caw");
        var ctt = document.getElementById("notes");
        if((cw.value == '')||(ctt.value == '')){
            alert("Sila Masukkan data di ruangan yang bertanda *.");
            return false;
        }
        return true;
    }
</script>