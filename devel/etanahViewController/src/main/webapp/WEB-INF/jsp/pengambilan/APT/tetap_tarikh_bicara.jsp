<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready(function() {
    });

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.sek8.TetapPerbicaraanActionBean">
    <s:messages/>
    <div class="subtitle"> <fieldset class="aras1">
            <legend>Tetap Tarikh Bicara </legend>
            <center>
                <display:table class="tablecloth" name="${actionBean.listBorangE}" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="" id="line">
                    <display:column title="Nama Borang"  ><s:checkbox name="check" value="${line.id}"></s:checkbox></display:column>
                    <display:column title="No" sortable="true">${line_rowNum}<s:hidden name="idHm">${line.id}</s:hidden></display:column>

                    <display:column title="Id Hakmilik"  >${line.hm.hakmilik.idHakmilik}</display:column>
                    <display:column title="Tarikh Bicara"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilikPerbicaraan.tarikhBicara}"/></display:column>
                    <display:column title="Masa Bicara" ><fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilikPerbicaraan.tarikhBicara}"/></display:column>
                    <display:column title="Tempat Bicara" >${line.hakmilikPerbicaraan.lokasiBicara}</display:column>

                </display:table></center>
        </fieldset></div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Perbicaraan </legend>
            <p>
                <label for="Permohonan">Tarikh Bicara :</label>
                <font style="text-transform:uppercase;"> <s:text name="tarikhBicara"/></font>&nbsp; &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label for="ID Permohonan">Masa Bicara :</label>
                <s:text name="masaBicara"/>
            </p>
            <p>
                <label for="ID Permohonan">Tempat Bicara :</label>
                <s:text name="tempatBicara"/>
            </p>
            <p>
                <label for="ID Permohonan">Keterangan Bicara :</label>
                <s:textarea name="keteranganBicara"/>
            </p>
        </fieldset>
        <p>
            <label for="ID Permohonan"> &nbsp;</label>
            <s:button name="simpan" class="longbtn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
        </p>      

    </div>
</s:form>