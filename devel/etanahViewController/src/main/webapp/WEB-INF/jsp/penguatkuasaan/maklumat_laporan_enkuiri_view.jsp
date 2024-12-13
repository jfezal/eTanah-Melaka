<%-- 
    Document   : laporan_enkuiri
    Created on : June 22, 2010, 6:00:05 PM
    Author     : nurshahida.radzi
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

        window.open("${pageContext.request.contextPath}/lelong/maklumat_enkuiri?savePeringatan&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanEnkuiriActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Enkuiri
            </legend>
            <div class="content">

                <p>
                    <label>Tarikh :</label>
                    <fmt:formatDate value="${actionBean.enkuiri.tarikhMula}" pattern="dd/MM/yyyy"/>&nbsp;
                </p>
                <p>
                    <label>Hari :</label>
                    <fmt:formatDate value="${actionBean.enkuiri.tarikhMula}" pattern="EEEE"/>&nbsp;
                </p>
                <p>
                    <label>Masa :</label>
                    <fmt:formatDate value="${actionBean.enkuiri.tarikhMula}" pattern="hh:mm aaa"/>&nbsp;
                </p>
                <p>
                    <label> Tempat :</label>
                    ${actionBean.enkuiri.tempat}&nbsp;
                </p>
                <p>
                    <label> Perkara :</label>
                    ${actionBean.enkuiri.catatan}&nbsp;
                </p>

            </div>
        </fieldset>
    </div>

<%--    <div class="content" align="right">
        <p>
            <s:button name="cetak" onclick="edit1(this.form, '${line.dasarTuntutanCukai.idDasar}');" id="btn1" disabled="${actionBean.disabled}" value="Cetak Notis Siasatan" class="longbtn" style=""/>
            <s:button name="" value="Cetak Notis" class="btn" />
        </p>
    </div>--%>
</s:form>