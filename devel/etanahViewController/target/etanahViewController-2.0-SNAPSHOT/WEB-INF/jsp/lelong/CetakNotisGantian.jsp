<%-- 
    Document   : CetakNotisGantian
    Created on : Jun 4, 2010, 10:39:15 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/lelong/notis_gantian?saveNotisGantian&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

</script>

<s:form beanclass="etanah.view.stripes.lelong.NotisGantianActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <fieldset class="aras1">
        <legend>
            Arahan Penyampaian Notis Gantian
        </legend><br><br>
        <div class="content">
                <p>
                    <label>Ulasan :</label>
                    ${actionBean.fasaPermohonan.ulasan}&nbsp;
                </p>

            <p align="right">
                 <s:button name="saveNotisGantian" onclick="edit1(this.form, this.name, 'page_div');" id="btn1" disabled="${actionBean.disabled}" value="Cetak Notis Gantian" class="longbtn" style=""/>
            </p>
        </div>
    </fieldset>
</s:form>
