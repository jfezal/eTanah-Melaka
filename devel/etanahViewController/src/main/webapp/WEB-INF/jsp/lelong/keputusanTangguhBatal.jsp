<%--
    Document   : keputusanTangguhBatal
    Created on : Jun 1, 2010, 9:59:39 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script language="JavaScript" type="text/javascript">


</script>


<s:form beanclass="etanah.view.stripes.lelong.KeputusanTangguhBatalActionBean">


    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <fieldset class="aras1">

        <legend>
            No ID Permohonan/ID Hakmilik
        </legend><br><br>

        <div class="subtitle displaytag" >

            <p>
                <label>ID Hakmilik : </label>
                ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}
            </p><br>

            <p>
                <label>ID Permohonan : </label>
                ${actionBean.hakmilikPermohonan.permohonan.idPermohonan}
            </p><br>

            <p>
                <label>ID Permohonan Lama : </label>
                ${actionBean.hakmilikPermohonan2.permohonan.idPermohonan}
            </p>

            <br><br>
        </div>


        <div class="content" align="right">
            <p>
                <s:button class="btn"  name="simpan" value="Simpan" disabled="" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </div>

    </fieldset>

</s:form>