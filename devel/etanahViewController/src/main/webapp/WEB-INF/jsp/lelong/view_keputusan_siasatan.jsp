<%-- 
    Document   : view_keputusan_siasatan
    Created on : Jul 3, 2012, 3:43:26 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
</script>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatEnkuiriActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <br>
    
       <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Keputusan Siasatan</legend></br>
            <p>
                <label> Keputusan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.mohonfasa1.keputusan.nama}</font>&nbsp;
            </p>
            <p>
                <label></font>Ulasan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.mohonfasa1.ulasan}</font>&nbsp;
            </p>
            <p>
                <label>Peringkat :</label>
                <font style="text-transform:uppercase;"> ${actionBean.mohonfasa1.idAliran}</font>&nbsp;
            </p>
            <p>
                <label>Keputusan Oleh :</label>
                <font style="text-transform:uppercase;"> ${actionBean.mohonfasa1.infoAudit.dimasukOleh.nama}</font>&nbsp;
            </p></br>
        </fieldset>
    </div>
</s:form>
